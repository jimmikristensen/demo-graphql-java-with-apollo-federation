package image_service;

public class Deck {

    Image backgroundImage;

    Deck(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "backgroundImage=" + backgroundImage +
                '}';
    }
}
