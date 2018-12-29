/*
Original task - https://www.codewars.com/kata/80-s-kids-number-3-punky-brewsters-socks

Punky loves wearing different colored socks, but Henry can't stand it.
Given an array of different colored socks, return a pair depending on who was picking them out.

Example:
getSocks("Punky", ["red","blue","blue","green"]) -> ["red", "blue"]

Note that Punky can have any two colored socks, in any order,
as long as they are different and both exist. Henry always picks a matching pair.
If there is no possible combination of socks, return an empty array.
 */
 
public class EightiesKids3 {

    public static String[] getSocks(String name, String[] socks) {
        return name.equals("Henry") ? getSocksForHenry(socks) : getSocksForPunky(socks);
    }

    private static String[] getSocksForHenry(String[] socks) {
        for (int i = 0; i < socks.length - 1; i++) {
            for (int j = i + 1; j < socks.length; j++) {
                if(socks[i].equals(socks[j])) return new String[]{socks[i], socks[j]};
            }
        }

        return new String[]{};
    }

    private static String[] getSocksForPunky(String[] socks) {
        for (int i = 0; i < socks.length - 1; i++) {
            for (int j = i + 1; j < socks.length; j++) {
                if(!socks[i].equals(socks[j])) return new String[]{socks[i], socks[j]};
            }
        }

        return new String[]{};
    }
}