/*
Original task - https://www.codewars.com/kata/weight-for-weight

My friend John and I are members of the "Fat to Fit Club (FFC)".
John is worried because each month a list with the weights of members is published
and each month he is the last on the list which means he is the heaviest.
I am the one who establishes the list so I told him:
"Don't worry any more, I will modify the order of the list".
It was decided to attribute a "weight" to numbers.
The weight of a number will be from now on the sum of its digits.
For example 99 will have "weight" 18, 100 will have "weight" 1 so in the list 100 will come before 99.
Given a string with the weights of FFC members in normal order can you give this string ordered by "weights" of these numbers?

Example:
"56 65 74 100 99 68 86 180 90" ordered by numbers weights becomes: "100 180 90 56 65 74 68 86 99"
When two numbers have the same "weight", let us class them as if they were strings and not numbers:
100 is before 180 because its "weight" (1) is less than the one of 180 (9) and 180 is before 90 since,
having the same "weight" (9) it comes before as a string.

Notes
All numbers in the list are positive numbers and the list can be empty.
It may happen that the input string have leading, trailing whitespaces and more than a unique whitespace between two consecutive numbers.
Don't modify the input.
 */
 
import java.util.ArrayList;

public class WeightSort {

    public static String orderWeight(String string) {
        String[] values = parseString(string);
        int[] weights = getWeights(values);
        sortArraysByWeight(values, weights);
        String result = getFormatString(values);

        return result;
    }

    private static boolean isDigit(int codePoint) {
        return codePoint > 47 && codePoint < 58;
    }

    private static int[] getWeights(String[] values) {
        int[] byWeights = new int[values.length];
        for (int i = 0; i < byWeights.length; i++) {
            int weight = 0;
            for (int j = 0; j < values[i].length(); j++) {
                weight += Integer.parseInt(values[i].charAt(j) + "");
            }
            byWeights[i] = weight;
        }

        return byWeights;
    }

    private static String[] parseString(String string) {
        ArrayList<String> values = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            while (i < length && isDigit(string.codePointAt(i)) ) {
                tmp.append(string.charAt(i));
                i++;
            }
            if(!tmp.toString().equals("")) {
                values.add(tmp.toString());
                tmp = new StringBuilder();
            }
        }
        String[] strValues = new String[values.size()];
		
        return values.toArray(strValues);
    }

    private static void sortArraysByWeight(String[] values, int[] weights) {
        for (int i = 0; i < weights.length - 1; i++) {
            int indexMin = i;
            int min = weights[indexMin];
            for (int j = i + 1; j < weights.length; j++) {
                if (weights[j] == min) {
                    if (values[j].compareTo(values[indexMin]) < 0) {
                        min = weights[j];
                        indexMin = j;
                    }
                }
                if (weights[j] < min) {
                    min = weights[j];
                    indexMin = j;
                }
            }

            int tmp = weights[i];
            weights[i] = min;
            weights[indexMin] = tmp;
            String tmpStr = values[i];
            values[i] = values[indexMin];
            values[indexMin] = tmpStr;
        }
    }

    private static String getFormatString(String[] array) {
        if(array.length == 0) return "";
        StringBuilder result = new StringBuilder();

        for (String s : array) {
            result.append(s);
            result.append(" ");
        }
        result.deleteCharAt(result.length()-1);

        return result.toString();
    }
}