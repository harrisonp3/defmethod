package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

public class InputParser {
	private Scanner _scanner;
	private String _delimiter;
	private HashSet<Person> _people;
	public static final String COMMA = "comma";
	public static final String SPACE = "space";
	public static final String PIPE = "pipe";
	public static final int SORT_BY_GENDER_AND_LAST_NAME = 1;
	public static final int SORT_BY_BIRTHDAY_AND_LAST_NAME = 2;
	public static final int SORT_BY_LAST_NAME_DESCENDING = 3;

	/**
	 * Constructor
	 */
	public InputParser() {}

	/**
	 * Set the Scanner object for this class
	 * @param String filepath path to the file to be parsed
	 * @throws FileNotFoundException
	 */
	public void setScanner(String filepath) throws FileNotFoundException {
		this._scanner = new Scanner(new File(filepath));
	}
	
	/**
	 * @throws ParseException 
	 */
	public void parse() throws ParseException {
		String currentLine = getInput();
		this.determineDelimiter(currentLine);
		parseFile(currentLine);
	}
	
	/**
	 * Based on the delimiter, split the string
	 * 
	 * @param String curr
	 * @return String[]
	 */
	protected String[] splitStringByDelimiter(String curr) {
		if (isPipeDelimited()) {
			return curr.split("\\|");
		} else if (isCommaDelimited()) {
			return curr.split("\\,");
		} else if (isSpaceDelimited()) {
			return curr.split(" ");
		}
		System.out.println("ERROR: No delimiter determined for file");
		return new String[1];
	}
	
	/**
	 * Get a person object from the String array passed in
	 * 
	 * @param String[] personProps
	 * @return void
	 * @throws ParseException
	 */
	protected Person getPersonFromProps(String[] personProps) throws ParseException {
		if (isPipeDelimited()) {
			return getPersonFromPipeProps(personProps);
		} else if (isCommaDelimited()) {
			return getPersonFromCommaProps(personProps);
		} else if (isSpaceDelimited()) {
			return getPersonFromSpaceProps(personProps);
		}
		return null;
	}
	
	/**
	 * Whether or not the file being parsed is delimited with pipe ("|") char
	 * 
	 * @return boolean
	 */
	private boolean isPipeDelimited() {
		return _delimiter == PIPE;
	}
	
	/**
	 * Whether or not the file being parsed is delimited with comma (",") char
	 * 
	 * @return boolean
	 */
	private boolean isCommaDelimited() {
		return _delimiter == COMMA;
	}
	
	/**
	 * Whether or not the file being parsed is delimited with space (" ") char
	 * 
	 * @return boolean
	 */
	private boolean isSpaceDelimited() {
		return _delimiter == SPACE;
	}
	
	/**
	 * Logic specific to grabbing information from the String array
	 * derived from space-delimited data (how many elements in the array,
	 * what elements are at what index) and instantiating a Person object
	 * 
	 * @param String[] personProps
	 * @return Person
	 * @throws ParseException
	 */
	protected Person getPersonFromSpaceProps(String[] personProps) throws ParseException {
		if (personProps.length != 6) {
			System.out.println("ERROR: Do not have all required props");
			return null;
		}
		String lastName  = personProps[0].replaceAll("\\s", "");
		String firstName = personProps[1].replaceAll("\\s", "");
		String gender    = personProps[3].replaceAll("\\s", "");
		String dob       = personProps[4].replaceAll("\\s", "");
		String color     = personProps[5].replaceAll("\\s", "");
		Person person    = new Person(lastName, firstName, gender, color, dob);
		return person;
	}
	
	/**
	 * Logic specific to grabbing information from the String array
	 * derived from pipe-delimited data (how many elements in the array,
	 * what elements are at what index) and instantiating a Person object
	 * 
	 * @param String[] personProps
	 * @return Person
	 * @throws ParseException
	 */
	protected Person getPersonFromPipeProps(String[] personProps) throws ParseException {
		if (personProps.length != 6) {
			System.out.println("ERROR: Do not have all required props");
			return null;
		}
		String lastName  = personProps[0].replaceAll("\\s", "");
		String firstName = personProps[1].replaceAll("\\s", "");
		String gender    = personProps[3].replaceAll("\\s", "");
		String color     = personProps[4].replaceAll("\\s", "");
		String dob       = personProps[5].replaceAll("\\s", "");
		Person person    = new Person(lastName, firstName, gender, color, dob);
		return person;
	}
	
	/**
	 * Logic specific to grabbing information from the String array
	 * derived from comma-delimited data (how many elements in the array,
	 * what elements are at what index) and instantiating a Person object
	 * 
	 * @param String[] personProps
	 * @return Person
	 * @throws ParseException
	 */
	protected Person getPersonFromCommaProps(String[] personProps) throws ParseException {
		if (personProps.length != 5) {
			System.out.println("ERROR: Do not have all required props");
			return null;
		}
		String lastName  = personProps[0].replaceAll("\\s", "");
		String firstName = personProps[1].replaceAll("\\s", "");
		String gender    = personProps[2].replaceAll("\\s", "");
		String color     = personProps[3].replaceAll("\\s", "");
		String dob       = personProps[4].replaceAll("\\s", "");
		Person person    = new Person(lastName, firstName, gender, color, dob);
		return person;
	}
	/**
	 * Get the next line in the input from the Scanner object
	 * 
	 * @return String
	 */
	public String getInput() {
		return _scanner.nextLine();
	}
	
