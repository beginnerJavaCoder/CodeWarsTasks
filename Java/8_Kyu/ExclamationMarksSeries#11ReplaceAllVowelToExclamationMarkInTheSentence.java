/*
Original task - https://www.codewars.com/kata/exclamation-marks-series-number-11-replace-all-vowel-to-exclamation-mark-in-the-sentence

Description:
Replace all vowel to exclamation mark in the sentence. aeiouAEIOU is vowel.

Examples
replace("Hi!") === "H!!"
replace("!Hi! Hi!") === "!H!! H!!"
replace("aeiou") === "!!!!!"
replace("ABCDE") === "!BCD!"
 */
 
public class Solution {
    public static String replace(final String s) {
        return s.replaceAll("[aeiouAEIOU]", "!");
    }
}