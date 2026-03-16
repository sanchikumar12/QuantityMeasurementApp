package com.apps.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String errorMessage;

    public QuantityMeasurementEntity(
            String operation,
            String operand1,
            String operand2,
            String result,
            String errorMessage) {

        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.errorMessage = errorMessage;
    }
}