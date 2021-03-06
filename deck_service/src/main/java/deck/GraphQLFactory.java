package deck;

import com.apollographql.federation.graphqljava.Federation;
import deck.schema.model.Deck;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Factory
public class GraphQLFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLFactory.class);

    @Inject
    DeckDatastore deckDatastore;

    @Bean
    @Singleton
    public GraphQL graphQL(ResourceResolver resourceResolver, DeckDataFetcher deckDataFetcher) {

        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        // Parse the schema.
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))));

        // Create the runtime wiring.
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("deck", deckDataFetcher))
                .build();

        // Instead of creating the executable schema like below
//        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        // Create a federated schema like this
        // NOTE that the resolver and fetcher simply returns NULL as this service does not @extends other
        // schema objects but both of them needs to be present as we have declared a @key
        // All queries for this service are handled by the runtimeWiring and not the resolver and
        // fetcher below
        GraphQLSchema graphQLSchema = Federation.transform(typeRegistry, runtimeWiring)
                .resolveEntityType(env -> null)
                .fetchEntities(env -> null).build();

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
