/*
Original task - https://www.codewars.com/kata/a-needle-in-the-haystack

Can you find the needle in the haystack?
Write a function findNeedle() that takes an array full of junk but containing one "needle"
After your function finds the needle it should return a message (as a string) that says:
"found the needle at position " plus the index it found the needle, so:

findNeedle(new Object[] {"hay", "junk", "hay", "hay", "moreJunk", "needle", "randomJunk"})
should return
"found the needle at position 5"
 */
 
public class Kata {
    public static String findNeedle(Object[] haystack) {
        for(int i = 0; i < haystack.length; i++)
            if(haystack[i] != null && haystack[i].equals("needle"))
                return "found the needle at position " + i;
                
        return "";
    }
}