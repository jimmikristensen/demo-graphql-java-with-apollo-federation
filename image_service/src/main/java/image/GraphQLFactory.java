package image;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import image.schema.model.extended.Deck;
import image.schema.model.extended.Entity;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Factory
public class GraphQLFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLFactory.class);

    @Inject
    ImageDatastore imageDatastore;

    @Bean
    @Singleton
    public GraphQL graphQL(ResourceResolver resourceResolver, ImageDataFetcher imageDataFetcher) {

        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        // Parse the schema.
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))));

        // Create the runtime wiring.
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("images", imageDataFetcher))
                .build();

        // Instead of creating the executable schema like below
//        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        // Create a federated schema like this
        GraphQLSchema graphQLSchema = Federation.transform(typeRegistry, runtimeWiring).resolveEntityType(env -> {
            final Object src = env.getObject();
            LOGGER.debug("Resolve entity type: "+src);
            if (src instanceof Entity) {
                return env.getSchema().getObjectType("Entity");
            }
            if (src instanceof Deck) {
                return env.getSchema().getObjectType("Deck");
            }
            return null;
        }).fetchEntities(env -> env.<List<Map<String, Object>>>getArgument(_Entity.argumentName)
                .stream()
                .map(values -> {
                    LOGGER.debug("Fetch Entity: "+values.get("__typename"));
                    if ("Entity".equals(values.get("__typename"))) {
                        final Object guid = values.get("guid");
                        if (guid instanceof String) {
                           return new Entity(imageDatastore.lookupImageByEntityGuid(convertIdToInt(guid)));
                        }
                    }
                    if ("Deck".equals(values.get("__typename"))) {
                        final Object deckId = values.get("deckId");
                        if (deckId instanceof Integer) {
                            return new Deck(imageDatastore.lookupBGImageByDeckId((Integer) deckId));
                        }
                    }
                    return null;
                }).collect(Collectors.toList())
        ).build();

        // Return the GraphQL bean.
        return GraphQL.newGraphQL(graphQLSchema).instrumentation(new CustomInstrumentation()).build();
    }

    private int convertIdToInt(Object idObj) {
        try {
            return Integer.parseInt((String) idObj);
        } catch (NumberFormatException e) {
            // log number format error in real app
            LOGGER.error(e.getMessage(), e);
        }
        return 0;
    }
}
