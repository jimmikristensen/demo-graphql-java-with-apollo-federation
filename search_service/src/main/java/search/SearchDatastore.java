package search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import search.schema.model.Search;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class SearchDatastore {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchDatastore.class);

    private Map<String, Integer> entityMap;

    public SearchDatastore() {
        entityMap = new HashMap<>();
        entityMap.put("Blade Runner", 0);
        entityMap.put("Revenge of the Nerds", 1);
        entityMap.put("Computer Chronicles", 2);
    }

    public Search searchTitle(String title) {
        delay();

        LOGGER.debug("Search for title: "+title);
        List<Integer> matches = entityMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(title))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        Search searchResult = new Search(matches);
        LOGGER.debug("Search result: "+searchResult);

        return searchResult;
    }

    private void delay() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {}
    }

}
