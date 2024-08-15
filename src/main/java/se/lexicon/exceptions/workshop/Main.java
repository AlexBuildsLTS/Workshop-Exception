package se.lexicon.exceptions.workshop;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> maleNames;
        List<String> femaleNames;
        List<String> lastNames;


        try {
            maleNames = CSVReader_Writer.getMaleFirstNames();
            femaleNames = CSVReader_Writer.getFemaleFirstNames();
            lastNames = CSVReader_Writer.getLastNames();
            System.out.println("Names loaded successfully from files.");
        } catch (IOException e) {
            logError("Error loading names from files: " + e.getMessage());

            maleNames = new ArrayList<>(List.of("Timothy", "Jon", "Alex"));
            femaleNames = new ArrayList<>(List.of("Saoirse", "Pia", "Linda"));
            lastNames = new ArrayList<>(List.of("Ronan", "Jones", "Youssef"));
        }

        NameService nameService = new NameService(maleNames, femaleNames, lastNames);


        try {
            nameService.addMaleFirstName("");
            System.out.println("New male first name added and saved.");
        } catch (DuplicateNameException e) {
            System.err.println("Error: " + e.getMessage());
            logError("Duplicate name error: " + e.getMessage());
        }


        try {
            nameService.addFemaleFirstName("Emma");
            System.out.println("New female first name added and saved.");
        } catch (DuplicateNameException e) {
            System.err.println("Error: " + e.getMessage());
            logError("Duplicate name error: " + e.getMessage());
        }


        Person randomPerson = nameService.getNewRandomPerson();
        System.out.println("Generated Person: " + randomPerson);


        CSVReader_Writer.saveMaleNames(maleNames);
        CSVReader_Writer.saveFemaleNames(femaleNames);
        CSVReader_Writer.saveLastNames(lastNames);
        System.out.println("Names saved successfully.");
    }

    /**
     * Logs error messages to a file for better tracking and debugging.
     * @param message The error message to logs
     */
    private static void logError(String message) {
        try (FileWriter fw = new FileWriter("error_log.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(message);
        } catch (IOException e) {
            System.err.println("Failed to write to error log: " + e.getMessage());
        }
    }
}
