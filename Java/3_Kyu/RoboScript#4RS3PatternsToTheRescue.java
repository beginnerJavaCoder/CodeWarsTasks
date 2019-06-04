/*
Original task - https://www.codewars.com/kata/roboscript-number-4-rs3-patterns-to-the-rescue

RoboScript #4 - RS3 Patterns to the Rescue
Disclaimer
The story presented in this Kata Series is purely fictional;
any resemblance to actual programming languages, products,
organisations or people should be treated as purely coincidental.

About this Kata Series
This Kata Series is based on a fictional story about a computer scientist and engineer
who owns a firm that sells a toy robot called MyRobot which can interpret its own
(esoteric) programming language called RoboScript.
Naturally, this Kata Series deals with the software side of things
(I'm afraid Codewars cannot test your ability to build a physical robot!).

Story
Ever since you released RS2 to the market,
there have been much fewer complaints from RoboScript developers
about the inefficiency of the language and the popularity of your programming language has continuously soared.
It has even gained so much attention that Zachary Mikowski,
the CEO of the world-famous Doodle search engine,
has contacted you to try out your product!
Initially, when you explain the RoboScript (RS2) syntax to him,
he looks satisfied, but then he soon finds a major loophole in the efficiency
of the RS2 language and brings forth the following program:

(F2LF2R)2FRF4L(F2LF2R)2(FRFL)4(F2LF2R)2
As you can see from the program above,
the movement sequence (F2LF2R)2 has to be rewritten every time
and no amount of RS2 syntax can simplify it
because the movement sequences in between are different each time (FRF4L and (FRFL)4).
If only RoboScript had a movement sequence reuse feature that makes writing programs like these less repetitive ...

Task
Define and implement the RS3 specification whose syntax is a superset of RS2 (and RS1) syntax.
Your interpreter should be named execute() and accept exactly 1 argument code, the RoboScript code to be executed.

Patterns - The New Feature
To solve the problem outlined in the Story above,
you have decided to introduce a new syntax feature to RS3 called the "pattern".
The "pattern" as defined in RS3 behaves rather like a primitive version of functions/methods
in other programming languages - it allows the programmer to define and name
(to a certain extent) a certain sequence of movements which can be easily
referenced and reused later instead of rewriting the whole thing.

The basic syntax for defining a pattern is as follows:

p(n)<CODE_HERE>q
Where:

p is a "keyword" that declares the beginning of a pattern definition
(much like the function keyword in JavaScript or the def keyword in Python)
(n) is any non-negative integer (without the round brackets)
which acts as a unique identifier for the pattern (much like a function/method name)
<CODE_HERE> is any valid RoboScript code (without the angled brackets)
q is a "keyword" that marks the end of a pattern definition (like the end keyword in Ruby)
For example, if I want to define (F2LF2R)2 as a pattern and reuse it later in my code:

p0(F2LF2R)2q
It can also be rewritten as below since (n) only serves as an identifier and its value doesn't matter:

p312(F2LF2R)2q
Like function/method definitions in other languages,
merely defining a pattern (or patterns) in RS3 should cause no side effects, so:

execute("p0(F2LF2R)2q");   # => '*'
execute("p312(F2LF2R)2q"); # => '*'
To invoke a pattern (i.e. make the MyRobot move according to the movement sequences defined inside the pattern),
a capital P followed by the pattern identifier (n) is used:

P0
(or P312, depending on which example you are using)

So:

execute("p0(F2LF2R)2qP0");     # => "    *\r\n    *\r\n  ***\r\n  *  \r\n***  "
execute("p312(F2LF2R)2qP312"); # => "    *\r\n    *\r\n  ***\r\n  *  \r\n***  "
Additional Rules for parsing RS3
It doesn't matter whether the invocation of the pattern or the pattern definition comes first -
pattern definitions should always be parsed first, so:

execute("P0p0(F2LF2R)2q");     # => "    *\r\n    *\r\n  ***\r\n  *  \r\n***  "
execute("P312p312(F2LF2R)2q"); # => "    *\r\n    *\r\n  ***\r\n  *  \r\n***  "
Of course, RoboScript code can occur anywhere before and/or after a pattern definition/invocation, so:

execute("F3P0Lp0(F2LF2R)2qF2"); # => "       *\r\n       *\r\n       *\r\n       *\r\n     ***\r\n     *  \r\n******  "
Much like a function/definition can be invoked multiple times in other languages,
a pattern should also be able to be invoked multiple times in RS3. So:

execute("(P0)2p0F2LF2RqP0"); # => "      *\r\n      *\r\n    ***\r\n    *  \r\n  ***  \r\n  *    \r\n***    "
If a pattern is invoked which does not exist, your interpreter should throw.
This could be anything and will not be tested,
but ideally it should provide a useful message which describes the error in detail.

In java, throw a RuntimeException.

execute("p0(F2LF2R)2qP1");   # throws
execute("P0p312(F2LF2R)2q"); # throws
execute("P312");             # throws
Much like any good programming language will allow you to define an unlimited number of functions/methods,
your RS3 interpreter should also allow the user to define a virtually unlimited number of patterns.
A pattern definition should be able to invoke other patterns if required.
If the same pattern (i.e. both containing the same identifier (n)) is defined more than once,
your interpreter should throw (again, anything).

In java, throw a RuntimeException.

execute("P1P2p1F2Lqp2F2RqP2P1");                      # => "  ***\r\n  * *\r\n*** *"
execute("p1F2Lqp2F2Rqp3P1(P2)2P1q(P3)3");             # => "  *** *** ***\r\n  * * * * * *\r\n*** *** *** *"
execute("p1F2Lqp1(F3LF4R)5qp2F2Rqp3P1(P2)2P1q(P3)3"); # throws exception
Furthermore, your interpreter should be able to detect (potentially) infinite recursion,
including mutual recursion.
Instead of just getting stuck in an infinite loop and timing out,
your interpreter should throw (yes, anything again) when the "stack"
(or just the total number of pattern invocations) exceeds a particular very high (but sensible) threshold.

In java, throw a RuntimeException.

execute("p1F2RP1F2LqP1");      # throws
execute("p1F2LP2qp2F2RP1qP1"); # throws
For the sake of simplicity, you may assume that all programs passed into your interpreter
contains valid syntax and that pattern definitions will never be empty.
Furthermore, nesting pattern definitions is not allowed either
(it is considered a syntax error) so your interpreter will not need to account for these.
 */
 
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoboScript {

    private static final String SEPARATOR = "\r\n";
    private static final char MARK = '*';
    private static final Pattern patternFinder = Pattern.compile("p\\d+.*?q");
    private static final Pattern commonCommands = Pattern.compile("[FLR]\\d*");
    private static final Pattern patternCall = Pattern.compile("P\\d+");
    private static Map<String, String> patternMap;
    private static Map<String, String> usedPatterns;
    private static ArrayList<StringBuilder> grid;
    private static StringBuilder compiledCode;
    private static int currentDirection;//R = 0, D = 1, L = 2, U = 3
    private static int currentColumn;
    private static int currentLine;

    private static void initialization(String code) {
        grid = new ArrayList<>();
        grid.add(new StringBuilder("*"));
        usedPatterns = new HashMap<>();
        patternsCompilation(code);
        code = replaceAllPatterns(code);
        code = replaceAllCalls(code);
        compiledCode = getSimplifiedExpression(code);
        currentDirection = 0;
        currentLine = 0;
        currentColumn = 0;
    }

    public static String execute(String code) {
        initialization(code);
        Matcher matcher = commonCommands.matcher(compiledCode);
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

    private static void patternsCompilation(String code) {
        Matcher patternMatcher = patternFinder.matcher(code);
        patternMap = new HashMap<>();
        while(patternMatcher.find()) {
            String id = "";
            for (int i = 1; i < patternMatcher.group().length(); i++) {
                if(patternMatcher.group().codePointAt(i) > 47 && patternMatcher.group().codePointAt(i) < 58)
                    id += patternMatcher.group().charAt(i);
                else break;
            }
            for (String ids : patternMap.keySet()) {
                if(ids.equals(id))
                    throw new RuntimeException("The same names of patterns");
            }
            patternMap.put(id, patternMatcher.group().substring(1 + id.length(), patternMatcher.group().length()-1));
        }

        Matcher matcher = patternCall.matcher(code);
        ArrayList<String> groups = new ArrayList<>();
        ArrayList<String> unusedPatterns = new ArrayList<>();
        while (matcher.find()) {
            groups.add(matcher.group().substring(1));
        }

        Set<String> patternsInTheMap = new HashSet<>();
        for (String key : patternMap.keySet()) {
            patternsInTheMap.add(key);
        }
        for (String patternID : patternsInTheMap) {
            for (int i = 0; i < groups.size(); i++) {
                if(patternID.equals(groups.get(i))) break;
                if (!patternID.equals(groups.get(i)) && i == groups.size() - 1) {
                    unusedPatterns.add(patternID);
                    patternMap.remove(patternID, patternMap.get(patternID));
                }
            }
        }
        String tmpCode = code;
        for (String unused : unusedPatterns) {
            tmpCode = tmpCode.replaceAll("p" + unused + ".+?q", "");
        }
        invalidCallsSearch(tmpCode);
        tmpCode = code;
        tmpCode = replaceAllPatterns(tmpCode);
        if (isInfiniteLoop(tmpCode, 0)) {
            throw new RuntimeException("recursion");
        }
        for (String patternID : usedPatterns.keySet()) {
            patternMap.put(patternID, patternWithoutCalls(patternMap.get(patternID)));
        }
    }

    private static String patternWithoutCalls(String pattern) {
        Matcher matcher = patternCall.matcher(pattern);
        while (matcher.find()) {
            pattern = pattern.replace(matcher.group(), patternMap.get(matcher.group().substring(1)));
            matcher = patternCall.matcher(pattern);
        }

        return pattern;
    }

    private static boolean isInfiniteLoop(String checkIt, int count) {
        if(count == 50) return true;
        Matcher patternCallMatcher = patternCall.matcher(checkIt);
        while (patternCallMatcher.find()) {
            if(!usedPatterns.containsKey(patternCallMatcher.group().substring(1)))
                usedPatterns.put(patternCallMatcher.group().substring(1), patternMap.get(patternCallMatcher.group().substring(1)));
            if (isInfiniteLoop(patternMap.get(patternCallMatcher.group().substring(1)), ++count)) {
                return true;
            }
        }

        return false;
    }

    private static void invalidCallsSearch(String code) {
        Matcher invalidCallMatcher = patternCall.matcher(code);
        while (invalidCallMatcher.find()) {
            if (patternMap.get(invalidCallMatcher.group().substring(1)) == null) {
                throw new RuntimeException("invalid call");
            }
        }
    }

    private static String replaceAllPatterns(String code) {
        Matcher matcher = patternFinder.matcher(code);
        while (matcher.find()) {
            code = code.replace(matcher.group(), "");
            matcher = patternFinder.matcher(code);
        }

        return code;
    }

    private static String replaceAllCalls(String code) {
        Matcher matcher = patternCall.matcher(code);
        while (matcher.find()) {
            code = code.replace(matcher.group(), patternMap.get(matcher.group().substring(1)));
            matcher = patternCall.matcher(code);
        }

        return code;
    }

    private static StringBuilder getSimplifiedExpression(String code) {
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

        return parsedString;
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