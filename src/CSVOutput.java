import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dan Harshbarger on 1/22/2016.
 */
public class CSVOutput {

    public static void generateCSV(int daysBack, ArrayList<Day> days) {

        try{
            FileWriter writer = new FileWriter("Average.csv");

            for(int i = 0; i < days.size();i++){

                for(int j = 0; j < days.get(i).getIntervals().size();j++){

                    writer.append(days.get(i).getIntervals().get(i).getStartDateTime().toString());
                    writer.append("Download: " + days.get(i).getIntervals().get(i).getAvgDown() + " mbps");
                    writer.append("Upload: " + days.get(i).getIntervals().get(i).getAvgUp() + " mbps");
                }
            }

            writer.close();

        }catch(IOException e){
            System.out.println("Didn't Work");
        }
    }
}
