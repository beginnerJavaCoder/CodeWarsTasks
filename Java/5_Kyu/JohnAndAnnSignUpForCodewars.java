/*
Original task - https://www.codewars.com/kata/john-and-ann-sign-up-for-codewars

John and his wife Ann have decided to go to Codewars.
On first day Ann will do one kata and John - he wants to know how it is working - 0 kata.
Let us call a(n) the number of katas done by Ann at day n. 
We have a(0) = 1 and in the same manner j(0) = 0 (or a(1) = 1 and j(1) = 0 
for languages that have arrays with indices beginning at 1).

They have chosen the following rules:
On day n the number of katas done by Ann should be n minus the number of katas done by John at day t, 
t being equal to the number of katas done by Ann herself at day n - 1.

On day n the number of katas done by John should be n minus the number of katas done by Ann at day t, 
t being equal to the number of katas done by John himself at day n - 1.

Whoops! I think they need to lay out a little clearer exactly what there're getting themselves into!
Could you write:
1) two functions ann and john (parameter n) giving the list of the numbers of 
katas Ann and John should take on the first n days (see first examples below)?
2) The total number of katas taken by ann function sum_ann(n) and john function sum_john(n) - on the first n days?

Examples:
john(11) -->  [0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6]
ann(6) -->  [1, 1, 2, 2, 3, 3]

sum_john(75) -->  1720
sum_ann(150) -->  6930
 */
 
import java.util.ArrayList;
import java.util.List;

public class Johnann {
	
    private static ArrayList<Long> annList;
    private static ArrayList<Long> johnList;
    private static long[] ann;
    private static long[] john;

    private static void calculate(long day) {
        ann = new long[(int) day];
        john = new long[(int) day];
        annList = new ArrayList<>((int) day);
        johnList = new ArrayList<>((int) day);

        ann[0] = 1;
        john[0] = 0;
        if(day == 0) {
            copyToList(day);
            return;
        }
        for(int i = 1; i < day; i++) {
            ann[i] = getKataInThisDayAnn(i);
            john[i] = getKataInThisDayJohn(i);
        }
        copyToList(day);
    }

    public static List<Long> john(long n) {
        calculate(n);
        return johnList;
    }

    public static List<Long> ann(long n) {
        calculate(n);
        return annList;
    }

    private static long getKataInThisDayAnn(long day) {
        return day - john[(int)ann[(int)day-1]];
    }
    private static long getKataInThisDayJohn(long day) {
        return day - ann[(int)john[(int)day-1]];
    }

    public static long sumJohn(long n) {
        calculate(n);
        long sum = 0;
        for (int i = 0; i < n; i++) sum += johnList.get(i);
        return sum;
    }
	
    public static long sumAnn(long n) {
        calculate(n);
        long sum = 0;
        for (int i = 0; i < n; i++) sum += annList.get(i);
        return sum;
    }

    private static void copyToList(long n) {
        for(int i = 0; i < n; i++) {
            annList.add(ann[i]);
            johnList.add(john[i]);
        }
    }
}