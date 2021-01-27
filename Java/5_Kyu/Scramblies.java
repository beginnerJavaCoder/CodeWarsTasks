/*
Original task - https://www.codewars.com/kata/55c04b4cc56a697bb0000048

Complete the function scramble(str1, str2) that returns true if a portion of str1 characters 
can be rearranged to match str2, otherwise returns false.

Notes:

Only lower case letters will be used (a-z). 
No punctuation or digits will be included.
Performance needs to be considered
Input strings s1 and s2 are null terminated.

Examples
scramble('rkqodlw', 'world') ==> True
scramble('cedewaraaossoqqyt', 'codewars') ==> True
scramble('katas', 'steak') ==> False
 */
 
public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        StringBuilder characters = new StringBuilder(str1);
        int index;
        for (int i = 0; i < str2.length(); i++) {
            index = characters.indexOf(str2.substring(i, i + 1));
            if(index == -1) return false;
            characters.deleteCharAt(index);
        }

        return true;
    }
}
