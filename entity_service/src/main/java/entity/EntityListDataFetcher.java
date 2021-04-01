package entity;

import entity.schema.model.Entity;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class EntityListDataFetcher implements DataFetcher<List<Entity>> {

    @Inject
    EntityDatastore entityDatastore;

    @Override
    public List<Entity> get(DataFetchingEnvironment environment) {
        List<Integer> ids = environment.getArgument("guids");
        return entityDatastore.lookupEntityIds(ids);
    }
}
