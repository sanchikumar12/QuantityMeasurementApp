
# QuantityMeasurement

## UC6 – Addition of Two Length Units

### Description

UC6 extends the previous use case by adding support for **addition of two length measurements** belonging to the same category.

The system automatically converts units, performs the addition using a base unit, and returns the result in the **unit of the first operand**.

---

## Supported Units

- FEET  
- INCHES  
- YARDS  
- CENTIMETERS  

---

## Features

- Add two `Length` / `Quantity` objects  
- Automatic unit conversion before addition  
- Result returned in the unit of the **first operand**  
- Uses base unit normalization (**FEET**)  
- Immutable design (returns a new object)  
- Input validation for:
  - null values  
  - NaN values  
  - Infinite values  

---

## Addition Logic

1. Convert both operands to base unit (**FEET**)  
2. Add the normalized values  
3. Convert the result back to the **first operand’s unit**  
4. Return a new `Length` / `Quantity` object  

---

## Examples

- `1 FEET + 2 FEET = 3 FEET`  
- `1 FEET + 12 INCHES = 2 FEET`  
- `12 INCHES + 1 FEET = 24 INCHES`  
- `1 YARD + 3 FEET = 2 YARDS`  

---

## 📂 Folder Structure

```
Quantity-Measurement
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── apps/
│   │               └── quantitymeasurement/
│   │                   ├── app/
│   │                   │   └── QuantityMeasurementApp.java
│   │                   │
│   │                   └── domain/
│   │                       ├── Quantity.java
│   │                       ├── LengthUnit.java
│   │                       └── InvalidQuantityException.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── apps/
│                   └── quantitymeasurement/
│                       └── domain/
│                           └── QuantityTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## Example Usage

```java
Quantity length1 = new Quantity(1, LengthUnit.FEET);
Quantity length2 = new Quantity(12, LengthUnit.INCHES);

Quantity result = length1.add(length2);

System.out.println(result); // 2 FEET
```

---

## Technologies Used

- Java  
- Maven  
- JUnit 5






























