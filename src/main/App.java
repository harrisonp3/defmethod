package main;

import java.io.*;
import java.text.ParseException;

public class App {

	public App() {}

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		if (args.length < 2 || args[0] == null || args[0].trim().isEmpty()) {
			System.out.println("Enter a filepath and a number between 1 and 3 for your sorting preference!!");
			return;
		}
		String path = args[0];
//		String path = "./src/main/pipe.txt";
		int sortPreference = Integer.parseInt(args[1]);
		InputParser parser = new InputParser();
		parser.setScanner(path);
		parser.parse();
		parser.getOutput(sortPreference);
	}

}
