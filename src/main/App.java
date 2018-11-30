package main;

import java.io.*;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException {
//		if (args.length == 0 || args[0] == null || args[0].trim().isEmpty()) {
//			System.out.println("Enter a filepath!");
//			return;
//		}
//		String path = args[0];
		String path = "./src/main/pipe.txt";
		InputParser parser = new InputParser(path);
	}

}
