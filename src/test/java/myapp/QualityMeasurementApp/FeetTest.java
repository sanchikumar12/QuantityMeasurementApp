package myapp.QualityMeasurementApp;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetTest {

    private final Feet sample = Feet.fromString("1.0"); // create once using fromString

    @Test
    void givenSameValueWhenComparedThenShouldBeEqual() {
        Feet other = Feet.fromString("1.0");
        assertEquals(sample, other);
    }

    @Test
    void givenDifferentValueWhenComparedThenShouldNotBeEqual() {
        Feet other = Feet.fromString("2.0");
        assertNotEquals(sample, other);
    }

    @Test
    void givenNullWhenComparedThenShouldReturnFalse() {
        assertNotEquals(null, sample);
    }

    @Test
    void givenSameReferenceWhenComparedThenShouldReturnTrue() {
        assertEquals(sample, sample);
    }

    @Test
    void givenDifferentTypeWhenComparedThenShouldReturnFalse() {
        assertFalse(sample.equals("1.0"));
    }

    @Test
    void givenValidStringInputThenShouldCreateFeet() {
        Feet fromString = Feet.fromString("1.0");
        assertEquals(sample, fromString);
    }

    @Test
    void givenInvalidStringInputThenShouldThrowException() {
        assertThrows(InvalidFeetException.class, () -> Feet.fromString("abc"));
    }
}
