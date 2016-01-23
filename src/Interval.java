import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Interval {
    ArrayList<Test> tests = new ArrayList<Test>();
    private double avgUp;
    private double avgDown;
    private Date startDateTime;

    public Interval(ArrayList<Test> test) {
        this.tests = tests;

        setStartDateTime();

        avgUp = averageUp();
        avgDown = averageDown();
    }

    public double averageUp(){
        int n = tests.size();
        double sum = 0;

        for(int i=0; i<n; i++) {
            sum += tests.get(i).getUp();
        }

        return sum/n;
    }

    public double averageDown(){
        int n = tests.size();
        double sum = 0;

        for(int i=0; i<n; i++) {
            sum += tests.get(i).getDown();
        }

        return sum/n;
    }

    public double getAvgUp() {
        return avgUp;
    }

    public double getAvgDown() {
        return avgDown;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime() {
        long min = Long.MAX_VALUE;
        long current = Long.MAX_VALUE;

        for(int i=0; i<tests.size(); i++){
            current = SpeedTest.dateToUnix(tests.get(i).getDateTime());
            if(current < min) {
                min = current;
            }
        }

        startDateTime = SpeedTest.unixToDate(min);
    }
}
