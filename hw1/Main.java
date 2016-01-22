package hw1;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Main {

	//method calls take input
	public static void main (String[] args) throws java.lang.Exception {

		takeInput();

	}

	public static void takeInput() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Input # of days back you want to view: ");
		int in = scanner.nextInt();
		scanner.close();
		readInput(in + "");
	}

	public static void readInput (String input) {
		
		int days = -1;
		boolean valid = true;

		try {
			days = Integer.parseInt(input);
			if (days < 0)
				valid = false;
		} catch (NumberFormatException e) {
			valid = false;
		}

		System.out.println(valid);

		if (valid == true)
			search(days);
		else
			System.out.println("Error: Invalid Input. Must be int greater than or equal to zero.");

	}

	public static void search(int d) {

		//calls file
		File data = new File("speed.txt");

		ArrayList<String> entries = new ArrayList<String>();
		ArrayList<String> neededEntries = new ArrayList<String>();

		try {
			Scanner checkScan = new Scanner(data);  //scans to find start of next entry
			Scanner readScan = new Scanner(data);  //takes the lines into entries in ArrayList
			String checker = "";  //used to take line found by checker for analysis
			String compiler = "";  //compiles all lines needed to add into ArrayList as a single entry

			checkScan.nextLine();  //checkScan starts one line ahead of readScan

			while (readScan.hasNextLine()) {

				if (checkScan.hasNextLine()) {
					checker = checkScan.nextLine();
					compiler += readScan.nextLine() + "\n";  //collects lines for entry
					if (checker.startsWith("Mon ") || checker.startsWith("Tue ") ||
							checker.startsWith("Wed ") || checker.startsWith("Thu ") ||
							checker.startsWith("Fri ") || checker.startsWith("Sat ") ||
							checker.startsWith("Sun ")) {  //if line is start of new entry, compiler is
						entries.add(compiler);             //added to the arraylist and reset
						compiler = "";
					}

				} else {  //exits loop when full data has been read
					break;
				}

			}

			checkScan.close();
			readScan.close();

			System.out.println("Finished.");

		} catch (Exception e) {
			System.out.println("Dummies!");
		}

		//finds the last entry of data
		Scanner read = new Scanner(entries.get(entries.size() - 1));
		String ln = read.nextLine();

		read.close();

		Scanner scl = new Scanner(ln);  //finds the current date, month, and year
		scl.next();
		String month = scl.next();
		int dy = scl.nextInt();
		scl.next();
		scl.next();
		int yr = scl.nextInt();

		scl.close();

		String searchKey = "";  //compiles the proper date to be found to read from in data

		if (dy <= d) {  //handles the calculation of date to look for
			if (month == "Jan") {
				searchKey += "Dec " + (31 + (dy - d));
			} else if (month == "Feb") {
				searchKey += "Jan " + (31 + (dy - d));
			} else if (month == "Mar") {
				if (yr % 4 == 0)
					searchKey += "Feb " + (29 + (dy - d));
				else
					searchKey += "Feb " + (28 + (dy - d));
			} else if (month == "Apr") {
				searchKey += "Mar " + (31 + (dy - d));
			} else if (month == "May") {
				searchKey += "Apr " + (30 + (dy - d));
			} else if (month == "Jun") {
				searchKey += "May " + (31 + (dy - d));
			} else if (month == "Jul") {
				searchKey += "Jun " + (30 + (dy - d));
			} else if (month == "Aug") {
				searchKey += "Jul " + (31 + (dy - d));
			} else if (month == "Sep") {
				searchKey += "Aug " + (31 + (dy - d));
			} else if (month == "Oct") {
				searchKey += "Sep " + (30 + (dy - d));
			} else if (month == "Nov") {
				searchKey += "Oct " + (31 + (dy - d));
			} else if (month == "Dec") {
				searchKey += "Nov " + (30 + (dy - d));
			}
		} else if (dy < 10) {
			searchKey = month + "  " + (dy - d);
		} else {
			searchKey = month + " " + (dy - d);
		}

		System.out.println("Searching " + searchKey + "...");

		for (int i = 0; i < entries.size(); i++) {  //creates arrayList of entries just from requested day
			if (entries.get(i).contains(searchKey)) {
				neededEntries.add(entries.get(i));
			}
		}

		parse(neededEntries);
	}

	//Parses data from file
	public static void parse(ArrayList<String> tests) 
	{
		//Array Lists that store time, download, and upload values
		ArrayList<Date> time = new ArrayList<Date>();
		ArrayList<Double> download = new ArrayList<Double>();
		ArrayList<Double> upload = new ArrayList<Double>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		String scn = "";

		for(int i = 0; i < tests.size(); i++) {

			scn = tests.get(i); //Gets current entry

			Scanner lineReader = new Scanner(scn); //Reads in a single line at a time
			String happyBirthdayBryce = "";

			while(lineReader.hasNextLine())
			{
				happyBirthdayBryce = lineReader.nextLine(); //Stores a single line in variable

				if(happyBirthdayBryce.startsWith("Mon") || happyBirthdayBryce.startsWith("Tue") || 
						happyBirthdayBryce.startsWith("Wed") || happyBirthdayBryce.startsWith("Thu") || 
						happyBirthdayBryce.startsWith("Fri") || happyBirthdayBryce.startsWith("Sat") ||
						happyBirthdayBryce.startsWith("Sun"))
				{
					try
					{
						time.add(dateFormat.parse(happyBirthdayBryce)); //Adds date and time to time ArrayList
					}
					catch(Exception e)
					{
						System.out.println("Check the parse method, dummy.");
					}
				}

				//Adds Download value to download ArrayList
				if(happyBirthdayBryce.startsWith("Download: "))
				{
					Scanner bryceScan = new Scanner(happyBirthdayBryce);
					bryceScan.next();
					download.add(bryceScan.nextDouble());
					bryceScan.close();
				}

				//Adds Upload value to download ArrayList
				if(happyBirthdayBryce.startsWith("Upload: "))
				{
					Scanner bryceScan = new Scanner(happyBirthdayBryce);
					bryceScan.next();
					upload.add(bryceScan.nextDouble());
					bryceScan.close();
				}
			}

			lineReader.close();

		}

		//ArrayLists that store average every 15 minutes
		ArrayList<Date> timeSeg = new ArrayList<Date>();
		ArrayList<Double> downloadAvg = new ArrayList<Double>();
		ArrayList<Double> uploadAvg = new ArrayList<Double>();

		double downTotal = 0;
		double upTotal = 0;

		for(int i = 0; i < time.size(); i+=3)
		{
			//Finds totals for every 15 minutes and stores average in new variable
			if (i + 2 < time.size()) {
				downTotal = download.get(i) + download.get(i + 1) + download.get(i + 2);
				upTotal = upload.get(i) + upload.get(i + 1) + upload.get(i + 2);
			}

			//Average ArrayLists
			timeSeg.add(time.get(i));
			downloadAvg.add(downTotal/3);
			uploadAvg.add(upTotal/3);
		}

		try
		{
			//Creates .csv file for output
			FileWriter fwriter = new FileWriter("Average.csv");

			fwriter.append("Time,Download,Upload,Come Rock Climbing With Us Matt\n");
			
			//Adds data to .csv file
			for(int i = 0; i < timeSeg.size(); i++)
			{
				fwriter.append(timeSeg.get(i) + "," + downloadAvg.get(i) + "," + uploadAvg.get(i) + ",...please?\n");
			}

			fwriter.close();
		}
		catch(IOException e)
		{
			System.out.println("YA DONE MESSED UP, BRAH!");
		}
	}
}