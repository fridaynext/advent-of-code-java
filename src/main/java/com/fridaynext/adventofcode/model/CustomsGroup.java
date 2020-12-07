package com.fridaynext.adventofcode.model;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CustomsGroup {
    // contains a string with all the 'yes' answers
    private List<Person> people;
    private Set<Character> uniqueQuestionsAnswered;
    private int numUniqueAnswers;

    public CustomsGroup() {}
    public CustomsGroup(List<Person> people) {
        this.people = people;
        uniqueQuestionsAnswered = updateUniqueAnswers(this.people);
        this.numUniqueAnswers = this.uniqueQuestionsAnswered != null ? this.numUniqueAnswers : 0;
    }

    // Custom add person method
    public void addPerson(Person newPerson) {
        List<Person> existingPeeps = new ArrayList<>();
        if (getPeople() != null) {
            existingPeeps = getPeople();
        }
        existingPeeps.add(newPerson);
        this.setPeople(existingPeeps);
        this.uniqueQuestionsAnswered = updateUniqueAnswers(this.people);
        this.numUniqueAnswers = this.uniqueQuestionsAnswered.size();
    }

    public Set<Character> updateUniqueAnswers(List<Person> people) {
        Set<Character> theseAnswers = new LinkedHashSet<>();
        for (Person thisPerson : people) {
            // get this person's answers
            theseAnswers.addAll(thisPerson.getIndividualAnswers());
        }
        return theseAnswers;
    }

    public int getNumUnanimousAnswers() {
        StringBuilder allAnswers = new StringBuilder();

        for (Person single : this.people) {
            allAnswers.append(single.getYesAnswers());

        }
        System.out.println("All yes answers for this group: " + allAnswers.toString());
        int numRepeats = 0;
        for (char letter : this.people.get(0).getIndividualAnswers()) {
            System.out.println("this letter: " + (letter+""));
            int thisLetter = StringUtils.countOccurrencesOf(allAnswers.toString(), (letter+""));
            numRepeats += thisLetter == this.people.size() ? 1 : 0;
        }
        return numRepeats;
    }

    // Getters & Setters
    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public Set<Character> getUniqueQuestionsAnswered() {
        return uniqueQuestionsAnswered;
    }

    public void setUniqueQuestionsAnswered(LinkedHashSet<Character> uniqueQuestionsAnswered) {
        this.uniqueQuestionsAnswered = uniqueQuestionsAnswered;
    }

    public int getNumUniqueAnswers() {
        return numUniqueAnswers;
    }

    public void setNumUniqueAnswers(int numUniqueAnswers) {
        this.numUniqueAnswers = numUniqueAnswers;
    }
}
