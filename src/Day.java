import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Day {
    private ArrayList<Interval> intervals = new ArrayList<Interval>();
    private Date date; // calculate

    public Day(ArrayList<Interval> intervals, Date date) {
        this.intervals = intervals;
        this.date = new Date(date.getTime());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }
}
