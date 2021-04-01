package entity.schema.model.extended;

import entity.schema.model.Entity;

import java.util.List;

public class Search {

    private List<Entity> entities;

    public Search(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "Search{" +
                "entities=" + entities +
                '}';
    }
}
