package HW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class HourParser {

    public static void main(String[] args) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("speed.txt"));
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
                Date formattedDate = dateFormatter.parse(fileScanner.nextLine());
                System.out.println(formattedDate);
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

}