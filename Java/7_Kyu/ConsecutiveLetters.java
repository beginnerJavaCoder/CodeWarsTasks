/*
Original task - https://www.codewars.com/kata/5ce6728c939bf80029988b57

In this Kata, we will check if a string contains consecutive letters 
as they appear in the English alphabet and if each letter occurs only once.

Rules are: (1) the letters are adjacent in the English alphabet, and (2) each letter occurs only once.

For example: 
solve("abc") = True, because it contains a,b,c
solve("abd") = False, because a, b, d are not consecutive/adjacent in the alphabet, and c is missing.
solve("dabc") = True, because it contains a, b, c, d
solve("abbc") = False, because b does not occur once.
solve("v") = True
All inputs will be lowercase letters.

More examples in test cases. Good luck!
 */
 
class Solution {
    public static boolean solve(String s) {
        int[] codePoints = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int alphabetPosition = s.charAt(i) - 97;
            if(codePoints[alphabetPosition] > 0) return false;
            else codePoints[alphabetPosition]++;
        }

        int firstSequenceIndex = 0;
        while (codePoints[firstSequenceIndex] != 1) {
            firstSequenceIndex++;
        }
        int lastSequenceIndex = firstSequenceIndex + 1;
        while (lastSequenceIndex < 26 && codePoints[lastSequenceIndex] != 0) {
            lastSequenceIndex++;
        }
        for (int i = lastSequenceIndex + 1; i < codePoints.length; i++) {
            if (codePoints[i] == 1) return false;
        }

        return true;
    }
}
