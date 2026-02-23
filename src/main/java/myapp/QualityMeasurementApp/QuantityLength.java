package myapp.QualityMeasurementApp;



import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
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

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = this.toBase();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Null not allowed");

        double sumBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBase());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}