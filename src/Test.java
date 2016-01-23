import java.util.Date;

/**
 * Created by Keathan on 1/22/2016.
 */
public class Test {
    private Date dateTime;
    private double down;
    private double up;

    public Test(Date unixTime, double down, double up) {
        this.dateTime = unixTime;
        this.down = down;
        this.up = up;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
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
