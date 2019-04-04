/*
Original task - https://www.codewars.com/kata/cat-years-dog-years

Kata Task
I have a cat and a dog.
I got them at the same time as kitten/puppy. That was humanYears years ago.
Return their respective ages now as [humanYears,catYears,dogYears]

NOTES:
humanYears >= 1
humanYears are whole numbers only
Cat Years
15 cat years for first year
+9 cat years for second year
+4 cat years for each year after that
Dog Years
15 dog years for first year
+9 dog years for second year
+5 dog years for each year after that
 */
 
public class Dinglemouse {
    public static int[] humanYearsCatYearsDogYears(final int humanYears) {
        int[] years = new int[3];
        years[0] = humanYears;
        years[1] = humanYears > 1 ? humanYears > 2 ? 24 + (humanYears - 2) * 4 : 24 : 15;
        years[2] = humanYears > 1 ? humanYears > 2 ? 24 + (humanYears - 2) * 5 : 24 : 15;
        
        return years;
    }
}