/*
Original task - https://www.codewars.com/kata/reverse-or-rotate

The input is a string str of digits.
Cut the string into chunks (a chunk here is a substring of the initial string)
of size sz (ignore the last chunk if its size is less than sz).
If a chunk represents an integer such as the sum of the cubes of its digits is divisible by 2, reverse that chunk;
otherwise rotate it to the left by one position. Put together these modified chunks and return the result as a string.

If
sz is <= 0 or if str is empty return ""
sz is greater (>) than the length of str it is impossible to take a chunk of size sz hence return "".

Examples:
revrot("123456987654", 6) --> "234561876549"
revrot("123456987653", 6) --> "234561356789"
revrot("66443875", 4) --> "44668753"
revrot("66443875", 8) --> "64438756"
revrot("664438769", 8) --> "67834466"
revrot("123456779", 8) --> "23456771"
revrot("", 8) --> ""
revrot("123456779", 0) --> ""
revrot("563000655734469485", 4) --> "0365065073456944"
 */
 
class RevRot {
    public static String revRot(String string, int sz) {
        int length = string.length();
        if(sz <= 0)     return "";
        if(length == 0) return "";
        if(sz > length) return "";

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i += sz) {
            int end = sz + i;
            if (end > length) break;
            StringBuilder chunk = new StringBuilder(string.substring(i, end));

            int sumOfCubes = 0;
            for (int j = 0; j < sz; j++) {
                sumOfCubes += Math.pow(chunk.charAt(j) - 48, 3);
            }

            if(sumOfCubes % 2 == 0) result.append(chunk.reverse());
            else result.append(chunk.substring(1, sz)).append(chunk.charAt(0));
        }

        return result.toString();
    }
}