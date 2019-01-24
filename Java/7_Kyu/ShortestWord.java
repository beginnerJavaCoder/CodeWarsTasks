/*
Original task - https://www.codewars.com/kata/shortest-word

Simple, given a string of words, return the length of the shortest word(s).
String will never be empty and you do not need to account for different data types.
 */
 
public class Kata {
    public static int findShort(String s) {
        String[] arr = s.split(" ");
        int minLength = arr[0].length();
        for(int i = 1; i < arr.length; i++) {
            int currentLength = arr[i].length();
            if(currentLength < minLength)
                minLength = currentLength;
        }
        
        return minLength;
    }
}