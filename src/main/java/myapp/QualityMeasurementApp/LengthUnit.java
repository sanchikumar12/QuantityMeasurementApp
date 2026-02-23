package myapp.QualityMeasurementApp;



public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toFeet(double value) {
        return value * toFeetFactor;
    }
    public static LengthUnit fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (input.trim().toLowerCase()) {
            case "feet":
            case "foot":
            case "ft":
                return FEET;

            case "inch":
            case "inches":
            case "in":
                return INCH;

            default:
                throw new IllegalArgumentException("unsupported unit: " + input);
        }
    }
}