package image;

public class Image {

    int imageId;
    String url;
    String type;

    Image(int imageId, String url, String type) {
        this.imageId = imageId;
        this.url = url;
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
