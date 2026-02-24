# QuantityMeasurement

## UC3 – Generic Quantity Class (DRY Principle)

### Description

UC3 refactors the separate `Feet` and `Inches` classes into a single generic **Quantity** class using a `LengthUnit` enum.

This approach eliminates code duplication and follows the **DRY (Don't Repeat Yourself)** principle.

---

## How It Works

- User provides a numeric value and unit (`FEET` or `INCH`).
- The value is internally converted to a **common base unit (feet)**.
- Equality is checked using **value-based comparison**, allowing cross-unit comparison.

Example:  
**1 foot = 12 inches**

---

## Key Concepts

- DRY Principle  
- Enum Usage  
- Encapsulation  
- Abstraction  
- Proper `equals()` and `hashCode()` contract  
- Cross-unit comparison  

---

## Features

- Single `Quantity` class for all length measurements  
- `LengthUnit` enum for unit handling  
- Internal conversion to base unit (feet)  
- Cross-unit equality support  
- Immutable design  
- Unit testing using JUnit 5  

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

## Equality Rules

- Same value and unit → `true`  
- Equivalent cross-unit values → `true`  
  - Example: `1 foot == 12 inches`  
- Different values → `false`  
- Null comparison → `false`  
- Same reference → `true`  
- Different object type → `false`  

---

## Example Usage

```java
Quantity oneFoot = new Quantity(1, LengthUnit.FEET);
Quantity twelveInches = new Quantity(12, LengthUnit.INCH);

System.out.println(oneFoot.equals(twelveInches)); // true
```

---

## Technologies Used

- Java  
- Maven  
- JUnit 5  
