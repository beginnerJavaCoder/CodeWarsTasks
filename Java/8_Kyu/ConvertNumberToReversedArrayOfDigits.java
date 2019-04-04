/*
Original task - https://www.codewars.com/kata/convert-number-to-reversed-array-of-digits

Convert number to reversed array of digits
Given a random number.
You have to return the digits of this number within an array in reverse order.
Example:
348597 => [7,9,5,8,4,3]
 */
 
public class Kata {
    public static int[] digitize(long n) {
        String strNum = n + "";
        int[] digits = new int[strNum.length()];
        for(int i = 0; i < digits.length; i++)
            digits[i] = strNum.codePointAt(digits.length - i - 1) - 48;
        
        return digits;
    }
}