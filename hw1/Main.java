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

		/*Scanner finder = new Scanner("speed.txt");
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

		return 0;*/
	}

	public static void parse(ArrayList<String> tests) 
	{
		ArrayList<Date> time = new ArrayList<Date>();
		ArrayList<Double> download = new ArrayList<Double>();
		ArrayList<Double> upload = new ArrayList<Double>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		String scan = "";

		for(int i = 0; i < tests.size(); i++) {

			scan = tests.get(i);

			Scanner lineReader = new Scanner(scan);
			String happyBirthdayBryce = "";

			while(lineReader.hasNextLine())
			{
				happyBirthdayBryce = lineReader.nextLine();

				if(happyBirthdayBryce.startsWith("Mon") || happyBirthdayBryce.startsWith("Tue") || 
						happyBirthdayBryce.startsWith("Wed") || happyBirthdayBryce.startsWith("Thu") || 
						happyBirthdayBryce.startsWith("Fri") || happyBirthdayBryce.startsWith("Sat") ||
						happyBirthdayBryce.startsWith("Sun"))
				{
					try
					{
						time.add(dateFormat.parse(happyBirthdayBryce));
					}
					catch(Exception e)
					{
						System.out.println("Check the parse method, dummy.");
					}
				}

				if(happyBirthdayBryce.startsWith("Download: "))
				{
					download.add(lineReader.nextDouble());
				}

				if(happyBirthdayBryce.startsWith("Upload: "))
				{
					upload.add(lineReader.nextDouble());
				}
			}
		}

		ArrayList<Date> timeSeg = new ArrayList<Date>();
		ArrayList<Double> downloadAvg = new ArrayList<Double>();
		ArrayList<Double> uploadAvg = new ArrayList<Double>();

		double downTotal = 0;
		double upTotal = 0;

		for(int i = 0; i < time.size(); i+=3)
		{
			downTotal = download.get(i) + download.get(i + 1) + download.get(i + 2);
			upTotal = upload.get(i) + upload.get(i + 1) + upload.get(i + 2);

			timeSeg.add(time.get(i));
			downloadAvg.add(downTotal/3);
			uploadAvg.add(upTotal/3);
		}

		try
		{
			FileWriter fwriter = new FileWriter("Average.csv");
			
			for(int i = 0; i < timeSeg.size(); i++)
			{
				fwriter.append("Time: " + timeSeg.get(i) + " " + "Average Download Speed: " + downloadAvg.get(i) 
				+ " " + "Average Upload Speed: " + uploadAvg.get(i));
			}
			
			fwriter.close();
		}
		catch(IOException e)
		{
			
		}
	}
}