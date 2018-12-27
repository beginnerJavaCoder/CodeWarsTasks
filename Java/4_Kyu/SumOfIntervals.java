/*
Original task - https://www.codewars.com/kata/sum-of-intervals

Write a function that accepts an array of intervals,
and returns the sum of all the interval lengths. Overlapping intervals should only be counted once.

Intervals
Intervals are represented by a pair of integers in the form of an array.
The first value of the interval will always be less than the second value.
Interval example: [1, 5] is an interval from 1 to 5. The length of this interval is 4.

Overlapping Intervals
List containing overlapping intervals:
[
   [1,4],
   [7, 10],
   [3, 5]
]
The sum of the lengths of these intervals is 7.
Since [1, 4] and [3, 5] overlap, we can treat the interval as [1, 5], which has a length of 4.

Examples:
// null argument
Interval.sumIntervals(null);  // => 0

// empty intervals
Interval.sumIntervals(new int[][]{});  // => 0
Interval.sumIntervals(new int[][]{{2,2}, {5,5}});  // => 0

// disjoined intervals
Interval.sumIntervals(new int[][]{
  {1,2},{3,5}
});  // => (2-1) + (5-3) = 3

// overlapping intervals
Interval.sumIntervals(new int[][]{
  {1,4},{3,6},{2,8}
});  // [1,8] => 7
 */
 
import java.util.ArrayList;
import java.util.Arrays;

public class Interval {

    public static int sumIntervals(int[][] intervals) {
        int sumOfIntervals = 0;
        if(intervals == null || Arrays.equals(intervals, new int[][]{})) return sumOfIntervals;

        ArrayList<Integer> indexesOfUsingIntervals = new ArrayList<>();
        ArrayList<Integer> indexesOfUnmergedIntervals = new ArrayList<>();
        ArrayList<int[]> mergedIntervals = new ArrayList<>();

        for (int i = 0; i < intervals.length - 1; i++) {
            if(indexesOfUsingIntervals.contains(i)) continue;
            int[] comparable = intervals[i];
            if (isNotInterval(comparable)) {
                indexesOfUsingIntervals.add(i);
                continue;
            }
            boolean isMerged = false;
            for (int j = i + 1; j < intervals.length; j++) {
                if(indexesOfUsingIntervals.contains(j)) continue;
                if (isNotInterval(intervals[j])) {
                    indexesOfUsingIntervals.add(j);
                    continue;
                }
                if (isOverlapped(comparable, intervals[j])) {
                    comparable = mergeIntervals(comparable, intervals[j]);
                    isMerged = true;
                    indexesOfUsingIntervals.add(j);
                    j = i;//we need to compare our new merged interval with earlier intervals
                }
            }
            if(isMerged) {
                indexesOfUsingIntervals.add(i);
                mergedIntervals.add(comparable);
            } else indexesOfUnmergedIntervals.add(i);
        }
        //if last interval not used, its index add in unmerged
        if(!indexesOfUsingIntervals.contains(intervals.length-1)) indexesOfUnmergedIntervals.add(intervals.length - 1);
        //sum of merged intervals
        for (int[] interval : mergedIntervals) {
            sumOfIntervals += interval[1] - interval[0];
        }
        //sum of unmerged intervals
        for (int i = 0; i < indexesOfUnmergedIntervals.size(); i++) {
            sumOfIntervals += intervals[indexesOfUnmergedIntervals.get(i)][1] - intervals[indexesOfUnmergedIntervals.get(i)][0];
        }

        return sumOfIntervals;
    }

    private static boolean isOverlapped(int[] first, int[] second) {
        if (first[0] < second[0]) return first[1] >= second[0];
        return second[1] > first[0];
    }

    private static boolean isNotInterval(int[] interval) {
        return interval[0] == interval[1];
    }

    private static int[] mergeIntervals(int[] first, int[] second) {
        int[] resultInterval = new int[2];

        if(first[0] < second[0]) resultInterval[0] = first[0];
        else resultInterval[0] = second[0];

        if(first[1] > second[1]) resultInterval[1] = first[1];
        else resultInterval[1] = second[1];

        return resultInterval;
    }
}