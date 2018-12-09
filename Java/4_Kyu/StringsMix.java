/*
Original task - https://www.codewars.com/kata/strings-mix

Given two strings s1 and s2, we want to visualize how different the two strings are.
We will only take into account the lowercase letters (a to z).
First let us count the frequency of each lowercase letters in s1 and s2.

s1 = "A aaaa bb c"
s2 = "& aaa bbb c d"

s1 has 4 'a', 2 'b', 1 'c'
s2 has 3 'a', 3 'b', 1 'c', 1 'd'

So the maximum for 'a' in s1 and s2 is 4 from s1; the maximum for 'b' is 3 from s2. 
In the following we will not consider letters when the maximum of their occurrences is less than or equal to 1.
We can resume the differences between s1 and s2 in the following string: "1:aaaa/2:bbb" 
where 1 in 1:aaaa stands for string s1 and aaaa because the maximum for a is 4. 
In the same manner 2:bbb stands for string s2 and bbb because the maximum for b is 3.

The task is to produce a string in which each lowercase letters of s1 or s2 appears as many times 
as its maximum if this maximum is strictly greater than 1; 
these letters will be prefixed by the number of the string where they appear with their maximum value and :.
If the maximum is in s1 as well as in s2 the prefix is =:.
In the result, substrings (a substring is for example 2:nnnnn or 1:hhh; it contains the prefix) 
will be in decreasing order of their length and when they have the same length sorted in ascending lexicographic order 
(letters and digits - more precisely sorted by codepoint); 
the different groups will be separated by '/'. See examples and "Example Tests".

Examples:
s1 = "my&friend&Paul has heavy hats! &"
s2 = "my friend John has many many friends &"
mix(s1, s2) --> "2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"

s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
mix(s1, s2) --> "1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"

s1="Are the kids at home? aaaaa fffff"
s2="Yes they are here! aaaaa fffff"
mix(s1, s2) --> "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh"
 */
 
public class Mixing {
    private static final String SEPARATOR_FIRST = "1:";
    private static final String SEPARATOR_SECOND = "2:";
    private static final String SEGMENT_SEPARATOR = "/";
    private static final String EQUALS_SEPARATOR = "=:";
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static StringBuilder result;
    
    public static String mix(String s1, String s2) {
        result = new StringBuilder();
        int[] s1LettersCount = countLettersInString(s1);
        int[] s2LettersCount = countLettersInString(s2);

        int currentCountOfLetters = 0;
        StringBuilder equalString = new StringBuilder("");
        while(true){
            int indexMaxInS1 = getIndexOfMaxElement(s1LettersCount);
            int indexMaxInS2 = getIndexOfMaxElement(s2LettersCount);

            int currentMax = Math.max(s1LettersCount[indexMaxInS1], s2LettersCount[indexMaxInS2]);
            if(currentCountOfLetters == 0) {
                currentCountOfLetters = currentMax;
            }
            if(currentCountOfLetters > currentMax) {
                result.append(equalString);
                equalString = new StringBuilder("");
                currentCountOfLetters = currentMax;
            }

            if (s1LettersCount[indexMaxInS1] < 2 && s2LettersCount[indexMaxInS2] < 2) break;

            if (indexMaxInS1 == indexMaxInS2) {
                if (s1LettersCount[indexMaxInS1] > s2LettersCount[indexMaxInS2]) {
                    result.append(SEPARATOR_FIRST);
                    appendLetters(indexMaxInS1, s1LettersCount[indexMaxInS1]);
                } else if (s2LettersCount[indexMaxInS2] > s1LettersCount[indexMaxInS1]) {
                    result.append(SEPARATOR_SECOND);
                    appendLetters(indexMaxInS2, s2LettersCount[indexMaxInS2]);
                } else {
                    equalString.append(EQUALS_SEPARATOR);
                    for (int i = s1LettersCount[indexMaxInS1]; i > 0; i--) {
                        equalString.append(alphabet.charAt(indexMaxInS1));
                    }
                    equalString.append(SEGMENT_SEPARATOR);
                }
                s1LettersCount[indexMaxInS1] = 0;
                s2LettersCount[indexMaxInS2] = 0;
            }
            else {
                if(s1LettersCount[indexMaxInS1] > s2LettersCount[indexMaxInS2]) {
                    result.append(SEPARATOR_FIRST);
                    appendLetters(indexMaxInS1, s1LettersCount[indexMaxInS1]);
                    s1LettersCount[indexMaxInS1] = 0;
                    s2LettersCount[indexMaxInS1] = 0;
                } else if (s2LettersCount[indexMaxInS2] > s1LettersCount[indexMaxInS1]) {
                    result.append(SEPARATOR_SECOND);
                    appendLetters(indexMaxInS2, s2LettersCount[indexMaxInS2]);
                    s1LettersCount[indexMaxInS2] = 0;
                    s2LettersCount[indexMaxInS2] = 0;
                } else {
                    if(s1LettersCount[indexMaxInS1] == s2LettersCount[indexMaxInS1]) {
                        equalString.append(EQUALS_SEPARATOR);
                        for (int i = s1LettersCount[indexMaxInS1]; i > 0; i--) {
                            equalString.append(alphabet.charAt(indexMaxInS1));
                        }
                        equalString.append(SEGMENT_SEPARATOR);
                    } else {
                        result.append(SEPARATOR_FIRST);
                        appendLetters(indexMaxInS1, s1LettersCount[indexMaxInS1]);

                    }
                    s1LettersCount[indexMaxInS1] = 0;
                    s2LettersCount[indexMaxInS1] = 0;
                }
            }
        }
		
        if(!result.toString().equals("")) result.deleteCharAt(result.length()-1);
        return result.toString();
    }
    
    private static int[] countLettersInString(String s) {
        int length = s.length();
        int[] result = new int[26];
        int tmp;
        for (int i = 0; i < length; i++) {
            tmp = s.codePointAt(i);
            if (tmp > 96 && tmp < 123) {
                result[tmp - 97]++;
            }
        }

        return result;
    }
    
    private static int getIndexOfMaxElement(int[] arr) {
        int length = arr.length;
        int indexOfMaxElement = 0;
        for (int i = 1; i < length; i++) {
            if(arr[i] > arr[indexOfMaxElement]) indexOfMaxElement = i;
        }

        return indexOfMaxElement;
    }
    
    private static void appendLetters(int indexInAlphabet, int countOfRepeat) {
        for (int i = countOfRepeat; i > 0; i--) {
            result.append(alphabet.charAt(indexInAlphabet));
        }
        result.append(SEGMENT_SEPARATOR);
    }
}