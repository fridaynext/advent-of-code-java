package com.fridaynext.adventofcode.model;

import com.fridaynext.adventofcode.repository.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Numbers {
    public static void main(String[] args) throws IOException {
        FileReader xmasNumbers = new FileReader("xmas.txt");

        // Need a register of 25 to hold previous nums
        ArrayList<Double> prevTwentyFive = new ArrayList<>();
        int index = 0;
        for (String xmasNumber : xmasNumbers.getFileLines() ) {
            // Convert it to a number
            double xmasInt = Double.parseDouble(xmasNumber);

            if (prevTwentyFive.size() < 25 && index < 25) {
                prevTwentyFive.add(xmasInt);
            } else {
                prevTwentyFive.add(xmasInt);
                // Check if the current number could be the sum of any two previous numbers
                boolean foundMatch = false;
                for (int i = 0; i < 26; i++) {
                    for (int j=0; j < 26; j++) {
                        if (j != i) {
                            if (prevTwentyFive.get(i) + prevTwentyFive.get(j) == xmasInt) {
                                foundMatch = true;
                                System.out.println("First num: " + prevTwentyFive.get(i) + ", Second num: " + prevTwentyFive.get(j) + ", sum: " + xmasInt);
                            }
                        }
                    }
                }
                // now we've checked all the numbers, let's see if we found a match
                if (!foundMatch) {
                    double smallestNum = 0;
                    for (double thisNum : prevTwentyFive) {

                    }
                    System.out.println(xmasInt + " did not pass the test");
                }

                // we have 25, so let's start popping these puppies out
                prevTwentyFive.remove(0);
            }
            System.out.println("New array length is: " + prevTwentyFive.size());

            index++;
        }
    }
}
