/*
Original task - https://www.codewars.com/kata/find-nearest-square-number

Your task is to find the nearest square number, nearest_sq(n), of a positive integer n.
 */
 
public class CodeWarsMath {
    public static int nearestSq(final int n) {
        int nearestSqrt = 1;
        for(int i = 1; i <= (int)Math.sqrt(n) + 1; i++) {
            int tmp = (int) Math.pow(i, 2);
            if(Math.abs(n - tmp) < n - nearestSqrt) {
                nearestSqrt = tmp;
            }
        }
        
        return nearestSqrt;
    }
}