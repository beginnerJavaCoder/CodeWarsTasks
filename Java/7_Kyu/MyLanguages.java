/*
Original task - https://www.codewars.com/kata/mylanguages

Your task
Given a dictionary of languages and your respective test results,
return the list of languages where your test score is at least 60, in descending order of the results.

Note: There will be no duplicate values.

Examples
{"Java" => 10, "Ruby" => 80, "Python" => 65}  --> ["Ruby", "Python"]
{"Hindi" => 60, "Dutch" => 93, "Greek" => 71} --> ["Dutch", "Greek", "Hindi"]
{"C++" => 50, "ASM" => 10, "Haskell" => 20}   --> []
 */
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyLanguages {

    public static List<String> myLanguages(final Map<String, Integer> results) {
        List<String> languages = new ArrayList<>();
        List<Integer> testResults = new ArrayList<>();
        fillLists(results, languages, testResults);
        sortResults(languages, testResults);

        return languages;
    }

    private static void fillLists(Map<String, Integer> results, List<String> languages, List<Integer> testResults) {
        for (Map.Entry e : results.entrySet()) {
            if ((int) e.getValue() > 59) {
                languages.add((String) e.getKey());
                testResults.add((int) e.getValue());
            }
        }
    }

    private static void sortResults(List<String> languages, List<Integer> testResults) {
        for (int i = 0; i < testResults.size() - 1; i++) {
            int max = testResults.get(i);
            int maxIndex = i;
            for (int j = i + 1; j < testResults.size(); j++) {
                if (testResults.get(j) > max) {
                    max = testResults.get(j);
                    maxIndex = j;
                }
            }
            swap(languages, testResults, max, maxIndex, i);
        }
    }

    private static void swap(List<String> languages, List<Integer> testResults, int max, int maxIndex, int pos) {
        int score = testResults.get(pos);
        testResults.remove(pos);
        testResults.add(pos, max);
        testResults.remove(maxIndex);
        testResults.add(maxIndex, score);

        String tmpStr = languages.get(pos);
        String maxLang = languages.get(maxIndex);
        languages.remove(pos);
        languages.add(pos, maxLang);
        languages.remove(maxIndex);
        languages.add(maxIndex, tmpStr);
    }
}