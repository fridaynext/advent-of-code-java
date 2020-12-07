package com.fridaynext.adventofcode.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    // Used for the customs answers problem
    private String yesAnswers;
    private List<Character> individualAnswers;

    // Constructor
    public Person() {}
    public Person(String yesAnswers) {
        this.yesAnswers = yesAnswers;
        this.individualAnswers = decodeAnswers();
    }

    // method to create a list of individual answers
    public List<Character> decodeAnswers() {
        // separate into individual answers
        char[] eachAnswer = this.yesAnswers.toCharArray();
        List<Character> individualAns = new ArrayList<>();
        for (char thisAnswer : eachAnswer) {
            if (!(thisAnswer == ' ')) {
                individualAns.add(thisAnswer);
            }
        }
        return individualAns;
    }

    // Getters and Setters
    public String getYesAnswers() {
        return yesAnswers;
    }

    public void setYesAnswers(String yesAnswers) {
        this.yesAnswers = yesAnswers;
    }

    public List<Character> getIndividualAnswers() {
        return individualAnswers;
    }

    public void setIndividualAnswers(List<Character> individualAnswers) {
        this.individualAnswers = individualAnswers;
    }
}
