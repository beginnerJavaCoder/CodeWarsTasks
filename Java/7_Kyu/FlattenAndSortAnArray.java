/*
Original task - https://www.codewars.com/kata/flatten-and-sort-an-array

Challenge:

Given a two-dimensional array of integers, 
return the flattened version of the array with all the integers in the sorted (ascending) order.

Example:

Given [[3, 2, 1], [4, 6, 5], [], [9, 7, 8]], 
your function should return [1, 2, 3, 4, 5, 6, 7, 8, 9].
 */
 
import java.util.Arrays;

public class Kata {

    public static int[] flattenAndSort(int[][] array) {
        int[] flattenedArray = toFlattenArray(array);
        Arrays.sort(flattenedArray);

        return flattenedArray;
    }

    private static int getQuantityOfItems(int[][] array) {
        int length = 0;
        for (int[] subArray : array) {
            length += subArray.length;
        }

        return length;
    }

    private static int[] toFlattenArray(int[][] array) {
        int[] flattened = new int[getQuantityOfItems(array)];
        int index = 0;
        for (int[] subArray : array) {
            for (int value : subArray) {
                flattened[index++] = value;
            }
        }
        
        return flattened;
    }
}