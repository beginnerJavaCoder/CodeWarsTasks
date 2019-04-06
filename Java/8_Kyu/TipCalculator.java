/*
Original task - https://www.codewars.com/kata/tip-calculator

Complete the function, which calculates how much you need to tip based on the total amount of the bill and the service.
You need to consider the following ratings:
Terrible: tip 0%
Poor: tip 5%
Good: tip 10%
Great: tip 15%
Excellent: tip 20%
The rating is case insensitive (so "great" = "GREAT"). 
If an unrecognised rating is received, then you need to return null.
Because you're a nice person, you always round up the tip, regardless of the service.
 */
 
import java.util.Map;
import java.util.HashMap;

public class TipCalculator {
    public static Integer calculateTip(double amount, String rating) {
        Map<String, Double> ratings = new HashMap<>();
        ratings.put("terrible", 0.0);
        ratings.put("poor", 0.05);
        ratings.put("good", 0.1);
        ratings.put("great", 0.15);
        ratings.put("excellent", 0.2);
        
        return ratings.containsKey(rating.toLowerCase()) ? 
        (int) Math.ceil(amount * ratings.get(rating.toLowerCase())) : null;
    }
}