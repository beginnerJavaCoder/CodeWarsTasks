/*
Original task - https://www.codewars.com/kata/roboscript-number-5-the-final-obstacle-implement-rsu

NOTE: It is highly recommended that you complete the first 4 Kata in this Series before attempting this final Kata;
otherwise, the description would make little sense.
For java users: Each time you encounter an invalid case you'll have to throw a RuntimeException.

RoboScript #5 - The Final Obstacle (Implement RSU)

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
Since RS3 was released into the market which introduced handy pattern definitions
on top of command grouping and repetition, its popularity soared within the robotics community,
insomuch that other budding robotics firms have pleaded your company to allow them to use the
RoboScript programming language in their products.
In order to grant them their requests and protect your company at the same time,
you decided to apply for a patent which would allow other companies to utilize RoboScript
in their own products with certain restrictions and only with an annual fee paid to your company.
So far, so good - the patent application was successful and your firm gained
an ample amount of revenue in the first year from this patent alone.
However, since RoboScript is still a rather small and domain-specific programming language,
the restrictions listed on the patent were rather limited.
Competing firms soon found a loophole in the wording of the patent which allowed them
to develop their own RoboScript-like programming language with minor modifications
and improvements which allowed them to legally circumvent your patent.
Soon, these robotics firms start overtaking your company in terms of popularity, profitability and size.
In order to investigate the main cause of the downfall of your company, a secret survey was sent
to thousands of former MyRobot (and hence "official" RoboScript) users.
It was revealed in this survey that the main reason for this downfall was due
to the lack of readability of RS3 code (and RoboScript code in general),
especially as the program becomes very large and complex.
After all, it makes perfect sense - who would even bother to try to understand and maintain the following RS3 code,
let alone much larger and complex programs?
p0FFLFFR((FFFR)2(FFFFFL)3)4qp1FRqp2FP1qp3FP2qp4FP3qP0P1P2P3P4
In a final attempt to save your company from going bankrupt and disappearing from the world of robotics,
you decide to address all of the major issues identified in the secret survey head-on
by designing the fourth and final specification for the RoboScript programming language - RoboScript Ultimatum (RSU).
The only thing left for you to do is to properly implement the specification
by writing an RSU-compliant code executor - once that is achieved,
your company will catapult back into 1st position
in global robotics and perhaps even leave a mark in the history of technology ...

Task
RoboScript Ultimatum (RSU) - The Official Specification
RoboScript Ultimatum (RSU) is a superset of the RS3 specification
(which itself is a superset of RS2, RS2 being a superset of RS1).
However, it introduces quite a few new (and handy) features.

1. Whitespace and Indentation Support
In RSU, whitespace and indentation is supported to improve readability and can appear
anywhere in the program in any form except within a token itself (explained in more detail later).
For example, the program below is valid:

p0
  (
    F2 L
  )2 (
    F2 R
  )2
q

(
  P0
)2

While the following program is not valid:

p 0
  (
    F 2L
  ) 2 (
    F 2 R
  )         2
q

(
  P  0
)2

Of course, the code need not be neatly indented - it should be valid
 so long as tokens such as p0, F2, )2 do not contain any whitespace themselves.

2. Comment Support
RSU should support single line comments (optional code) // ... and multiline comments /* ... */
//like in many other programming languages such as JavaScript.
// All single line comments are terminated by either of a newline character \n and the end-of-file (EOF)
// (beware of this edge case ;) ) and multiline comments cannot be nested.
// For example, this is a valid program with comments:

/*
  RoboScript Ultimatum (RSU)
  A simple and comprehensive code example
*/

// Define a new pattern with identifier n = 0
/*
        p0
        // The commands below causes the MyRobot to move
        // in a short snake-like path upwards if executed
        (
        F2 L // Go forwards two steps and then turn left
        )2 (
        F2 R // Go forwards two steps and then turn right
        )2
        q
*/
// Execute the snake-like pattern twice to generate
// a longer snake-like pattern
/*
        (
        P0
        )2

Comments follow the same rules as whitespace and indentation -
they can be placed anywhere within the program except within a token itself, e.g. F/* ... */ //37 is invalid.
// Both single-line and multiline comments may be empty, i.e. /**/ /*and //\n are valid.

