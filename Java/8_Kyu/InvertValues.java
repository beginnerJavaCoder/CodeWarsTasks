/*
Original task - https://www.codewars.com/kata/invert-values

Given a set of numbers, return the additive inverse of each. 
Each positive becomes negatives, and the negatives become positives.

invert([1,2,3,4,5]) == [-1,-2,-3,-4,-5]
invert([1,-2,3,-4,5]) == [-1,2,-3,4,-5]
invert([]) == []
 */
 
public class Kata {
    public static int[] invert(int[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = -array[i];
        }
        
        return array;
    }
}