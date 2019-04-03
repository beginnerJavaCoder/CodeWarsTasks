/*
Original task - https://www.codewars.com/kata/switch-it-up

When provided with a number between 0-9, return it in words.
Input :: 1
Output :: "One".
Try using "Switch" statements.
 */
 
public class Kata {
    
    private static final String[] words = new String[]{
    "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    
    public static String switchItUp(int number) {
        return words[number];
    }
}