/*
3. Pattern Scoping
This is much like function and/or block scoping in many other programming languages.
While attempts to nest a pattern definition in another pattern yielded undefined behavior in RS3,
each pattern will have its own scope in RSU.
Furthermore, each pattern will be able to "see" pattern definitions
both in its own scope and in any subsequent parent scopes.
For example:
// The global scope can "see" P1 and P2
/*
        p1
        // P1 can see P2, P3 and P4
        p3
        // P3 can see P1, P2 and P4 though invoking
        // P1 will likely result in infinite recursion
        F L
        q
        p4
        // Similar rules apply to P4 as they do in P3
        F P3
        q

        F P4
        q
        p2
        // P2 can "see" P1 and therefore can invoke P1 if it wishes
        F3 R
        q

        (
        P1 P2
        )2 // Execute both globally defined patterns twice

Furthermore, an RSU program is still valid if more than one pattern with
the same identifier is defined provided that they all appear in different scopes.
In case of an identifier conflict between two patterns of different scopes,
the definition of the pattern in the innermost scope takes precedence.
For example:

        p1
        p1
        F R
        q

        F2 P1 // Refers to "inner" (locally defined) P1 so no infinite recursion results
        q

        (
        F2 P1 // Refers to "outer" (global) P1 since the
        // global scope can't "see" local P1
        )4

Equivalent to executing the following raw commands:
F F F F F R F F F F F R F F F F F R F F F F F R
However, pattern redefinition in the same scope is not allowed
and should throw an error at some stage (more details later).

Finally ...
... no other character sequences are allowed in an RSU program,
such as "stray comments" as in this is a stray comment not escaped
by a double slash or slash followed by asterisk F F F L F F F R F F F L F F F R
and lowercase "flr" are not acceptable as commands or "stray numbers" as in F 32R 298984.
Also, some edge cases in case you're wondering:
Zero postfixes (e.g. F0, L0 and of course P0) are allowed (F0 and L0 do nothing
while P0 invokes pattern with identifier n = 0).
Empty pattern definitions / bracketed repeat sequences p0 /* (empty) */ /*q, (), ()23 are allowed.
Leading zeroes (except for the number 0 itself) should not be allowed -
these errors should be thrown during the tokenizing process (more details later)
as a "token" containing a number with leading zeroes is an invalid token.
Pattern definitions can contain brackets within them (of course!) but bracketed sequences must
NOT contain any pattern definitions.
Such errors should be detected in the second stage of RSU code processing.
Calls to infinitely recursive patterns and/or non-existent patterns within
a bracketed sequence that is executed 0 times should not throw an error; they should simply be ignored.

RSU Code Executor - Structure

In this Kata, your RSU code executor should be a class RSUProgram
with a class constructor which accepts a string source,
the RSU source code to be executed.
No error checking is required in this part.
NOTE: All methods described below must be properly implemented and will be tested
extensively in the Submit tests - namely, getTokens, convertToRaw, executeRaw and execute
(or the equivalent function/method names in your language, according to your language's naming convention(s)).
If in doubt, you may always refer to the Sample Test Cases to get an idea of what will be tested in the Sumbit tests.

Tokenizer

Your class should have an instance method getTokens which accepts no arguments and
returns the tokens present in source (argument passed to class constructor) in order as an array.
The tokenizer should ignore all whitespace characters
(except when they prevent a token from being identified, e.g. in ) 2)
and comments and should throw if it encounters one or more invalid tokens in the process.
The following tokens are the only valid tokens:
Single commands F, L and R
Commands with repeats Fn, Ln and Rn
(n being a non-negative integer which may exceed 1 digit but may not contain any leading 0s)
Opening round brackets (
Closing round brackets, with or without a repeat prefix ) OR )n
(n a non-negative integer with the rules described above)
Pattern definition pn (n a non-negative integer ... )
End of pattern definition q
Pattern invocation Pn (n a non-negative integer ... )
During the tokenizing process, do not perform any form of analysis checking the order
of the tokens or whether a particular pattern invoked actually exists, etc.
Such errors should be left to later stages.

Compiler

Your class should have an instance method convertToRaw which accepts 1 argument tokens
(an array of tokens, ideally returned from the tokenizer in the previous step)
and returns an array of raw commands from processing the tokens.
"Raw commands" are the most basic commands that can be understood by the robot natively,
namely F, L and R (nothing else, not even with prefixes such as F2 or R5).
For example, the following RS3-compliant program from the Story:
p0FFLFFR((FFFR)2(FFFFFL)3)4qp1FRqp2FP1qp3FP2qp4FP3qP0P1P2P3P4
... can be converted into the following "raw commands"
after its tokenized form is passed in to this instance method (indented for better visualization):
        [
        "F", "F", "L", "F", "F", "R",
        "F", "F", "F", "R", "F", "F", "F", "R",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "R", "F", "F", "F", "R",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "R", "F", "F", "F", "R",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "R", "F", "F", "F", "R",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "F", "F", "F", "F", "L",
        "F", "R",
        "F", "F", "R",
        "F", "F", "F", "R",
        "F", "F", "F", "F", "R"
        ]

See the "Sample Tests" for more examples.
Remember from RS3 that placing a pattern invocation before its definition is valid,
e.g. P0P1P2P3P4p0FFLFFR((FFFR)2(FFFFFL)3)4qp1FRqp2FP1qp3FP2qp4FP3q should produce the same result as the above program.
On the other hand, the following RSU programs are invalid and should throw an error at this stage:
Unmatched bracketing and/or pattern definition sequences, e.g. (p0q or p1(q)34 (an obvious syntax error)
Nesting pattern definitions within bracketed sequences, e.g. (p0/* ... *//*q).
This should be treated as a syntax error and as such, it should not appear anywhere within the program,
even if it is nested within multiple pattern definitions and never invoked.
Attempting to invoke a non-existent pattern or one that invokes a
non-existing pattern definition in its pattern body, etc., in the global scope
Attempting to invoke an infinitely recursive pattern of any form
(including non-recursive patterns which call on infinitely recursive patterns in their pattern body, etc.)
in the global scope.
Extreme cases (e.g. 500 levels of non-infinite recursion)
will not be tested in the test cases so a sensible recursion limit will do.
As for the input token array tokens,
you may assume that it will always be valid provided that your tokenizer is working properly ;)

Machine Instruction Executor

Now that you've implemented the most challenging part of your RSUProgram class,
it is time to wind down a little and implement something more straightforward :)
Your class should have an instance method executeRaw which receives an array of
raw commands (consisting of only F, L and/or R) returned from your compiler
and returns a string representation of the path that the MyRobot walks on the floor.
This string representation is identical in format as the ones required in Kata #2 through #4 of this Series.
For example, the raw commands (comparable to machine instructions/code in computers) obtained from this program:

/*
  RoboScript Ultimatum (RSU)
  A simple and comprehensive code example
*/

