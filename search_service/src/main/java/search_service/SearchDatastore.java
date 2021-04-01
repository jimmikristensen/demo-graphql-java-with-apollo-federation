package search_service;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class SearchDatastore {

    private Map<String, Integer> entityMap;

    public SearchDatastore() {
        entityMap = new HashMap<>();
        entityMap.put("Stormester", 0);
        entityMap.put("Korpset", 1);
        entityMap.put("Kardashians", 2);
    }

    public Search searchTitle(String title) {
        delay();

        System.out.println("Search for title: "+title);
        List<Integer> matches = entityMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(title))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        Search searchResult = new Search(matches);
        System.out.println("Search result: "+searchResult);

        return searchResult;
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

}
