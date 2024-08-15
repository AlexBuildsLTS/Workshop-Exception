package se.lexicon.exceptions.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private NameService nameService;

    @BeforeEach
    void setup() {

        List<String> maleNames;
        List<String> femaleNames;
        List<String> lastNames;

        try {
            maleNames = CSVReader_Writer.getMaleFirstNames();
            femaleNames = CSVReader_Writer.getFemaleFirstNames();
            lastNames = CSVReader_Writer.getLastNames();
        } catch (IOException e) {
            maleNames = new ArrayList<>(List.of("Timothy", "Jon", "Alex"));
            femaleNames = new ArrayList<>(List.of("Saoirse", "Pia", "Linda"));
            lastNames = new ArrayList<>(List.of("Ronan", "Jones", "Youssef"));
        }

        nameService = new NameService(maleNames, femaleNames, lastNames);
    }

    @Test
    void testMainMethod() {
        // Ensure that the main method runs without throwing any exceptions
        assertDoesNotThrow(() -> {
            Main.main(new String[]{});
        });
    }

    @Test
    void testFileLoading() {
        // Ensure that the name lists are not empty after loading
        assertNotNull(nameService);
        assertFalse(nameService.getRandomMaleFirstName().isEmpty(), "Male names should not be empty");
        assertFalse(nameService.getRandomFemaleFirstName().isEmpty(), "Female names should not be empty");
        assertFalse(nameService.getRandomLastName().isEmpty(), "Last names should not be empty");
    }

    @Test
    void testAddMaleFirstName() {
        // Test adding a new male first name
        assertDoesNotThrow(() -> nameService.addMaleFirstName("Alex"));
        assertThrows(DuplicateNameException.class, () -> nameService.addMaleFirstName("John"));
    }

    @Test
    void testAddFemaleFirstName() {
        // Test adding a new female first name
        assertDoesNotThrow(() -> nameService.addFemaleFirstName("Emma"));
        assertThrows(DuplicateNameException.class, () -> nameService.addFemaleFirstName("Anna"));
    }

    @Test
    void testGeneratePerson() {
        // Ensure that a person is generated correctly
        Person person = nameService.getNewRandomPerson();
        assertNotNull(person);
        assertNotNull(person.getFirstName(), "First name should not be null");
        assertNotNull(person.getLastName(), "Last name should not be null");
        assertNotNull(person.getGender(), "Gender should not be null");
    }

    // More tests will be added if needed
}
