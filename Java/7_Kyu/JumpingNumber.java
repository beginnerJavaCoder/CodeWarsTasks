/*
Original task - https://www.codewars.com/kata/jumping-number-special-numbers-series-number-4

Definition
Jumping number is the number that All adjacent digits in it differ by 1.

Task
Given a number, Find if it is Jumping or not.

Notes
Number passed is always Positive .
Return the result as String .
The difference between ‘9’ and ‘0’ is not considered as 1 .
All single digit numbers are considered as Jumping numbers.

Input >> Output Examples
1- jumpingNumber(9) ==> return "Jumping!!"
Explanation:
It's single-digit number
2- jumpingNumber(79) ==> return "Not!!"
Explanation:
Adjacent digits don't differ by 1
3- jumpingNumber(23) ==> return "Jumping!!"
Explanation:
Adjacent digits differ by 1
4- jumpingNumber(556847) ==> return "Not!!"
Explanation:
Adjacent digits don't differ by 1
5- jumpingNumber(4343456) ==> return "Jumping!!"
Explanation:
Adjacent digits differ by 1
6- jumpingNumber(89098) ==> return "Not!!"
Explanation:
Adjacent digits don't differ by 1
7- jumpingNumber(32) ==> return "Jumping!!"
Explanation:
Adjacent digits differ by 1
 */
 
public class Solution {
    public static String jumpingNumber(int number) {
        String num = number + "";
        if(num.length() == 1) return "Jumping!!";
        for (int i = 1; i < num.length(); i++) {
            if(i == num.length()-1 ? (Math.abs(num.charAt(i) - num.charAt(i-1)) != 1) :
               (Math.abs(num.charAt(i) - num.charAt(i-1)) != 1 || Math.abs(num.charAt(i + 1) - num.charAt(i)) != 1))
                return "Not!!";
        }

        return "Jumping!!";
    }
}