/*
Original task - https://www.codewars.com/kata/welcome-to-the-city

Create a method sayHello that takes as input a name, city, and state to welcome a person. 
Note that name will be an array consisting of one or more values that should be joined together 
with one space betweeen each, and the length of the name array in test cases will vary.

Example:
sayHello(new String[]{"John", "Smith"}, "Phoenix", "Arizona")
This example will return the string Hello, John Smith! Welcome to Phoenix, Arizona!
 */
 
public class Hello {
    public String sayHello(String [] name, String city, String state) {
        return "Hello, " + String.join(" ", name) + "! Welcome to " + city + ", " + state + "!";
    }
}