// Define a new pattern with identifier n = 0
//      p0
        // The commands below causes the MyRobot to move
        // in a short snake-like path upwards if executed
//     (
//      F2 L // Go forwards two steps and then turn left
//     )2 (
//     F2 R // Go forwards two steps and then turn right
//      )2
//      q

// Execute the snake-like pattern twice to generate
// a longer snake-like pattern
//     (
//      P0
//      )2
/*
... should evaluate to the string "* \r\n* \r\n***\r\n *\r\n***\r\n* \r\n***\r\n *\r\n***".
Once again, you may assume that the array of raw commands passed in
will always be valid provided that your tokenizer and compiler are both working properly.
Quick Tip:
If you have completed Kata #2 of this Series (Implement the RS1 Specification),
you may pass this section by simply copying and pasting your solution to that Kata here and making minor modifications.

One-Step Execution

Hooray - you have successfully implemented an RSU-compliant code executor!
In order to tidy things up a little, define an instance method execute
which accepts no arguments and combines the three previous instance methods
in a way such that when this method is invoked
(without invoking any other methods before it), it tokenizes, compiles and executes the source
(from the constructor) in one go and returns the string representation of the MyRobot's path.
For example:

        List<String> lst = Arrays.asList(
        "/*",
        "  RoboScript Ultimatum (RSU)",
        "  A simple and comprehensive code example",
        "*//*",
        "// Define a new pattern with identifier n = 0",
        "p0",
        "  // The commands below causes the MyRobot to move",
        "  // in a short snake-like path upwards if executed",
        "  (",
        "    F2 L // Go forwards two steps and then turn left",
        "  )2 (",
        "    F2 R // Go forwards two steps and then turn right",
        "  )2",
        "q",
        "// Execute the snake-like pattern twice to generate",
        "// a longer snake-like pattern",
        "(",
        "  P0",
        ")2");

new RSUProgram(String.join("\n",lst)).execute(); // => "*  \r\n*  \r\n***\r\n  *\r\n***\r\n*  \r\n***\r\n  *\r\n***"
 */
 
