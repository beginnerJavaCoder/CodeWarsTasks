/*
Original task - https://www.codewars.com/kata/sort-my-textbooks

HELP! Jason can't find his textbook! 
It is two days before the test date, and Jason's textbooks are all out of order! 
Help him sort a list full of textbooks by subject, so he can study before the test.
The sorting should NOT be case sensitive.
 */
 
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

class sorter {
    public static List<String> sort(List<String> textbooks) {
        Collections.sort(textbooks, new Comparator<String>() {
            public int compare(String s1, String s2) {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
            }
        });
        
        return textbooks;
    }
}