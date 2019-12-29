/*
Original task - https://www.codewars.com/kata/third-angle-of-a-triangle

You are given two angles (in degrees) of a triangle.
Write a function to return the 3rd.
Note: only positive integers will be tested.
 */
 
public class ThirdAngle {
    public static int otherAngle(int angle1, int angle2) {
        return 180 - angle1 - angle2;
    }
}