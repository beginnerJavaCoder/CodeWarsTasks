/*
Original task - https://www.codewars.com/kata/camelcase-method

Write simple camelCase method for strings.
All words must have their first letter capitalized without spaces.

For instance:
camelCase("hello case"); // => "HelloCase"
camelCase("camel case word"); // => "CamelCaseWord"
 */
 
public class Solution {
    public static String camelCase(String str) {
        if(str.length() == 0) return str; 
        if(str.startsWith(" ")) str = str.replaceFirst("\\s+", "");
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            int firstCodePoint = word.codePointAt(0);
            if (firstCodePoint > 96 && firstCodePoint < 123) {
                result.append((char) (firstCodePoint - 32)).append(word.substring(1));
            } else result.append(word);
        }

        return result.toString();
    }
}