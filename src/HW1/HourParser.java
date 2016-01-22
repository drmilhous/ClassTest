package HW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Write a program that will ask the user the number of hours to go back.
 * Create a summery given for each hour. Produce a CSV file.
 * As user for the number of hours. (TeamName)
 */
public class HourParser {
	private final int SEGLENGTH = 10; //Length one segment

    private String fileName;
    private int hours;

    public HourParser(String name, int h) {
        fileName = name;
        hours = h;
    }

    public void readFile() {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.err.println("Error finding the file.");
            System.exit(0);
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        
        try {
    		//Creates array depending on how many hours the user enters.
    		String[][] segments = new String[hours * 12][SEGLENGTH];
    		segments = getSegments(fileScanner,segments);
    		
    		//Uncomment to see the output of the segments array.
//    		for(int x=0;x<(hours*12);x++) {
//    			if(x%12 == 0) {
//    				System.out.println("\n=============================NEW HOUR=============================");
//    			}
//    			for(int i=0;i<SEGLENGTH;i++) {
//    				System.out.println(segments[x][i]);
//    			}
//    			System.out.println("\n");
//    		}

          } catch(Exception e) { //Change Exception
              System.out.println("Error in parsing the line.");
          }
    }

    /**
     * This method should get the segments of info depending on the hours.
     */
    public String[][] getSegments(Scanner scan, String[][] seg) {
    	int segmentCount = -1;
    	int lineCount = 0;
    	int segmentCheck = 0; //Should always equal 1 when program is ran.
    	
    	while(scan.hasNextLine()) {
    		if(scan.hasNext("Mon") || scan.hasNext("Tue") || scan.hasNext("Wed") || scan.hasNext("Thu") || scan.hasNext("Fri") ||
    		   scan.hasNext("Sat") || scan.hasNext("Sun")) {
    			segmentCount++;
    			segmentCheck++;
    			System.out.println(segmentCount + " SegCheck: " + segmentCheck + " Line: " + lineCount);
    		}
    		
    		//Checks if there is a another start of a date.
    		if(segmentCheck > 1) {
    			System.out.println("Found Fail Case....");
    			segmentCount--;
    			segmentCheck= 1;
    			lineCount = 0;
    		}
    		
    		seg[segmentCount][lineCount] = scan.nextLine();
    		
    		if(segmentCount == (hours * 12)-1 && lineCount == SEGLENGTH-1) {
    			break; //Breaks if the user entered hour is equal to the hour counter
    		}else if(lineCount == SEGLENGTH-1) {
    			segmentCheck--;
    			lineCount = 0;
    		}else {
    			lineCount++;
    		}
    	}
    	
    	return seg;
    }

    /**
     * This method gets the throughput from each segment of data.
     */
    public void getThroughput(String[][] seg) {
    	int hoursCount;  // keeps track of hours until target is met
    	int testCount;  // approx 9 tests/hour
    	double avgUp, avgDown;
    	Scanner numFinder = new Scanner();
    	
    	while(hoursCount < hours){
    		double up = 0;
    		double down = 0;
    		
    		while(testCount < 9){   // totaling the previous 9 upload and download speeds
    			up+=numFinder.nextDouble(seg[hoursCount][7]);
    			down+=numFinder.nextDouble(seg[hoursCount][9]);
    			testCount++;
    		}
    		avgUp = up / 9;  // compute average Upload speed
    		avgDown = down / 9;  // compute average Download speed
    		hoursCount++;
    		System.out.println("\n"+hoursCount+" hour(s) ago... \n"+"  Avg Upload: "+avgUp+"\n  Avg Download: "+avgDown);
    	}
    	numFinder.close();
    }

    /**
     * This method will use the data and write to a csv file.
     */
    public void writeToCSV() {

    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the number of hours: ");
        int hours = keyboard.nextInt();
        keyboard.close();

        new HourParser("speed.txt", hours).readFile();
    }
}