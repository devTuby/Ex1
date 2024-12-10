//package assignments.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class Ex1Test {
    @Test
    void computeNumberTest() {
        String s2 = "1011b2";
        int v = Ex1.number2Int(s2);
        assertEquals(v, 11);
        String s10 = "1011bA";
        v = Ex1.number2Int(s10);
        s2 = Ex1.int2Number(v, 2);
        int v2 = Ex1.number2Int(s2);
        assertEquals(v, v2);
        assertTrue(Ex1.equals(s10, s2));
        assertTrue(Ex1.equals("FFFbG", "4095"));
        assertTrue(Ex1.equals("FFFbG", "4095bA"));
    }

    @Test
    void isBasisNumberTest() {
        String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
        for (int i = 0; i < good.length; i = i + 1) {
            boolean ok = Ex1.isNumber(good[i]);
            assertTrue(ok);
        }
        String[] not_good = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
        for (int i = 0; i < not_good.length; i = i + 1) {
            boolean not_ok = Ex1.isNumber(not_good[i]);
            assertFalse(not_ok);
        }
    }

    @Test
    void int2NumberTest() {
        assertEquals(Ex1.int2Number(158, 2), "10011110b2");
        assertEquals(Ex1.int2Number(158, 7), "314b7");
        assertEquals(Ex1.int2Number(158, 14), "B4bE");
        assertEquals(Ex1.int2Number(158, 16), "9EbG");
        assertEquals(Ex1.int2Number(987, 6), "4323b6");
        assertEquals(Ex1.int2Number(987, 16), "3DBbG");
        assertEquals(Ex1.int2Number(4095, 16), "FFFbG");
        assertEquals(Ex1.int2Number(999, 10), "999");
    }

    @Test
    void maxIndexTest() {
        String[] arr1 = {"FFFbG", "4323b6", "9EbG", "100b2"};
        String[] arr2 = {"C6BbG", "CCCAbG", "1000b2", "999"};
        String[] arr3 = {null, "b!", "1000b2", "", "100"};
        assertEquals(Ex1.maxIndex(arr1), 0);
        assertEquals(Ex1.maxIndex(arr2), 1);
        assertEquals(Ex1.maxIndex(arr3), 4);
    }

    @Test
    void isStringOnlyDigitsTest() {
        String[] strArr = {"1234", "1234", "0001", "0192", "1"};
        for (String str : strArr) {
            assertTrue(Ex1.isStringOnlyDigits(str));
        }

        String[] strArr2 = {"1234b", "0a001", "1'2'34", "_0192", "AB", "a", "!123"};
        for (String str : strArr2) {
            assertFalse(Ex1.isStringOnlyDigits(str));
        }
    }

    @Test
    void isStringOnlyLettersOrDigitsTest() {
        String[] strArr = {"1234A", "1234A", "0001p", "01bQ92", "1A"};
        for (String str : strArr) {
            assertTrue(Ex1.isStringOnlyLettersOrDigits(str));
        }

        String[] strArr2 = {"1234b_", "0a0!01", "134,123", "_0192", "AB-", "a/"};
        for (String str : strArr2) {
            assertFalse(Ex1.isStringOnlyLettersOrDigits(str));
        }
    }

    @Test
    void convertCharToIntTest() {
        char c = 'A';
        for (int i = 10; i <= 16; i++) { // Loop from 'A' to 'G', and check is equal to 10-16
            assertEquals(i, Ex1.convertCharToInt(c));
            c++;
        }

        for (int i = 2; i <= 9; i++) {
            char ch = (char) (i + '0');
            assertEquals(i, Ex1.convertCharToInt(ch));
        }
    }

    @Test
    void convertIntToCharTest() {
        for (int i = 0; i <= 9; i++) {
            char ch = (char) (i + '0');
            assertEquals(ch, Ex1.convertIntToChar(i));
        }

        for (int i = 10; i <= 16; i++) {
            char ch = (char) ((i - 10) + 'A');
            assertEquals(ch, Ex1.convertIntToChar(i));
        }
    }

    @Test
    void convertBaseToIntTest() {
        for (int i = 2; i <= 16; i++) {
            char ch = Ex1.convertIntToChar(i);
            assertEquals(Ex1.convertCharToInt(ch), Ex1.convertBaseToInt(Character.toString(ch)));
        }
    }
}
