/*
Original task - https://www.codewars.com/kata/maximum-length-difference

You are given two arrays a1 and a2 of strings.
Each string is composed with letters from a to z.
Let x be any string in the first array and y be any string in the second array.

Find max(abs(length(x) − length(y)))

If a1 and/or a2 are empty return -1;

#Example:
a1 = ["hoqq", "bbllkw", "oox", "ejjuyyy", "plmiis", "xxxzgpsssa", "xxwwkktt", "znnnnfqknaz", "qqquuhii", "dvvvwz"]
a2 = ["cccooommaaqqoxii", "gggqaffhhh", "tttoowwwmmww"]
mxdiflg(a1, a2) --> 13
 */
 
class MaxDiffLength {
    public static int mxdiflg(String[] a1, String[] a2) {
        if(a1.length == 0 || a2.length == 0) return -1;
        int maxDiff = 0;
        for (String x : a1) {
            for (String y : a2) {
                int diff = Math.abs(x.length() - y.length());
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
        }

        return maxDiff;
    }
}