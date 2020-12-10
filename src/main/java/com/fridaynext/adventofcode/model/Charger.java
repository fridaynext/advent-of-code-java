package com.fridaynext.adventofcode.model;

import com.fridaynext.adventofcode.repository.FileReader;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Charger {
    // to provide all the jolts
    public static void main(String[] args) throws IOException {
        FileReader joltLines = new FileReader("jolts.txt");
        ArrayList<Integer> jolts = new ArrayList<>();
        int maxAdapter = 0;
        for (String jolt : joltLines.getFileLines()) {
            int thisAdapter = Integer.parseInt(jolt);
            maxAdapter = Math.max(thisAdapter, maxAdapter);
            jolts.add(thisAdapter);
        }
        maxAdapter += 3; // this is the device voltage
        // now sort the list in order
        Collections.sort(jolts);
        System.out.println(jolts);
        int ones = 1; int threes = 1;
        int contiguousOnes = 1;
        boolean lightSwitch = true;
        ArrayList<Integer> powers = new ArrayList<>();
        for (int i = 1; i < jolts.size(); i++) {
            if (jolts.get(i) - jolts.get(i-1) == 1) {
                if (!lightSwitch) {
                    contiguousOnes = 1;
                }
                ones++;
                contiguousOnes++;
                lightSwitch = true;
            } else if(jolts.get(i) - jolts.get(i-1) == 3) {
                if (lightSwitch) {
                    System.out.println("contiguous ones were: " + contiguousOnes);
                    powers.add(contiguousOnes);
                }
                threes++;
                lightSwitch = false;
            }
        }

        System.out.println("Ones: " + ones + ", Threes: " + threes);

        double totalArrangements = 1;
        boolean theresMore = true;
        for (int power : powers) {
            totalArrangements *= Math.pow(2, power) - 1;
        }

        System.out.printf("Total arrangements: %.0f\n", totalArrangements);
    }
}
