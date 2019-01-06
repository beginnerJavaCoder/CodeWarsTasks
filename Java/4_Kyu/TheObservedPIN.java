/*
Original task - https://www.codewars.com/kata/the-observed-pin

Alright, detective, one of our colleagues successfully observed our target person, Robby the robber.
We followed him to a secret warehouse, where we assume to find all the stolen stuff.
The door to this warehouse is secured by an electronic combination lock.
Unfortunately our spy isn't sure about the PIN he saw, when Robby entered it.

The keypad has the following layout:
┌───┬───┬───┐
│ 1 │ 2 │ 3 │
├───┼───┼───┤
│ 4 │ 5 │ 6 │
├───┼───┼───┤
│ 7 │ 8 │ 9 │
└───┼───┼───┘
    │ 0 │
    └───┘
He noted the PIN 1357, but he also said, it is possible that each of the digits he saw
could actually be another adjacent digit (horizontally or vertically, but not diagonally).
E.g. instead of the 1 it could also be the 2 or 4. And instead of the 5 it could also be the 2, 4, 6 or 8.
He also mentioned, he knows this kind of locks.
You can enter an unlimited amount of wrong PINs, they never finally lock the system or sound the alarm.
That's why we can try out all possible (*) variations.
* possible in sense of: the observed PIN itself and all variations considering the adjacent digits.
Can you help us to find all those variations?
It would be nice to have a function, that returns a list of all variations for
an observed PIN with a length of 1 to 8 digits.
We could name the function getPINs.
But please note that all PINs, the observed one and also the results,
must be strings, because of potentially leading '0's.
Detective, we count on you!
 */
 
import java.util.ArrayList;
import java.util.List;

public class ObservedPin {

    private static final int[][] possibleDigits = new int[][]{
            {0, 8},
            {1, 2, 4},
            {1,2,3,5},
            {2,3,6},
            {1,4,5,7},
            {2,4,5,6,8},
            {3,5,6,9},
            {4,7,8},
            {5,7,8,9,0},
            {6,8,9}};

    public static List<String> getPINs(String observed) {
        int PINsQuantity = getQuantityOfPINs(observed);
        ArrayList<StringBuilder> PINs = new ArrayList<>();
        init(PINs, observed, PINsQuantity);
        fill(PINs, observed, PINsQuantity);

        return fromSBtoS(PINs);
    }

    private static int getQuantityOfPINs(String observed) {
        int PINsQuantity = 1;
        for (int i = 0; i < observed.length(); i++) {
            PINsQuantity *= possibleDigits[Integer.parseInt(observed.substring(i,i+1))].length;
        }

        return PINsQuantity;
    }

    private static void init(ArrayList<StringBuilder> PINs, String observed, int PINsQuantity) {
        int digit = Integer.parseInt(observed.substring(0,1));
        int segmentOfSameDigits = PINsQuantity / possibleDigits[digit].length;
        for (int i = 0; i < possibleDigits[digit].length; i++) {
            for (int j = 0; j < segmentOfSameDigits; j++) {
                PINs.add(j + i * segmentOfSameDigits, new StringBuilder("" + possibleDigits[digit][i]));
            }
        }
    }

    private static void fill(ArrayList<StringBuilder> PINs, String observed, int PINsQuantity) {
        int previousSegmentLength = PINsQuantity;
        for (int i = 1; i < observed.length(); i++) {
            int digit = Integer.parseInt(observed.substring(i,i+1));
            int currentSegmentLength = previousSegmentLength / possibleDigits[Integer.parseInt(observed.substring(i-1,i+1-1))].length;
            int lengthOfSameSegments = currentSegmentLength / possibleDigits[digit].length;
            for (int j = 0; j < PINsQuantity/currentSegmentLength; j++) {
                int shift = j * currentSegmentLength;
                for (int digit0 = 0; digit0 < possibleDigits[digit].length; digit0++) {
                    int digitsShift = digit0 * lengthOfSameSegments;
                    for (int k = 0; k < lengthOfSameSegments; k++) {
                        PINs.get(k + digitsShift + shift).append(possibleDigits[digit][digit0]);
                    }
                }
            }
            previousSegmentLength = currentSegmentLength;
        }
    }

    private static ArrayList<String> fromSBtoS(ArrayList<StringBuilder> listSB) {
        ArrayList<String> list = new ArrayList<>();
        for (StringBuilder s : listSB) {
            list.add(s.toString());
        }

        return list;
    }
}