/*
Original task - https://www.codewars.com/kata/roboscript-number-3-implement-the-rs2-specification

RoboScript #3 - Implement the RS2 Specification
Disclaimer
The story presented in this Kata Series is purely fictional;
any resemblance to actual programming languages, products,
organisations or people should be treated as purely coincidental.

About this Kata Series
This Kata Series is based on a fictional story about a computer scientist and engineer
who owns a firm that sells a toy robot called MyRobot which can interpret its own (esoteric)
programming language called RoboScript.
Naturally, this Kata Series deals with the software side of things
(I'm afraid Codewars cannot test your ability to build a physical robot!).

Story
Last time, you implemented the RS1 specification
which allowed your customers to write more concise scripts for their robots
by allowing them to simplify consecutive repeated commands by
postfixing a non-negative integer onto the selected command.
For example, if your customers wanted to make their robot move 20 steps to the right,
instead of typing FFFFFFFFFFFFFFFFFFFF, they could simply type F20
which made their scripts more concise.
However, you later realised that this simplification wasn't enough.
What if a set of commands/moves were to be repeated?
The code would still appear cumbersome.
Take the program that makes the robot move in a snake-like manner, for example.
The shortened code for it was F4LF4RF4RF4LF4LF4RF4RF4LF4LF4RF4RF4 which still contained a lot of repeated commands.

Task
Your task is to allow your customers to further shorten their scripts and
make them even more concise by implementing the newest specification of RoboScript
(at the time of writing) that is RS2.
RS2 syntax is a superset of RS1 syntax which means that all valid RS1 code from the previous Kata
of this Series should still work with your RS2 interpreter.
The only main addition in RS2 is that the customer should be able
to group certain sets of commands using round brackets.
For example, the last example used in the previous Kata in this Series:

LF5RF3RF3RF7
... can be expressed in RS2 as:

LF5(RF3)(RF3R)F7
Or ...

(L(F5(RF3))(((R(F3R)F7))))
Simply put, your interpreter should be able to deal with nested brackets of any level.
And of course, brackets are useless if you cannot use them to repeat a sequence of movements!
Similar to how individual commands can be postfixed by a non-negative integer
to specify how many times to repeat that command,
a sequence of commands grouped by round brackets () should also be repeated n times
provided a non-negative integer is postfixed onto the brackets, like such:

(SEQUENCE_OF_COMMANDS)n
... is equivalent to ...

SEQUENCE_OF_COMMANDS...SEQUENCE_OF_COMMANDS (repeatedly executed "n" times)
For example, this RS1 program:

F4LF4RF4RF4LF4LF4RF4RF4LF4LF4RF4RF4
... can be rewritten in RS2 as:

F4L(F4RF4RF4LF4L)2F4RF4RF4
Or:

F4L((F4R)2(F4L)2)2(F4R)2F4
All 4 example tests have been included for you. Good luck :D
 */
 
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoboScript {

    private static final String SEPARATOR = "\r\n";
    private static final char MARK = '*';
    private static ArrayList<StringBuilder> grid;
    private static int currentDirection;//R = 0, D = 1, L = 2, U = 3
    private static int currentColumn;
    private static int currentLine;

    public static String execute(String code) {
        initialization();
        Matcher matcher = Pattern.compile("[FLR]\\d*").matcher(getSimplifiedExpression(code));
        while (matcher.find()) {
            String command = matcher.group();
            if (command.length() == 1) command += "1";
            switch (command.charAt(0)) {
                case 'F' : {
                    goForward(Integer.valueOf(command.substring(1)));
                    break;
                }
                case 'L' : {
                    turnLeft(Integer.valueOf(command.substring(1)));
                    break;
                }
                case 'R' : {
                    turnRight(Integer.valueOf(command.substring(1)));
                    break;
                }
            }
        }

        return getStringMap();
    }

    private static String getSimplifiedExpression(String code) {
        StringBuilder parsedString = new StringBuilder(code);
        ArrayList<Integer> openingBracketsPositions = new ArrayList<>();
        for (int i = 0; i < parsedString.length(); i++) {
            if(parsedString.charAt(i) == '(') openingBracketsPositions.add(i);
            if (parsedString.charAt(i) == ')') {
                StringBuilder countOfRepeats = new StringBuilder();
                int j = i + 1;
                while (j < parsedString.length() && parsedString.codePointAt(j) > 47 && parsedString.codePointAt(j) < 58) {
                    countOfRepeats.append(parsedString.charAt(j));
                    j++;
                }
                if (countOfRepeats.length() == 0) {
                    parsedString.deleteCharAt(i);
                    parsedString.deleteCharAt(openingBracketsPositions.get(openingBracketsPositions.size()-1));
                    openingBracketsPositions.remove(openingBracketsPositions.size()-1);
                    i -= 2;
                } else {
                    int openingBracketPosition = openingBracketsPositions.get(openingBracketsPositions.size() - 1);
                    int repeats = Integer.valueOf(countOfRepeats.toString());
                    parsedString.replace(openingBracketPosition, j,
                            parsedString.substring(openingBracketPosition + 1, i).repeat(repeats));
                    i += (repeats - 1) * (j - countOfRepeats.length() - openingBracketPosition - 2) - 2;
                    openingBracketsPositions.remove(openingBracketsPositions.size()-1);
                }
            }
        }

        return parsedString.toString();
    }

    private static void initialization() {
        grid = new ArrayList<>();
        grid.add(new StringBuilder("*"));
        currentDirection = 0;
        currentLine = 0;
        currentColumn = 0;
    }

    private static void turnLeft(int times) {
        while(times >= 4) times -= 4;
        currentDirection -= times;
        if(currentDirection < 0) currentDirection += 4;
    }

    private static void turnRight(int times) {
        while(times >= 4) times -= 4;
        currentDirection += times;
        if(currentDirection > 3) currentDirection -= 4;
    }

    private static void goForward(int times) {
        for (int i = 0; i < times; i++) {
            switch (currentDirection) {
                case 0 : {
                    if (++currentColumn == grid.get(0).length()) {
                        extendRightBound();
                    }
                    grid.get(currentLine).setCharAt(currentColumn, MARK);
                    break;
                }
                case 1 : {
                    if (++currentLine == grid.size()) {
                        extendBottomBound();
                    }
                    grid.get(currentLine).setCharAt(currentColumn, MARK);
                    break;
                }
                case 2 : {
                    if (--currentColumn < 0) {
                        extendLeftBound();
                        currentColumn = 0;
                    }
                    grid.get(currentLine).setCharAt(currentColumn, MARK);
                    break;
                }
                case 3 : {
                    if (--currentLine < 0) {
                        extendUpperBound();
                        currentLine = 0;
                    }
                    grid.get(currentLine).setCharAt(currentColumn, MARK);
                    break;
                }
            }
        }
    }

    private static void extendRightBound() {
        for (StringBuilder line : grid) {
            line.append(" ");
        }
    }

    private static void extendLeftBound() {
        for (StringBuilder line : grid) {
            line.insert(0, " ");
        }
    }

    private static void extendUpperBound() {
        grid.add(0, new StringBuilder().append(" ".repeat(grid.get(0).length())));
    }

    private static void extendBottomBound() {
        grid.add(new StringBuilder().append(" ".repeat(grid.get(0).length())));
    }

    private static String getStringMap() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < grid.size() - 1; i++) {
            result.append(grid.get(i)).append(SEPARATOR);
        }

        return result.append(grid.get(grid.size()-1)).toString();
    }
}