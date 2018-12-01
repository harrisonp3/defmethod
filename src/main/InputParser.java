package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Scanner;

public class InputParser {
	private String _filepath;
	private Scanner _input;
	private String _delimiter;
	private static HashSet<Person> _people;

	public InputParser(String filepath) throws FileNotFoundException, ParseException {
		_filepath = filepath;
		File file = new File(filepath);
		_input = new Scanner(file);
		this.parse();
	}
	
	/**
	 * @throws ParseException 
	 */
	private void parse() throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		String currentLine = _input.nextLine();
		this.determineDelimiter(currentLine);
		if (_delimiter == "pipe") {
			parsePipeFile(currentLine);
		} else if (_delimiter == "comma") {
			parseCommaFile(currentLine);
		} else if (_delimiter == "space") {
			parseSpaceFile(currentLine);
		}
	}
	
	private void parsePipeFile(String currentLine) throws ParseException {
		HashSet<Person> set = new HashSet<Person>();
		while (true) {
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
			if (_input.hasNextLine()) {
				currentLine = _input.nextLine();
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
			if (_input.hasNextLine()) {
				currentLine = _input.nextLine();
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
			if (_input.hasNextLine()) {
				currentLine = _input.nextLine();
			} else {
				_people = set;
				break;
			}
		}
	}
	
	public static void getOutput() {
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
