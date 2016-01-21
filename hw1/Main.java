package hw1

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {

		takeInput();

	}
	
	public takeInput () {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input # of days back you want to view: ")
		String in = scan.nextLine();
		readInput(in);
		takeInput();
		
	}
	
	public readInput (String input) {
		
		int d;
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
	
	public search (int d) {
		
		//INSERT CODE HERE
		
	}
}
