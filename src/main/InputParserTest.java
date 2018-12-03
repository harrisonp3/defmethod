package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.text.ParseException;
import org.junit.*;

public class InputParserTest {
	
	@Test
	public void testGetPersonFromSpaceProps() throws ParseException {
		String[] testInput = new String[6];
		testInput[0] = "Seles ";
		testInput[1] = "Monica ";
		testInput[2] = "H ";
		testInput[3] = "F ";
		testInput[4] = "12-2-1973 ";
		testInput[5] = "Black ";
		
		InputParser parser = new InputParser();
		Person testPerson = parser.getPersonFromSpaceProps(testInput);
		assertEquals(testPerson.getLastName(), "Seles");
		assertEquals(testPerson.getFirstName(), "Monica");
		assertEquals(testPerson.getGender(), "Female");
		assertEquals(testPerson.getBirthday(), "12/02/1973");
		assertEquals(testPerson.getColor(), "Black");
	}
	
	@Test
	public void testGetPersonFromCommaProps() throws ParseException {
		String testInput = "Abercrombie, Neil, Male, Tan, 2/13/1943";
		InputParser parser = new InputParser();
		parser.determineDelimiter(testInput);
		assertEquals(parser.getDelimiter(), "comma");
		String[] props = parser.splitStringByDelimiter(testInput);
		Person testPerson = parser.getPersonFromCommaProps(props);
		assertEquals(testPerson.getLastName(), "Abercrombie");
		assertEquals(testPerson.getFirstName(), "Neil");
		assertEquals(testPerson.getGender(), "Male");
		assertEquals(testPerson.getBirthday(), "2/13/1943");
		assertEquals(testPerson.getColor(), "Tan");
	}
	
	@Test
	public void testGetPersonFromPipeProps() throws ParseException {
		String testInput = "Smith | Steve | D | M | Red | 3-3-1985";
		InputParser parser = new InputParser();
		parser.determineDelimiter(testInput);
		assertEquals(parser.getDelimiter(), "pipe");
		String[] props = parser.splitStringByDelimiter(testInput);
		Person testPerson = parser.getPersonFromPipeProps(props);
		assertEquals(testPerson.getLastName(), "Smith");
		assertEquals(testPerson.getFirstName(), "Steve");
		assertEquals(testPerson.getGender(), "Male");
		assertEquals(testPerson.getBirthday(), "03/03/1985");
		assertEquals(testPerson.getColor(), "Red");
	}

	@Test
	public void testDetermineDelimiter() {
		// pipe test case
		InputParser parser = new InputParser();
		parser.determineDelimiter("Smith | Steve | D | M | Red | 3-3-1985");
		assertEquals(parser.getDelimiter(), "pipe");
		
		// comma test case
		InputParser parser2 = new InputParser();
		parser2.determineDelimiter("Abercrombie, Neil, Male, Tan, 2/13/1943");
		assertEquals(parser2.getDelimiter(), "comma");
		
		// space test case
		InputParser parser3 = new InputParser();
		parser3.determineDelimiter("Kournikova Anna F F 6-3-1975 Red");
		assertEquals(parser3.getDelimiter(), "space");
	}

}
