/*
Original task - https://www.codewars.com/kata/alphabetical-addition

Your task is to add up letters to one letter.
The function will be given an array of single character Strings, each one being a letter to add.

Notes:

Letters will always be lowercase.
Letters can overflow (see second to last example of the description)
If no letters are given, the function should return 'z'

Examples:

addLetters("a", "b", "c") = "f"
addLetters("a", "b") = "c"
addLetters("z") = "z"
addLetters("z", "a") = "a"
addLetters("y", "c", "b") = "d" // notice the letters overflowing
addLetters() = "z"
 */
 
public class Kata {
    public static String addLetters(String... letters) {
        if(letters.length == 0) return "z";

        int sumOfCodePoints = 0;
        for (String letter : letters) {
            sumOfCodePoints += letter.codePointAt(0) - 96;
        }

        while (sumOfCodePoints > 26) {
            sumOfCodePoints -= 26;
        }

        return String.valueOf((char) (sumOfCodePoints + 96));
    }
}
