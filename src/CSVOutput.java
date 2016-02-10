import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dan Harshbarger on 1/22/2016.
 */
public class CSVOutput {
    static int DAY_WIDTH = 86400;

    public static void generateCSV(int days, List<Interval> inv) {

        Date curDate = new Date();
        curDate.setHours(23);
        curDate.setMinutes(59);
        curDate.setSeconds(59);

        long to = dateToUnix(curDate);
        long from = to - (days * DAY_WIDTH) + 1;
        long tempDate = 0;

        DecimalFormat df = new DecimalFormat("#.00");
        int counter = 0;

        try{
            FileWriter writer = new FileWriter("Average.csv");

            writer.append("Date, Download Speed, Upload Speed\n");
            for(int i = inv.size()-1; i >= 0; i--){
                    tempDate = inv.get(i).getStartDateTime();
                if(tempDate <= to && tempDate >= from) {
                    writer.append(unixToDate(tempDate).toString() + ",");
                    writer.append(df.format(inv.get(i).getAvgDown()) + ",");
                    writer.append(df.format(inv.get(i).getAvgUp()) + " ,\n");
                    counter ++;
                }
            }

            if(counter == 0){
                writer.append("No records found for entered time-frame");
            }
            writer.close();

        }catch(IOException e){
            System.out.println("Didn't Work");
        }
    }

    public static Date unixToDate(long ut) {

        Date date = new Date(ut * 1000);
        return date;
    }

    public static long dateToUnix(Date date){
        long unixTime = date.getTime()/1000;
        return unixTime;
    }
}
