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
	
	
	public Person(String lastName, String firstName, String gender, String color, String dob) throws ParseException {
		setLastName(lastName);
		setFirstName(firstName);
		setGender(gender);
		setColor(color);
		setBirthday(dob);
	}

	private void setGender(String s) {
		if (s.contains("F")) {
			_gender = "Female";
		} else {
			_gender = "Male";
		}
	}
	
	private void setLastName(String name) {
		_lastName = name;
	}
	
	private void setColor(String color) {
		_color = color;
	}
	
	private void setFirstName(String name) {
		_firstName = name;
	}
	
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
	
	public String getLastName() {
		return _lastName;
	}
	
	public String getFirstName() {
		return _firstName;
	}
	
	public String getGender() {
		return _gender;
	}
	
	public String getColor() {
		return _color;
	}
	
	public String getBirthday() {
		return _birthday;
	}
}
