/*
Original task - https://www.codewars.com/kata/the-old-switcheroo

Write a function
Kata.Vowel2Index("this is my string") == "th3s 6s my str15ng"
Kata.Vowel2Index("Codewars is the best site in the world") == "C2d4w6rs 10s th15 b18st s23t25 27n th32 w35rld"
Your function should be case insensitive to the vowels.
 */
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kata {
    public static String vowel2Index(String s) {
        Pattern pattern = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            s = s.replaceFirst(matcher.group(), matcher.start() + 1 + "");
        }

        return s;
    }
}