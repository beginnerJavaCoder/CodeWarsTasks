/*
Original task - https://www.codewars.com/kata/decode-the-morse-code

Part of Series 1/3
This kata is part of a series on the Morse code. 
After you solve this kata, you may move to the next one.

In this kata you have to write a simple Morse code decoder. 
While the Morse code is now mostly superceded by voice and digital data communication channels, 
it still has its use in some applications around the world.
The Morse code encodes every character as a sequence of "dots" and "dashes". 
For example, the letter A is coded as ·−, letter Q is coded as −−·−, 
and digit 1 is coded as ·−−−−. 
The Morse code is case-insensitive, traditionally capital letters are used. 
When the message is written in Morse code, a single space is used to separate the character codes 
and 3 spaces are used to separate words. 
For example, the message HEY JUDE in Morse code is ···· · −·−−   ·−−− ··− −·· ·.

NOTE: Extra spaces before or after the code have no meaning and should be ignored.

In addition to letters, digits and some punctuation, 
there are some special service codes, 
the most notorious of those is the international distress signal SOS 
(that was first issued by Titanic), that is coded as ···−−−···. 
These special codes are treated as single special characters, 
and usually are transmitted as separate words.

Your task is to implement a function 
that would take the morse code as input and return a decoded human-readable string.

For example:

MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. .")
//should return "HEY JUDE"
NOTE: For coding purposes you have to use ASCII characters . and -, not Unicode characters.

The Morse code table is preloaded for you as a dictionary, feel free to use it:
Java: MorseCode.get(".--")

All the test strings would contain valid Morse code, 
so you may skip checking for errors and exceptions. 

Good luck!
 */
 
public class MorseCodeDecoder {

    public static String decode(String morseCode) {
        String[] morseCodeWords = splitToWords(removeUnnecessarySpaces(morseCode));
        StringBuilder humanReadableText = new StringBuilder();

        for (String word : morseCodeWords) {
            String[] letters = splitToLetters(word);
            for (String letter : letters) {
                humanReadableText.append(MorseCode.get(letter));
            }
            humanReadableText.append(" ");
        }

        return humanReadableText.deleteCharAt(humanReadableText.length() - 1).toString();
    }

    private static String removeUnnecessarySpaces(String morseCode) {
        int startIndex = 0;
        int endIndex = morseCode.length() - 1;
        
        while(morseCode.charAt(startIndex) == ' ' && startIndex < endIndex) startIndex++;
        while(morseCode.charAt(endIndex) == ' ' && endIndex > 0) endIndex--;

        return morseCode.substring(startIndex, endIndex + 1);
    }

    private static String[] splitToWords(String morseCode) {
        return morseCode.split("\\s{3}");
    }

    private static String[] splitToLetters(String morseCodeWord) {
        return morseCodeWord.split("\\s");
    }
}