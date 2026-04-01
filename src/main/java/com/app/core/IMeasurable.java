package com.app.core;


public interface IMeasurable {

    String getUnitName();

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);



    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }


    default void validateOperationSupport(String operation) {
        // do nothing by default
    }
}