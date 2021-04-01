package entity;

import entity.schema.model.Entity;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EntityDataFetcher implements DataFetcher<Entity> {

    @Inject
    EntityDatastore entityDatastore;

    @Override
    public Entity get(DataFetchingEnvironment env) {
        int id = env.getArgument("guid");
        return entityDatastore.lookupEntityId(id);
    }
}
