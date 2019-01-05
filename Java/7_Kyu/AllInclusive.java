/*
Original task - https://www.codewars.com/kata/all-inclusive

Input:
a string strng
an array of strings arr
Output of function contain_all_rots(strng, arr):
a boolean true if all rotations of strng are included in arr
false otherwise

Examples:
contain_all_rots(
  "bsjq", ["bsjq", "qbsj", "sjqb", "twZNsslC", "jqbs"]) -> true

contain_all_rots(
  "Ajylvpy", ["Ajylvpy", "ylvpyAj", "jylvpyA", "lvpyAjy", "pyAjylv", "vpyAjyl", "ipywee"]) -> false)
  
Note:
Though not correct in a mathematical sense
we will consider that there are no rotations of strng == ""
and for any array arr: contain_all_rots("", arr) --> true
 */
 
import java.util.List;

public class Rotations {
    public static boolean containAllRots(String strng, List<String> arr) {
        for (int i = 0; i < strng.length(); i++) {
            if(!arr.contains(strng.substring(i) + strng.substring(0, i))) return false;
        }

        return true;
    }
}