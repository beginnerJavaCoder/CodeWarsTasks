/*
Original task - https://www.codewars.com/kata/next-bigger-number-with-the-same-digits

You have to create a function that takes a positive integer number
and returns the next bigger number formed by the same digits:
12 ==> 21
513 ==> 531
2017 ==> 2071

If no bigger number can be composed using those digits, return -1:
9 ==> -1
111 ==> -1
531 ==> -1
 */
 
public class Kata {

    public static long nextBiggerNumber(long n) {
        long result = getNext(n);
        return n == result ? -1 : result;
    }

    private static long getNext(long source) {
        StringBuilder resultStr = new StringBuilder("" + source);
        int length = resultStr.length();
        if(length < 2) return source;
        int[] capacity = getCapacity(resultStr, length);

        for (int i = length - 2; i >= 0; i--) {
            int currentValue = capacity[i];
            int indexPossibleSwap = -1;
            for (int j = i + 1; j < length; j++) {
                if (capacity[j] > currentValue) {
                    if (indexPossibleSwap == -1) indexPossibleSwap = j;
                    else if(capacity[j] < capacity[indexPossibleSwap]) {
                        indexPossibleSwap = j;
                    }
                }
            }

            if (indexPossibleSwap != -1) {
                resultStr.replace(indexPossibleSwap, indexPossibleSwap + 1, capacity[i] + "");
                resultStr.replace(i, i + 1, capacity[indexPossibleSwap] + "");
                capacity[indexPossibleSwap] = capacity[i];
                
                for (int j = i + 1; j < length - 1; j++) {
                    int indexMin = j;
                    for (int k = j + 1; k < length; k++) {
                        if(capacity[k] < capacity[indexMin]) indexMin = k;
                    }
                    int tmp = capacity[j];
                    capacity[j] = capacity[indexMin];
                    capacity[indexMin] = tmp;
                }

                for (int x = i + 1; x < length; x++) {
                    resultStr.replace(x, x + 1, capacity[x] + "");
                }
                break;
            }
        }

        return Long.parseLong(resultStr.toString());
    }

    private static int[] getCapacity(StringBuilder sourceStr, int length) {
        int[] capacity = new int[length];
        for (int i = 0; i < length; i++) {
            capacity[i] = sourceStr.codePointAt(i) - 48;
        }
        return capacity;
    }
}