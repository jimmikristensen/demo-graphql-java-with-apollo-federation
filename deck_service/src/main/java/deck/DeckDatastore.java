package deck;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class DeckDatastore {

    private List<Deck> deckList;

    public DeckDatastore() {
        deckList = new ArrayList<>();
        deckList.add(new Deck(0, Arrays.asList(0, 1)));
        deckList.add(new Deck(1, Arrays.asList(0, 2)));
        deckList.add(new Deck(2, Arrays.asList(2, 1)));
    }

    public Deck lookupDeckId(int deckId) {
        delay();

        System.out.println("Lookup deck: "+deckId);
        Deck deck = deckList.get(deckId);
        if (deck != null) {
            return deck;
        }
        return null;
    }

    private void delay() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {}
    }

}
