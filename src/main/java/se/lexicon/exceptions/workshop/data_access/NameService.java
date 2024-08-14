package se.lexicon.exceptions.workshop.data_access;

import se.lexicon.exceptions.workshop.DuplicateNameException;
import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.util.List;
import java.util.Random;

public class NameService {

	private List<String> maleFirstNames;
	private List<String> femaleFirstNames;
	private List<String> lastNames;
	private static Random random = new Random();

	// Constructor - should ensure no nulls
	public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {
		this.maleFirstNames = maleFirstNames;
		this.femaleFirstNames = femaleFirstNames;
		this.lastNames = lastNames;
	}

	/**
	 * Task: Successfully create a Person object using the method getNewRandomPerson.
	 * This method selects a random gender, first name, and last name to create a new Person object.
	 * @return Person - a randomly generated person with a first name, last name, and gender.
	 */
	public Person getNewRandomPerson() {
		Gender gender = getRandomGender();
		Person person = null;
		switch (gender) {
			case MALE:
				person = new Person(getRandomMaleFirstName(), getRandomLastName(), gender);
				break;
			case FEMALE:
				person = new Person(getRandomFemaleFirstName(), getRandomLastName(), gender);
				break;
		}
		return person;
	}


	public String getRandomFemaleFirstName() {
		return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
	}


	public String getRandomMaleFirstName() {
		return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
	}


	public String getRandomLastName() {
		return lastNames.get(random.nextInt(lastNames.size()));
	}


	public Gender getRandomGender() {
		return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
	}

	/**
	 * Task: Check if List<String> femaleFirstNames already contains the name.
	 * If name already exists, throw a new custom exception called DuplicateNameException.
	 * @param name - the name to add to the femaleFirstNames list.
	 * @throws DuplicateNameException - if the name already exists in the list.
	 */
	public void addFemaleFirstName(String name) throws DuplicateNameException {
		if (femaleFirstNames.contains(name)) {
			throw new DuplicateNameException("The name '" + name + "' already exists in the female first names list.");
		}
		femaleFirstNames.add(name);
		CSVReader_Writer.saveFemaleNames(femaleFirstNames);
	}

	/**
	 * Task: Check if List<String> maleFirstNames already contains the name.
	 * If name already exists, throw a new custom exception called DuplicateNameException.
	 * @param name - the name to add to the maleFirstNames list.
	 * @throws DuplicateNameException - if the name already exists in the list.
	 */
	public void addMaleFirstName(String name) throws DuplicateNameException {
		if (maleFirstNames.contains(name)) {
			throw new DuplicateNameException("The name '" + name + "' the persons name already exists.");
		}
		maleFirstNames.add(name);
		CSVReader_Writer.saveMaleNames(maleFirstNames);
	}

	/**
	 * Task: Check if List<String> lastNames already contains the name.
	 * If name already exists, throw a new custom exception called DuplicateNameException.
	 * @param lastName - the last name to add to the lastNames list.
	 * @throws DuplicateNameException - if the last name already exists in the list.
	 */
	public void addLastName(String lastName) throws DuplicateNameException {
		if (lastNames.contains(lastName)) {
			throw new DuplicateNameException("The name '" + lastName + "' already exists in the last names list.");
		}
		lastNames.add(lastName);
		CSVReader_Writer.saveLastNames(lastNames);
	}
}
