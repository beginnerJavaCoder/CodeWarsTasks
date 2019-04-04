/*
Original task - https://www.codewars.com/kata/surface-area-and-volume-of-a-box

Write a function that returns the total surface area and volume of a box as an array: [area, volume].
 */
 
public class Kata {
    public static int[] getSize(int w,int h,int d) {
        return new int[]{2 * (w * h + w * d + h * d), w * h * d};
    }
}