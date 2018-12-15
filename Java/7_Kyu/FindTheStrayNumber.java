/*
Original task - https://www.codewars.com/kata/find-the-stray-number

You are given an odd-length array of integers, 
in which all of them are the same, except for one single number.
Complete the method which accepts such an array, and returns that single different number.

The input array will always be valid! (odd-length >= 3)
Examples:
[1, 1, 2] ==> 2
[17, 17, 3, 17, 17, 17, 17] ==> 3
 */
 
class Solution {
    static int stray(int[] numbers) {
        int firstIndex = 0;
        int secondIndex = 0;
        int countOfFirst = 1;
        for(int i = 1; i < numbers.length; i++) {
            if(numbers[i] != numbers[firstIndex])    secondIndex = i;
            else                                     countOfFirst++;
            if(secondIndex != 0 && countOfFirst > 1) return numbers[secondIndex];
        }
        return numbers[firstIndex];
    }
}