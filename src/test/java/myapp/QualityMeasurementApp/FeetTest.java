package myapp.QualityMeasurementApp;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeetTest {

    private final Feet sample = Feet.fromString("1.0"); // create once using fromString

    @Test
    void testFeetEquality_SameValue() {
        Feet other = Feet.fromString("1.0");
        assertEquals(sample, other);
    }

    @Test
    void testFeetEquality_DifferentValue() {
        Feet other = Feet.fromString("2.0");
        assertNotEquals(sample, other);
    }

    @Test
    void testFeetEquality_NullComparison() {
        assertNotEquals(null, sample);
    }

    @Test
    void testFeetEquality_DifferentClass() {
        assertEquals(sample, sample);
    }

    @Test
    void testFeetEquality_SameReference() {
        assertFalse(sample.equals("1.0"));
    }

    @Test
    void testFeetEquality_ValidInput() {
        Feet fromString = Feet.fromString("1.0");
        assertEquals(sample, fromString);
    }

    @Test
    void testFeetEquality_InvalidInput() {
        assertThrows(InvalidFeetException.class, () -> Feet.fromString("abc"));
    }
}
