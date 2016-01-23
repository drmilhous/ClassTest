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

            writer.append("Time, Avg Download, Avg Upload");

            for(int i = 0; i < days.size();i++){
                writer.append(days.get(i).getDate() + "," + days.get(i).getIntervals().getAvgDown() + "," + days.get(i).getIntervals());
                for(int j = 0; j < days.get(i).getIntervals().size(){
                    writer.append(days.get(i).getIntervals(j).)
                }
            }

            writer.close();

        }catch(IOException e){
            System.out.println("Didn't Work");
        }
    }
}