import java.util.*;

public class RSUProgram {

    private final String source;

    public RSUProgram(String src) {
        source = src;
    }

    public List<String> getTokens() {
        return new Tokenizer(source).getTokens();
    }

    public List<String> convertToRaw(List<String> tokens) {
        return new Compiler(tokens).convertToRaw();
    }

    public String executeRaw(List<String> commands) {
        return new Executor(commands).executeRaw();
    }

    public String execute() {
        List<String> tokens = getTokens();
        List<String> commands = convertToRaw(tokens);
        return executeRaw(commands);
    }
}

class Tokenizer {

    private static final String ILLEGAL_FOLLOWING_NUMBER_MSG =
            "There is an illegal token %s, because the following number starts with zero";
    private static final String PATTERN_WITHOUT_IDENTIFIER_MSG =
            "Pattern %s at index %d has no its own identifier";
    private static final String UNEXPECTED_SYMBOL_MSG =
            "Unexpected symbol \'%s\' at index %d";
    private static final String ILLEGAL_DEFINITION_OF_MULTILINE_COMMENT_MSG =
            "Unclosed multiline comment starting at index %d";
    private static final String INVALID_TOKEN_MSG =
            "Invalid token \"%s\" at index %d";

    private final String source;
    private List<String> tokens;

    Tokenizer(String source) {
        this.source = source;
        tokens = new ArrayList<>();
    }

    List<String> getTokens() {
        for (int i = 0; i < source.length(); i++) {
            String currentCharacter = source.substring(i, i + 1);
            //These tokens may or may not have a following number
            if (currentCharacter.matches("[FLR)]")) {
                String number = getFollowingNumber(i + 1);
                if (number.equals("")) {
                    tokens.add(currentCharacter);
                } else {
                    if (isValidNumber(number)){
                        tokens.add(currentCharacter + number);
                        i += number.length();
                    } else {
                        throw new RuntimeException(String.format(ILLEGAL_FOLLOWING_NUMBER_MSG, currentCharacter + number));
                    }
                }
            //These tokens haven't postfix indexes
            } else if (currentCharacter.matches("[(q]")) {
                tokens.add(currentCharacter);
            //These tokens must have their own identifiers
            } else if (currentCharacter.matches("[Pp]")) {
                String number = getFollowingNumber(i + 1);
                if (number.equals("")) {
                    throw new RuntimeException(String.format(PATTERN_WITHOUT_IDENTIFIER_MSG, currentCharacter.equals("p") ? "definition" : "call", i));
                } else if (!isValidNumber(number)) {
                    throw new RuntimeException(String.format(ILLEGAL_FOLLOWING_NUMBER_MSG, currentCharacter + number));
                } else {
                    tokens.add(currentCharacter + number);
                    i += number.length();
                }
            //It's a possible start of the one-/multi- line comment
            } else if (currentCharacter.equals("/")) {
                int nextIndex = getEndOfComment(i) + 1;
                if (isRuiningToken(nextIndex)) {
                    throw new RuntimeException(String.format(INVALID_TOKEN_MSG, source.substring(i, nextIndex + 1), i));
                } else i = nextIndex - 1;
            //Check that whitespace don't ruin current token
            } else if (currentCharacter.matches("\\s")) {
                if (isRuiningToken(i + 1)) {
                    throw new RuntimeException(String.format(INVALID_TOKEN_MSG, source.substring(i, i + 2), i));
                }
            } else throw new RuntimeException(String.format(UNEXPECTED_SYMBOL_MSG, currentCharacter, i));
        }

        return tokens;
    }

