package search;

import com.apollographql.federation.graphqljava.Federation;
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
import search.schema.model.Search;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Factory
public class GraphQLFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLFactory.class);

    @Inject
    SearchDatastore searchDatastore;

    @Bean
    @Singleton
    public GraphQL graphQL(ResourceResolver resourceResolver, SearchDataFetcher searchDataFetcher) {

        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        // Parse the schema.
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))));

        // Create the runtime wiring.
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("search", searchDataFetcher))
                .build();

        // Create the executable schema.
//        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        GraphQLSchema graphQLSchema = Federation.transform(typeRegistry, runtimeWiring).resolveEntityType(env -> {
            // Does not extend another schema, so we always return this schema object
            LOGGER.debug("Resolve entity type: "+env.getObject());
            return env.getSchema().getObjectType("Search");
        }).fetchEntities(env -> {
            // Does not extend another schema, so we always return this object
            LOGGER.debug("Fetch Entity");
            return new Search(new ArrayList<>());
        }).build();

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
