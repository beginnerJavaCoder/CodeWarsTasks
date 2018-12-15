/*
Original task - https://www.codewars.com/kata/fixme-static-electrickery

The code provided has a method plus100 which is supposed to return the number of the parameter incremented by 100.
But it's not working properly.

Task
Fix the code so we can all go home early.

Restrictions
Do not modify the constructor
Do not modify the plus100 method
Do not modify the ONE_HUNDRED declaration
 */
 
public class Dinglemouse {

    public static final ReturnMeRight INST = new ReturnMeRight();
      
    private static int ONE_HUNDRED = 100;

    private final int value; 

    private Dinglemouse() {
        value = ONE_HUNDRED;
    }
  
    public int plus100(int n) {
        return value + n;
    }
}

class ReturnMeRight {
    public int plus100(int n) {
        return 100 + n;
    }
}