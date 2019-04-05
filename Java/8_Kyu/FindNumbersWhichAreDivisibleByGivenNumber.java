/*
Original task - https://www.codewars.com/kata/find-numbers-which-are-divisible-by-given-number

Complete the function which takes two arguments and returns all numbers which are divisible by given divisor. 
First argument is an array of numbers and the second is the divisor.

Example
divisibleBy([1, 2, 3, 4, 5, 6], 2) == [2, 4, 6]
divisibleBy([1, 2, 3, 4, 5, 6], 2) == [2, 4, 6]
 */
 
import java.util.ArrayList;

public class EvenNumbers {
    public static int[] divisibleBy(int[] numbers, int divider) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int num : numbers)
            if(num % divider == 0) list.add(num);
            
        int[] array = new int[list.size()];
        for(int i = 0; i < array.length; i++)
            array[i] = list.get(i);
            
        return array;
    }
}