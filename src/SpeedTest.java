import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Keathan on 1/22/2016.
 */
public class SpeedTest {
    static int INTERVAL_WIDTH = 1800;
    static int DAY_WIDTH = 86400;

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        System.out.println("This program will show the speed test results.\nHow many days would you like to check?\n");
        int days2Check = in.nextInt();

        File file = new File("speed.txt");

        Parser parser = new Parser(file);
        Test[] tests = Parser.readInFile();

        Date startDate = unixToDate(tests[0].getDateTime());
        if (startDate.getMinutes() < 30) {
            startDate.setMinutes(0);
        }else {
            startDate.setMinutes(30);
        }
        startDate.setSeconds(0);

        long intervalDate = dateToUnix(startDate);
        ArrayList<Test> testInInterval= new ArrayList<Test>();
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        int intervalCounter = 0;

        for(int i = 0; i < tests.length; i++) {
            if(tests[i].getDateTime() < intervalDate + INTERVAL_WIDTH){
                testInInterval.add(new Test(tests[i].getDateTime(), tests[i].getDown(), tests[i].getUp()));
            }else {
                intervals.add(new Interval(testInInterval, intervalDate));
                intervalCounter++;
                intervalDate += INTERVAL_WIDTH;
                testInInterval = new ArrayList<Test>();
                testInInterval.add(new Test(tests[i]));
            }
        }

        if(!testInInterval.isEmpty()) {
            intervals.add(new Interval(testInInterval, intervalDate));
        }


      printResults(days2Check, intervals);

    }

    public static long dateToUnix(Date date){
        long unixTime = date.getTime()/1000;
        return unixTime;
    }

    public static Date unixToDate(long ut) {

        Date date = new Date(ut * 1000);
        return date;
    }

    public static void printResults(int days, ArrayList<Interval> inv) {
        Date curDate = new Date();
        curDate.setHours(23);
        curDate.setMinutes(59);
        curDate.setSeconds(59);

        long to = dateToUnix(curDate);
        long from = to - (days * DAY_WIDTH) + 1;
        long tempDate = 0;

        DecimalFormat df = new DecimalFormat("#.00");

        //From : 1453528800
        //To : 1453615199

        int counter = 0;

        for(int i = inv.size()-1; i >= 0; i--) {
            tempDate = inv.get(i).getStartDateTime();
            if(tempDate <= to && tempDate >= from){
                System.out.println(unixToDate(tempDate));
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
