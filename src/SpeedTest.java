import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Keathan on 1/22/2016.
 */
public class SpeedTest {
    static int INTERVAL_WIDTH = 1800;
    int DAY_WIDTH = 86400;

    public static void main(String args[]) {
        File file = new File("speed.txt");

        Parser parser = new Parser(file);

        Test[] tests = parser.readInFile();

        Date startDate = tests[0].getDateTime();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        long lastDay = dateToUnix(startDate);

        System.out.print(lastDay);
        long lastInterval = dateToUnix(startDate);

        ArrayList<Interval> intervals = new ArrayList<Interval>();

        ArrayList<Test> tests4Interval = new ArrayList<Test>();

        int intervalCounter = 0;


        for(int i=0; i<tests.length; i++){
            if(dateToUnix(tests[i].getDateTime()) <= lastInterval + INTERVAL_WIDTH){
                tests4Interval.add(tests[i]);
            }else {
                intervals.add(new Interval(tests4Interval));
                tests4Interval.clear();
                tests4Interval.add(tests[i]);
            }
        }

        if(!tests4Interval.isEmpty()) {
            intervals.add(new Interval(tests4Interval));
        }
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
