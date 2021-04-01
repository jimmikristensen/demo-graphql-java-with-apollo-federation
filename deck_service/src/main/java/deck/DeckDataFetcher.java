package deck;

import deck.schema.model.Deck;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeckDataFetcher implements DataFetcher<Deck> {

    @Inject
    DeckDatastore deckDatastore;

    @Override
    public Deck get(DataFetchingEnvironment env) {
        int deckId = env.getArgument("id");
        Deck res = deckDatastore.lookupDeckId(deckId);
        return res;
    }
}
