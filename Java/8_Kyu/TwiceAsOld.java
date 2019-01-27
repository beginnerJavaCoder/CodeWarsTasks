/*
Original task - https://www.codewars.com/kata/twice-as-old

Your function takes two arguments:
current father's age (years)
current age of his son (years)
Сalculate how many years ago the father was twice as old as his son 
(or in how many years he will be twice as old).
 */
 
public class TwiceAsOld {
    public static int TwiceAsOld(int dadYears, int sonYears) {
        if(dadYears - sonYears * 2 >= 0) return dadYears - sonYears * 2;
        int rightAge = dadYears;
        while(true) {
            if(--rightAge / --sonYears == 2)
                break;
        }
        
        return dadYears - rightAge;
    }
}