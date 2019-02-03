/*
Original task - https://www.codewars.com/kata/find-the-force-of-gravity-between-two-objects

You job is to find the gravitational force between two spherical objects (obj1 , obj2).

Two arrays are give :

arr_val (value array), consists of 3 elements
1st element : mass of obj 1
2nd element : mass of obj 2
3rd element : distance between their centers

arr_unit (unit array), consists of 3 elements
1st element : unit for mass of obj 1
2nd element : unit for mass of obj 2
3rd element : unit for distance between their centers

mass units are :
kilogram (kg)
gram (g)
milligram (mg)
microgram (μg)
pound (lb)

distance units are :
meter (m)
centimeter (cm)
millimeter (mm)
micrometer (μm)
feet (ft)

Note
value of G = 6.67 x 10-11N.kg–2.m2
1ft = 0.3048m
1lb = 0.453592kg

return value must be Newton for force (obviously)
μ copy this from here to use it in your solution
 */
 
import java.util.Map;
import java.util.HashMap;

public class Solution {
    private static final double G = 6.67E-11;
    private static Map<String, Double> forMass;
    private static Map<String, Double> forDistance;
    static {
        forMass = new HashMap<>();
        forMass.put("kg", 1.0);
        forMass.put("g", 1E-3);
        forMass.put("mg", 1E-6);
        forMass.put("μg", 1E-9);
        forMass.put("lb", 0.453592);
        
        forDistance = new HashMap<>();
        forDistance.put("m", 1.0);
        forDistance.put("cm", 1E-2);
        forDistance.put("mm", 1E-3);
        forDistance.put("μm", 1E-6);
        forDistance.put("ft", 0.3048);
    }
    
    public static double solution(double[] arrVal, String[] arrUnit) {
        return G * arrVal[0] * arrVal[1] * forMass.get(arrUnit[0]) * forMass.get(arrUnit[1]) /
               Math.pow(arrVal[2] * forDistance.get(arrUnit[2]), 2);
    }
}