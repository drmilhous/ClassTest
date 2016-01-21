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
        //int segmentCounter = 0; //So we only get one segment to test with.
        while(fileScanner.hasNextLine())
        {
            try {
                //Testing code from Homework specs.
                String date = fileScanner.nextLine();
                Date formattedDate = dateFormatter.parse(date);
                System.out.println(date + "\n" + formattedDate);
                System.exit(0);

                //System.out.println(fileScanner.nextLine());
                //segmentCounter++;
                //if (segmentCounter == 10)
                //    break;
            } catch(ParseException e) {
                System.out.println("Error in parsing the line.");
            }
        }
    }

    /**
     * This method should get the segments of info depending on the hours.
     */
    public void getSegments() {

    }

    /**
     * This method gets the throughput from each segment of data.
     */
    public void getThroughput() {

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