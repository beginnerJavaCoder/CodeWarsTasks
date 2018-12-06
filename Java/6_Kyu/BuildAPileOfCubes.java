/*
Original task - https://www.codewars.com/kata/build-a-pile-of-cubes

Your task is to construct a building which will be a pile of n cubes. 
The cube at the bottom will have a volume of n^3, 
the cube above will have volume of (n-1)^3 and so on until the top which will have a volume of 1^3.

You are given the total volume m of the building. 
Being given m can you find the number n of cubes you will have to build?

The parameter of the function findNb will be an integer m and you have to return 
the integer n such as n^3 + (n-1)^3 + ... + 1^3 = m if such a n exists or -1 if there is no such n.

Examples:
findNb(1071225) --> 45
findNb(91716553919377) --> -1
 */
 
import java.math.BigInteger;

public class ASum {
	public static long findNb(long m) {
	    String str = "" + m;
        BigInteger n = new BigInteger("1");
        BigInteger volume = new BigInteger("0");
        BigInteger in = new BigInteger(str);
        while (true) {
            volume = volume.add(n.pow(3));
            if(volume.equals(in)) return n.longValue();
            if(in.compareTo(volume) < 0) return -1;
            n = n.add(BigInteger.ONE);
        }
	}
}