/*
Original task - https://www.codewars.com/kata/disarium-number-special-numbers-series-number-3

Disarium number is the number that
The sum of its digits powered with their respective positions is equal to the number itself.

Task
Given a number, Find if it is Disarium or not.

Notes
Number passed is always Positive.
Return the result as String.

Input >> Output Examples
1- disariumNumber(89) ==> return "Disarium !!"
Explanation:
Since, 8^1 + 9^2 = 89 , thus output is "Disarium !!"
2- disariumNumber(564) ==> return "Not !!"
Explanation:
Since, 5^1 + 6^2 + 4^3 = 105 != 564 , thus output is "Not !!"
 */
 
public class Solution {
    public static String disariumNumber(int number) {
        String num = number + "";
        int poweredDigitsSum = 0;
        for (int i = 0; i < num.length(); i++) {
            poweredDigitsSum += Math.pow((num.codePointAt(i) - 48), i+1);
        }

        return poweredDigitsSum == number ? "Disarium !!" : "Not !!";
    }
}