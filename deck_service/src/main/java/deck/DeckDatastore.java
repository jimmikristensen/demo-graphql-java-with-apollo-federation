package deck;

import deck.schema.model.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class DeckDatastore {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeckDatastore.class);

    private List<Deck> deckList;

    public DeckDatastore() {
        deckList = new ArrayList<>();
        deckList.add(new Deck(0, Arrays.asList(0, 1)));
        deckList.add(new Deck(1, Arrays.asList(0, 2)));
        deckList.add(new Deck(2, Arrays.asList(2, 1)));
    }

    public Deck lookupDeckId(int deckId) {
        delay();

        LOGGER.debug("Lookup deck: "+deckId);
        return deckList.get(deckId);
    }

    private void delay() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {}
    }

}
