/*
Original task - https://www.codewars.com/kata/how-good-are-you-really

There was a test in your class and you passed it. Congratulations!
But you're an ambitious person. 
You want to know if you're better than the average student in your class.
You got an array with your colleges' points. 
Now calculate the average to your points!

Return True if you're better, else False!

Note:
Your points are not included in the array of your classes points. 
For calculating the average point you may add your point to the given array!
 */
 
public class Kata {
    public static boolean betterThanAverage(int[] classPoints, int yourPoints) {
        int sum = 0;
        for(int i = 0; i < classPoints.length; i++)
            sum += classPoints[i];
        
        return yourPoints > sum / classPoints.length;
    }
}