/*
Original task - https://www.codewars.com/kata/beginner-reduce-but-grow

Given a non-empty array of integers, 
return the result of multiplying the values together in order. 

Example:
[1, 2, 3, 4] => 1 * 2 * 3 * 4 = 24
 */
 
public class Kata {
    public static int grow(int[] x) {
        int product = 1;
        for(int num : x)
            product *= num;
            
        return product;
    }
}