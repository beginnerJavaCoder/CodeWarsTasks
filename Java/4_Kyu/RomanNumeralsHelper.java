/*
Original task - https://www.codewars.com/kata/51b66044bce5799a7f000003

Task
Create a RomanNumerals class that can convert a roman numeral to and from an integer value. 
It should follow the API demonstrated in the examples below. 
Multiple roman numeral values will be tested for each helper method.

Modern Roman numerals are written by expressing each digit separately starting with 
the left most digit and skipping any digit with a value of zero. 
In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 
2008 is written as 2000=MM, 8=VIII; or MMVIII. 
1666 uses each Roman symbol in descending order: MDCLXVI.

Examples
RomanNumerals.toRoman(1000) // should return 'M'
RomanNumerals.fromRoman("M") // should return 1000
Help
| Symbol | Value |
|----------------|
| I      |  1    |
| V      |  5    |
| X      |  10   |
| L      |  50   |
| C      |  100  |
| D      |  500  |
| M      |  1000 |
 */
 
public class RomanNumerals {

    static class RomanToArabian {
        private final int arabian;
        private final String roman;

        public RomanToArabian(int arabian, String roman) {
            this.arabian = arabian;
            this.roman = roman;
        }

        public int getArabian() {
            return arabian;
        }

        public String getRoman() {
            return roman;
        }
    }

    private static final RomanToArabian[] numbers = new RomanToArabian[] {
            new RomanToArabian(1, "I"),
            new RomanToArabian(5, "V"),
            new RomanToArabian(10, "X"),
            new RomanToArabian(50, "L"),
            new RomanToArabian(100, "C"),
            new RomanToArabian(500, "D"),
            new RomanToArabian(1000, "M"),
            new RomanToArabian(5000, "v"),
            new RomanToArabian(10000, "x"),
    };

    public static String toRoman(int n) {
        StringBuilder roman = new StringBuilder();

        for (int i = 1000, currentIndex = 6; i > 0; i /= 10, currentIndex -= 2) {
            n = toRomanHelper(n, i, currentIndex, roman);
        }

        return roman.toString();
    }

    private static int toRomanHelper(int remainder, int divider, int currentIndex, StringBuilder roman) {
        int quantity = remainder / divider;
        if (quantity == 9) {
            roman.append(numbers[currentIndex].getRoman()).append(numbers[currentIndex + 2].getRoman());
        } else if (quantity == 4) {
            roman.append(numbers[currentIndex].getRoman()).append(numbers[currentIndex + 1].getRoman());
        } else {
            if (quantity > 4) {
                roman.append(numbers[currentIndex + 1].getRoman());
                quantity -= 5;
                remainder -= divider * 5;
            }
            for (int i = 0; i < quantity; i++) {
                roman.append(numbers[currentIndex].getRoman());
            }
        }

        return remainder - quantity * divider;
    }

    public static int fromRoman(String romanNumeral) {
        int result = 0;
        int prevIndex = 6;
        String number;
        for (int i = 0; i < romanNumeral.length(); i++) {
            number = romanNumeral.substring(i, i + 1);

            int currentIndex = 6;
            while (!numbers[currentIndex].getRoman().equals(number)) {
                currentIndex--;
            }

            if (prevIndex - currentIndex >= 0) {
                result += numbers[currentIndex].getArabian();
                prevIndex = currentIndex;
            } else if (currentIndex - prevIndex == 1) {
                result += 3 * numbers[prevIndex].getArabian();
            } else if (currentIndex - prevIndex == 2) {
                result += 8 * numbers[prevIndex].getArabian();
            }
        }

        return result;
    }
}
