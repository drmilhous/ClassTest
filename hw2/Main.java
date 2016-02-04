package hw2;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String fileName = "Speed.txt";
		
		Parse file = new Parse (fileName);
		List<LogEntry> entries = file.getEntries();
		
//		for(LogEntry i: entries)
//		{
//			System.out.println(i);
//		}
		
		//Command line for user input of how many days to go back to view the average of the day 
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input # of days to go back you want to view: ");
		int input = scanner.nextInt();
		scanner.close();
		
		TeamAmericanFilter filter = new TeamAmericanFilter(input);

	}

}
