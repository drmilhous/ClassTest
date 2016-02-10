import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static int DAY_WIDTH = 86400;

	public static void main(String[] args) {
		String path = "speed.txt";
		if (args.length > 0)
		{
			path = args[0];
		}
		ParseFile pf = new ParseFile(path);
		List<LogEntry> entries = pf.getEntries();
		System.out.println(entries.size());

		Date start = new Date();
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);

		start.setDate(9);

		Date end = new Date();
		end.setHours(0);
		end.setMinutes(0);
		end.setSeconds(0);

		end.setDate(10);
		//MillerFilter m = new MillerFilter(start, end);
		Filter j = new JuicyFilter(10); //parameter should be days to look back
		List<Interval> juicies = j.logLazer(entries);

		Scanner in = new Scanner(System.in);
		System.out.println("This program will show the speed test results.\nHow many days would you like to check?\n");
		int days2Check = in.nextInt();


		printResults(days2Check, juicies);
		CSVOutput out = new CSVOutput();
		out.generateCSV(days2Check,juicies);
	}

	public static void printResults(int days, List<Interval> inv) {
		Date curDate = new Date();
		curDate.setHours(23);
		curDate.setMinutes(59);
		curDate.setSeconds(59);

		long to = UnixDateUtil.dateToUnix(curDate);
		long from = to - (days * DAY_WIDTH) + 1;
		long tempDate = 0;

		DecimalFormat df = new DecimalFormat("#.00");

		int counter = 0;

		for(int i = inv.size()-1; i >= 0; i--) {
			tempDate = inv.get(i).getStartDateTime();
			if(tempDate <= to && tempDate >= from){
				System.out.println(UnixDateUtil.unixToDate(tempDate));
				System.out.println("Average Download: " + df.format(inv.get(i).getAvgDown()));
				System.out.println("Average Upload: " + df.format(inv.get(i).getAvgUp()));
				System.out.println();
				counter++;
			}
		}

		if(counter == 0) {
			System.out.println("No records found for the entered time-frame.");
		}
	}

}
