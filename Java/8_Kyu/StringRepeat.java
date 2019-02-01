/*
Original task - https://www.codewars.com/kata/string-repeat

Write a function called repeatStr which repeats the given string string exactly n times.
repeatStr(6, "I") // "IIIIII"
repeatStr(5, "Hello") // "HelloHelloHelloHelloHello"
 */
 
public class Solution {
    public static String repeatStr(final int repeat, final String string) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < repeat; i++) {
            result.append(string);
        }
        
        return result.toString();
    }
}