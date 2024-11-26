//package assignments.ex1;

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
//        System.out.println(convertCharToInt('G'));
//        System.out.println(convertBaseToInt("F"));
//        char ch = '4';
//        int value = (int) ch;
//        System.out.println(value);
        int value = number2Int("10bG");
        System.out.println(value);
    }

    /**
     * Convert the given number (num) to a decimal representation (as int).
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
            valueIn_10 += (int) (charIntValue*Math.pow(baseAsInt, valueIn_bLength-i-1));
        }
        ans = valueIn_10;

        return ans;
    }

    /**
     * This static functions converts a String (base) to its corresponding int value (2-16)
     *
     * @param base a String representing a base
     * @return int value of the base, binary to Hexa (2-16)
     */
    public static int convertBaseToInt(String base) {
        // Check if base is already represented in digits (2-9)
        if (base.matches("\\d+")) {
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
     *      default: Base 10 is default, so if "number" only has digits, it will be accepted
     *      first: "number" must be in the format of {value}b{base}
     *      second: All chars in the String must be uppercase, except the small 'b' that divides {value} and {base}
     *      third: base must be either 2-9, or 'A'-'G' [base 10 - base 16]
     *      fourth: All chars and digits in {value} must be lower than the digit/char in base.
     *              For instance, if base is 9, {value} must use 0-8. If base is E, {value} must use 0-9,A-D
     *      fifth:
     * @param numString a String representing a number
     * @return true iff the given String is in a number format
     */
    public static boolean isNumber(String numString) {
        boolean ans = true;

        // default case: only digits in the String,
        if (numString.matches("\\d+")) {
            return ans; // =true
        }

        // first rule: check if in format {value}b{base}
        String[] splittedString = numString.split("b");
        if (splittedString.length != 2) {
            ans = false;
            return ans;
        }

        return ans;
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
        // add your code here

        ////////////////////
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
        boolean ans = true;
        // add your code here

        ////////////////////
        return ans;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     *
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     */
    public static int maxIndex(String[] arr) {
        int ans = 0;
        // add your code here

        ////////////////////
        return ans;
    }
}
