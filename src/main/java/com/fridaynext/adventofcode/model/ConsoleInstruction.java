package com.fridaynext.adventofcode.model;

import com.fridaynext.adventofcode.repository.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInstruction {
    private String instruction;
    private int modifier;

    // zero-argument constructor
    public ConsoleInstruction() {}
    public ConsoleInstruction(String instruction, int modifier) {
        this.instruction = instruction;
        this.modifier = modifier;
    }

    // take in a single line from the file and extract the values to create a ConsoleInstruction
    public ConsoleInstruction(String rawText) {
        // separate two strings by a space
        String[] values = rawText.split(" ");

        // first value will be the instruction
        String thisInstruction = values[0];

        // second value is the modifier - need to separate the + / - from the actual number
        String combiner = values[1].substring(0,1);
        int value = combiner.equals("+") ? Integer.parseInt(values[1].substring(1)) :  - Integer.parseInt(values[1].substring(1));

        // create the instance now
        this.instruction = thisInstruction;
        this.modifier = value;
    }

    // Decode the input file and create lotsa instances of a Console Instruction
    public static void main(String[] args) throws IOException {
        FileReader handheldInstructions = new FileReader("handheld-console.txt");
        List<ConsoleInstruction> allInstructions = new ArrayList<>();

        // Loop through and instantiate all instructions
        for (String thisLine : handheldInstructions.getFileLines()) {
            ConsoleInstruction thisInstruction = new ConsoleInstruction(thisLine);
            allInstructions.add(thisInstruction);
        }

        // Keep track of the accumulator as you iterate through all instructions
        int accumulator = 0;
        int i = 0;
        boolean keepGoing = true;
        // Now go through each instruction and keep track of which have been performed
        List<Integer> usedIndices = new ArrayList<>();

        do {
            if (usedIndices.contains(i)) {
                keepGoing = false;
                System.out.println("index - " + i);
                System.out.println("Accumulator before infinite loop is: " + accumulator);
                break;
            } else {
                usedIndices.add(i);
                System.out.println("index - " + i);
                switch (allInstructions.get(i).getInstruction()) {
                    case "nop":
                        System.out.println("nop");
                        i++;
                        break;
                    case "jmp":
                        i += allInstructions.get(i).getModifier();
                        System.out.println("jmp - " + i);
                        break;
                    case "acc":
                        accumulator += allInstructions.get(i).getModifier();
                        System.out.println("acc = " + accumulator);
                        i++;
                        break;
                    default:
                        break;
                }
            }
        } while(keepGoing);
    }

    // Getters & Setters
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
}
