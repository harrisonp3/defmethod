package main;

public class Person {
	private String _lastName;
	private String _firstName;
	private String _gender;
	private String _color;
	private String _birthday;
	
	
	public Person(String lastName, String firstName, String gender, String color, String dob) {
		_lastName = lastName;
		_firstName = firstName;
		setGender(gender);
		_color = color;
		_birthday = dob;
	}

	private void setGender(String s) {
		if (s == "M" || s == "Male") {
			_gender = "Male";
		} else if (s == "F" || s == "Female") {
			_gender = "Female";
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
