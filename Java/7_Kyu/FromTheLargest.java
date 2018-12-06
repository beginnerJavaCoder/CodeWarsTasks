/*
Original task - https://www.codewars.com/kata/form-the-largest

Task
Given a number , Return The Maximum number could be formed from the digits of the number given .

Notes
Only Positve numbers passed to the function , numbers Contain digits [1:9] inclusive
Digit Duplications could occur , So also consider it when forming the Largest

Input >> Output Examples:
1- maxNumber (213) ==> return (321)
Explanation:
As 321 is The Maximum number could be formed from the digits of the number 213 .
2- maxNumber (7389) ==> return (9873)
Explanation:
As 9873 is The Maximum number could be formed from the digits of the number 7389 .
3- maxNumber (63729) ==> return (97632)
Explanation:
As 97632 is The Maximum number could be formed from the digits of the number 63729 .
4- maxNumber (566797) ==> return (977665)
Explanation:
As 977665 is The Maximum number could be formed from the digits of the number 566797 .
Note : Digit duplications are considered when forming the largest .
5- maxNumber (17693284) ==> return (98764321)
Explanation:
As 98764321 is The Maximum number could be formed from the digits of the number 17693284 .
 */

public class Solution {

    public static long maxNumber(long num) {
        return sort(num + "");
    }

    private static long sort(String num) {
        int length = num.length();
        StringBuilder result = new StringBuilder(num);
        char max, tmp;
        int maxID;
        for(int i = 0; i < length - 1; i++) {
            max = result.charAt(i);
            maxID = i;
            for(int j = i + 1; j < length; j++) {
                if(result.charAt(j) > max) {
                    max = result.charAt(j);
                    maxID = j;
                }
            }
            tmp = result.charAt(i);
            result.deleteCharAt(i);
            result.insert(i, max);
            result.deleteCharAt(maxID);
            result.insert(maxID, tmp);
        }

        return Long.parseLong(result.toString());
    }
}