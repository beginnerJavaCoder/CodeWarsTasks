/*
Original task - https://www.codewars.com/kata/cwars

Normally we have firstname, middle and the last name but there may be more than one word in a name . 
Like a South indian one .
Similar to those kinda names we need to initiallize the names .
See the pattern Below
initials('code wars') => returns C.Wars
initials('Barack Hussain obama') => returns B.H.Obama
Complete the function initials.
 */
 
class CWars {
    public static String initials(String name) {
        StringBuilder result = new StringBuilder();
        String[] words = name.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            result.append(words[i].substring(0,1).toUpperCase()).append(".");
        }
        result.append(words[words.length-1].substring(0,1).toUpperCase()).append(words[words.length-1].substring(1));
        
        return result.toString();
    }
}