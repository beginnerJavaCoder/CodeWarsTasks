/*
Original task - https://www.codewars.com/kata/remove-string-spaces

Simple, remove the spaces from the string, then return the resultant string.
 */
 
public class Kata {
    public static String noSpace(final String x) {
        return x.replaceAll(" ", "");
    }
}