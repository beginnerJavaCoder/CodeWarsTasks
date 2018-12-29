/*
Original task - https://www.codewars.com/kata/80-s-kids-number-4-legends-of-the-hidden-temple

You've made it through the moat and up the steps of knowledge.
You've won the temples games and now you're hunting for treasure in the final temple run.
There's good news and bad news. You've found the treasure but you've triggered a nasty trap.
You'll surely perish in the temple chamber.

With your last movements, you've decided to draw an "X" marks the spot for the next archeologist.
Given an odd number, n, draw an X for the next crew. Follow the example below.

markSpot(5) ->
X       X
  X   X
    X
  X   X
X       X

For a clearer understanding of the output, let '.' represent a space and \n the newline.
X.......X\n
..X...X\n
....X\n
..X...X\n
X.......X\n

markSpot(3) ->
X   X
  X
X   X

If n = 1 return 'X\n' and if you're given an even number or invalid input, return '?'.
The output should be a string with no spaces after the final X on each line, but a \n to indicate a new line.
 */
 
public class EightiesKids4 {
    public static String markSpot(float n) {
        if(n < 0) return "?";
        if(n % (int) n != 0) return "?";
        if(n % 2 == 0) return "?";
        if(n == 1) return "X\n";

        StringBuilder xMark = new StringBuilder();
        StringBuilder line = new StringBuilder();
        String[] lines = new String[(int) n/2];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < i * 2; j++) {
                line.append(" ");
            }
            line.append("X");
            for (int j = 0; j < n * 2 - 3 - i * 4; j++) {
                line.append(" ");
            }
            line.append("X\n");
            xMark.append(line);
            lines[i] = line.toString();
            line = new StringBuilder();
        }

        for (int i = 0; i < n - 1; i++) {
            xMark.append(" ");
        }
        xMark.append("X\n");

        for (int i = lines.length - 1; i >= 0; i--) {
            xMark.append(lines[i]);
        }

        return xMark.toString();
    }
}