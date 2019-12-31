/*
Original task - https://www.codewars.com/kata/decode-the-morse-code-advanced

Part of Series 2/3
This kata is part of a series on the Morse code. 
Make sure you solve the previous, part before you try this one. 
After you solve this kata, you may move to the next one.

In this kata you have to write a Morse code decoder for wired electrical telegraph.
Electric telegraph is operated on a 2-wire line with a key that, 
when pressed, connects the wires together, which can be detected on a remote station. 
The Morse code encodes every character being transmitted as a sequence of "dots" 
(short presses on the key) and "dashes" (long presses on the key).

When transmitting the Morse code, the international standard specifies that:

"Dot" – is 1 time unit long.
"Dash" – is 3 time units long.
Pause between dots and dashes in a character – is 1 time unit long.
Pause between characters inside a word – is 3 time units long.
Pause between words – is 7 time units long.

However, the standard does not specify how long that "time unit" is. 
And in fact different operators would transmit at different speed. 
An amateur person may need a few seconds to transmit a single character, 
a skilled professional can transmit 60 words per minute, and robotic transmitters may go way faster.

For this kata we assume the message receiving is performed automatically 
by the hardware that checks the line periodically, 
and if the line is connected (the key at the remote station is down), 
1 is recorded, and if the line is not connected (remote key is up), 0 is recorded. 
After the message is fully received, it gets to you for decoding as a string containing only symbols 0 and 1.

For example, the message HEY JUDE, that is ···· · −·−− ·−−− ··− −·· · may be received as follows:

1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011

As you may see, this transmission is perfectly accurate according to the standard, 
and the hardware sampled the line exactly two times per "dot".

That said, your task is to implement two functions:

Function decodeBits(bits), that should find out the transmission rate of the message, 
correctly decode the message to dots ., dashes - and spaces (one between characters, three between words) 
and return those as a string. 
Note that some extra 0's may naturally occur at the beginning and the end of a message, make sure to ignore them. 
Also if you have trouble discerning if the particular sequence of 1's is a dot or a dash, assume it's a dot.

Function decodeMorse(morseCode), that would take the output of the previous function and return a human-readable string.

NOTE: For coding purposes you have to use ASCII characters . and -, not Unicode characters.

The Morse code table is preloaded for you as MORSE_CODE dictionary; 
in Java MorseCode class is provided; Feel free to use this preload.

All the test strings would be valid to the point that they could be reliably decoded as described above, 
so you may skip checking for errors and exceptions, just do your best in figuring out what the message is!

Good luck!
 */
 
public class MorseCodeDecoder {

    public static String decodeBits(String bits) {
        String clearBits = deleteUnnecessaryZeros(bits);
        int bitsPerUnit = getQuantityOfBitsPerUnit(clearBits);
        
        clearBits = clearBits.replaceAll("0000000".repeat(bitsPerUnit), "   ");
        clearBits = clearBits.replaceAll("000".repeat(bitsPerUnit), " ");
        clearBits = clearBits.replaceAll("111".repeat(bitsPerUnit), "-");
        clearBits = clearBits.replaceAll("1".repeat(bitsPerUnit), ".");
        clearBits = clearBits.replaceAll("0".repeat(bitsPerUnit), "");

        return clearBits;
    }

    private static String deleteUnnecessaryZeros(String bits) {
        int startIndex = 0;
        int endIndex = bits.length() - 1;

        while(bits.charAt(startIndex) == '0' && startIndex < endIndex) startIndex++;
        while(bits.charAt(endIndex) == '0' && endIndex > 0) endIndex--;

        return startIndex > endIndex ? "" : bits.substring(startIndex, endIndex + 1);
    }

    private static int getQuantityOfBitsPerUnit(String bits) {
        int minSubstringLength = bits.length();
        int tmpLength;

        for (int i = 0; i < bits.length(); i++) {
            tmpLength = getLengthOfSubstring(bits, i, bits.charAt(i));
            if (minSubstringLength > tmpLength) {
                minSubstringLength = tmpLength;
            }
            i += tmpLength - 1;
        }

        return minSubstringLength;
    }

    private static int getLengthOfSubstring(String string, int start, char character) {
        int i = start;
        while (i < string.length() && string.charAt(i) == character) {
            i++;
        }

        return i - start;
    }

    public static String decodeMorse(String morseCode) {
        String[] morseCodeWords = splitToWords(morseCode.trim());
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

    private static String[] splitToWords(String morseCode) {
        return morseCode.split("\\s{3}");
    }

    private static String[] splitToLetters(String morseCodeWord) {
        return morseCodeWord.split("\\s");
    }
}