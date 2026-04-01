package com.apps.controller;

import com.apps.dto.QuantityInputDTO;
import com.apps.dto.QuantityMeasurementDTO;
import com.apps.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements",
     description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    private static final Logger log =
            LoggerFactory.getLogger(QuantityMeasurementController.class);

    @Autowired
    private IQuantityMeasurementService service;

    // ─── POST /compare ────────────────────────────────────────────────────────

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities",
               description = "Returns true if both quantities are equal after unit conversion")
    public ResponseEntity<QuantityMeasurementDTO> performComparison(
            @Valid @RequestBody QuantityInputDTO input) {

        log.info("POST /compare called");
        QuantityMeasurementDTO result =
                service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(result);
    }

    // ─── POST /convert ────────────────────────────────────────────────────────

    @PostMapping("/convert")
    @Operation(summary = "Convert a quantity to another unit",
               description = "Converts the first quantity to the unit specified in the second quantity")
    public ResponseEntity<QuantityMeasurementDTO> performConversion(
            @Valid @RequestBody QuantityInputDTO input) {

        log.info("POST /convert called");
        QuantityMeasurementDTO result =
                service.convert(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(result);
    }

    // ─── POST /add ────────────────────────────────────────────────────────────

    @PostMapping("/add")
    @Operation(summary = "Add two quantities",
               description = "Adds two compatible quantities and returns the result")
    public ResponseEntity<QuantityMeasurementDTO> performAddition(
            @Valid @RequestBody QuantityInputDTO input) {

        log.info("POST /add called");
        QuantityMeasurementDTO result =
                service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(result);
    }

    // ─── POST /subtract ───────────────────────────────────────────────────────

    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities",
               description = "Subtracts the second quantity from the first")
    public ResponseEntity<QuantityMeasurementDTO> performSubtraction(
            @Valid @RequestBody QuantityInputDTO input) {

        log.info("POST /subtract called");
        QuantityMeasurementDTO result =
                service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(result);
    }

    // ─── POST /divide ─────────────────────────────────────────────────────────

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities",
               description = "Divides the first quantity by the second and returns the scalar result")
    public ResponseEntity<QuantityMeasurementDTO> performDivision(
            @Valid @RequestBody QuantityInputDTO input) {

        log.info("POST /divide called");
        QuantityMeasurementDTO result =
                service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(result);
    }

    // ─── GET /history/operation/{operation} ───────────────────────────────────

    @GetMapping("/history/operation/{operation}")
    @Operation(summary = "Get operation history",
               description = "Returns all measurement records for the given operation type (COMPARE, CONVERT, ADD, SUBTRACT, DIVIDE)")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(
            @Parameter(description = "Operation type: COMPARE, CONVERT, ADD, SUBTRACT, DIVIDE")
            @PathVariable String operation) {

        log.info("GET /history/operation/{}", operation);
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }

    // ─── GET /history/type/{measurementType} ──────────────────────────────────

    @GetMapping("/history/type/{measurementType}")
    @Operation(summary = "Get history by measurement type",
               description = "Returns all measurement records for the given type (LengthUnit, WeightUnit, etc.)")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByType(
            @Parameter(description = "Measurement type: LengthUnit, WeightUnit, VolumeUnit, TemperatureUnit")
            @PathVariable String measurementType) {

        log.info("GET /history/type/{}", measurementType);
        return ResponseEntity.ok(service.getHistoryByMeasurementType(measurementType));
    }

    // ─── GET /history/errored ─────────────────────────────────────────────────

    @GetMapping("/history/errored")
    @Operation(summary = "Get error history",
               description = "Returns all measurement records that resulted in an error")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {
        log.info("GET /history/errored called");
        return ResponseEntity.ok(service.getErrorHistory());
    }

    // ─── GET /count/{operation} ───────────────────────────────────────────────

    @GetMapping("/count/{operation}")
    @Operation(summary = "Get successful operation count",
               description = "Returns the count of successful operations for the given operation type")
    public ResponseEntity<Long> getOperationCount(
            @Parameter(description = "Operation type to count")
            @PathVariable String operation) {

        log.info("GET /count/{}", operation);
        return ResponseEntity.ok(service.getOperationCount(operation));
    }
}