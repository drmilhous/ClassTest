import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Interval {
    private ArrayList<Test> tests;
    private double avgUp;
    private double avgDown;
    private long startDateTime;

    public Interval(ArrayList<Test> t, long date) {
        this.tests = new ArrayList<Test>();
        for(int i=0; i<t.size(); i++) {
            this.tests.add(new Test(t.get(i)));
        }
        this.startDateTime = date;

        double maxUp = Double.MIN_VALUE;
        double minUp = Double.MAX_VALUE;
        double maxDown = Double.MIN_VALUE;
        double minDown = Double.MAX_VALUE;

        ArrayList<Double> revisedUp = new ArrayList<Double>();
        ArrayList<Double> revisedDown = new ArrayList<Double>();

        int minDownIndex = -1;
        int maxDownIndex = -1;
        int minUpIndex = -1;
        int maxUpIndex = -1;

        if(tests.size() > 2) {
            for(int i=0; i<tests.size(); i++) {
                if(tests.get(i).getDown() < minDown) {
                    minDown = tests.get(i).getDown();
                    minDownIndex = i;
                }
                if(tests.get(i).getUp() < minUp) {
                    minUp = tests.get(i).getUp();
                    minUpIndex = i;
                }
                if(tests.get(i).getDown() > maxDown) {
                    maxDown = tests.get(i).getDown();
                    maxDownIndex = i;
                }
                if(tests.get(i).getUp() > maxUp) {
                    maxUp = tests.get(i).getUp();
                    maxUpIndex = i;
                }


                revisedUp.add(new Double(tests.get(i).getUp()));
                revisedDown.add(new Double(tests.get(i).getDown()));

            }

            revisedDown.remove(Math.max(minDownIndex, maxDownIndex));
            revisedDown.remove(Math.min(minDownIndex, maxDownIndex));
            revisedUp.remove(Math.max(minUpIndex, maxUpIndex));
            revisedUp.remove(Math.min(minUpIndex, maxUpIndex));

        }else {
            for(int i=0; i<t.size(); i++) {
                revisedUp.add(new Double(tests.get(i).getUp()));
                revisedDown.add(new Double(tests.get(i).getDown()));
            }
        }

        this.avgUp = average(revisedUp);
        this.avgDown = average(revisedDown);
    }

    private static double average(ArrayList<Double> dubs){
        int n = dubs.size();
        double sum = 0;

        for(int i=0; i<n; i++) {
            sum += dubs.get(i);
        }

        return sum/n;
    }

    public double getAvgUp() {
        return avgUp;
    }

    public double getAvgDown() {
        return avgDown;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