    /*
     * Returns a number, which placed after the current token from the source.
     * startIndex is an index of the first character after the current token (tokenIndex + 1).
     */
    private String getFollowingNumber(int startIndex) {
        StringBuilder followingNumber = new StringBuilder();
        for (int i = startIndex; i < source.length(); i++) {
            int codePoint = source.codePointAt(i);
            if(codePoint > 47 && codePoint < 58)
                followingNumber.append(source.charAt(i));
            else break;
        }

        return followingNumber.toString();
    }

    /*
     * The number is valid if its length == 1 or its length > 1 and its doesn't start from zero.
     */
    private boolean isValidNumber(String number) {
        return !(number.startsWith("0") && number.length() > 1);
    }

    /*
     * Returns index of the last character of the comment.
     * Throws exception if the comment is incorrect.
     */
    private int getEndOfComment(int startIndex) {
        int lastCharacterIndex;

        if (source.length() > startIndex + 1 && source.charAt(startIndex + 1) == '/') {
            String sub = source.substring(startIndex);
            if (!sub.contains("\n")) {
                lastCharacterIndex = source.length() - 1;
            } else {
                lastCharacterIndex = startIndex + sub.indexOf("\n");
            }
        } else if (source.length() > startIndex + 1 && source.charAt(startIndex + 1) == '*') {
            String sub = source.substring(startIndex);
            if (!sub.contains("*/")) {
                throw new RuntimeException(String.format(ILLEGAL_DEFINITION_OF_MULTILINE_COMMENT_MSG, startIndex));
            } else {
                lastCharacterIndex = startIndex + sub.indexOf("*/") + 1;
            }
        } else throw new RuntimeException(String.format(UNEXPECTED_SYMBOL_MSG, "/", startIndex));

        return lastCharacterIndex;
    }

    /*
     * Comment or whitespace is ruining token when the following character is digital.
     */
    private boolean isRuiningToken(int nextIndex) {
        return nextIndex < source.length() && source.substring(nextIndex, nextIndex + 1).matches("\\d");
    }
}

class Compiler {

    private final static String IDENTIFIERS_CONFLICT_MSG =
            "Pattern \"%s\" already exists";
    private static final String UNCLOSED_PATTERN_MSG =
            "There is an unclosed pattern in the program";
    private static final String SYNTAX_ERROR_MSG =
            "Syntax error in the %d token";
    private static final String UNCLOSED_BRACKET_MSG =
            "Source code has at least one unclosed bracket";
    private static final String BRACKETED_PATTERN_DEFINITION_MSG =
            "Pattern \"%s\" is enclosed in brackets";
    private static final String NONEXISTENT_PATTERNS_CALL_MSG =
            "Pattern \"%s\" doesn't exist";

    private static final String CLOSING_BRACKET_PATTERN = "\\)\\d*";
    private static final String SIMPLE_COMMAND_PATTERN = "[FLR]";
    private static final String N_SIMPLE_COMMANDS_PATTERN = "[FLR]\\d+";
    private static final String PATTERN_DEFINITION_PATTERN = "p\\d+";
    private static final String PATTERN_CALL_PATTERN = "P\\d+";

    private final List<String> tokens;
    private final RSPattern topOfPatternsHierarchy;//a parent of all patterns in current pattern's scoping
    private int deepOfCalls;

    private List<String> commands;
    private Map<String, RSPattern> patternsScoping;//containing all descendants of current top hierarchy's pattern

    Compiler(List<String> tokens) {
        this.tokens = tokens;
        commands = new ArrayList<>();
        topOfPatternsHierarchy = new RSPattern(new ArrayList<>(), null);
        patternsScoping = new HashMap<>();
        deepOfCalls = 0;
    }

    /*
     * Constructor for compile of any separate pattern.
     */
    Compiler(List<String> tokens, RSPattern parent, int deepOfCalls) {
        this.tokens = tokens;
        commands = new ArrayList<>();
        topOfPatternsHierarchy = parent;
        patternsScoping = new HashMap<>();
        this.deepOfCalls = deepOfCalls;
    }

    List<String> convertToRaw() {
        collectAllRSPatterns();
        tokensProcessing(tokens);

        return commands;
    }

