/*
Original task - https://www.codewars.com/kata/is-it-a-number

Given a string s, write a method that will return true if its 
a valid single integer or floating number or false if its not.

Valid examples, should return true:
isDigit("3")
isDigit("  3  ")
isDigit("-3.23")

should return false:
isDigit("3-4")
isDigit("  3   5")
isDigit("3 5")
isDigit("zero")
 */
 
public class MyUtilities {

    public boolean isDigit(String s) {
        boolean isAlreadyDigit = false;
        boolean isAlreadyDouble = false;
        if(s.isEmpty()) return false;
        s = resetSpaces(s);
        for (char c : s.toCharArray()) {
            if(c != 45 & c != 46 & (c < 48 | c > 57)) return false;
            if(c == 45 & isAlreadyDigit) return false;
            if(c == 46 & isAlreadyDouble) return false;
            if(c == 46 & !isAlreadyDouble) isAlreadyDouble = true;
            if (c > 47 & c < 58) {
                if(!isAlreadyDigit) isAlreadyDigit = true;
            }
        }

        return true;
    }
   
    private static String resetSpaces(String s) {
        if(s.length() == 1 & s.charAt(0) == ' ') return " ";
        int startIndex = 0;
        int endIndex = s.length();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' ') break;
            startIndex++;
        }
        for(int i = endIndex - 1; i >= 0; i--) {
            if(s.charAt(i) != ' ') break;
            endIndex--;
        }
		
        return new String(s.toCharArray(), startIndex, endIndex - startIndex);
    }
}