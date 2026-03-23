package com.apps.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;

import com.apps.core.LengthUnit;
import com.apps.core.WeightUnit;
import com.apps.core.VolumeUnit;
import com.apps.core.TemperatureUnit;

@Schema(description = "Input wrapper containing two quantities for measurement operations")
public class QuantityInputDTO {

    @Valid
    @Schema(description = "First quantity operand")
    private QuantityDTO thisQuantityDTO;

    @Valid
    @Schema(description = "Second quantity operand")
    private QuantityDTO thatQuantityDTO;

    // No-args constructor
    public QuantityInputDTO() {
    }

    // All-args constructor
    public QuantityInputDTO(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        this.thisQuantityDTO = thisQuantityDTO;
        this.thatQuantityDTO = thatQuantityDTO;
    }

    // Getters
    public QuantityDTO getThisQuantityDTO() {
        return thisQuantityDTO;
    }

    public QuantityDTO getThatQuantityDTO() {
        return thatQuantityDTO;
    }

    // Setters
    public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
        this.thisQuantityDTO = thisQuantityDTO;
    }

    public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
        this.thatQuantityDTO = thatQuantityDTO;
    }

    // Validation
    @AssertTrue(message = "Unit must be valid for the specified measurement type")
    public boolean isUnitValidForMeasurementType() {
        return isUnitValid(thisQuantityDTO) && isUnitValid(thatQuantityDTO);
    }

    private boolean isUnitValid(QuantityDTO dto) {
        if (dto == null || dto.getUnit() == null || dto.getMeasurementType() == null) return true;

        String unit = dto.getUnit().toUpperCase();
        String type = dto.getMeasurementType();

        try {
            switch (type) {
                case "LengthUnit":      LengthUnit.valueOf(unit);      break;
                case "WeightUnit":      WeightUnit.valueOf(unit);      break;
                case "VolumeUnit":      VolumeUnit.valueOf(unit);      break;
                case "TemperatureUnit": TemperatureUnit.valueOf(unit); break;
                default: return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "QuantityInputDTO{" +
                "thisQuantityDTO=" + thisQuantityDTO +
                ", thatQuantityDTO=" + thatQuantityDTO +
                '}';
    }
}