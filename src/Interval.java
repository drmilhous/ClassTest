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

    public Interval(ArrayList<Test> test, Date date) {
        this.tests = test;
        this.startDateTime = new Date(date.getTime());

        init(test);
    }

    private static void init(ArrayList<Test> t) {
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

        if(t.size() > 2) {
            for(int i=0; i<t.size(); i++) {
                if(t.get(i).getDown() < minDown) {
                    minDown = t.get(i).getDown();
                    minDownIndex = i;
                }
                if(t.get(i).getUp() < minUp) {
                    minUp = t.get(i).getUp();
                    minUpIndex = i;
                }
                if(t.get(i).getDown() > maxDown) {
                    maxDown = t.get(i).getDown();
                    maxDownIndex = i;
                }
                if(t.get(i).getUp() > maxUp) {
                    maxUp = t.get(i).getUp();
                    maxUpIndex = i;
                }


                revisedUp.add(t.get(i).getUp());
                revisedDown.add(t.get(i).getDown());

            }

            revisedDown.remove(Math.max(minDownIndex, maxDownIndex));
            revisedDown.remove(Math.min(minDownIndex, maxDownIndex));
            revisedUp.remove(Math.max(minUpIndex, maxUpIndex));
            revisedUp.remove(Math.min(minUpIndex, maxUpIndex));

        }else {
            for(int i=0; i<t.size(); i++) {
                revisedUp.add(t.get(i).getUp());
                revisedDown.add(t.get(i).getDown());
            }
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

}
