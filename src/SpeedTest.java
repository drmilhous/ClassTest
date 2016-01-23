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
        System.out.println("This program will show the speed test results.\nHow many days would you like to check?\n");

        int days2Check = scanin.nextInt();

        File file = new File("speed.txt");

        Parser parser = new Parser(file);
        Test[] tests = parser.readInFile();

        Date startDate = tests[0].getDateTime();

        startDate.setSeconds(0);
        if(startDate.getMinutes() < 30) {
            startDate.setMinutes(0);
        }else{
            startDate.setMinutes(30);
        }


        long lastInterval = dateToUnix(startDate);

        ArrayList<Interval> intervals = new ArrayList<Interval>();
        ArrayList<Test> tests4Interval = new ArrayList<Test>();

        Date tempDate = startDate;


        for(int i=0; i<tests.length; i++){
            if(dateToUnix(tests[i].getDateTime()) < lastInterval + INTERVAL_WIDTH){
                tests4Interval.add(tests[i]);
            }else {
                intervals.add(new Interval(tests4Interval, new Date(tempDate.getTime())));
                tests4Interval.clear();
                tests4Interval.add(tests[i]);
                tempDate = new Date(tests[i].getDateTime().getTime());
                tempDate.setSeconds(0);
                if(tempDate.getMinutes() < 30) {
                    tempDate.setMinutes(0);
                }else{
                    tempDate.setMinutes(30);
                }
                lastInterval = dateToUnix(tempDate);

            }
        }
        if(!tests4Interval.isEmpty()) {
            intervals.add(new Interval(tests4Interval, tempDate));
        }

        startDate.setSeconds(0);
        startDate.setMinutes(0);
        startDate.setHours(0);

        long lastDay = dateToUnix(startDate);

        ArrayList<Day> days = new ArrayList<Day>();
        ArrayList<Interval> daily = new ArrayList<Interval>();

        Date intervalDate;
        tempDate = startDate;

        for(int i = 0; i<intervals.size(); i++) {
            if(dateToUnix(intervals.get(i).getStartDateTime()) < lastDay + DAY_WIDTH) {
                daily.add(intervals.get(i));
            }else {
                days.add(new Day(daily, new Date(tempDate.getTime())));
                daily.clear();
                daily.add(intervals.get(i));
                tempDate = new Date(intervals.get(i).getStartDateTime().getTime());
                tempDate.setSeconds(0);
                tempDate.setMinutes(0);
                tempDate.setHours(0);
                lastDay = dateToUnix(tempDate);
            }


        }

        if(!daily.isEmpty()) {
            days.add(new Day(daily, tempDate));
        }

       printResults(days2Check, days);

    }

    public static long dateToUnix(Date date){
        long unixTime = date.getTime()/1000;
        return unixTime;
    }

    public static Date unixToDate(long ut) {

        Date date = new Date(ut * 1000);
        return date;
    }

    public static void printResults(int days, ArrayList<Day> dayList) {
        for(int i=0; i<dayList.size(); i++) {
            for(int j=0; j<dayList.get(i).getIntervals().size(); j++) {
                System.out.println(dayList.get(i).getIntervals().get(j).getStartDateTime());
                System.out.println("Download: " + dayList.get(i).getIntervals().get(j).getAvgDown() + " mbps");
                System.out.println("Upload: " + dayList.get(i).getIntervals().get(j).getAvgUp() + " mbps");
            }
        }
    }

}