    /*
     * Processing all tokens from source and appending results to List<String> commands.
     */
    private void tokensProcessing(List<String> source) {
        for (int i = 0; i < source.size(); i++) {
            String token = source.get(i);
            if (token.matches(SIMPLE_COMMAND_PATTERN)) {
                commands.add(token);
            } else if (token.matches(N_SIMPLE_COMMANDS_PATTERN)) {
                addTokenNTimes(token);
            } else if (token.matches(PATTERN_DEFINITION_PATTERN)) {
                i += patternsScoping.get(token).getSize() - 1;
            } else if (token.matches(PATTERN_CALL_PATTERN)) {
                patternsCallProcessing(token);
                deepOfCalls--;
            } else if (token.equals("(")) {
                simplifyBracketedExpression(collectBracketedExpression(i));
                i--;
            } else {
                throw new RuntimeException(String.format(SYNTAX_ERROR_MSG, i));
            }
        }
    }

    private void addTokenNTimes(String token) {
        String command = token.substring(0, 1);
        int times = Integer.parseInt(token.substring(1));
        for (int i = 0; i < times; i++)
            commands.add(command);
    }

    private void collectAllRSPatterns() {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).matches(PATTERN_DEFINITION_PATTERN)) {
                List<String> pattern = collectRSPattern(i);
                checkPatternSyntax(pattern, i);
                createRSPattern(pattern);
                i += pattern.size() - 1;
            }
        }
    }

    /*
     * Returns tokens representation of the pattern, which starts from startIndex.
     * If pattern has no end, throws new exception.
     */
    private List<String> collectRSPattern(int startIndex) {
        List<String> pattern = new ArrayList<>();
        pattern.add(tokens.get(startIndex));
        int countOfUnclosedPatterns = 1;
        for (int i = startIndex + 1; i < tokens.size(); i++) {
            pattern.add(tokens.get(i));
            if (tokens.get(i).matches(PATTERN_DEFINITION_PATTERN)) {
                countOfUnclosedPatterns++;
            }
            if (tokens.get(i).equals("q")) {
                if (--countOfUnclosedPatterns == 0) {
                    break;
                }
            }
        }

        if (countOfUnclosedPatterns != 0) {
            throw new RuntimeException(UNCLOSED_PATTERN_MSG);
        }

        return pattern;
    }

    /*
     * Check if the pattern has occurrences with brackets or with nested patterns.
     * It's needed to validate the pattern even if it's not called.
     */
    private void checkPatternSyntax(List<String> pattern, int shift) {
        List<String> allPatternsInThisScope = new ArrayList<>();
        int countOfOpeningBrackets = 0;
        for (int i = 1; i < pattern.size(); i++) {
            if (pattern.get(i).equals("(")) {
                countOfOpeningBrackets++;
            }
            if (pattern.get(i).matches(PATTERN_DEFINITION_PATTERN)) {
                if (countOfOpeningBrackets > 0)
                    throw new RuntimeException(String.format(BRACKETED_PATTERN_DEFINITION_MSG, pattern.get(i)));
                if (allPatternsInThisScope.contains(pattern.get(i))) {
                    throw new RuntimeException(String.format(IDENTIFIERS_CONFLICT_MSG, pattern.get(i)));
                } else {
                    allPatternsInThisScope.add(pattern.get(i));
                    List<String> nested = collectRSPattern(shift + i);
                    checkPatternSyntax(nested, shift + i);
                    i += nested.size() - 1;
                }
            }
            if (pattern.get(i).matches(CLOSING_BRACKET_PATTERN)) {
                if(--countOfOpeningBrackets < 0)
                    throw new RuntimeException(String.format(SYNTAX_ERROR_MSG, i));
            }
        }
        if(countOfOpeningBrackets != 0) throw new RuntimeException(UNCLOSED_BRACKET_MSG);
    }

    /*
     * Creates new RSPattern and puts its into global scoping Map.
     * If pattern with the same identifier already exists, throws new exception.
     */
    private void createRSPattern(List<String> pattern) {
        if(patternsScoping.containsKey(pattern.get(0)))
            throw new RuntimeException(String.format(IDENTIFIERS_CONFLICT_MSG, pattern.get(0)));
        patternsScoping.put(pattern.get(0), new RSPattern(pattern, topOfPatternsHierarchy));
    }

    /*
     * Returns closed bracketed expression which may contain another brackets in itself,
     * otherwise throws new exception. Also throws exception if meets any pattern's definition.
     */
    private List<String> collectBracketedExpression(int startIndex) {
        int countOfOpeningBrackets = 1;
        int endIndex = startIndex;

        for (int i = startIndex + 1; i < tokens.size(); i++) {
            if (tokens.get(i).matches(PATTERN_DEFINITION_PATTERN)) {
                throw new RuntimeException(String.format(BRACKETED_PATTERN_DEFINITION_MSG, tokens.get(i)));
            }
            if (tokens.get(i).equals("(")) {
                countOfOpeningBrackets++;
            }
            if (tokens.get(i).matches(CLOSING_BRACKET_PATTERN)) {
                if (--countOfOpeningBrackets == 0) {
                    endIndex = i;
                    break;
                }
            }
        }

        if(countOfOpeningBrackets != 0)
            throw new RuntimeException(UNCLOSED_BRACKET_MSG);

        return tokens.subList(startIndex, endIndex + 1);
    }

    private void simplifyBracketedExpression(List<String> bracketedExpression) {
        ArrayList<Integer> openingBracketsPositions = new ArrayList<>();
        for (int i = 0; i < bracketedExpression.size(); i++) {
            if (bracketedExpression.get(i).equals("(")) {
                openingBracketsPositions.add(i);
            }
            if (bracketedExpression.get(i).matches(CLOSING_BRACKET_PATTERN)) {
                int openingBracketIndex = openingBracketsPositions.get(openingBracketsPositions.size() - 1);
                //if closing bracket has no postfix
                if (bracketedExpression.get(i).length() == 1) {
                    removePairOfBrackets(bracketedExpression, openingBracketIndex, i);
                    i -= 2;
                } else {
                    int times = Integer.parseInt(bracketedExpression.get(i).substring(1));
                    if (times == 0) {
                        bracketedExpression.subList(openingBracketIndex, i + 1).clear();
                        i = openingBracketIndex - 1;
                    } else {
                        removePairOfBrackets(bracketedExpression, openingBracketIndex, i);
                        List<String> subExpression = getObjectsInRange(bracketedExpression, openingBracketIndex, i - 1);
                        int insertionIndex = i - 1;
                        for (int j = 1; j < times; j++) {
                            bracketedExpression.addAll(insertionIndex, subExpression);
                            insertionIndex += subExpression.size();
                        }
                        i = insertionIndex - 1;
                    }
                }
                openingBracketsPositions.remove(openingBracketsPositions.size() - 1);
                if(openingBracketsPositions.isEmpty()) break;
            }
        }
    }

    private void removePairOfBrackets(List<String> source, int openingIndex, int closingIndex) {
        source.remove(closingIndex);
        source.remove(openingIndex);
    }

    private List<String> getObjectsInRange(List<String> source, int startPosition, int endPosition) {
        List<String> subSequence = new ArrayList<>();
        for (int j = startPosition; j < endPosition; j++) {
            subSequence.add(source.get(j));
        }

        return subSequence;
    }

    private RSPattern getGlobalPatternsScoping() {
        RSPattern globalScoping = topOfPatternsHierarchy;
        while (globalScoping.getParent() != null) {
            globalScoping = globalScoping.getParent();
        }

        return globalScoping;
    }

    private void patternsCallProcessing(String token) {
        List<String> compiledPattern = null;
        token = token.replaceFirst("P", "p");
        //search in the same scope with the pattern's call
        if (patternsScoping.containsKey(token)) {
            compiledPattern = patternsScoping.get(token).getCompiledRepresentation(++deepOfCalls);
        }
        //search in the parent RSPattern for the current scope
        else {
            RSPattern parent = topOfPatternsHierarchy.getParent();
            if(parent != null) {
                RSPattern descendant = parent.getDescendant(token);
                if (descendant != null) {
                    compiledPattern = descendant.getCompiledRepresentation(++deepOfCalls);
                }
                //search in the global scope
                else {
                    RSPattern globalScoping = getGlobalPatternsScoping();
                    RSPattern globalPattern = globalScoping.getDescendant(token);
                    if (globalPattern != null) {
                        compiledPattern = globalPattern.getCompiledRepresentation(++deepOfCalls);
                    }
                }
            }
        }

        if(compiledPattern == null)
            throw new RuntimeException(String.format(NONEXISTENT_PATTERNS_CALL_MSG, token));
        commands.addAll(compiledPattern);
    }
}

