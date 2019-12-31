/*
Original task - https://www.codewars.com/kata/unfinished-loop-bug-fixing-number-1

Oh no, Timmy's created an infinite loop! 
Help Timmy find and fix the bug in his unfinished For Loop!
 */
 
import java.util.*;

class Kata {
    public static List CreateList(int number) {
        List list = new ArrayList(); 
        for(int count = 1; count <= number; count++) {
            list.add(count);
        }
    
        return list;
    }
}