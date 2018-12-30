/*
Original task - https://www.codewars.com/kata/easy-line

In the drawing below we have a part of the Pascal's triangle, lines are numbered from zero (top).
We want to calculate the sum of the squares of the binomial coefficients on a given line
with a function called easyLine.
Can you write a program which calculate easyLine(n) where n is the line number?
The function will take n (with: n >= 0) as parameter and
will return the sum of the squares of the binomial coefficients on line n.

Examples:

easyline(0) => 1
easyline(1) => 2
easyline(4) => 70
easyline(50) => 100891344545564193334812497256
 */
 
import java.math.BigInteger;

public class Easyline {
    public static BigInteger easyLine(int n) {
        BigInteger result = new BigInteger("1");
        BigInteger nextValueInARow = new BigInteger("1");
        for(int i = 1; i <= n; i++) {
            nextValueInARow = nextValueInARow.multiply(new BigInteger((n + 1 - i) + "")).divide(new BigInteger(i + ""));
            result = result.add(new BigInteger(nextValueInARow + "").pow(2));
        }

        return result;
    }
}