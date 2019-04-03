/*
Original task - https://www.codewars.com/kata/exclusive-or-xor-logical-operator

Overview
In some scripting languages like PHP, there exists a logical operator (e.g. &&, ||, and, or, etc.) called the "Exclusive Or". 
The exclusive or evaluates two booleans. 
It then returns true if exactly one of the two expressions are true, false otherwise. 

For example:
false xor false == false // since both are false
true xor false == true // exactly one of the two expressions are true
false xor true == true // exactly one of the two expressions are true
true xor true == false // Both are true.  "xor" only returns true if EXACTLY one of the two expressions evaluate to true.

Task
Your task is to define a function xor(a, b) where a and b are the two expressions to be evaluated. 
Your xor function should have the behaviour described above, 
returning true if exactly one of the two expressions evaluate to true, false otherwise.
 */
 
public class XOR {
    public static boolean xor(boolean a, boolean b) {
        return !a && b || a && !b;
    }
}