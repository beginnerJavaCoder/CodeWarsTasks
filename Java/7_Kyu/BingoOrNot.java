/*
Original task - https://www.codewars.com/kata/bingo-or-not

For this game of BINGO, you will receive a single array of 10 numbers from 1 to 26 as an input.
Duplicate numbers within the array are possible.
Each number corresponds to their alphabetical order letter (e.g. 1 = A. 2 = B, etc).
Write a function where you will win the game if your numbers can spell "BINGO".
They do not need to be in the right order in the input array.
Otherwise you will lose. Your outputs should be "WIN" or "LOSE" respectively.
 */
 
public class BingoOrNot {
    public static String bingo(int[] numberArray){
        boolean b, i, n, g, o;
        b = i = n = g = o = false;

        for (int num : numberArray) {
            switch (num) {
                case 2 : {
                    b = true;
                    break;
                }
                case 9 : {
                    i = true;
                    break;
                }
                case 14 : {
                    n = true;
                    break;
                }
                case 7 : {
                    g = true;
                    break;
                }
                case 15 : {
                    o = true;
                }
            }
        }

        return b && i && n && g && o ? "WIN" : "LOSE";
    }
}