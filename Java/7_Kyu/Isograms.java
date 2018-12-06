/*
Original task - https://www.codewars.com/kata/isograms

An isogram is a word that has no repeating letters, consecutive or non-consecutive. 
Implement a function that determines whether a string that contains only letters is an isogram. 
Assume the empty string is an isogram. Ignore letter case.

isIsogram "Dermatoglyphics" == true
isIsogram "moose" == false
isIsogram "aba" == false
 */
 
import java.util.ArrayList;

public class isogram {
    public static boolean isIsogram(String str) {
        if(str.isEmpty()) return true;
        String tmp = str.toLowerCase();
        ArrayList<Character> arrayList = new ArrayList<>(0);
        for(int i = 0; i < tmp.length(); i++) {
            for(char ch : arrayList) {
                if( tmp.charAt(i) == ch) return false;
            }
            arrayList.add(tmp.charAt(i));
        }
        return true;
    } 
}