package com.fridaynext.adventofcode.model;

import com.fridaynext.adventofcode.repository.FileReader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Bag {
    // Bags should have a color, and a group of bags (or none) that they contain
    private String color;
    private List<Bag> interiorBags;
    private int quantity;

    public Bag() {}
    public Bag(String color, List<Bag> interiorBags) {
        this.quantity = 1;
        this.color = color;
        this.interiorBags = interiorBags;
    }

    // The meat and potatoes shall reside here
    public static void main(String[] args) throws IOException {
        FileReader bagReader = new FileReader("handy-haversacks.txt");

        // Store all the bags we find
        List<Bag> allTheBags = new ArrayList<>();
        for (String thisLine : bagReader.getFileLines()) {
            // get the color of the outer bag
            System.out.println(thisLine);
            int firstStep = thisLine.indexOf("contain");

            // first half
            String outerBagColor = thisLine.substring(0, thisLine.indexOf(" bags"));
            Bag outerBag = new Bag();
            outerBag.setColor(outerBagColor);
            List<Bag> theseBags = new ArrayList<>();


            // index 9 to start inner contents
            String innerContents = thisLine.substring(firstStep+8);
            System.out.println("Inner contents: " + innerContents);
            // get the other bags separated by commas
            String[] innerBags = innerContents.split(", ");
            for (String bag : innerBags) {
                // quantity, space color
                Bag thisBag = new Bag();
                if (bag.equals("no other bags.")) {
                    // nothing inside, nothing to assign
                } else {
                    thisBag.setQuantity(Integer.parseInt(bag.substring(0,1)));
                    thisBag.setColor(bag.substring(2, bag.indexOf(" bag")));
                    theseBags.add(thisBag);
                }
            }
            outerBag.setInteriorBags(theseBags);
            allTheBags.add(outerBag);
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Bag> getInteriorBags() {
        return interiorBags;
    }

    public void setInteriorBags(List<Bag> interiorBags) {
        this.interiorBags = interiorBags;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
