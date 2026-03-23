package com.apps.dto;

import com.apps.model.QuantityMeasurementEntity;

import java.util.List;
import java.util.stream.Collectors;

public class QuantityMeasurementDTO {

    private double  thisValue;
    private String  thisUnit;
    private String  thisMeasurementType;

    private double  thatValue;
    private String  thatUnit;
    private String  thatMeasurementType;

    private String  operation;

    private String  resultString;
    private double  resultValue;
    private String  resultUnit;
    private String  resultMeasurementType;

    private String  errorMessage;
    private boolean error;

    // No-args constructor
    public QuantityMeasurementDTO() {
    }

    // All-args constructor
    public QuantityMeasurementDTO(double thisValue, String thisUnit, String thisMeasurementType,
                                  double thatValue, String thatUnit, String thatMeasurementType,
                                  String operation, String resultString, double resultValue,
                                  String resultUnit, String resultMeasurementType,
                                  String errorMessage, boolean error) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultString = resultString;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
        this.errorMessage = errorMessage;
        this.error = error;
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    public double getThisValue() { return thisValue; }
    public String getThisUnit() { return thisUnit; }
    public String getThisMeasurementType() { return thisMeasurementType; }

    public double getThatValue() { return thatValue; }
    public String getThatUnit() { return thatUnit; }
    public String getThatMeasurementType() { return thatMeasurementType; }

    public String getOperation() { return operation; }

    public String getResultString() { return resultString; }
    public double getResultValue() { return resultValue; }
    public String getResultUnit() { return resultUnit; }
    public String getResultMeasurementType() { return resultMeasurementType; }

    public String getErrorMessage() { return errorMessage; }
    public boolean isError() { return error; }

    // ─── Setters ─────────────────────────────────────────────────────────────

    public void setThisValue(double thisValue) { this.thisValue = thisValue; }
    public void setThisUnit(String thisUnit) { this.thisUnit = thisUnit; }
    public void setThisMeasurementType(String thisMeasurementType) { this.thisMeasurementType = thisMeasurementType; }

    public void setThatValue(double thatValue) { this.thatValue = thatValue; }
    public void setThatUnit(String thatUnit) { this.thatUnit = thatUnit; }
    public void setThatMeasurementType(String thatMeasurementType) { this.thatMeasurementType = thatMeasurementType; }

    public void setOperation(String operation) { this.operation = operation; }

    public void setResultString(String resultString) { this.resultString = resultString; }
    public void setResultValue(double resultValue) { this.resultValue = resultValue; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }
    public void setResultMeasurementType(String resultMeasurementType) { this.resultMeasurementType = resultMeasurementType; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public void setError(boolean error) { this.error = error; }

    // ─── Static factory: Entity → DTO ────────────────────────────────────────

    public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity entity) {
        if (entity == null) return null;

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(entity.getThisValue());
        dto.setThisUnit(entity.getThisUnit());
        dto.setThisMeasurementType(entity.getThisMeasurementType());

        dto.setThatValue(entity.getThatValue());
        dto.setThatUnit(entity.getThatUnit());
        dto.setThatMeasurementType(entity.getThatMeasurementType());

        dto.setOperation(entity.getOperation());
        dto.setResultString(entity.getResultString());
        dto.setResultValue(entity.getResultValue());
        dto.setResultUnit(entity.getResultUnit());
        dto.setResultMeasurementType(entity.getResultMeasurementType());

        dto.setErrorMessage(entity.getErrorMessage());
        dto.setError(entity.isError());
        return dto;
    }

    // ─── Instance method: DTO → Entity ───────────────────────────────────────

    public QuantityMeasurementEntity toEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(this.thisValue);
        entity.setThisUnit(this.thisUnit);
        entity.setThisMeasurementType(this.thisMeasurementType);

        entity.setThatValue(this.thatValue);
        entity.setThatUnit(this.thatUnit);
        entity.setThatMeasurementType(this.thatMeasurementType);

        entity.setOperation(this.operation);
        entity.setResultString(this.resultString);
        entity.setResultValue(this.resultValue);
        entity.setResultUnit(this.resultUnit);
        entity.setResultMeasurementType(this.resultMeasurementType);

        entity.setErrorMessage(this.errorMessage);
        entity.setError(this.error);
        return entity;
    }

    // ─── List conversions ─────────────────────────────────────────────────────

    public static List<QuantityMeasurementDTO> fromEntityList(List<QuantityMeasurementEntity> entities) {
        return entities.stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public static List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtos) {
        return dtos.stream()
                .map(QuantityMeasurementDTO::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "QuantityMeasurementDTO{" +
                "operation='" + operation + '\'' +
                ", thisValue=" + thisValue +
                ", thisUnit='" + thisUnit + '\'' +
                ", thatValue=" + thatValue +
                ", thatUnit='" + thatUnit + '\'' +
                ", resultValue=" + resultValue +
                ", resultUnit='" + resultUnit + '\'' +
                ", error=" + error +
                '}';
    }
}