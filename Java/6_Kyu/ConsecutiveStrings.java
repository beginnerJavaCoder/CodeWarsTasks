/*
Original task - https://www.codewars.com/kata/56a5d994ac971f1ac500003e

You are given an array(list) strarr of strings and an integer k. 
Your task is to return the first longest string consisting of k consecutive strings taken in the array.

Examples:
strarr = ["tree", "foling", "trashy", "blue", "abcdef", "uvwxyz"], k = 2

Concatenate the consecutive strings of strarr by 2, we get:

treefoling   (length 10)  concatenation of strarr[0] and strarr[1]
folingtrashy ("      12)  concatenation of strarr[1] and strarr[2]
trashyblue   ("      10)  concatenation of strarr[2] and strarr[3]
blueabcdef   ("      10)  concatenation of strarr[3] and strarr[4]
abcdefuvwxyz ("      12)  concatenation of strarr[4] and strarr[5]

Two strings are the longest: "folingtrashy" and "abcdefuvwxyz".
The first that came is "folingtrashy" so 
longest_consec(strarr, 2) should return "folingtrashy".

In the same way:
longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) --> "abigailtheta"
n being the length of the string array, if n = 0 or k > n or k <= 0 return "".

Note
consecutive strings : follow one after another without an interruption
 */
 
class LongestConsec {
  
    public static String longestConsec(String[] strArr, int k) {
        if(k < 1 || strArr.length < k) return "";

        int[] lengthsArr = getArrayOfLengths(strArr);
        int maxConsecStartIndex = getMaxConsecStartIndex(lengthsArr, k);

        return getLongestConsecution(strArr, maxConsecStartIndex, k);
    }

    private static int[] getArrayOfLengths(String[] strArr) {
        int[] lengthsArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            lengthsArr[i] = strArr[i].length();
        }

        return lengthsArr;
    }

    private static int getMaxConsecStartIndex(int[] lengthsArr, int k) {
        int maxConsecLength = 0;
        int maxConsecStartIndex = 0;
        for (int i = 0; i < lengthsArr.length - k + 1; i++) {
            int currentConsecLength = 0;
            for (int j = i; j < i + k; j++) {
                currentConsecLength += lengthsArr[j];
            }
            if (currentConsecLength > maxConsecLength) {
                maxConsecLength = currentConsecLength;
                maxConsecStartIndex = i;
            }
        }

        return maxConsecStartIndex;
    }

    private static String getLongestConsecution(String[] strArr, int maxConsecStartIndex, int k) {
        StringBuilder result = new StringBuilder();
        for (int i = maxConsecStartIndex; i < maxConsecStartIndex + k; i++) {
            result.append(strArr[i]);
        }

        return result.toString();
    }
}
