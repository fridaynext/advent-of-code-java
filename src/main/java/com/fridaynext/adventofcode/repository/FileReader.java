package com.fridaynext.adventofcode.repository;

import com.fridaynext.adventofcode.model.CustomsGroup;
import com.fridaynext.adventofcode.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileReader {

    // read in the file and parse it
    private Path filePath;
    private List<String> fileLines;
    private String fileName;

    public static void main(String[] args) throws IOException {
        // Just doing things in main for now - classy classes come consequentially

        // Create an arraylist to hold all the groups
        List<CustomsGroup> allGroups = new ArrayList<>();
        CustomsGroup tempGroup = new CustomsGroup();

        int tot = 0;
        // for customs questionnaire, we'll be returning a list of question groups
        FileReader customsReader = new FileReader("customs-questions.txt");
        for (int i = 0; i < customsReader.fileLines.size(); i++) {
            // grab all answers until reaching a blank line
            if (customsReader.fileLines.get(i).equals("")) {
                // we've reached a blank line, so increment the group index
                tot += tempGroup.getNumUniqueAnswers();
                allGroups.add(tempGroup);
                tempGroup = new CustomsGroup();

            } else {
                Person thisPerson = new Person(customsReader.fileLines.get(i));
                tempGroup.addPerson(thisPerson);
                if(i == (customsReader.fileLines.size() -1)) {
                    allGroups.add(tempGroup);
                }
            }
        }

        // calculate the number of unique answers
        int totalUnique = 0;
        int totalUnanimous = 0;
        int index = 0;
        for (CustomsGroup thisGroup : allGroups) {
            System.out.println(index + " Total: " + thisGroup.getNumUniqueAnswers() + " Answers: " + thisGroup.getUniqueQuestionsAnswered());
            totalUnique += thisGroup.getNumUniqueAnswers();
            totalUnanimous += thisGroup.getNumUnanimousAnswers();
            index++;
        }

        System.out.println("Total unique yes answers: " + totalUnique);
        System.out.println("Total unanimous yes answers: " + totalUnanimous);
    }

    public FileReader() {};
    public FileReader(String fileName) throws IOException {
        this.fileName = fileName;
        this.filePath = Paths.get(System.getProperty("user.dir"), "data", fileName);
        System.out.println(this.filePath.toString());
        this.fileLines = Files.readAllLines(this.filePath);
    }

    // Getters & Setters
    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public void setFileLines(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
