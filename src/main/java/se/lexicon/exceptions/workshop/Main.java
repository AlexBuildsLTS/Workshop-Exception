package se.lexicon.exceptions.workshop;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.DuplicateNameException;
import se.lexicon.exceptions.workshop.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> maleNames = new ArrayList<>(List.of("Alex", "Michael", "David"));
        List<String> femaleNames = new ArrayList<>(List.of("Melina", "Emily", "Anna"));
        List<String> lastNames = new ArrayList<>(List.of("Scott", "Blunt", "Jackson"));

        // Create the NameService object
        NameService nameService = new NameService(maleNames, femaleNames, lastNames);


        try {
            nameService.addMaleFirstName("Alex");
            System.out.println("New male first name added and saved.");
        } catch (DuplicateNameException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            nameService.addFemaleFirstName("Daniella");
            System.out.println("New female first name added and saved.");
        } catch (DuplicateNameException e) {
            System.err.println("Error: " + e.getMessage());
        }


        Person randomPerson = nameService.getNewRandomPerson();
        System.out.println("Generated Person: " + randomPerson);
    }
}