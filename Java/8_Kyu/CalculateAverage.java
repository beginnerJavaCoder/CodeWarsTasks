/*
Original task - https://www.codewars.com/kata/calculate-average

Write function avg which calculates average of numbers in given list.
 */
 
public class Kata {
    public static double find_average(int[] array) {   
        double sum = 0;
        for(int i = 0; i < array.length; i++)
            sum += array[i];
        
        return sum / array.length;
    }
}