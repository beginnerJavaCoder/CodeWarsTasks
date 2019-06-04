/*
Original task - https://www.codewars.com/kata/is-it-negative-zero-0

There exist two zeroes: +0 (or just 0) and -0.
Write a function that returns true if the input number is -0 and false otherwise.
In Java the input will be a float.
 */
 
public class NegativeZeroValidator {
    public static boolean isNegativeZero(float n) {
        return String.valueOf(n).equals("-0.0");
    }
}