import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Keathan on 2/8/2016.
 */
public class JuicyFilter implements Filter {
    int INTERVAL_WIDTH = 1800;


    @Override
    public List<LogEntry> logLazer(List<LogEntry> l) {
        Date startDate = UnixDateUtil.unixToDate(l.get(0).getTime());
        if (startDate.getMinutes() < 30) {
            startDate.setMinutes(0);
        }else {
            startDate.setMinutes(30);
        }
        startDate.setSeconds(0);

        long intervalDate = UnixDateUtil.dateToUnix(startDate);
        LinkedList<LogEntry> testInInterval= new LinkedList<LogEntry>();
        LinkedList<Interval> intervals = new LinkedList<Interval>();

        for(int i=0; i<l.size(); i++) {
            if(UnixDateUtil.dateToUnix(l.get(i).getTime()) < intervalDate + INTERVAL_WIDTH){
                testInInterval.add(new Test(UnixDateUtil.dateToUnix(l.get(i).getTime()), l.get(i).getDownloadSTuff(), l.get(i).getUpload()));
            }else {
                intervals.add(new Interval(testInInterval, intervalDate));
                intervalDate += INTERVAL_WIDTH;
                testInInterval = new LinkedList<LogEntry>();
                testInInterval.add(new LogEntry(l.get(i).getDownloadSTuff(), l.get(i).getUpload(), l.get(i).getTime()));
            }
        }

        if(!testInInterval.isEmpty()) {
            intervals.add(new Interval(testInInterval, intervalDate));
        }

    }
}
