package search_service;

import java.util.List;

public class Search {

    List<Integer> guids;

    Search(List<Integer> guids) {
        this.guids = guids;
    }

    public List<Integer> getGuids() {
        return guids;
    }

    public void setGuids(List<Integer> guids) {
        this.guids = guids;
    }

    @Override
    public String toString() {
        return "Search{" +
                "guids=" + guids +
                '}';
    }
}
