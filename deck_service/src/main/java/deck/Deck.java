package deck;

import java.util.List;

public class Deck {

    int deckId;
    List<Integer> guids;

    Deck(int id, List<Integer> guids) {
        this.deckId = id;
        this.guids = guids;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public List<Integer> getGuids() {
        return guids;
    }

    public void setGuids(List<Integer> guids) {
        this.guids = guids;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckId=" + deckId +
                ", guids=" + guids +
                '}';
    }
}
