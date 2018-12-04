package main;

import java.io.*;
import java.text.ParseException;

public class App {

	public App() {}

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		if (args.length == 0 || args[0] == null || args[0].trim().isEmpty()) {
			System.out.println("Enter a filepath!");
			return;
		}
		String path = args[0];
//		String path = "./src/main/pipe.txt";
		InputParser parser = new InputParser();
		parser.setScanner(path);
		parser.parse();
		parser.getOutput();
	}

}
