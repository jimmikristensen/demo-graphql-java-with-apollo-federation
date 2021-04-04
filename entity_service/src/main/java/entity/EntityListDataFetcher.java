package entity;

import com.apollographql.federation.graphqljava._Entity;
import entity.schema.model.Entity;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class EntityListDataFetcher implements DataFetcher<List<Entity>> {

    @Inject
    EntityDatastore entityDatastore;


    @Override
    public List<Entity> get(DataFetchingEnvironment env) {
        return entityDatastore.lookupEntityIds(getGuidsFromArgs(env));
    }

    private List<Integer> getGuidsFromArgs(DataFetchingEnvironment env) {

        // In case the request is part of a federated request (execution path /_entities)
        // the argument list will look like below with a representations object (_Entity.argumentName)
        // {representations=[{__typename=Search, guids=[2, 1, 0]}]}
        // In case the request is a direct request, not part of a federation (execution path /entities),
        // the argument list will look like below with direct access to the guids object
        // {guids=[0, 1, 2]}

        if (env.containsArgument(_Entity.argumentName)) { // handles federated request
            List<Object> match = env.<List<Map<String, Object>>>getArgument(_Entity.argumentName).stream()
                .map(values -> values.get("guids"))
                .collect(Collectors.toList());

            return (List<Integer>) match.get(0);

        } else if (env.containsArgument("guids")) { // handles direct requests
            List<Integer> ids = env.getArgument("guids");
            return ids;
        }

        return new ArrayList<>();
    }
}
