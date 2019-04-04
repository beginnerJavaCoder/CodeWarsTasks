/*
Original task - https://www.codewars.com/kata/reversed-words

Complete the solution so that it reverses all of the words within the string passed in.

Example:
ReverseWords.reverseWords("The greatest victory is that which requires no battle");
// should return "battle no requires which that is victory greatest The"
 */
 
public class ReverseWords {
    public static String reverseWords(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for(int i = words.length - 1; i > 0; i--)
            result.append(words[i]).append(" ");
        
        return result.append(words[0]).toString();
    }
}