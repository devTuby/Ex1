//package assignments.ex1;

import java.util.Scanner;

/**
 * Intro2CS, Ex1 - very basic "main template"
 * Make sure your implementation of this main performs as the Ex1Sol.jar solution implement all needed functions.
 */
public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2 = "", quit = "quit";
        while (!num1.equals(quit) && !num2.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (!num1.equals("quit")) {
                boolean isNum1Number = Ex1.isNumber(num1); // save boolean of isNumber(num1)
                // print our wanted text, to show current results
                System.out.println("num1= " + num1 + " is number: " + isNum1Number + " , value: " + Ex1.number2Int(num1));
                if (!isNum1Number) { // print appropriate text if num1 is in wrong format
                    System.out.println("ERR: num1 is in the wrong format! (" + num1 + ")");
                    continue;
                } else { // if num1 is in valid format
                    // save num1 value for later calculations
                    int num1Value = Ex1.number2Int(num1);

                    System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
                    num2 = sc.next();
                    if (!num2.equals("quit")) {
                        boolean isNum2Number = Ex1.isNumber(num2); // save boolean of isNumber(num2)
                        System.out.println("num2= " + num2 + " is number: " + isNum2Number + " , value: " + Ex1.number2Int(num2));
                        if (!isNum2Number) {// print appropriate text if num2 is in wrong format
                            System.out.println("ERR: num2 is in the wrong format! (" + num2 + ")");
                            continue;
                        } else { // if num2 is in valid format
                            // save num2 value for later calculations
                            int num2Value = Ex1.number2Int(num2);

                            System.out.println("Enter a base for output: (a number [2,16]");

                            // We should check the input is even an int, the solution that was posted, didn't check for it,
                            // and the solution's Scanner throws and Exception. I've asked Ilan (ours' Trigul Teacher),
                            // and he replied we don't need to add a check for it.
                            // The instructions were: "Edit Ex1Main.java so that it will perform as in the Ex1Sol.jar file."
                            // So I do not want to add something that we were told not to add, even though
                            // I do think it is necessary for the code to behave normally.
                            // If we do need though, I would have added:
                            /**
                                if (!sc.hasNextInt()) {
                                    System.out.println("You have not entered an int for a base");
                                    sc.next(); // to consume the wrong input
                                    continue;
                                }
                             */

                            int baseIntInput = sc.nextInt();
                            // We check if the input is between [2,16]
                            if (baseIntInput > 16 || baseIntInput < 2) {
                                System.out.println("ERR: wrong base, should be [2,16], got (" + baseIntInput + ")");
                                continue;
                            }
                            ;

                            String base = Integer.toString(baseIntInput);
                            int baseIntValue = Ex1.convertBaseToInt(base);

                            int num1PlusNum2Value = num1Value + num2Value;
                            String num1PlusNum2InBase = Ex1.int2Number(num1PlusNum2Value, baseIntValue);

                            int num1TimesNum2Value = num1Value * num2Value;
                            String num1TimesNum2InBase = Ex1.int2Number(num1TimesNum2Value, baseIntValue);

                            System.out.println(num1 + " + " + num2 + " = " + num1PlusNum2InBase);
                            System.out.println(num1 + " * " + num2 + " = " + num1TimesNum2InBase);

                            String[] nums = {num1, num2, num1PlusNum2InBase, num1TimesNum2InBase};
                            int maxNumIndex = Ex1.maxIndex(nums);

                            System.out.println("Max number over [" + num1 + "," + num2 + "," + num1PlusNum2InBase + "," + num1TimesNum2InBase + "] is: " + nums[maxNumIndex]);

                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println("quiting now...");
    }
}
