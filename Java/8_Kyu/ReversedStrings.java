/*
Original task - https://www.codewars.com/kata/reversed-strings

Complete the solution so that it reverses the string value passed into it.
Kata.solution("world") //returns "dlrow"
 */
 
public class Kata {
    public static String solution(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}