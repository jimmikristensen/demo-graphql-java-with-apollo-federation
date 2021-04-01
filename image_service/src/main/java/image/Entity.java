package image;

import java.util.List;

public class Entity {

    List<Image> images;

    Entity(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "images=" + images +
                '}';
    }
}
