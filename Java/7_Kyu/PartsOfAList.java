/*
Original task - https://www.codewars.com/kata/parts-of-a-list

Write a function partlist that gives all the ways to divide a list (an array) 
of at least two elements into two non-empty parts.

Each two non empty parts will be in a pair.
Each part will be in a string.
Elements of a pair must be in the same order as in the original array.
Example:
a = ["az", "toto", "picaro", "zone", "kiwi"] -->
[["az", "toto picaro zone kiwi"], 
["az toto", "picaro zone kiwi"], 
["az toto picaro", "zone kiwi"], 
["az toto picaro zone", "kiwi"]]
 */
 
public class Partlist {
    public static String[][] partlist(String[] arr) {
        String[][] result = new String[arr.length - 1][2];
        StringBuilder tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i == 0) {
                result[i][0] = arr[i];
            } else {
                result[i][0] = result[i-1][0].concat(" ").concat(arr[i]);
            }
            tmp = new StringBuilder("");
            for (int j = i + 1; j < arr.length; j++) {
                tmp.append(arr[j]).append(" ");
            }
            result[i][1] = tmp.substring(0, tmp.length()-1);
        }

        return result;
    }
}