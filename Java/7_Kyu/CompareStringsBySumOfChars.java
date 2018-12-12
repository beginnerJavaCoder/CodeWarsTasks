/*
Original task - https://www.codewars.com/kata/compare-strings-by-sum-of-chars

Compare two strings by comparing the sum of their values (ASCII character code).

For comparing treat all letters as UpperCase
null/NULL/Nil/None should be treated as empty strings
If the string contains other characters than letters, treat the whole string as it would be empty
Your method should return true, if the strings are equal and false if they are not equal.

Examples:
"AD", "BC"  -> equal
"AD", "DD"  -> not equal
"gf", "FG"  -> equal
"zz1", ""   -> equal (both are considered empty)
"ZzZz", "ffPFF" -> equal
"kl", "lz"  -> not equal
null, ""    -> equal
 */
 
public class Kata {
	
    public static boolean compare(String s1, String s2) {
        return calculateSumOfChars(s1) == calculateSumOfChars(s2);
    }
  
    private static int calculateSumOfChars(String string) {
        int sumOfCharacters = 0;
        if(string == null) return sumOfCharacters;
        if(string.isEmpty()) return sumOfCharacters;
		
        int length = string.length();
        int charIndex;
        for(int i = 0; i < length; i++) {
            charIndex = string.codePointAt(i);
            if(!((charIndex > 64 & charIndex < 91) ||
                 (charIndex > 96 & charIndex < 123))) return sumOfCharacters;
        }
		
        String upperCase = string.toUpperCase();
        for (int i = 0; i < length; i++) {
            sumOfCharacters += upperCase.codePointAt(i);
        }

        return sumOfCharacters;
    }
}