package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class InputParser {
	private String _filepath;
	private Scanner _input;
	private String _delimiter;
	private static HashSet<Person> _people;

	public InputParser(String filepath) throws FileNotFoundException {
		_filepath = filepath;
		File file = new File(filepath);
		_input = new Scanner(file);
		this.parse();
	}
	
	private void parse() {
		HashSet<Person> set = new HashSet<Person>();
		String currentLine = _input.nextLine();
		this.determineDelimiter(currentLine);
		if (_delimiter == "pipe") {
			while (true) {
				System.out.println(currentLine);
				//get each of the properties 
				// create a new person passing props as params
				// add person to result array
				String[] personProps = currentLine.split("\\|");
				if (personProps.length != 6) System.out.println("ERROR: Do not have all required props");
				String lastName = personProps[0].replaceAll("\\s", "");
				String firstName = personProps[1].replaceAll("\\s", "");
				String gender = personProps[3].replaceAll("\\s", "");
				String color = personProps[4].replaceAll("\\s", "");
				String dob = personProps[5].replaceAll("\\s", ""); //@todo create a private method to convert date format
				Person person = new Person(lastName, firstName, gender, color, dob);
				set.add(person);
				if (_input.hasNextLine()) {
					currentLine = _input.nextLine();
				} else {
					_people = set;
					break;
				}
				
			}
		}
	}
	
	public static void getOutput() {
		if (!_people.isEmpty()) {
			
		}
	}

	private void determineDelimiter(String input) {
		if (input.contains("|")) {
			_delimiter = "pipe";
//			_input.useDelimiter("\\|"); // MAYBE I DONT NEED THIS IF IM DOING IT USING split() above
		} else if (input.contains(",")) {
			_delimiter = "comma";
			_input.useDelimiter("\\,");
		} else {
			_delimiter = "space";
			//@todo not sure what to do here
		}
	}
}
