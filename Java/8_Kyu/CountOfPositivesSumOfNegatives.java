/*
Original task - https://www.codewars.com/kata/count-of-positives-slash-sum-of-negatives

Given an array of integers.
Return an array, where the first element is the count of positives numbers 
and the second element is sum of negative numbers.

If the input array is empty or null, return an empty array.

Example
For input [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15], you should return [10, -65].
 */
 
public class Kata {
    public static int[] countPositivesSumNegatives(int[] input) {
        if(input == null || input.length == 0) return new int[]{};
        int countOfPositive = 0;
        int sumOfNegative = 0;
        for(int num : input) {
            if(num > 0) countOfPositive++;
            else sumOfNegative += num;
        }
        
        return new int[]{countOfPositive, sumOfNegative};
    }
}