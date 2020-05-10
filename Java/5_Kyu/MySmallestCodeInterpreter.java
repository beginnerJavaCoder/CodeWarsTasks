/*
Original task - https://www.codewars.com/kata/526156943dfe7ce06200063e

Inspired from real-world Brainf**k, 
we want to create an interpreter of that language which will support the following instructions:

> increment the data pointer (to point to the next cell to the right).
< decrement the data pointer (to point to the next cell to the left).
+ increment (increase by one, truncate overflow: 255 + 1 = 0) the byte at the data pointer.
- decrement (decrease by one, treat as unsigned byte: 0 - 1 = 255 ) the byte at the data pointer.
. output the byte at the data pointer.
, accept one byte of input, storing its value in the byte at the data pointer.
[ if the byte at the data pointer is zero, 
then instead of moving the instruction pointer forward to the next command, 
jump it forward to the command after the matching ] command.
] if the byte at the data pointer is nonzero, 
then instead of moving the instruction pointer forward to the next command, 
jump it back to the command after the matching [ command.

The function will take in input...
the program code, a string with the sequence of machine instructions,
the program input, a string, eventually empty, 
that will be interpreted as an array of bytes using each character's ASCII code and will be consumed by the , instruction

... and will return ...
the output of the interpreted code (always as a string), produced by the . instruction.
Implementation-specific details for this Kata:

Your memory tape should be large enough - the original implementation had 30,000 cells 
but a few thousand should suffice for this Kata
Each cell should hold an unsigned byte 
with wrapping behavior (i.e. 255 + 1 = 0, 0 - 1 = 255), initialized to 0
The memory pointer should initially point to a cell in the tape 
with a sufficient number (e.g. a few thousand or more) of cells to its right. 
For convenience, you may want to have it point to the leftmost cell initially
You may assume that the , command will never be invoked when the input stream is exhausted
Error-handling, e.g. unmatched square brackets and/or 
memory pointer going past the leftmost cell is not required in this Kata. 
If you see test cases that require you to perform error-handling 
then please open an Issue in the Discourse for this Kata 
(don't forget to state which programming language you are attempting this Kata in).
 */
 
import java.util.ArrayList;

public class BrainLuck {

    private String source;
    private StringBuilder output;
    private String input;
    private int[] data;
    private int pointer;
    private int inputPtr;
    private int instructionPtr;
    ArrayList<Integer> startLoopIndices;

    public BrainLuck(String source) {
        this.source = source;
        data = new int[30000];
        pointer = 15000;
    }

    public String process(String input) {
        initProcess(input);
        
        for (instructionPtr = 0; instructionPtr < source.length(); instructionPtr++) {
            switch (source.charAt(instructionPtr)) {
                case '>' : {
                    incPtr();
                    break;
                }
                case '<' : {
                    decPtr();
                    break;
                }
                case '+' : {
                    incData();
                    break;
                }
                case '-' : {
                    decData();
                    break;
                }
                case '.' : {
                    output();
                    break;
                }
                case ',' : {
                    input();
                    break;
                }
                case '[' : {
                    loop();
                    break;
                }
                case ']' : {
                    closeLoop();
                    break;
                }
            }
        }

        return output.toString();
    }

    private void incPtr() {
        pointer++;
    }

    private void decPtr() {
        pointer--;
    }

    private void incData() {
        if (data[pointer] == 255) {
            data[pointer] = 0;
        } else {
            data[pointer]++;
        }
    }

    private void decData() {
        if (data[pointer] == 0) {
            data[pointer] = 255;
        } else {
            data[pointer]--;
        }
    }

    private void output() {
        output.append((char) data[pointer]);
    }

    private void input() {
        data[pointer] = input.charAt(inputPtr++);
    }

    private void loop() {
        if(data[pointer] == 0) {
            findLoopEnd();
        } else {
            startLoopIndices.add(instructionPtr);
        }
    }

    private void closeLoop() {
        if (data[pointer] != 0) {
            instructionPtr = startLoopIndices.get(startLoopIndices.size() - 1);
        } else {
            startLoopIndices.remove(startLoopIndices.size() - 1);
        }
    }

    private void findLoopEnd() {
        int countOfStarts = 0;
        for (int i = instructionPtr + 1; i < source.length(); i++) {
            if (source.charAt(i) == '[') {
                countOfStarts++;
            }
            if (source.charAt(i) == ']') {
                if (countOfStarts != 0) {
                    countOfStarts--;
                } else {
                    instructionPtr = i;
                    break;
                }
            }
        }

    }

    private void refreshOutput() {
        output = new StringBuilder();
    }

    private void setInput(String input) {
        this.input = input;
        inputPtr = 0;
    }

    private void initProcess(String input) {
        refreshOutput();
        setInput(input);
        startLoopIndices = new ArrayList<>();
    }
}
