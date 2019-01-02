/*
Original task - https://www.codewars.com/kata/closest-and-smallest

Input:

A string strng of n positive numbers (n = 0 or n >= 2).
Let us call weight of a number the sum of its digits.
For example 99 will have "weight" 18, 100 will have "weight" 1.
Two numbers are "close" if the difference of their weights is small.

Task:
For each number in strng calculate its "weight" and then find two numbers of strng that have:
the smallest difference of weights ie that are the closest with the smallest weights
and with the smallest indices (or ranks, numbered from 0) in strng.

Output:
An array of two arrays, each subarray in the following format:
[number-weight, index in strng of the corresponding number, original corresponding number instrng]

The two subarrays are sorted in ascending order by their number weights if these weights are different,
by their indexes in the string if they have the same weights.

Examples:
Let us call that function closest.
strng = "103 123 4444 99 2000"
the weights are 4, 6, 16, 18, 2 (ie 2, 4, 6, 16, 18)

closest should return [[2, 4, 2000], [4, 0, 103]]
because 2000 and 103 have for weight 2 and 4, ther indexes in strng are 4 and 0.
The smallest difference is 2.
4 (for 103) and 6 (for 123) have a difference of 2 too but they are not
the smallest ones with a difference of 2 between their weights.
....................

strng = "80 71 62 53"
All the weights are 8.
closest should return [[8, 0, 80], [8, 1, 71]]
71 and 62 have also:
- the smallest weights (which is 8 for all)
- the smallest difference of weights (which is 0 for all pairs)
- but not the smallest indices in strng.
....................

strng = "444 2000 445 544"
the weights are 12, 2, 13, 13 (ie 2, 12, 13, 13)

closest should return [[13, 2, 445], [13, 3, 544]]
444 and 2000 have the smallest weights (12 and 2) but not the smallest difference of weights;
they are not the closest.
Here the smallest difference is 0 and in the result the indexes are in ascending order.
...................

closest("444 2000 445 644 2001 1002") --> [[3, 4, 2001], [3, 5, 1002]]
Here the smallest difference is 0 and in the result the indexes are in ascending order.
...................

closest("239382 162 254765 182 485944 468751 49780 108 54")
The weights are: 27, 9, 29, 11, 34, 31, 28, 9, 9.
closest should return  [[9, 1, 162], [9, 7, 108]]
108 and 54 have the smallest difference of weights too, they also have
the smallest weights but they don't have the smallest ranks in the original string.
..................

closest("54 239382 162 254765 182 485944 468751 49780 108")
closest should return  [[9, 0, 54], [9, 2, 162]]

Notes :
If n == 0, `closest("") should return [].
 */
 
class ClosestWeight {
	
    public static int[][] closest(String strng) {
        if(strng.length() == 0) return new int[0][];
        String[] strArray = strng.split(" ");
        int[] weights = calculateWeights(strArray);
        int[] indexes = getIndexesArray(strArray.length);
        sortByWeights(weights, indexes);

        return findClosestWeight(weights, indexes, strArray);
    }

    private static int[] calculateWeights(String[] numbers) {
        int[] weights = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            int weight = 0;
            for (int j = 0; j < numbers[i].length(); j++) {
                weight += Integer.parseInt(numbers[i].substring(j, j + 1));
            }
            weights[i] = weight;
        }

        return weights;
    }

    private static int[] getIndexesArray(int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = i;
        }

        return result;
    }

    private static void sortByWeights(int[] weights, int[] indexes) {
        for (int i = 0; i < weights.length - 1; i++) {
            int min = weights[i];
            int indexMin = i;
            for (int j = i + 1; j < weights.length; j++) {
                if (weights[j] < min) {
                    min = weights[j];
                    indexMin = j;
                }
            }
            if (indexMin != i) {
                int tmp = weights[i];
                weights[i] = min;
                weights[indexMin] = tmp;

                tmp = indexes[i];
                indexes[i] = indexes[indexMin];
                indexes[indexMin] = tmp;
            }
        }
    }

    private static int[][] findClosestWeight(int[] weights, int[] indexes, String[] strArray) {
        int firstIndex = 0;
        int secondIndex = 1;
        int smallestDifference = weights[secondIndex] - weights[firstIndex];
        for (int i = 0; i < weights.length - 1; i++) {
            for (int j = i + 1; j < weights.length; j++) {
                int difference = weights[j] - weights[i];
                if (difference < smallestDifference) {
                    smallestDifference = difference;
                    if (indexes[j] < indexes[i] && weights[i] == weights[j]) {
                        firstIndex = j;
                        secondIndex = i;
                    } else {
                        firstIndex = i;
                        secondIndex = j;
                    }
                } else if (difference == smallestDifference && weights[firstIndex] == weights[j]) {
                    if (indexes[j] < indexes[firstIndex]) {
                        secondIndex = firstIndex;
                        firstIndex = j;
                    } else if (indexes[j] < indexes[secondIndex] && indexes[j] > indexes[firstIndex]) {
                        secondIndex = j;
                    }
                } else break;
            }
        }

        return new int[][]{{weights[firstIndex], indexes[firstIndex], Integer.parseInt(strArray[indexes[firstIndex]])},
                         {weights[secondIndex], indexes[secondIndex], Integer.parseInt(strArray[indexes[secondIndex]])}};
    }
}