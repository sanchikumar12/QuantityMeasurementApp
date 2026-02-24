# QuantityMeasurement

## UC2 - Equality Comparison

This use case implements equality comparison for:

- Feet  
- Inches  

Objects are created using:

```java
Feet.fromString("1.0");
Inches.fromString("1.0");
```

Invalid numeric input throws a custom exception.

---

## Features

- Immutable `Feet` and `Inches` classes  
- Factory method `fromString()` for object creation  
- Custom exception for invalid numeric input  
- Proper `equals()` and `hashCode()` implementation  
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
│   │                       ├── Feet.java
│   │                       ├── Inches.java
│   │                       └── InvalidFeetException.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── apps/
│                   └── quantitymeasurement/
│                       └── domain/
│                           ├── FeetTest.java
│                           └── InchesTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## Equality Rules

- Same value → `true`  
- Different value → `false`  
- Null comparison → `false`  
- Same reference → `true`  
- Different class → `false`  

---

## Example Usage

```java
Feet feet1 = Feet.fromString("1.0");
Feet feet2 = Feet.fromString("1.0");

Inches inch1 = Inches.fromString("1.0");

System.out.println(feet1.equals(feet2)); // true
System.out.println(feet1.equals(inch1)); // false
```

---

## Technologies Used

- Java  
- Maven  
- JUnit 5  