class Executor {

    private static final String SEPARATOR = "\r\n";
    private static final char MARK = '*';

    private final List<String> commands;
    private ArrayList<StringBuilder> grid;

    private int currentDirection;//R = 0, D = 1, L = 2, U = 3
    private int currentColumn;
    private int currentLine;

    Executor(List<String> commands) {
        this.commands = commands;
        grid = new ArrayList<>();
        grid.add(new StringBuilder("*"));
        currentDirection = 0;
        currentLine = 0;
        currentColumn = 0;
    }

    String executeRaw() {
        for (String command : commands) {
            switch (command) {
                case "F" : {
                    goForward();
                    break;
                }
                case "L" : {
                    turnLeft();
                    break;
                }
                case "R" : {
                    turnRight();
                    break;
                }
            }
        }

        return getStringMap();
    }

    private void turnLeft() {
        if(--currentDirection < 0) currentDirection += 4;
    }

    private void turnRight() {
        if(++currentDirection > 3) currentDirection -= 4;
    }

    private void goForward() {
        switch (currentDirection) {
            case 0 : {
                if (++currentColumn == grid.get(0).length()) {
                    extendRightBound();
                }
                makeStep();
                break;
            }
            case 1 : {
                if (++currentLine == grid.size()) {
                    extendBottomBound();
                }
                makeStep();
                break;
            }
            case 2 : {
                if (--currentColumn < 0) {
                    extendLeftBound();
                }
                makeStep();
                break;
            }
            case 3 : {
                if (--currentLine < 0) {
                    extendUpperBound();
                }
                makeStep();
                break;
            }
        }
    }

