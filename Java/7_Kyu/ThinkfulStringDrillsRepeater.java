/*
Original task - https://www.codewars.com/kata/thinkful-string-drills-repeater

Write a class function named repeat() that takes two arguments (a string and a long integer),
and returns a new string where the input string is repeated that many times.

For example:
Repeater.repeat("a", 5)
should return
"aaaaa"
 */
 
public class Repeater {
    public static String repeat(String string, long n) {
        StringBuilder repeated = new StringBuilder();
        while(n-- > 0) repeated.append(string);
        return repeated.toString();
    }
}