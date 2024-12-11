# Ex1 - Foundations of Functional Programming & Testing

This project explores the fundamentals of **functional programming** and **testing** in Java. The focus is on handling **strings**, **arrays**, and **numerical computations** to design a **number formatting converter and calculator**. 

The program operates on string-based numbers across bases **2–16**, with bases 10–16 represented by characters `A, B, ..., G`. It emphasizes input validation, testing, and iterative code refinement for correctness and usability.

---
## **Functions Implemented**
- **number2Int**: Converts a number in a specified base (2–16) to decimal (base 10) integer representation, returning -1 if invalid.
- **isNumber**: Checks if a string adheres to valid number format rules and returns true if valid, false otherwise.
- **int2Number**: Converts a non-negative integer to its representation in a specified base (2–16) or returns an empty string if invalid.
- **equals**: Compares two string-based numbers and returns true if they have the same value.
- **maxIndex**: Finds the index of the largest valid number (by value) in an array of strings, ignoring invalid or null entries.
- Additional helper functions support internal checks within these main functions.

---
## **Key Features**
- **Number Validation**: Identifies valid `{value}b{base}` format.
- **Arithmetic Operations**: Supports addition and multiplication of valid numbers.
- **Base Conversion**: Converts numbers to any base within the range [2,16].
- **Error Handling**: Manages invalid input gracefully.
- **Extensive Testing**: Ensures robustness with JUnit tests.

---
## **Examples**
### Valid Formats:
- `135bA` → Decimal: `135` in base `10`
- `100111b2` → Decimal: `39` in base `2`
- `1DbG` → Decimal: `29` in base `16`

### Invalid Formats:
- `b2` → Missing value.
- `123b` → Missing base.
- `1234b11` → Base out of range.
- `-3b5` → Negative numbers not supported.
- `GbG` → Letters not in base scope.

---
## **Suggested Fix**
The provided `Ex1Sol.jar` solution crashes when non-integer input is provided for the base. Example:
```
Enter a base for output: (a number [2,16]
r
Exception in thread "main" java.util.InputMismatchException
    at java.base/java.util.Scanner.throwFor(Scanner.java:964)
    ...
```
### Fix:
Add a check in `Ex1Main.java` to validate input:
```java
System.out.println("Enter a base for output: (a number [2,16]");
if (!sc.hasNextInt()) {
    System.out.println("You have not entered an int for a base");
    sc.next(); // Consume the invalid input
    continue;
}
```

