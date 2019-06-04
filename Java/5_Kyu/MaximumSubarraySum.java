/*
Original task - https://www.codewars.com/kata/maximum-subarray-sum

The maximum sum subarray problem consists in finding
the maximum sum of a contiguous subsequence in an array of integers:
Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
// should be 6: {4, -1, 2, 1}

Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array.
If the list is made up of only negative numbers, return 0 instead.
Empty list is considered to have zero greatest sum.
Note that the empty list or array is also a valid sublist/subarray.
 */
 
public class Max {
    
    public static int sequence(int[] arr) {
        int maxSum = 0, currentSum;
        if(arr.length == 0 || areOnlyNegativeNumbers(arr)) return maxSum;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                currentSum = 0;
                for (int k = j; k < arr.length - i + j; k++) {
                    currentSum += arr[k];

                }
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }

        }

        return maxSum;
    }

    private static boolean areOnlyNegativeNumbers(int[] arr) {
        for(int num : arr) {
            if(num > 0)
                return false;
        }

        return true;
    }
}