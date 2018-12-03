package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Scanner;

public class InputParser {
	private final Scanner _scanner;
	private String _delimiter;
	private HashSet<Person> _people;

	public InputParser(String filepath) throws FileNotFoundException, ParseException {
		this(new Scanner(new File(filepath)));
		this.parse();
	}
	
	InputParser(Scanner scanner) {
		this._scanner = scanner;
	}
	
	/**
	 * @throws ParseException 
	 */
	private void parse() throws ParseException {
		String currentLine = getInput();
		this.determineDelimiter(currentLine);
		parseFile(currentLine);
	}
	
	private String[] splitStringByDelimiter(String curr) {
		if (_delimiter == "pipe") {
			return curr.split("\\|");
		} else if (_delimiter == "comma") {
			return curr.split("\\,");
		} else if (_delimiter == "space") {
			return curr.split(" ");
		}
		System.out.println("ERROR: No delimiter determined for file");
		return new String[1];
	}
	
	private Person getPersonFromProps(String[] personProps) throws ParseException {
		if (_delimiter == "pipe") {
			return getPersonFromPipeProps(personProps);
		} else if (_delimiter == "comma") {
			return getPersonFromCommaProps(personProps);
		} else if (_delimiter == "space") {
			return getPersonFromSpaceProps(personProps);
		}
		return null;
	}
	
	private Person getPersonFromSpaceProps(String[] personProps) throws ParseException {
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
	
	private Person getPersonFromPipeProps(String[] personProps) throws ParseException {
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
	
	private Person getPersonFromCommaProps(String[] personProps) throws ParseException {
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
	
	public String getInput() {
		return _scanner.nextLine();
	}
	
	private void parseFile(String currentLine) throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		while (true) {
			String[] personProps = splitStringByDelimiter(currentLine);
			Person person = getPersonFromProps(personProps);
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
	
	public void getOutput() {
		if (!_people.isEmpty()) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("output.txt", "UTF-8");
				for (Person person : _people) {
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

	private void determineDelimiter(String input) {
		if (input.contains("|")) {
			_delimiter = "pipe";
		} else if (input.contains(",")) {
			_delimiter = "comma";
		} else {
			_delimiter = "space";
		}
	}
}
