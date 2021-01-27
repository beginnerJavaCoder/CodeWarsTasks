/*
Original task - https://www.codewars.com/kata/520b9d2ad5c005041100000f

Move the first letter of each word to the end of it, 
then add "ay" to the end of the word. Leave punctuation marks untouched.

Examples
pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
pigIt('Hello world !');     // elloHay orldway !
 */
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin {
    public static String pigIt(String str) {
        StringBuilder result = new StringBuilder();

        Pattern singleWord = Pattern.compile("[A-Za-z]+");
        Matcher matcher = singleWord.matcher(str);

        if(!matcher.find()) return str;
        else {
            int index = 0;
            do {
                int start = matcher.start();
                if (index != start) {
                    result.append(str.substring(index, start));
                }
                if(matcher.group().length() > 1) {
                    result.append(str.substring(start + 1, matcher.end()));
                }
                result.append(str.charAt(start));
                result.append("ay");
                index = matcher.end();
            } while (matcher.find());

            result.append(str.substring(index, str.length()));
        }

        return result.toString();
    }
}
