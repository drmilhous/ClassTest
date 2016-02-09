
package HW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Write a program that will ask the user the number of hours to go back.
 * Create a summery given for each hour. Produce a CSV file.
 * As user for the number of hours. (TeamName)
 */
public class HourParser {

    private String fileName;
    private int hours;
    private double[] upload;
    private double[] download;
    
    List<Segment> segments = new LinkedList<Segment>();

    public HourParser(String name, int h) {
        fileName = name;
        hours = h;
        upload = new double[hours];
        download = new double[hours];
    }

    public void readFile() {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.err.println("Error finding the file.");
            System.exit(0);
        }
        
        try {
    		getSegments(fileScanner);
    		
//Uncomment to print all the segment's information.
//    		for(Segment x : segments) {
//    			System.out.println("Download: " + x.getDownloadTime() + " Upload: " + x.getUploadTime() + " Failed: " + x.isFailedTest());
//    		}
    		
    		//getThroughput(segments);
            //writeToCSV();
            
          } catch(Exception e) { //Change Exception
            System.out.println("Error in parsing the line.");
            e.printStackTrace();
        }
    }

    /**
     * This method should get the segments of info depending on the hours.
     */
    public void getSegments(Scanner scan) {
    	SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    	
    	int hourCounter = -1;
    	int previousHour = 0;
    	
    	boolean failedTest = false;
    	
    	String line = scan.nextLine();
    	
    	while(scan.hasNextLine()) {
    		//Breaks out of the loop if enough hours have been searched.
    		if(hourCounter == this.hours) {
    			break;
    		}
    		
    		//Finds the current hour we are on and adds on to the hour counter.
    		try {
    			Date date = format.parse(line);
    			String time = date.getHours() + ":" + date.getMinutes()+ ":" + date.getSeconds();
    			
    			if(date.getHours() != previousHour) { //Iterates the hour counter if there is a change.
    				hourCounter++;
    				previousHour = date.getHours();
    			}
    			//====================Only gets past here if it doesn't catch an exception=====================
    			scan.nextLine();
    			scan.nextLine();
    			line = scan.nextLine();
    			
    			if(line.contains("Testing") && scan.hasNextLine()) { //Finds the download and upload speeds.
    				double download = 0;
    				double upload = 0;
    				
    				while(download == 0 || upload == 0) {
    					if(scan.hasNext("Download:")) {
    						scan.next();
    						download = scan.nextDouble();
    					}else if(scan.hasNext("Upload:")) {
    						scan.next();
    						upload = scan.nextDouble();
    					}else{
    						scan.next();
    					}
    				}
    				//Uncomment to show all download/upload speeds being added.
    				//System.out.println("Download: " + download + "  Upload: " + upload + " Hour: "+ (hourCounter+1) + "\n");
    				
    				segments.add(new Segment(upload,download,failedTest));
    			}else {
    				failedTest = true;
    				segments.add(new Segment(0,0,failedTest));
    				failedTest = false;
    			}
    			
    			line = scan.nextLine();
    		} catch(Exception e) {
    			line = scan.nextLine();
    			//e.printStackTrace();
    		}
    	}
    	//The parser counts the start of a new hour as a part of previous hour.
    	//This just removes the last segment object from the segments list.
    	if(hours < 215) {
    		segments.remove(segments.size()-1);
    	}
    }

    /**
     * This method gets the throughput from each segment of data.
     */
    public void getThroughput(String[][] seg) {
    	int hoursCount = 0;  // keeps track of hours until target is met
    	int testCount = 0;  // approx 9 tests/hour
    	double avgUp, avgDown;
    	Scanner numFinder = null;
    	while(hoursCount < hours){
    		double up = 0;
    		double down = 0;
    		while(testCount < 9){   // totaling the previous 9 upload and download speeds

                numFinder = new Scanner(seg[hoursCount][7]);
                numFinder.next();
                down += numFinder.nextDouble();
                numFinder = new Scanner(seg[hoursCount][9]);
                numFinder.next();
                up += numFinder.nextDouble();
                testCount++;
                numFinder.close();
    		}
    		avgUp = up / 9;  // compute average Upload speed
    		upload[hoursCount] = avgUp;
    		
    		avgDown = down / 9;  // compute average Download speed
    		download[hoursCount] = avgDown;
    		
    		hoursCount++;
            testCount = 0; //Fixed the bug.
    		System.out.println("\n"+hoursCount+" hour(s) ago... \n"+"  Avg Upload: "+avgUp+"\n  Avg Download: "+avgDown);
    	}
    }

    /**
     * This method will use the data and write to a csv file.
     */
    public void writeToCSV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.DD");
        String fileName = dateFormat.format(new Date()) + " - log.csv";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.append("Hour,Download Average,Upload Average\n"); //Add the header.
            int hourCount = 0;
            for(int i = 0; i < download.length; i++)
            {
//                fileWriter.append("TESTHOUR,");
//                fileWriter.append("TESTDOWNLOAD,");
//                fileWriter.append("TESTUPLOAD\n");
                fileWriter.append(hourCount + ",");
                fileWriter.append(download[i] + ",");
                fileWriter.append(upload[i] + "\n");
                hourCount++;
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Write successful.");
        }
        catch(IOException e) {
            System.err.println("Error in the writing process.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the number of hours: ");
        int hours = keyboard.nextInt();
        keyboard.close();

        new HourParser("speed.txt", hours).readFile();
    }
}