package com.apps.core;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS, FAHRENHEIT, KELVIN;

    private static final SupportsArithmetic supportsArithmetic = () -> false;

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                this.name() + " does not support " + operation + " operation.");
    }

    private static final Function<Double, Double> F_TO_C = f -> (f - 32) * 5.0 / 9.0;
    private static final Function<Double, Double> C_TO_F = c -> (c * 9.0 / 5.0) + 32;
    private static final Function<Double, Double> K_TO_C = k -> k - 273.15;
    private static final Function<Double, Double> C_TO_K = c -> c + 273.15;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return switch (this) {
            case CELSIUS    -> value;
            case FAHRENHEIT -> F_TO_C.apply(value);
            case KELVIN     -> K_TO_C.apply(value);
        };
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return switch (this) {
            case CELSIUS    -> baseValue;
            case FAHRENHEIT -> C_TO_F.apply(baseValue);
            case KELVIN     -> C_TO_K.apply(baseValue);
        };
    }

    public double convertTo(double value, TemperatureUnit target) {
        if (target == null)
            throw new IllegalArgumentException("Target temperature unit cannot be null");

        if (this == target) return value;

        double celsiusValue = switch (this) {
            case CELSIUS    -> value;
            case FAHRENHEIT -> (value - 32) * 5.0 / 9.0;
            case KELVIN     -> value - 273.15;
        };

        return switch (target) {
            case CELSIUS    -> celsiusValue;
            case FAHRENHEIT -> (celsiusValue * 9.0 / 5.0) + 32;
            case KELVIN     -> celsiusValue + 273.15;
        };
    }
}