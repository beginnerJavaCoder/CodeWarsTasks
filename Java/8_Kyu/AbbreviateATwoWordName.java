/*
Original task - https://www.codewars.com/kata/abbreviate-a-two-word-name

Write a function to convert a name into initials. 
This kata strictly takes two words with one space in between them.
The output should be two capital letters with a dot seperating them.
It should look like this:
Sam Harris => S.H
Patrick Feeney => P.F
 */
 
public class AbbreviateTwoWords {
    public static String abbrevName(String name) {
        String[] initials = name.toUpperCase().split(" ");
        return initials[0].charAt(0) + "." + initials[1].charAt(0);
    }
}