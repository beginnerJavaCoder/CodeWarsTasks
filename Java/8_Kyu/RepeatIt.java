/*
Original task - https://www.codewars.com/kata/repeatit

Create a function that takes a string and an integer (n).
The function should return a string that repeats the input string n number of times.
If anything other than a string is passed in you should return "Not a string"

Example
"Hi", 2 --> "HiHi"
1234, 5 --> "Not a string"
 */
 
public class RepeatIt {
    public static String repeatString(final Object toRepeat, final int n) {
        String input;
        try {
        input = (String) toRepeat;
        } catch(ClassCastException e) {
            return "Not a string";
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < n; i++)
            result.append(input);
            
        return result.toString();
    }
}