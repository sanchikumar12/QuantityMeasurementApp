package com.apps.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "quantity_measurements",
    indexes = {
        @Index(name = "idx_operation",             columnList = "operation"),
        @Index(name = "idx_this_measurement_type", columnList = "thisMeasurementType"),
        @Index(name = "idx_created_at",            columnList = "createdAt"),
        @Index(name = "idx_is_error",              columnList = "isError")
    }
)
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operation;

    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String resultString;
    private double resultValue;
    private String resultUnit;
    private String resultMeasurementType;

    private String errorMessage;

    @Column(nullable = false)
    private boolean isError;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // No-args constructor
    public QuantityMeasurementEntity() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getOperation() { return operation; }

    public double getThisValue() { return thisValue; }
    public String getThisUnit() { return thisUnit; }
    public String getThisMeasurementType() { return thisMeasurementType; }

    public double getThatValue() { return thatValue; }
    public String getThatUnit() { return thatUnit; }
    public String getThatMeasurementType() { return thatMeasurementType; }

    public String getResultString() { return resultString; }
    public double getResultValue() { return resultValue; }
    public String getResultUnit() { return resultUnit; }
    public String getResultMeasurementType() { return resultMeasurementType; }

    public String getErrorMessage() { return errorMessage; }
    public boolean isError() { return isError; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setOperation(String operation) { this.operation = operation; }

    public void setThisValue(double thisValue) { this.thisValue = thisValue; }
    public void setThisUnit(String thisUnit) { this.thisUnit = thisUnit; }
    public void setThisMeasurementType(String t) { this.thisMeasurementType = t; }

    public void setThatValue(double thatValue) { this.thatValue = thatValue; }
    public void setThatUnit(String thatUnit) { this.thatUnit = thatUnit; }
    public void setThatMeasurementType(String t) { this.thatMeasurementType = t; }

    public void setResultString(String resultString) { this.resultString = resultString; }
    public void setResultValue(double resultValue) { this.resultValue = resultValue; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }
    public void setResultMeasurementType(String t) { this.resultMeasurementType = t; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public void setError(boolean error) { this.isError = error; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}