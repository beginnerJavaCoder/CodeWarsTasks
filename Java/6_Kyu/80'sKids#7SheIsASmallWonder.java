/*
Original task - https://www.codewars.com/kata/80-s-kids-number-7-shes-a-small-wonder

Vicky is quite the small wonder.
Most people don't even realize she's not a real girl, but a robot living amongst us.
Sure, if you stick around her home for a while you might see her creator open up her
back and make a few tweaks and even see her recharge in the closet instead of sleeping in a bed.

In this kata, we're going to help Vicky keep track of the words she's learning.
Write a function, learnWord(word) which is a method of the Robot object.
The function should report back whether the word is now stored, or if she already knew the word.

Example:
Robot vicky = new Robot();
vicky.learnWord("hello") -> "Thank you for teaching me hello"
vicky.learnWord("abc") -> "Thank you for teaching me abc"
vicky.learnWord("hello") -> "I already know the word hello"
vicky.learnWord("wow!") -> "I do not understand the input"
Case shouldn't matter. Only alpha characters are valid. There's also a little trick here. Enjoy!
 */
 
import java.util.ArrayList;

public class Robot {
    private ArrayList<String> dictionary;
    
    public Robot() {
        dictionary = new ArrayList<>();
        dictionary.add("i");
        dictionary.add("do");
        dictionary.add("not");
        dictionary.add("understand");
        dictionary.add("the");
        dictionary.add("input");
        dictionary.add("already");
        dictionary.add("know");
        dictionary.add("word");
        dictionary.add("thank");
        dictionary.add("you");
        dictionary.add("for");
        dictionary.add("teaching");
        dictionary.add("me");
    }
    
    public String learnWord(String word) {
        String ignoreCase = word.toLowerCase();
        if(isNotAlphaString(ignoreCase)) return "I do not understand the input";
        if(dictionary.contains(ignoreCase)) return "I already know the word ".concat(word);
        dictionary.add(ignoreCase);
        return "Thank you for teaching me ".concat(word);
    }

    private static boolean isNotAlphaString(String string) {
        int length = string.length();
        if(length == 0) return true;
        for (int i = 0; i < length; i++) {
            if(!(string.codePointAt(i) > 96 && string.codePointAt(i) < 123)) return true;
        }

        return false;
    }
}