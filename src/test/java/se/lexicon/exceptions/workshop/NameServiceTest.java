package se.lexicon.exceptions.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NameServiceTest {

    private NameService nameService;

    @BeforeEach
    void setup() {
        // Initialize the NameService with some sample data before each test
        nameService = new NameService(
                List.of("Timothy", "Jon", "Alex"),  // Sample male names
                List.of("Saoirse", "Pia", "Linda"),     // Sample female names
                List.of("Ronan", "Jones", "Youssef")    // Sample last names
        );
    }

    @Test
    void testAddMaleFirstName() {
        // Test adding a new male first name - should not throw an exception
        assertDoesNotThrow(() -> nameService.addMaleFirstName("David"));

        // Test adding a duplicate male first name - should throw DuplicateNameException
        assertThrows(DuplicateNameException.class, () -> nameService.addMaleFirstName("Alex"));
    }

    @Test
    void testAddFemaleFirstName() {
        // Test adding a new female first name - should not throw an exception
        assertDoesNotThrow(() -> nameService.addFemaleFirstName("Mia"));

        // Test adding a duplicate female first name - should throw DuplicateNameException
        assertThrows(DuplicateNameException.class, () -> nameService.addFemaleFirstName("Linda"));
    }

    @Test
    void testGeneratePerson() {
        // Test generating a random person - should not return null
        Person person = nameService.getNewRandomPerson();
        assertNotNull(person, "Generated person should not be null");

        // Additional checks to ensure the person has valid attributes
        assertNotNull(person.getFirstName(), "First name should not be null");
        assertNotNull(person.getLastName(), "Last name should not be null");
        assertNotNull(person.getGender(), "Gender should not be null");
    }
}
