/*
Original task - https://www.codewars.com/kata/strip-comments

Complete the solution so that it strips all text that follows any of a set of comment markers passed in. 
Any whitespace at the end of the line should also be stripped out.

Example:
Given an input string of:
apples, pears # and bananas
grapes
bananas !apples

The output expected would be:
apples, pears
grapes
bananas

The code would be called like so:
var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
// result should == "apples, pears\ngrapes\nbananas"
 */
 
import java.util.ArrayList;

public class StripComments {

	public static String stripComments(String text, String[] commentSymbols) {
	    StringBuilder output = new StringBuilder();
        ArrayList<String> list = getSubstrings(text);
        for (String line : list) {
            output.append(clearLine(line, commentSymbols)).append('\n');
        }
        output.deleteCharAt(output.length()-1);
        if(isAllSpaces(output)) return "";
        return output.toString();
	}
  
    private static ArrayList<String> getSubstrings(String text) {
        StringBuilder input = new StringBuilder(text);
        ArrayList<String> result = new ArrayList<>();
        int index;
        while (true){
            index = input.indexOf("\n");
            if(index == -1) break;
            result.add(input.substring(0, index));
            input.delete(0, index+1);
        }
        result.add(input.toString());
        return result;
    }
  
    private static StringBuilder clearLine(String line, String[] commentSymbols) {
        StringBuilder result = new StringBuilder();
        int length = line.length();
        int quantityOfMarks = commentSymbols.length;
        char tmp;
        boolean isLegal = true;
        for(int i = 0; i < length; i++) {
            tmp = line.charAt(i);
            for(int j = 0; j < quantityOfMarks; j++) {
                if(tmp == commentSymbols[j].charAt(0)) {
                    isLegal = false;
                    break;
                }
            }
            if(isLegal) result.append(tmp);
            else break;
        }

        if(result.length() != 0 && result.charAt(result.length()-1) == ' ') result.deleteCharAt(result.length()-1);

        return result;
    }
  
    private static boolean isAllSpaces(StringBuilder str) {
        int length = str.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if(str.charAt(i) == ' ') count++;
        }
        if(count == length) return true;
        return false;
    }	
}