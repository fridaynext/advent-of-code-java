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
        double invalidNum = 0;
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
                    double biggestNum = 0;
                    for (double thisNum : prevTwentyFive) {
                        if (thisNum > biggestNum) {
                            biggestNum = thisNum;
                        }
                    }
                    smallestNum = biggestNum;
                    for (double thisNum : prevTwentyFive) {
                        if (thisNum < smallestNum) {
                            smallestNum = thisNum;
                        }
                    }
                    // System.out.println("Sum of magic = " + (smallestNum + biggestNum));
                    // System.out.printf("Sum of Magic: %.0f\n", (smallestNum + biggestNum));
                    System.out.println(xmasInt + " did not pass the test");
                    invalidNum = xmasInt;
                    break;
                }

                // we have 25, so let's start popping these puppies out
                prevTwentyFive.remove(0);
            }
            System.out.println("New array length is: " + prevTwentyFive.size());

            index++;
        }
        ArrayList<Double> storedNums = new ArrayList<>();
        for (String xmasNumber : xmasNumbers.getFileLines() ) {
            // invalidNum is what we're looking for
            storedNums.add(Double.parseDouble(xmasNumber));
            if (storedNums.size() > 2) {
                double storageSum = 0;
                ArrayList<Double> currentLookie = new ArrayList<>();
                for (int i = storedNums.size()-1; i >= 0; i--) {
                    currentLookie.add(storedNums.get(i));
                    storageSum += storedNums.get(i);
                    if (storageSum == invalidNum) {
                        // we found it!
                        double smallestNum = 0;
                        double biggestNum = 0;
                        for (double thisNum : currentLookie) {
                            if (thisNum > biggestNum) {
                                biggestNum = thisNum;
                            }
                        }
                        smallestNum = biggestNum;
                        for (double thisNum : currentLookie) {
                            if (thisNum < smallestNum) {
                                smallestNum = thisNum;
                            }
                        }
                        System.out.printf("The encryption weakness is: %.0f\n", (smallestNum+biggestNum));
                    }
                }
                currentLookie.clear();
            }

        }
    }
}
