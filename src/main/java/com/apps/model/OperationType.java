package com.apps.model;

public enum OperationType {
    COMPARE,
    CONVERT,
    ADD,
    SUBTRACT,
    DIVIDE;

    public static OperationType fromString(String value) {
        if (value == null) throw new IllegalArgumentException("OperationType cannot be null");
        try {
            return OperationType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid operation type: " + value +
                    ". Valid types: COMPARE, CONVERT, ADD, SUBTRACT, DIVIDE");
        }
    }
}