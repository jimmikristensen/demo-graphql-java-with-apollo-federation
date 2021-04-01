package search_service;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchDataFetcher implements DataFetcher<Search> {

    @Inject
    SearchDatastore searchDatastore;

    @Override
    public Search get(DataFetchingEnvironment env) {
        String title = env.getArgument("title");
        Search res = searchDatastore.searchTitle(title);
        return res;
    }
}
