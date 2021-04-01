package entity;

import entity.schema.model.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class EntityDatastore {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityDatastore.class);
    private final List<Entity> entityList;

    public EntityDatastore() {
        entityList = new ArrayList<>();
        entityList.add(new Entity(0, "Blade Runner", "In 2019 Los Angeles, former policeman Rick Deckard is detained by Officer Gaff, and brought to his former supervisor, Bryant. Deckard, whose job as a \"blade runner\" was to track down bioengineered humanoids known as replicants and terminally \"retire\" them, is informed that four replicants are on Earth illegally."));
        entityList.add(new Entity(1, "Revenge of the Nerds", "Best friends and nerds Lewis Skolnick and Gilbert Lowe enroll in Adams College to study computer science. The Alpha Betas, a fraternity that includes most of the Adams football team, carelessly burn down their own house and, urged by Coach Harris, take over the freshman dorms."));
        entityList.add(new Entity(2, "Computer Chronicles", "The series was created in 1983 by Stewart Cheifet (later the show's co-host), who was then the station manager of the College of San Mateo's KCSM-TV. The show was initially broadcast as a local weekly series. The show was co-produced by WITF-TV in Harrisburg, Pennsylvania."));
    }

    public Entity lookupEntityId(int entityId) {
        delay();

        LOGGER.debug("Lookup single entity: "+entityId);
        Entity ety = entityList.get(entityId);
        if (ety != null) {
            return ety;
        }
        return null;
    }

    public List<Entity> lookupEntityIds(List<Integer> ids) {
        delay();

        LOGGER.debug("Lookup multiple entities: "+ids);
        List<Entity> resultSet = new ArrayList<>();
        for (Integer id : ids) {
            if (0 <= id && id < entityList.size()) {
                resultSet.add(entityList.get(id));
            }
        }
        return resultSet;
    }

    private void delay() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {}
    }
}
