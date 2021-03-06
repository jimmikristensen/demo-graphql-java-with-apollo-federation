package image.schema.model.extended;

import image.schema.model.Image;

public class Deck {

    Image backgroundImage;

    public Deck(Image backgroundImage) {
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
