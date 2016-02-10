import java.awt.geom.Path2D;
import java.util.LinkedList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Interval {
    private LinkedList<LogEntry> logEntries;
    private float avgUp;
    private float avgDown;
    private long startDateTime;

    public Interval(LinkedList<LogEntry> l, long date) {
        this.logEntries = new LinkedList<LogEntry>();
        for(int i=0; i<l.size(); i++) {
            this.logEntries.add(new LogEntry(l.get(i).getDownloadSTuff(), l.get(i).getUpload(), l.get(i).getTime()));
        }
        this.startDateTime = date;

        float maxUp = Float.MIN_VALUE;
        float minUp = Float.MAX_VALUE;
        float maxDown = Float.MIN_VALUE;
        float minDown = Float.MAX_VALUE;

        LinkedList<Float> revisedUp = new LinkedList<Float>();
        LinkedList<Float> revisedDown = new LinkedList<Float>();

        int minDownIndex = -1;
        int maxDownIndex = -1;
        int minUpIndex = -1;
        int maxUpIndex = -1;

        if(logEntries.size() > 2) {
            for(int i=0; i<logEntries.size(); i++) {
                if(logEntries.get(i).getDownloadSTuff() < minDown) {
                    minDown = logEntries.get(i).getDownloadSTuff();
                    minDownIndex = i;
                }
                if(logEntries.get(i).getUpload() < minUp) {
                    minUp = logEntries.get(i).getUpload();
                    minUpIndex = i;
                }
                if(logEntries.get(i).getDownloadSTuff() > maxDown) {
                    maxDown = logEntries.get(i).getDownloadSTuff();
                    maxDownIndex = i;
                }
                if(logEntries.get(i).getUpload() > maxUp) {
                    maxUp = logEntries.get(i).getUpload();
                    maxUpIndex = i;
                }


                revisedUp.add(new Float(logEntries.get(i).getUpload()));
                revisedDown.add(new Float(logEntries.get(i).getDownloadSTuff()));

            }

            revisedDown.remove(Math.max(minDownIndex, maxDownIndex));
            revisedDown.remove(Math.min(minDownIndex, maxDownIndex));
            revisedUp.remove(Math.max(minUpIndex, maxUpIndex));
            revisedUp.remove(Math.min(minUpIndex, maxUpIndex));

        }else {
            for(int i=0; i<l.size(); i++) {
                revisedUp.add(new Float(logEntries.get(i).getUpload()));
                revisedDown.add(new Float(logEntries.get(i).getDownloadSTuff()));
            }
        }

        this.avgUp = average(revisedUp);
        this.avgDown = average(revisedDown);
    }

    private static float average(LinkedList<Float> dubs){
        int n = dubs.size();
        float sum = 0;

        for(int i=0; i<n; i++) {
            sum += dubs.get(i);
        }

        return sum/n;
    }

    public float getAvgUp() {
        return avgUp;
    }

    public float getAvgDown() {
        return avgDown;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public LinkedList<LogEntry> getLogEntries() {
        return logEntries;
    }
}
