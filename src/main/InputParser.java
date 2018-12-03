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
		if (_delimiter == "pipe") {
			parsePipeFile(currentLine);
		} else if (_delimiter == "comma") {
			parseCommaFile(currentLine);
		} else if (_delimiter == "space") {
			parseSpaceFile(currentLine);
		}
	}
	
	public String getInput() {
		return _scanner.nextLine();
	}
	
	private void parsePipeFile(String currentLine) throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		while (true) {
			//@todo i think i need to split out this function into small pieces
			// like building the person should be private method
			// then this method should return the hash set
			// and perhaps this method should have the scanner as a param
			String[] personProps = currentLine.split("\\|");
			if (personProps.length != 6) {
				System.out.println("ERROR: Do not have all required props");
				break;
			}
			String lastName  = personProps[0].replaceAll("\\s", "");
			String firstName = personProps[1].replaceAll("\\s", "");
			String gender    = personProps[3].replaceAll("\\s", "");
			String color     = personProps[4].replaceAll("\\s", "");
			String dob       = personProps[5].replaceAll("\\s", "");
			Person person    = new Person(lastName, firstName, gender, color, dob);
			set.add(person);
			if (_scanner.hasNextLine()) {
				currentLine = getInput();
			} else {
				_people = set;
				break;
			}	
		}
	}
	
	private void parseCommaFile(String currentLine) throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		while (true) {
			String[] personProps = currentLine.split("\\,");
			if (personProps.length != 5) {
				System.out.println("ERROR: Do not have all required props");
				break;
			}
			String lastName  = personProps[0].replaceAll("\\s", "");
			String firstName = personProps[1].replaceAll("\\s", "");
			String gender    = personProps[2].replaceAll("\\s", "");
			String color     = personProps[3].replaceAll("\\s", "");
			String dob       = personProps[4].replaceAll("\\s", "");
			Person person    = new Person(lastName, firstName, gender, color, dob);
			set.add(person);
			if (_scanner.hasNextLine()) {
				currentLine = getInput();
			} else {
				_people = set;
				break;
			}
		}
	}
	
	private void parseSpaceFile(String currentLine) throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		while (true) {
			String[] personProps = currentLine.split(" ");
			if (personProps.length != 6) {
				System.out.println("ERROR: Do not have all required props");
				break;
			}
			String lastName  = personProps[0].replaceAll("\\s", "");
			String firstName = personProps[1].replaceAll("\\s", "");
			String gender    = personProps[3].replaceAll("\\s", "");
			String dob       = personProps[4].replaceAll("\\s", "");
			String color     = personProps[5].replaceAll("\\s", "");
			Person person    = new Person(lastName, firstName, gender, color, dob);
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
