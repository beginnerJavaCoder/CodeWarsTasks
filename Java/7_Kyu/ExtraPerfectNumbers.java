/*
Original task - https://www.codewars.com/kata/extra-perfect-numbers-special-numbers-series-number-7

Definition
Extra perfect number is the number that first and last bits are set bits.

Task
Given a positive integer N, Return the extra perfect numbers in range from 1 to N.

Notes
Number passed is always Positive.
Returned array should contain the extra perfect numbers in ascending order from lowest to highest.

Input >> Output Examples
1- extraPerfect(3)  ==>  return {1,3}
Explanation:
(1)10 =(1)2
First and last bits as set bits.

(3)10 = (11)2
First and last bits as set bits.

2- extraPerfect(7)  ==>  return {1,3,5,7}
Explanation:
(5)10 = (101)2
First and last bits as set bits.

(7)10 = (111)2
First and last bits as set bits.
 */
 
import java.util.ArrayList;

public class Solution {
    public static int[] extraPerfect(int number) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            String binary = Integer.toBinaryString(i);
            if(binary.charAt(0) == binary.charAt(binary.length()-1) && binary.charAt(0) == '1')
                list.add(i);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}