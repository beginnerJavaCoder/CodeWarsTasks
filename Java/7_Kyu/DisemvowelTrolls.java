/*
Original task - https://www.codewars.com/kata/disemvowel-trolls

Trolls are attacking your comment section!
A common way to deal with this situation is to remove all of the vowels from the trolls' comments, neutralizing the threat.
Your task is to write a function that takes a string and return a new string with all vowels removed.
For example, the string "This website is for losers LOL!" would become "Ths wbst s fr lsrs LL!".
Note: for this kata y isn't considered a vowel.
 */
 
public class Troll {

    private static final char[] vowels = {'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};
    private static final int vowelsQuantity = vowels.length;
	
    public static String disemvowel(String str) {
        StringBuilder result = new StringBuilder();
        int length = str.length();
        char tmp;
        boolean isVowel = false;
        for(int i = 0; i < length; i++) {
            tmp = str.charAt(i);
            for(int j = 0; j < vowelsQuantity; j++)
                if(tmp == vowels[j]) {
                    isVowel = true;
                    break;
                }
            if(isVowel) {
                isVowel = false;
                continue;
            }
            result.append(tmp);
        }
		
        return result.toString();
    }
}