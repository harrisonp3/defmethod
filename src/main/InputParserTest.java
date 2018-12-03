package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;

public class InputParserTest {

	@Test
	public void testGetInput() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOutput() {
		fail("Not yet implemented");
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
