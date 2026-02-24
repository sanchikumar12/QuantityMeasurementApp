# QuantityMeasurement - Feet

A simple Java application to measure and compare quantities in **Feet** using clean design principles and unit testing.

---

## Features

- Immutable `Feet` class  
- Factory method `fromString()`  
- Custom exception `InvalidFeetException`  
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
│   │                       └── InvalidFeetException.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── apps/
│                   └── quantitymeasurement/
│                       └── domain/
│                           └── FeetTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## Test Cases Covered

- Same value comparison  
- Different value comparison  
- Null comparison  
- Different type comparison  
- Valid string input  
- Invalid string input (Exception case)  

---

## Example Usage

```java
Feet feet1 = Feet.fromString("10 ft");
Feet feet2 = Feet.fromString("10 ft");

System.out.println(feet1.equals(feet2)); // true
```

---

## Example Output

```
true
```

---

## Technologies Used

- Java  
- Maven  
- JUnit 5  
