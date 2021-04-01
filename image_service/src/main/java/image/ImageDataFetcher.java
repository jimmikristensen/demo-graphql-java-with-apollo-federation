package image;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import image.schema.model.Image;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ImageDataFetcher implements DataFetcher<List<Image>> {

    @Inject
    ImageDatastore imageDatastore;

    @Override
    public List<Image> get(DataFetchingEnvironment env) {
        int id = env.getArgument("id");
        return imageDatastore.lookupImageByEntityGuid(id);
    }
}
