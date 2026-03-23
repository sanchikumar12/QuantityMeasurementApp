package com.apps.service;

import com.apps.core.*;
import com.apps.dto.QuantityDTO;
import com.apps.dto.QuantityMeasurementDTO;
import com.apps.exception.QuantityMeasurementException;
import com.apps.model.QuantityMeasurementEntity;
import com.apps.repository.QuantityMeasurementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private static final Logger log = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);

    @Autowired
    private QuantityMeasurementRepository repository;

    // ─── DTO → Quantity conversion ────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private Quantity<?> convertDtoToModel(QuantityDTO dto) {
        String unit = dto.getUnit().toUpperCase();

        try { return new Quantity<>(dto.getValue(), LengthUnit.valueOf(unit)); }
        catch (IllegalArgumentException ignored) {}

        try { return new Quantity<>(dto.getValue(), WeightUnit.valueOf(unit)); }
        catch (IllegalArgumentException ignored) {}

        try { return new Quantity<>(dto.getValue(), VolumeUnit.valueOf(unit)); }
        catch (IllegalArgumentException ignored) {}

        try { return new Quantity<>(dto.getValue(), TemperatureUnit.valueOf(unit)); }
        catch (IllegalArgumentException ignored) {}

        throw new IllegalArgumentException("Unsupported unit: " + dto.getUnit());
    }

    // ─── Build entity helper ──────────────────────────────────────────────────

    private QuantityMeasurementEntity buildEntity(QuantityDTO q1, QuantityDTO q2, String operation) {
        QuantityMeasurementEntity e = new QuantityMeasurementEntity();
        e.setOperation(operation);
        e.setThisValue(q1.getValue().doubleValue());
        e.setThisUnit(q1.getUnit());
        e.setThisMeasurementType(q1.getMeasurementType());
        if (q2 != null) {
            e.setThatValue(q2.getValue().doubleValue());
            e.setThatUnit(q2.getUnit());
            e.setThatMeasurementType(q2.getMeasurementType());
        }
        return e;
    }

    // ─── COMPARE ─────────────────────────────────────────────────────────────

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = buildEntity(q1, q2, "compare");
        try {
            Quantity<?> qty1 = convertDtoToModel(q1);
            Quantity<?> qty2 = convertDtoToModel(q2);
            boolean result = qty1.equals(qty2);

            entity.setResultString(String.valueOf(result));
            entity.setError(false);
            log.debug("Compare result: {}", result);
        } catch (Exception ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            log.error("Compare error: {}", ex.getMessage());
        }
        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    // ─── CONVERT ─────────────────────────────────────────────────────────────

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO convert(QuantityDTO input, QuantityDTO target) {
        QuantityMeasurementEntity entity = buildEntity(input, target, "convert");
        try {
            Quantity<?> quantity = convertDtoToModel(input);
            Object unit = quantity.getUnit();
            Quantity<?> result;

            if (unit instanceof LengthUnit) {
                result = ((Quantity<LengthUnit>) quantity)
                        .convertTo(LengthUnit.valueOf(target.getUnit().toUpperCase()));
            } else if (unit instanceof WeightUnit) {
                result = ((Quantity<WeightUnit>) quantity)
                        .convertTo(WeightUnit.valueOf(target.getUnit().toUpperCase()));
            } else if (unit instanceof VolumeUnit) {
                result = ((Quantity<VolumeUnit>) quantity)
                        .convertTo(VolumeUnit.valueOf(target.getUnit().toUpperCase()));
            } else if (unit instanceof TemperatureUnit) {
                result = ((Quantity<TemperatureUnit>) quantity)
                        .convertTo(TemperatureUnit.valueOf(target.getUnit().toUpperCase()));
            } else {
                throw new QuantityMeasurementException("Unsupported unit type for conversion");
            }

            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnit().getUnitName());
            entity.setResultMeasurementType(input.getMeasurementType());
            entity.setError(false);
            log.debug("Convert result: {}", result);
        } catch (Exception ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            log.error("Convert error: {}", ex.getMessage());
        }
        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    // ─── ADD ─────────────────────────────────────────────────────────────────

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = buildEntity(q1, q2, "add");
        try {
            Quantity<?> qty1 = convertDtoToModel(q1);
            Quantity<?> qty2 = convertDtoToModel(q2);

            if (!qty1.getUnit().getClass().equals(qty2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "add Error: Cannot perform arithmetic between different measurement categories: "
                    + q1.getMeasurementType() + " and " + q2.getMeasurementType());
            }

            Quantity<?> result = ((Quantity) qty1).add((Quantity) qty2);
            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnit().getUnitName());
            entity.setResultMeasurementType(q1.getMeasurementType());
            entity.setError(false);
            log.debug("Add result: {}", result);
        } catch (QuantityMeasurementException ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Add error: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Add error: {}", ex.getMessage());
            throw new QuantityMeasurementException("add Error: " + ex.getMessage(), ex);
        }
        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    // ─── SUBTRACT ────────────────────────────────────────────────────────────

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = buildEntity(q1, q2, "subtract");
        try {
            Quantity<?> qty1 = convertDtoToModel(q1);
            Quantity<?> qty2 = convertDtoToModel(q2);

            if (!qty1.getUnit().getClass().equals(qty2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "subtract Error: Cannot perform arithmetic between different measurement categories: "
                    + q1.getMeasurementType() + " and " + q2.getMeasurementType());
            }

            Quantity<?> result = ((Quantity) qty1).subtract((Quantity) qty2);
            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnit().getUnitName());
            entity.setResultMeasurementType(q1.getMeasurementType());
            entity.setError(false);
            log.debug("Subtract result: {}", result);
        } catch (QuantityMeasurementException ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Subtract error: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Subtract error: {}", ex.getMessage());
            throw new QuantityMeasurementException("subtract Error: " + ex.getMessage(), ex);
        }
        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    // ─── DIVIDE ──────────────────────────────────────────────────────────────

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = buildEntity(q1, q2, "divide");
        try {
            Quantity<?> qty1 = convertDtoToModel(q1);
            Quantity<?> qty2 = convertDtoToModel(q2);

            if (!qty1.getUnit().getClass().equals(qty2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "divide Error: Cannot perform arithmetic between different measurement categories: "
                    + q1.getMeasurementType() + " and " + q2.getMeasurementType());
            }

            double result = ((Quantity) qty1).divide((Quantity) qty2);
            entity.setResultValue(result);
            entity.setResultMeasurementType(q1.getMeasurementType());
            entity.setError(false);
            log.debug("Divide result: {}", result);
        } catch (QuantityMeasurementException ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Divide error: {}", ex.getMessage());
            throw ex;
        } catch (ArithmeticException ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Divide arithmetic error: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            entity.setErrorMessage(ex.getMessage());
            entity.setError(true);
            repository.save(entity);
            log.error("Divide error: {}", ex.getMessage());
            throw new QuantityMeasurementException("divide Error: " + ex.getMessage(), ex);
        }
        repository.save(entity);
        return QuantityMeasurementDTO.fromEntity(entity);
    }

    // ─── History & reporting ─────────────────────────────────────────────────

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
        List<QuantityMeasurementEntity> entities =
                repository.findByOperation(operation.toLowerCase());
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType) {
        List<QuantityMeasurementEntity> entities =
                repository.findByThisMeasurementType(measurementType);
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
    }
}