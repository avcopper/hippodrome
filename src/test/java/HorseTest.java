import java.lang.reflect.Field;
import org.mockito.MockedStatic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.mockStatic;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HorseTest {
    @Test
    public void firstParamConstructorNullException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    public void firstParamConstructorEmptyException(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1,2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void secondParamConstructorNegativeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", -1,2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void thirdParamConstructorNegativeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", 1,-2));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse("Test", 1,2);
        assertEquals("Test", horse.getName());
    }

    @Test
    public void getNameByReflection() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Test", 1,2);
        Field nameField = horse.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        String name = (String) nameField.get(horse);
        assertEquals("Test", name);
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Test", 1,2);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getSpeedByReflection() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Test", 1,2);
        Field speedField = horse.getClass().getDeclaredField("speed");
        speedField.setAccessible(true);
        double speed = (double) speedField.get(horse);
        assertEquals(1, speed);
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Test", 1,2);
        assertEquals(2, horse.getDistance());
    }

    @Test
    public void getDistanceByReflection() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Test", 1,2);
        Field distanceField = horse.getClass().getDeclaredField("distance");
        distanceField.setAccessible(true);
        double distance = (double) distanceField.get(horse);
        assertEquals(2, distance);
    }

    @Test
    public void getDefaultDistance() {
        Horse horse = new Horse("Test", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void getDefaultDistanceByReflection() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Test", 1);
        Field distanceField = horse.getClass().getDeclaredField("distance");
        distanceField.setAccessible(true);
        double distance = (double) distanceField.get(horse);
        assertEquals(0, distance);
    }

    @Test
    public void moveRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Test", 1, 2).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.4, 0.6, 0.8})
    public void moveDistance(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("Test", 1, 5);
            double distance = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(distance, horse.getDistance());
        }
    }
}
