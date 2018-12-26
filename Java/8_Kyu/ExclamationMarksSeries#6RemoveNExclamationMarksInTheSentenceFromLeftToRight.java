/*
Original task - https://www.codewars.com/kata/exclamation-marks-series-number-6-remove-n-exclamation-marks-in-the-sentence-from-left-to-right

Description:
Remove n exclamation marks in the sentence from left to right. n is positive integer.

Examples
remove("Hi!",1) === "Hi"
remove("Hi!",100) === "Hi"
remove("Hi!!!",1) === "Hi!!"
remove("Hi!!!",100) === "Hi"
remove("!Hi",1) === "Hi"
remove("!Hi!",1) === "Hi!"
remove("!Hi!",100) === "Hi"
remove("!!!Hi !!hi!!! !hi",1) === "!!Hi !!hi!!! !hi"
remove("!!!Hi !!hi!!! !hi",3) === "Hi !!hi!!! !hi"
remove("!!!Hi !!hi!!! !hi",5) === "Hi hi!!! !hi"
remove("!!!Hi !!hi!!! !hi",100) === "Hi hi hi"
 */
 
public class Kata {
    public static String remove(String s, int n) {
        int length = s.length();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < length; i++) {
            if (s.codePointAt(i) == 33) {
                if (n > 0) {
                    n--;
                } else {
                    result.append(s.substring(i));
                    break;
                }
            } else {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }
}