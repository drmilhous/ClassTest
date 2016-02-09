package hw2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String fileName = "Speed.txt";
		int lastDateinEntries;
		int dayToStart;

		Parse file = new Parse (fileName);
		List<LogEntry> entries = file.getEntries();


		//Setting the start and end date and time to zero
		Date start = new Date();
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);

		Date end = new Date();
		end.setHours(0);
		end.setMinutes(0);
		end.setSeconds(0);

		//Command line for user input of how many days to go back to view the average of the day 

		Scanner scanner = new Scanner(System.in);
		System.out.println("Input # of days to go back you want to view: ");
		int dayGoBack = scanner.nextInt();
		scanner.close();

		lastDateinEntries = ((LinkedList<LogEntry>) entries).getLast().getTime().getDate();

		//Test for the lastDateinEntries
		//				System.out.println(lastDateinEntries);

		dayToStart = lastDateinEntries - dayGoBack;

		//Set the start and end date for TeamAmericanFilter to desired date
		
		start.setDate(dayToStart);
		end.setDate(dayToStart + 1);

		//Test for the starting date and ending date
		//				System.out.println(start.getDate());
		//				System.out.println(end.getDate());

		TeamAmericanFilter america = new TeamAmericanFilter(start, end);
		List<LogEntry>americanFilteredList = america.filter(entries);
		FileWriter fwriter = new FileWriter("Output.csv");

		for (LogEntry l : americanFilteredList)
		{
			System.out.println(l);
			
			try
			{
				fwriter.append(l);
			}
			catch(IOException e)
			{
				System.out.println("Error writing to file.");
			}
		}
		
		fwriter.close();
	
	}

}
