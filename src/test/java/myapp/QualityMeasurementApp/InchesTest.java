package myapp.QualityMeasurementApp;

z

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InchesTest {
	private final Inches sample = Inches.fromString("1.0");
	
	@Test
	void testInchesEquality_SameValue() {
		Inches other = Inches.fromString("1.0");
		assertEquals(sample, other);
	}
	
	@Test
	void testInchesEquality_NullComparison() {
		assertNotEquals(null , sample);
	}
	
	@Test
	void testInchesEquality_SameReference() {
		assertEquals(sample, sample);
	}
	
	@Test
    void testInchesEquality_DifferentClass() {
        assertFalse(sample.equals("1.0"));
    }

    @Test
    void testInchesEquality_InvalidInput() {
        assertThrows(InvalidFeetException.class,
                () -> Inches.fromString("abc"));
    }
}
