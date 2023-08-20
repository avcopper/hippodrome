import java.util.List;
import org.slf4j.Logger;
import java.util.Comparator;
import java.util.Collections;
import org.slf4j.LoggerFactory;
import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            LOGGER.error("Hippodrome: Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            LOGGER.error("Hippodrome: Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        LOGGER.debug("Hippodrome: Создание Hippodrome, лошадей [{}]", horses.size());
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
