package myapp.QualityMeasurementApp;



import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
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
    
    public Length add(Length other) {
        if (other == null)
            throw new IllegalArgumentException("Cannot add null length");

        double thisBase = this.value * this.unit.getConversionFactor();
        double otherBase = other.value * other.unit.getConversionFactor();

        double sumBase = thisBase + otherBase;

        double resultValue = sumBase / this.unit.getConversionFactor();

        return new Length(resultValue, this.unit);
    }

    
    
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.value * this.unit.getConversionFactor(); 
        double convertedValue = baseValue / targetUnit.getConversionFactor(); 

        return new Length(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double baseValue = value * source.getConversionFactor();
        return baseValue / target.getConversionFactor();
    }
    
    private double toBase() {
        return this.value * this.unit.getConversionFactor();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;

        double thisBase = this.value * this.unit.getConversionFactor();
        double otherBase = other.value * other.unit.getConversionFactor();

        return Math.abs(thisBase - otherBase) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }

}