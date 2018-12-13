/*
Original task - https://www.codewars.com/kata/alternating-case-%3C-equals-%3E-alternating-case

altERnaTIng cAsE <=> ALTerNAtiNG CaSe
Define StringUtils.toAlternatingCase(), 
such that each lowercase letter becomes uppercase and each uppercase letter becomes lowercase. 

For example:
StringUtils.toAlternativeString("hello world") == "HELLO WORLD"
StringUtils.toAlternativeString("HELLO WORLD") == "hello world"
StringUtils.toAlternativeString("hello WORLD") == "HELLO world"
StringUtils.toAlternativeString("HeLLo WoRLD") == "hEllO wOrld"
StringUtils.toAlternativeString("12345") == "12345" // Non-alphabetical characters are unaffected
StringUtils.toAlternativeString("1a2b3c4d5e") == "1A2B3C4D5E"
StringUtils.toAlternativeString("StringUtils.toAlternatingCase") == "sTRINGuTILS.TOaLTERNATINGcASE"
As usual, your method should be pure, i.e. it should not mutate the original string.
 */
 
public class StringUtils { 
    public static String toAlternativeString(String string) {
        StringBuilder result = new StringBuilder();
        for(char tmp : string.toCharArray()) {
            if(tmp > 64 & tmp < 91) {
            result.append((char) (tmp + 32));
            continue;
            }
            if(tmp > 96 & tmp < 123) {
            result.append((char) (tmp - 32));
            continue;
            }
            result.append(tmp);
        }
		
        return result.toString();
    }
}