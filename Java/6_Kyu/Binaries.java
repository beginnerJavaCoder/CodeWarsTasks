/*
Original task - https://www.codewars.com/kata/binaries

Let us take a string composed of decimal digits: "10111213". 
We want to code this string as a string of 0 and 1 and after that be able to decode it.
We decompose the given string in its decimal digits 1 0 1 1 1 2 1 3 and we will code each.

Coding process to code a number n expressed in base 10:

a) Let k be the number of bits of n

b) Put k-1 times the digit 0 followed by the digit 1

c) Put number n in binary

d) Concat the result of b) and c)

So we code 0 as 10, 1 as 11 ... etc...

Repeating this process with the initial string

"10111213" becomes : "11101111110110110111" resulting of concatenation of 11 10 11 11 11 0110 11 0111 .

Task:

Given strng a string of digits representing a decimal number the function code(strng) 
should return the coding of strng as explained above.

Given a string strng resulting from the previous coding, 
decode it to get the corresponding decimal string.

Examples:
code("77338855") --> "001111001111011101110001100000011000001101001101"
code("77338")  --> "0011110011110111011100011000"
code("0011121314") --> "1010111111011011011111001100"

decode("001111001111011101110001100000011000001101001101") -> "77338855"
decode("0011110011110111011100011000") -> "77338"
decode("1010111111011011011111001100") -> "0011121314"
 */
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CodeDecode {

    public static String code(String string) {
        StringBuilder codeSequence = new StringBuilder();
        
        for (int i = 0; i < string.length(); i++) {
            codeSequence.append(getCodeOfDigit(Integer.parseInt(string.substring(i, i + 1))));
        }

        return codeSequence.toString();
    }

    public static String decode(String string) {
        StringBuilder decodeSequence = new StringBuilder();
        Pattern prefix = Pattern.compile("0*1");
        Matcher matcher = prefix.matcher(string);
        
        int start = 0;
        while(matcher.find(start)) {
            String binary = string.substring(matcher.end(), matcher.end() + matcher.group().length());
            Integer digit = Integer.parseInt(binary, 2);
            decodeSequence.append(digit);
            start += binary.length() * 2;
        }

        return decodeSequence.toString();
    }

    private static StringBuilder getCodeOfDigit(Integer digit) {
        StringBuilder codeOfDigit = new StringBuilder();
        String binary = Integer.toBinaryString(digit);

        for (int i = 0; i < binary.length() - 1; i++) {
            codeOfDigit.append(0);
        }

        return codeOfDigit.append(1).append(binary);
    }
}
