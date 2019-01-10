/*
Original task - https://www.codewars.com/kata/vowel-count

Return the number (count) of vowels in the given string.
We will consider a, e, i, o, and u as vowels for this Kata.
The input string will only consist of lower case letters and/or spaces.
 */
 
public class Vowels {
    public static int getCount(String str) {
        int vowelsCount = 0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            for (char vowel : vowels) {
                if (letter == vowel) {
                    vowelsCount++;
                    break;
                }
            }
        }

        return vowelsCount;
    }
}