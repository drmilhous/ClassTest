import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Interval {
    ArrayList<Test> tests = new ArrayList<Test>();
    private static double avgUp;
    private static double avgDown;
    private Date startDateTime;

    public Interval(ArrayList<Test> test) {
        this.tests = tests;
        setStartDateTime();
        init(test);
    }

    private static void init(ArrayList<Test> test) {
        double maxUp = Double.MIN_VALUE;
        double minUp = Double.MAX_VALUE;
        double maxDown = Double.MIN_VALUE;
        double minDown = Double.MAX_VALUE;

        ArrayList<Double> revisedUp = new ArrayList<Double>();
        ArrayList<Double> revisedDown = new ArrayList<Double>();

        int minDownIndex = 0;
        int maxDownIndex = 0;
        int minUpIndex = 0;
        int maxUpIndex = 0;

        if(test.size() < 2) {
            for(int i=0; i<test.size(); i++) {
                if(test.get(i).getDown() < minDown) {
                    minDown = test.get(i).getDown();
                    minDownIndex = i;
                }
                if(test.get(i).getUp() < minUp) {
                    minUp = test.get(i).getUp();
                    minUpIndex = i;
                }
                if(test.get(i).getDown() > maxDown) {
                    maxDown = test.get(i).getDown();
                    maxDownIndex = i;
                }
                if(test.get(i).getUp() > maxUp) {
                    maxUp = test.get(i).getUp();
                    maxUpIndex = i;
                }

                revisedUp.add(test.get(i).getUp());
                revisedDown.add(test.get(i).getDown());
            }

            revisedDown.remove(minDown);
            revisedDown.remove(maxDown);
            revisedUp.remove(minUp);
            revisedUp.remove(maxUp);

        }

        averageUp(revisedUp);
        averageDown(revisedDown);
    }

    private static void averageUp(ArrayList<Double> ups){
        int n = ups.size();
        double sum = 0;

        for(int i=0; i<n; i++) {
            sum += ups.get(i);
        }

        avgUp = sum/n;
    }

    private static void averageDown(ArrayList<Double> downs){
        int n = downs.size();
        double sum = 0;

        for(int i=0; i<n; i++) {
            sum += downs.get(i);
        }

        avgDown = sum/n;
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
