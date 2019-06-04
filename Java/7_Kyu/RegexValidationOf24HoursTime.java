/*
Original task - https://www.codewars.com/kata/regex-validation-of-24-hours-time

Write a regex to validate a 24 hours time string. 
See examples to figure out what you should check for:
Accepted: 01:00 - 1:00
Not accepted:
24:00
You should check for correct length and no spaces.
 */
 
public class RegexValidation {
    public static boolean validateTime(String time) {
        return time.matches("^([01][\\d]:[0-5][\\d])|([\\d]:[0-5][\\d])|(2[0-3]:[0-5][\\d])$");
    }
}