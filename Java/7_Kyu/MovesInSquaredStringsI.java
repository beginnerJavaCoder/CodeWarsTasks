/*
Original task - https://www.codewars.com/kata/moves-in-squared-strings-i

This kata is the first of a sequence of four about "Squared Strings".
You are given a string of n lines, each substring being n characters long:

For example:
s = "abcd\nefgh\nijkl\nmnop"

We will study some transformations of this square of strings.
Vertical mirror: vertMirror
vertMirror(s) => "dcba\nhgfe\nlkji\nponm"
Horizontal mirror: hor_mirror (or horMirror or hor-mirror)
hor_mirror(s) => "mnop\nijkl\nefgh\nabcd"
or printed:

vertical mirror | horizontal mirror
abcd --> dcba   | abcd --> mnop
efgh     hgfe   | efgh     ijkl
ijkl     lkji   | ijkl     efgh
mnop     ponm   | mnop     abcd

#Task:
Write these two functions and
high-order function oper(fct, s) where
fct is the function of one variable f to apply to the string s (fct will be one of vertMirror, horMirror)
#Examples:
s = "abcd\nefgh\nijkl\nmnop"
oper(vert_mirror, s) => "dcba\nhgfe\nlkji\nponm"
oper(hor_mirror, s) => "mnop\nijkl\nefgh\nabcd"
Forthcoming katas will study other tranformations.
 */
 
interface Expression {
    String mirror(String s);
}

class Opstrings {
    
    public static String vertMirror (String string) {
        String[] substrings = string.split("\n");
        StringBuilder result = new StringBuilder();
        for (String s : substrings) {
            result.append(new StringBuilder(s).reverse().toString()).append('\n');
        }
        result.deleteCharAt(result.length()-1);

        return result.toString();
    }

    public static String horMirror (String string) {
        String[] substrings = string.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = substrings.length - 1; i >= 0; i--) {
            result.append(substrings[i]).append('\n');
        }
        result.deleteCharAt(result.length()-1);

        return result.toString();
    }

    public static String oper(Expression func, String s) {
        return func.mirror(s);
    }
}