    private void extendRightBound() {
        for (StringBuilder line : grid) {
            line.append(" ");
        }
    }

    private void extendLeftBound() {
        for (StringBuilder line : grid) {
            line.insert(0, " ");
        }
        currentColumn = 0;
    }

    private void extendUpperBound() {
        grid.add(0, new StringBuilder().append(" ".repeat(grid.get(0).length())));
        currentLine = 0;
    }

    private void extendBottomBound() {
        grid.add(new StringBuilder().append(" ".repeat(grid.get(0).length())));
    }

    private void makeStep() {
        grid.get(currentLine).setCharAt(currentColumn, MARK);
    }

    private String getStringMap() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < grid.size() - 1; i++) {
            result.append(grid.get(i)).append(SEPARATOR);
        }

        return result.append(grid.get(grid.size() - 1)).toString();
    }
}

class RSPattern {

    private final List<String> pattern;
    private final int size;

    private final RSPattern parent;
    private List<RSPattern> descendants;

    private List<String> compiled;
    private boolean isCompiled;

    RSPattern(List<String> pattern, RSPattern parent) {
        this.pattern = pattern;
        size = pattern.size();
        isCompiled = false;
        this.parent = parent;
        addDescendantToItsParent();
        descendants = new ArrayList<>();
    }

    List<String> getCompiledRepresentation(int deepOfCalls) {
        if (deepOfCalls == 150) throw new RuntimeException("There is a recursive call in the program");
        return isCompiled ? compiled : compilationOfPattern(deepOfCalls);
    }

    private List<String> compilationOfPattern(int deepOfCalls) {
        compiled = new Compiler(pattern.subList(1, pattern.size() - 1), this, deepOfCalls).convertToRaw();
        isCompiled = true;

        return compiled;
    }

    RSPattern getDescendant(String token) {
        for (RSPattern rsp : descendants) {
            if (rsp.pattern.get(0).equals(token)) {
                return rsp;
            }
        }

        return null;
    }

    RSPattern getParent() {
        return parent;
    }

    int getSize() {
        return size;
    }

    private void addDescendantToItsParent() {
        if(this.parent != null)
            this.parent.descendants.add(this);
    }
}