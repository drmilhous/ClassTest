package HW2;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		String path = "speed.txt";
		if (args.length > 0) {
			path = args[0];
		}
		
		ParseFile pf = new ParseFile(path);
		List<LogEntry> entries = pf.getEntries();
		System.out.println(entries.size());

		int hours = 0;
		
		try {
			System.out.println("Enter The Number Of Hours To Go Back");
			
			Scanner scan = new Scanner(System.in);
			String scanIn = scan.next();
			hours = Integer.parseInt(scanIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TeamNameFilter m = new TeamNameFilter(hours);
		List<LogEntry> millers = m.logLazer(entries);	
		
		String[] averages = getAverage(millers);
		
		for (String x : averages) {
			System.out.println(x);
		}
		
		writeToCSV(averages);
	}
	
	//Will average out all of the hours and return it in a String array.
	public static String[] getAverage(List<LogEntry> l) {
		float runningDownloadAvg = 0;
		float runningUploadAvg = 0;
		int counter = 1;
		int numberCounter = 0;
		
		//Will store all of the information in this string array
		String[] hourlyAverages = new String[l.get(l.size()-1).getCurrentHour()];
		
		//Figures out the averages for each hour but will not put the last hour in the array.
		for(LogEntry m : l) {
			if(m.getCurrentHour() == counter) {
				numberCounter++;
				runningDownloadAvg += m.getDownload();
				runningUploadAvg += m.getUpload();
			}else {
				hourlyAverages[counter-1] = counter + ", Download Avg: " + (runningDownloadAvg/numberCounter) + ", Upload Avg: " + (runningUploadAvg/numberCounter) + " \n";
				
				counter++;
				runningDownloadAvg = 0;
				runningUploadAvg = 0;
				numberCounter = 0;
				
				runningDownloadAvg += m.getDownload();
				runningUploadAvg += m.getUpload();
				numberCounter++;
			}
		}
		//Puts the last hour in the array.
		hourlyAverages[counter-1] = counter + ", Download Avg: " + (runningDownloadAvg/numberCounter) + ", Upload Avg: " + (runningUploadAvg/numberCounter) + " \n";
		
		return hourlyAverages;
	}
	
	
	public static void writeToCSV(String[] averages) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.DD");
        String fileName = dateFormat.format(new Date()) + " - log.csv";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.append("Hour,Download Average,Upload Average\n"); //Add the header.

            for(String x : averages) {
            	fileWriter.append(x);
            }
            
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Write successful.");
        }
        catch(Exception e) {
            System.err.println("Error in the writing process.");
            System.exit(0);
        }
    }
}