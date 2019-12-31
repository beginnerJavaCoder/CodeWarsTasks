/*
Original task - https://www.codewars.com/kata/playing-with-cubes-i

Create a public class called Cube without a constructor which gets one single private Integer variable Side, 
a getter GetSide() and a setter SetSide(int num) method for this property.
In the next kata of this series, we gonna refactor the code and make it a bit more professional... 
Note: There's no need to check for negative values!
 */
 
public class Cube {
    
    private int side;
    
    public int getSide() {
        return side;
    }
    
    public void setSide(int side) {
        this.side = side;
    }
    
}