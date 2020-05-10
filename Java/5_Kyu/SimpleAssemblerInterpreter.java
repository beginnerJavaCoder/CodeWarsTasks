/*
Original task - https://www.codewars.com/kata/58e24788e24ddee28e000053

This is the first part of this kata series.

We want to create a simple interpreter of assembler which will support the following instructions:

mov x y - copies y (either a constant value or the content of a register) into register x
inc x - increases the content of the register x by one
dec x - decreases the content of the register x by one
jnz x y - jumps to an instruction y steps away (positive means forward, negative means backward, 
y can be a register or a constant), but only if x (a constant or a register) is not zero
Register names are alphabetical (letters only). Constants are always integers (positive or negative).

Note: the jnz instruction moves relative to itself. 
For example, an offset of -1 would continue at the previous instruction, 
while an offset of 2 would skip over the next instruction.

The function will take an input list with the sequence of the program instructions and will execute them. 
The program ends when there are no more instructions to execute, 
then it returns a dictionary with the contents of the registers.

Also, every inc/dec/jnz on a register will always be preceeded by a mov on the register first, 
so you don't need to worry about uninitialized registers.

Example
SimpleAssebmler.interpret(new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"});

''' visualized:
mov a 5
inc a
dec a
dec a
jnz a -1
inc a
''''

The above code will:
set register a to 5,
increase its value by 1,
decrease its value by 2,
then decrease its value until it is zero 
(jnz a -1 jumps to the previous instruction if a is not zero)
and then increase its value by 1, leaving register a at 1

So, the function should return
{a=1}
 */
 
import java.util.HashMap;
import java.util.Map;

public class SimpleAssembler {

    private static Integer currentCommandIndex;
    private static Map<String, Integer> registers;

    public static Map<String, Integer> interpret(String[] program) {
        registers = new HashMap<>();
        currentCommandIndex = 0;
        while (currentCommandIndex < program.length) {
            parseCommand(program[currentCommandIndex]);
        }

        return registers;
    }

    private static void parseCommand(String command) {
        String[] partsOfCommand = command.split("\\s");
        switch (partsOfCommand[0]) {
            case "mov" : {
                mov(partsOfCommand[1], partsOfCommand[2]);
                break;
            }
            case "inc" : {
                inc(partsOfCommand[1]);
                break;
            }
            case "dec" : {
                dec(partsOfCommand[1]);
                break;
            }
            case "jnz" : {
                jnz(partsOfCommand[1], Integer.parseInt(partsOfCommand[2]));
                break;
            }
        }
    }

    private static void mov(String register, String value) {
        if (isNumerical(value)) {
            registers.put(register, Integer.parseInt(value));
        } else {
            registers.put(register, registers.get(value));
        }
        currentCommandIndex++;
    }

    private static void inc(String register) {
        registers.put(register, registers.get(register) + 1);
        currentCommandIndex++;
    }

    private static void dec(String register) {
        registers.put(register, registers.get(register) - 1);
        currentCommandIndex++;
    }

    private static void jnz(String value, Integer quantityOfSteps) {
        if (isNumerical(value)) {
            indexAugmentation(Integer.parseInt(value), quantityOfSteps);
        } else {
            indexAugmentation(registers.get(value), quantityOfSteps);
        }
    }

    private static boolean isNumerical(String string) {
        return string.matches("-?\\d+");
    }

    private static void indexAugmentation(Integer valueOfJnz, Integer steps) {
        if (valueOfJnz != 0) {
            currentCommandIndex += steps;
        } else {
            currentCommandIndex++;
        }
    }
}
