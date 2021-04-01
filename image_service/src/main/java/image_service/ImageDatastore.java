package image_service;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class ImageDatastore {

    private Map<Integer, List<Image>> entityImages;
    private Map<Integer, Image> bgImages;

    public ImageDatastore() {
        entityImages = new HashMap<>();
        entityImages.put(0, Arrays.asList(
                new Image(0, "https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Blade_Runner_%281982_poster%29.png/220px-Blade_Runner_%281982_poster%29.png", "promotion"),
                new Image(1, "https://upload.wikimedia.org/wikipedia/en/thumb/4/49/Blade_Runner_spinner_flyby.png/220px-Blade_Runner_spinner_flyby.png", "keyframe")
        ));
        entityImages.put(1, Arrays.asList(
                new Image(2, "https://upload.wikimedia.org/wikipedia/en/thumb/5/54/Revengeofthenerdsposter.jpg/220px-Revengeofthenerdsposter.jpg", "promotion")
        ));
        entityImages.put(2, Arrays.asList(
                new Image(4, "https://upload.wikimedia.org/wikipedia/en/thumb/7/7b/Computer_Chronicles.jpg/250px-Computer_Chronicles.jpg", "promotion")
        ));

        bgImages = new HashMap<>();
        bgImages.put(0, new Image(6, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/800x600_Wallpaper_Blue_Sky.png/220px-800x600_Wallpaper_Blue_Sky.png", "background"));
        bgImages.put(1, new Image(7, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Animated_Wallpaper_Windows_10_-_Wallpaper_Engine.gif/440px-Animated_Wallpaper_Windows_10_-_Wallpaper_Engine.gif", "background"));

    }

    public List<Image> lookupImageByEntityGuid(int entityId) {
        delay();

        System.out.println("Lookup images for entity: "+entityId);
        List<Image> acts = entityImages.get(entityId);
        if (acts != null) {
            return entityImages.get(entityId);
        }
        return new ArrayList<>();
    }

    public Image lookupBGImageByDeckId(int deckId) {
        delay();

        System.out.println("Lookup background images for deck: "+deckId);
        return bgImages.get(deckId);
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }
}
