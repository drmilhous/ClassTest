package hw1;

import java.util.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {

		takeInput();

	}
	
	public static void takeInput() {
		
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
	
	public static int search(int d) {
		
		Scanner finder = new Scanner("speed.txt");
		Scanner grabber = new Scanner("speed.txt");
		String line = "";
		String weekDay = "";
		int lastLine = 0;
		
		for (int i = 0; !finder.hasNextLine(); i++) {
			line = finder.nextLine();
			if (line.startsWith("Mon") || line.startsWith("Tue") || line.startsWith("Wed")
					|| line.startsWith("Thu") || line.startsWith("Fri") || line.startsWith("Sat")
					|| line.startsWith("Sun"))
				lastLine = i;
		}
		
		for (int i = 0; i < lastLine; i++) {
			grabber.nextLine();
		}
		
		Scanner scanner = new Scanner("speed.txt");
		
		for (int i = 0; i < lastLine - (d*10); i++) {
			scanner.nextLine();
		}
		
		line = scanner.nextLine();
		
		Scanner reader1 = new Scanner(line);
		Scanner reader2 = new Scanner(line);

		int dateNum = reader1.nextInt();
		String date = "";
		
		if (dateNum < 10)
			date = reader2.next() + " " + reader2.next() + "  " + reader2.next();
		else
			date = reader2.next() + " " + reader2.next() + " " + reader2.next();
		
		return null;
	}
	
	public static void parse() {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
	
		
		
	}
}

