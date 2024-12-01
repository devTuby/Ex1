//package assignments.ex1;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {

    /**
     * REMEMBER TO DELETE THIS MAIN
     */
    public static void main(String[] args) {

    }

    /**
     * Convert the given number (num) in any base, to a decimal (base 10) representation (as int).
     * It the given number is not in a valid format returns -1.
     *
     * @param num a String representing a number in basis [2,16]
     * @return int value of num in base 10
     */
    public static int number2Int(String num) {
        int ans = -1;

        // if num is not in valid format
        if (!isNumber(num)) {
            return ans; // = -1
        }

        // if String 'num' is already in base 10, meaning the input is only numbers, convert num to int, and ans=num
        // We'll check if String 'num' contains 'b', which we know String 'num' must be in valid format, which must have 'b' in it.
        if (num.indexOf('b') == -1) { // 'b' is not in String 'num', meaning 'num' has only numbers.
            ans = Integer.parseInt(num);
            return ans;
        }

        // Now we know String 'num' must be in the format of {valueIn_b}b{base}
        // We will split {valueIn_b} and {base} by 'b'
        String[] valueIn_bAndBase = num.split("b"); // String[0]=valueIn_b, String[1]=base
        String valueIn_b = valueIn_bAndBase[0];
        String baseAsString = valueIn_bAndBase[1];
        int baseAsInt = convertBaseToInt(baseAsString); // Convert the baseAsString it its corresponding int value

        // Now we want to read valueIn_b and convert it to base 10
        int valueIn_10 = 0; // Initialize int value for valueIn_10, we will dynamically increase it, based on valueIn_b
        int valueIn_bLength = valueIn_b.length();

        for (int i = 0; i < valueIn_bLength; i++) {
            char ch_i = valueIn_b.charAt(i); // get char at index i
            int charIntValue = convertCharToInt(ch_i); // convert the char to its int value

            // Here we dynamically add the values. For instance ABC in base G(16) = 10*16^2+11*16^1+12*16^0
            valueIn_10 += (int) (charIntValue * Math.pow(baseAsInt, valueIn_bLength - i - 1));
        }
        ans = valueIn_10;

        return ans;
    }

    /**
     * This static function checks if all the chars of a String are digits
     *
     * @param str
     * @return true if the String contains only digits
     */
    public static boolean isStringOnlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This static function checks if all the chars of a String are digits/letters
     *
     * @param str
     * @return true if the String contains only digits/letters
     */
    public static boolean isStringOnlyLettersOrDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This static function converts a String (base) to its corresponding int value (2-16)
     *
     * @param base a String representing a base
     * @return int value of the base, binary to Hexa (2-16)
     */
    public static int convertBaseToInt(String base) {
        // Check if base is already represented in digits (2-9)
        if (isStringOnlyDigits(base)) { // regex rule to check if
            return Integer.parseInt(base); // Convert String "base" to int
        }
        // Now we know 'base' must be A-G (base 10 - base 16), so we will convert it to its int value
        char baseChar = base.charAt(0); // Get first char of String, which is the only char
        return convertCharToInt(baseChar); // return its corresponding int value
    }

    /**
     * This static functions converts A-G to their corresponding int value (10-16)
     * And keeps chars '0'-'9' as 0-9 int values
     *
     * @param ch a Char '0'-'9', 'A'-'G'
     * @return int value of its corresponding value
     */
    public static int convertCharToInt(char ch) {

        if (Character.isDigit(ch)) { // check if 'ch' is a digit
            return Character.getNumericValue(ch); // return int value of ch
        }

        // Switch case to assign value 10-16 for 'A'-'G'
        return switch (ch) {
            case 'A' -> 10;
            case 'B' -> 11;
            case 'C' -> 12;
            case 'D' -> 13;
            case 'E' -> 14;
            case 'F' -> 15;
            case 'G' -> 16;
            default -> -1;
        };
    }

    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     * Our valid "number" format is as follows:
     * default: Base 10 is default, so if "number" only has digits, it will be accepted
     * first: "number" must be in the format of {value}b{base} and {value} & {base} not empty and contain letters/digits
     * second: All chars in the String must be uppercase, except the small 'b' that divides {value} and {base}
     * third: base must be either 2-9, or 'A'-'G' [base 10 - base 16]
     * fourth: All chars and digits in {value} must be lower than the digit/char in base.
     * For instance, if base is 9, {value} must use 0-8. If base is E, {value} must use 0-9,A-D
     * fifth:
     *
     * @param numString a String representing a number
     * @return true iff the given String is in a number format
     */
    public static boolean isNumber(String numString) {
        boolean ans = true;

        // numString can't be null or empty
        if (numString == null || numString.isEmpty()) {
            return false;
        }

        // default case: only digits in the String,
        if (isStringOnlyDigits(numString)) {
            return true;
        }

        // first rule: check if in format {value}b{base} and {value} & {base} not empty
        String[] splittedString = numString.split("b");
        // Check if length of splittedString is exactly 2 => [{value},{base}]
        // Otherwise, return false. Could be more than one 'b' or none at all.
        if (splittedString.length != 2) {
            return false;
        }
        String value = splittedString[0];
        String baseAsString = splittedString[1];
        // make sure {value} and {base} are not empty
        if (value.isEmpty() || baseAsString.isEmpty()) {
            return false;
        }
        // check if contain only letters/digits
        if (!isStringOnlyLettersOrDigits(value) || !isStringOnlyLettersOrDigits(baseAsString)) {
            return false;
        }

        // second rule: all chars must be uppercase, except the small 'b'
        // We will check in splittedString if {value} and {base} are upper case
        // We will convert numString to uppercase, and then compare numStringUpperCase with numString
        if (!value.equals(value.toUpperCase())) { // first we check if {value} is only uppercase
            return false;
        } else if (!baseAsString.equals(baseAsString.toUpperCase())) { // then we check if {base} is only uppercase
            return false;
        }

        // third rule: base must be either 2-9, or 'A'-'G'
        // We'll check if the length is 1, since it must be 1 char or 1 digit.
        if (baseAsString.length() != 1) {
            return false;
        }

        // We get hold of the single char from baseAsString
        char baseCh = baseAsString.charAt(0);
        // Now we want to check if baseChar is either '1'-'9' or 'A'-'G'
        if (!((baseCh >= '1' && baseCh <= '9') || (baseCh >= 'A' && baseCh <= 'G'))) { // We check it by using char values
            return false;
        }

        // fourth rule: All chars and digits in {value} must be lower than the digit/char in base.
        // We'll divide the check, the case when the base is a digit, and when it is a char
        for (int i = 0; i < value.length(); i++) { // iterates through String Value
            char c = value.charAt(i);
            if (Character.isDigit(baseCh)) { // 1. If the base is a digit:
                int baseDigit = Character.getNumericValue(baseCh); // convert baseCh to its int value
                int cDigit = Character.getNumericValue(c); // convert c (must be digit) to its int value
                if (cDigit >= baseDigit && cDigit <= 9) { // and check if there are any digits >= baseDigit and <= 9
                    return false;
                }
            } else { // 2. If the base is a char (A-G)
                if (c >= baseCh && c <= 'G') {
                    return false;
                }
            }
        }


        return ans;
    }

    /**
     * This function converts intValue 0-16 to '0'-'9'-'A'-'G'
     *
     * @param intValue 0-15 corresponded to 0-9 or 'A'-'G' (10-16)
     * @return char represting
     */
    public static char convertIntToChar(int intValue) {
        if (intValue >= 0 && intValue <= 9) {
            return (char) ('0' + intValue);  // convert 0-9 to '0'-'9', since in ASCII table they have increasing values
        } else if (intValue >= 10 && intValue <= 16) {
            return (char) ('A' + (intValue - 10));  // convert 10-16 to 'A'-'G', since in ASCII table they have increasing values
        }
        return '?';
    }

    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     *
     * @param num  the natural number (include 0).
     * @param base the basis [2,16]
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */
    public static String int2Number(int num, int base) {
        String ans = "";

        if(base == 10){
            return Integer.toString(num);
        }

        int intDivision = num;
        int remainder;

        // This algo is to find the representation of the num in a given base
        do {
            remainder = intDivision % base;
            ans = convertIntToChar(remainder) + ans;
            intDivision /= base;
        } while (intDivision != 0);

        // Now we want to add "b{base}" to the final answer
        ans = ans + "b" + convertIntToChar(base);
        return ans;
    }

    /**
     * Checks if the two numbers have the same value.
     *
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {

        if (number2Int(n1) != number2Int(n2)) {
            return false;
        }
        return true;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     *
     * @return the index in the array in with the largest number (in value).
     */
    public static int maxIndex(String[] arr) {
        int max = -1;
        int maxIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            int numInDecimal = number2Int(arr[i]);
            if (numInDecimal > max) {
                max = numInDecimal;
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
