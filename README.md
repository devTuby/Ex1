# Ex1 - Foundations of Functional Programming & Testing

In this project I explored the fundamentals of **functional programming** and **testing** using Java. The focus is on handling **strings**, **arrays**, and **numerical computations** to design a **number formatting converter and calculator**. 

The program operates on string-based numbers across bases **2–16**, with bases 10–16 represented by characters `A, B, ..., G`. Input validation, testing, and iterative code refinement are emphasized for correctness and elegance.

---

## **Key Features**
- **Number Validation**: Identify if a string is in `{number}b{base}` format.
- **Arithmetic Operations**: Perform addition and multiplication on valid numbers.
- **Base Conversion**: Convert numbers to any base within the range [2,16].
- **Error Handling**: Manage invalid input formats.
- **Extensive Testing**: Ensure robustness with comprehensive JUnit tests.

---

## **Valid Format Examples**
- `135bA` → Decimal value: `135` in base `10`
- `100111b2` → Decimal value: `39` in base `2`
- `1DbG` → Decimal value: `29` in base `16`

## **Invalid Formats**
- `b2` → Missing number part.
- `123b` → Missing base.
- `1234b11` → Base out of range.
- `-3b5` → Negative numbers not supported.
- `GbG` → Using Letters not in base scope.

---

## **Core Tasks**
1. **Implementation**: Develop static methods in `Ex1.java` for validation, conversion, and calculations.
2. **Testing**: Write detailed JUnit tests in `Ex1Test.java` to ensure full coverage.
3. **Main Program**: Create an interactive console application in `Ex1Main.java`.

---
