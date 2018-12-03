package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
	private String _lastName;
	private String _firstName;
	private String _gender;
	private String _color;
	private String _birthday;
	
	/**
	 * Constructor, calls setters of all class variables
	 * 
	 * @param String lastName
	 * @param String firstName
	 * @param String gender
	 * @param String color
	 * @param String dob
	 * 
	 * @throws ParseException
	 */
	public Person(String lastName, String firstName, String gender, String color, String dob) throws ParseException {
		setLastName(lastName);
		setFirstName(firstName);
		setGender(gender);
		setColor(color);
		setBirthday(dob);
	}
/**
 * Setter for _gender value
 * 
 * @param String s
 */
	private void setGender(String s) {
		if (s.contains("F")) {
			_gender = "Female";
		} else {
			_gender = "Male";
		}
	}
	
	/**
	 * Setter for _lastName value
	 * 
	 * @param String name
	 */
	private void setLastName(String name) {
		_lastName = name;
	}
	
	/**
	 * Setter for _color value
	 * 
	 * @param String color
	 */
	private void setColor(String color) {
		_color = color;
	}
	
	/**
	 * Setter for _co_firstNamelor value
	 * 
	 * @param String name
	 */
	private void setFirstName(String name) {
		_firstName = name;
	}

	/**
	 * Setter for _birthday value
	 * Also contains logic for determining the date format and reformatting if necessary
	 * 
	 * @param String birthday
	 * 
	 * @throws ParseException
	 */
	private void setBirthday(String birthday) throws ParseException {
		if (birthday.contains("-")) {
			SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
			Date date = format1.parse(birthday);
			_birthday = format2.format(date).toString();
		} else {
			_birthday = birthday;
		}
	}
	/**
	 * Getter for _lastName
	 * @return String
	 */
	public String getLastName() {
		return _lastName;
	}
	
	/**
	 * Getter for _firstName
	 * @return String
	 */
	public String getFirstName() {
		return _firstName;
	}
	
	/**
	 * Getter for _gender
	 * @return String
	 */
	public String getGender() {
		return _gender;
	}
	
	/**
	 * Getter for _color
	 * @return String
	 */
	public String getColor() {
		return _color;
	}
	
	/**
	 * Getter for _birthday
	 * @return String
	 */
	public String getBirthday() {
		return _birthday;
	}
}
