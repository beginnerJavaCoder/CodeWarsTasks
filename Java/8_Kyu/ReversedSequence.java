/*
Original task - https://www.codewars.com/kata/reversed-sequence

Get the number n (n>0) to return the reversed sequence from n to 1.
Example : n=5 >> [5,4,3,2,1]
 */
 
public class Sequence {
    public static int[] reverse(int n) {
        int[] reverse = new int[n];
        for(; n > 0; n--) reverse[reverse.length - n] = n;
        return reverse;
    }
}