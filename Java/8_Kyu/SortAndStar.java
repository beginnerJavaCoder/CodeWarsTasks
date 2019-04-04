/*
Original task - https://www.codewars.com/kata/sort-and-star

You will be given an vector of string(s). 
You must sort it alphabetically (case-sensitive!!) and then return the first value.
The returned value must be a string, and have "***" between each of its letters.
You should not remove or add elements from/to the array.
 */
 
public class SortAndStar {
    public static String twoSort(String[] s) {
        String result = s[0];
        for(int i = 1; i < s.length; i++) {
            if(s[i].compareTo(result) < 0) {
                result = s[i];
            }
        }
        if(result.length() == 1) return result;
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < result.length() - 1; i++) {
            sb.append(result.charAt(i)).append("***");
        }
        
        return sb.append(result.charAt(result.length()-1)).toString();
    }
}