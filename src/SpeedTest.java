import java.io.File;
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
        Scanner scanin = new Scanner(System.in);
        System.out.println("This program will show the speed test results/nHow many days would you like to check?\n");

        int days2Check = scanin.nextInt();

        File file = new File("speed.txt");

        Parser parser = new Parser(file);
        Test[] tests = parser.readInFile();

        Date startDate = tests[0].getDateTime();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        long lastDay = dateToUnix(startDate);
        long lastInterval = dateToUnix(startDate);

        ArrayList<Interval> intervals = new ArrayList<Interval>();
        ArrayList<Test> tests4Interval = new ArrayList<Test>();


        for(int i=0; i<tests.length; i++){
            if(dateToUnix(tests[i].getDateTime()) <= lastInterval + INTERVAL_WIDTH){
                tests4Interval.add(tests[i]);
            }else {
                intervals.add(new Interval(tests4Interval));
                tests4Interval.clear();
                tests4Interval.add(tests[i]);
                lastInterval = dateToUnix(tests[i].getDateTime());
            }
        }

        if(!tests4Interval.isEmpty()) {
            intervals.add(new Interval(tests4Interval));
        }

        ArrayList<Day> days = new ArrayList<Day>();
        ArrayList<Interval> daily = new ArrayList<Interval>();

        Date intervalDate;

        for(int i = 0; i<intervals.size(); i++) {
            if(dateToUnix(intervals.get(i).getStartDateTime()) <= lastDay + DAY_WIDTH) {
                daily.add(intervals.get(i));
            }else {
                intervalDate = intervals.get(i).getStartDateTime();

                days.add(new Day(daily, intervalDate));
                daily.clear();
                daily.add(intervals.get(i));
                lastDay = dateToUnix(intervals.get(i).getStartDateTime());
            }
        }

        if(!daily.isEmpty()) {
            days.add(new Day(daily, daily.get(0).getStartDateTime()));
        }

        /* Dan you can call the print.out function from here if you want.

         */
    }

    public static long dateToUnix(Date date){
        long unixTime = date.getTime()/1000;
        return unixTime;
    }

    public static Date unixToDate(long ut) {

        Date date = new Date(ut * 1000);
        return date;
    }
}
