import java.util.Date;

public class UnixDateUtil {
    public static long dateToUnix(Date date){
        long unixTime = date.getTime()/1000;
        return unixTime;
    }

    public static Date unixToDate(long ut) {

        Date date = new Date(ut * 1000);
        return date;
    }

}
