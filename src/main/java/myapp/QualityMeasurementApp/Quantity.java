package myapp.QualityMeasurementApp;



import java.util.Objects;


public class Quantity {
	private final double value;
	private final LengthUnit unit;
	private static final double EPSILON = 1e-6;
	
	public Quantity(double value , LengthUnit unit) {
		if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
	}
	
	
	public double getValue() {
		return value;
	}
	
	 
	private double toBase() {
		return value * unit.getConversionFactor();	
    }
	
	public Quantity add(Quantity other) {
        return add(other, this.unit);
    }
	
	public Quantity add(Quantity other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = sumBase / targetUnit.getConversionFactor();

        return new Quantity(result, targetUnit);
    }

	public Quantity convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.toBase();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity(converted, targetUnit);
    }
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity)) return false;

        Quantity other = (Quantity) obj;

        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }
	
	@Override 
	public int hashCode() {
		return Objects.hash(this.toBase());
	}
	
	@Override
	public String toString() {
		return value + " " + unit.name().toLowerCase();
	}
}	
