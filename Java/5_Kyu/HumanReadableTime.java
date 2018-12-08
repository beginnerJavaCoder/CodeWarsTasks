/*
Original task - https://www.codewars.com/kata/human-readable-time

Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)

HH = hours, padded to 2 digits, range: 00 - 99
MM = minutes, padded to 2 digits, range: 00 - 59
SS = seconds, padded to 2 digits, range: 00 - 59
The maximum time never exceeds 359999 (99:59:59)
 */

public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        StringBuilder result = new StringBuilder();
        int tmp = seconds / 3600;
        if(tmp < 10) result.append('0');
        result.append(tmp).append(':');
        seconds -= tmp * 3600;
        tmp = seconds / 60;
        if(tmp < 10) result.append('0');
        result.append(tmp).append(':');
        seconds -= tmp * 60;
        if(seconds < 10) result.append('0');
        result.append(seconds);

        return result.toString();
    }
}