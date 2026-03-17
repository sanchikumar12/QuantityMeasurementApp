package com.apps.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String errorMessage;

    // No-args constructor
    public QuantityMeasurementEntity() {
    }

    // All-args constructor
    public QuantityMeasurementEntity(String operation, String operand1, String operand2, String result, String errorMessage) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.errorMessage = errorMessage;
    }

    // Getters
    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Setters
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // toString()
    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "operation='" + operation + '\'' +
                ", operand1='" + operand1 + '\'' +
                ", operand2='" + operand2 + '\'' +
                ", result='" + result + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}