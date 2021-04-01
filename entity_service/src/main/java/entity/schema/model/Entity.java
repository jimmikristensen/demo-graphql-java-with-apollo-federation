package entity.schema.model;

public class Entity {

    private int guid;
    private String title;
    private String description;

    public Entity(int entityId, String title, String description) {
        this.guid = entityId;
        this.title = title;
        this.description = description;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "guid=" + guid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
