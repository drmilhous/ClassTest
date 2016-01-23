import java.util.Date;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Test {
    private long dateTime;
    private double down;
    private double up;

    public Test(long unixTime, double down, double up) {
        this.dateTime = unixTime;
        this.down = down;
        this.up = up;
    }

    public Test(Test t) {
        this.dateTime = new Long (t.getDateTime());
        this.down = new Double(t.getDown());
        this.up = new Double(t.getUp());

    }

    public long getDateTime() {
        return dateTime;
    }

    public double getUp() {
        return up;
    }

    public double getDown() {
        return down;
    }

    @Override
    public String toString() {
        return "Test{" +
                "dateTime=" + dateTime +
                ", down=" + down +
                ", up=" + up +
                '}';
    }
}
