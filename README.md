# QuantityMeasurement

## UC4 – Quantity Measurement (Multiple Units)

### Supported Units

- Feet  
- Inch  
- Yard  
- Centimeter  

---

## Description

UC4 extends the Quantity Measurement application to support multiple length units.  
The system allows users to input values in different units, converts them internally to a common base unit, and performs **cross-unit equality comparison**.

---

## Features

- Takes input value and unit from the user  
- Supports multiple length units  
- Internal unit conversion to a common base unit  
- Value-based equality comparison  
- Cross-unit comparison support  
- Immutable design  
- Unit testing using JUnit 5  

---

## Unit Conversion Rules

- **1 Foot = 12 Inch**  
- **3 Feet = 1 Yard**  
- **1 Inch = 2.54 Centimeter**  

(All units are internally converted to a base unit for comparison.)

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

## Equality Examples

- `1 Foot == 12 Inch` → true  
- `3 Feet == 1 Yard` → true  
- `2.54 Centimeter == 1 Inch` → true  
- Different values → false  
- Null comparison → false  
- Different object type → false  

---

## Example Usage

```java
Quantity q1 = new Quantity(1, LengthUnit.FEET);
Quantity q2 = new Quantity(12, LengthUnit.INCH);

Quantity q3 = new Quantity(3, LengthUnit.FEET);
Quantity q4 = new Quantity(1, LengthUnit.YARD);

System.out.println(q1.equals(q2)); // true
System.out.println(q3.equals(q4)); // true
```

---

## Technologies Used

- Java  
- Maven  
- JUnit 5  