	/**
	 * Build a HashSet object of Person objects by calling String.split()
	 * on each line of the input file, generating a Person object from that,
	 * and adding to the HashSet
	 * 
	 * @param String currentLine
	 * @throws ParseException
	 */
	private void parseFile(String currentLine) throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		// Build up a hash set of Person objects from the lines of input
		while (true) {
			String[] personProps = splitStringByDelimiter(currentLine);
			Person person = getPersonFromProps(personProps);
			// If we weren't able to successfully parse a line of input, short circuit
			if (person == null) break;
			set.add(person);
			if (_scanner.hasNextLine()) {
				currentLine = getInput();
			} else {
				_people = set;
				break;
			}	
		}
	}
	
	/**
	 * Based on the sort ID passed in, sort the collection of Person objects
	 * 
	 * @param int x ID of sort to use
	 * @return List<Person>
	 */
	private List<Person> sort(int x) {
		switch(x) {
		case SORT_BY_GENDER_AND_LAST_NAME:
			return sortByGenderAndLastName();
		case SORT_BY_BIRTHDAY_AND_LAST_NAME:
			return sortByBirthdayAndLastName();
		case SORT_BY_LAST_NAME_DESCENDING:
		default:
			return sortByLastNameDescending();
		}
	}
	
	/**
	 * Print the properties of the Person objects in the HashSet
	 * 
	 * @param int sortPreference
	 */
	public void getOutput(int sortPreference) {
		List<Person> peopleSorted = sort(sortPreference);
		if (!peopleSorted.isEmpty()) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("output.txt", "UTF-8");
				for (Person person : peopleSorted) {
					String traits = person.getLastName() + " " + person.getFirstName() + " " + person.getGender() + " " + person.getBirthday() + " " + person.getColor();
					writer.println(traits);
				}
				writer.close();
				System.out.println("Output completed!");
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("ERROR: No Person objects created");
		}
	}

	/**
	 * Get the delimiter determined
	 * 
	 * @return String delimiter value
	 */
	public String getDelimiter() {
		return _delimiter;
	}

	/**
	 * Sort by gender ascending and then last name ascending
	 * 
	 * @return List<Person>
	 */
	@SuppressWarnings("unchecked")
	private List<Person> sortByGenderAndLastName() {
		List<Person> list = new ArrayList<Person>();
		if (!_people.isEmpty()) {
			for (Person person : _people) {
				list.add(person);
			}
			Collections.sort(list, new Comparator() {
				public int compare (Object o1, Object o2) {
					// gender then last name ascending
					String gender1 = ((Person) o1).getGender();
					String gender2 = ((Person) o2).getGender();
					int genComp = gender1.compareTo(gender2);
					if (genComp != 0) {
						return genComp < 0 ? -1 : 1;
					}
					String lastName1 = ((Person) o1).getLastName();
					String lastName2 = ((Person) o2).getLastName();
					int result = lastName1.compareTo(lastName2);
					return result < 0 ? -1 : 1;
				}
			});
		}
		return list;
	}
	
	/**
	 * Sort collection of people by birthday and then last name ascending
	 * 
	 * @return List<Person>
	 */
	@SuppressWarnings("unchecked")
	private List<Person> sortByBirthdayAndLastName() {
		List<Person> list = new ArrayList<Person>();
		if (!_people.isEmpty()) {
			
			for (Person person : _people) {
				list.add(person);
			}
			Collections.sort(list, new Comparator() {
				public int compare (Object o1, Object o2) {
					// bday then last name ascending
					Date bday1 = ((Person) o1).getBirthDate();
					Date bday2 = ((Person) o2).getBirthDate();
					int bdayComp = bday1.compareTo(bday2);
					if (bdayComp != 0) {
						return bdayComp;
					}
					
					String lastName1 = ((Person) o1).getLastName();
					String lastName2 = ((Person) o2).getLastName();
					return lastName1.compareTo(lastName2);
				}
			});
			
		}
		return list;
	}
	
	/**
	 * Sort by last name descending
	 * 
	 * @return List<Person>
	 */
	@SuppressWarnings("unchecked")
	private List<Person> sortByLastNameDescending() {
		List<Person> list = new ArrayList<Person>();
		if (!_people.isEmpty()) {
			
			for (Person person : _people) {
				list.add(person);
			}
			Collections.sort(list, new Comparator() {
				public int compare (Object o1, Object o2) {
					String lastName1 = ((Person) o1).getLastName();
					String lastName2 = ((Person) o2).getLastName();
					return lastName2.compareTo(lastName1);
				}
			});
			
		}
		return list;
	}
	
	/**
	 * Determine the delimiter (and set its value) based on the String passed in
	 * and the characters that it includes
	 * 
	 * @param String input
	 */
	protected void determineDelimiter(String input) {
		if (input.contains("|")) {
			_delimiter = PIPE;
		} else if (input.contains(",")) {
			_delimiter = COMMA;
		} else {
			_delimiter = SPACE;
		}
	}
}
