import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    @Test
    public void paramConstructorNullException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void paramConstructorEmptyException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Test" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
        Horse horse1 = new Horse("Test1", 1, 1);
        Horse horse2 = new Horse("Test2", 2, 5);
        Horse horse3 = new Horse("Test3", 3, 3);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertSame(horse2, hippodrome.getWinner());
    }
}
