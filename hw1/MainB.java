package hw1;

import java.util.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {

		takeInput();

	}
	
	public static void takeInput () {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input # of days back you want to view: ");
		String in = scan.nextLine();
		readInput(in);
		takeInput();
		
		
		
	}
	
	public static void readInput (String input) {
		
		int d = -1;
		boolean valid = true;
		
		try {
			d = Integer.parseInt(input);
			if (d < 0)
				valid = false;
		} catch (NumberFormatException e) {
			valid = false;
		}
		
		if (valid == true)
			search(d);
		else
			System.out.println("Error: Invalid Input. Must be int greater than or equal to zero.");
		
	}
	
	public static String search (int d) {
		
		//INSERT CODE HERE
		
		return null;
	}
	
	public static void fileGrab(){
		String file = "test.txt";
		//try{
		Scanner scan = new Scanner(file);
		//}
		//catch(FileNotFoundException e){
		//	System.out.println("File not found.");
		//}
	}
	
	public static void parse() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
	
		
		
	}
}
