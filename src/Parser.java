import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Zach on 1/22/2016.
 */
public class Parser {
    private static File file;

    public Parser(File file) {
        this.file = file;
    }

    public static Test[] readInFile(){

        ArrayList<String> testData = new ArrayList<String>();
        String aTestString = "";
        int bads = 0;
        int goods = 0;
        boolean flag = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while((line = br.readLine()) != null) {
                if(aTestString.contains("CST") && aTestString.contains("Download:") && aTestString.contains("Upload:")) {
                    testData.add(aTestString);
                    aTestString = line;
                }else if(line.contains("CST")){
                    aTestString = line;
                }else {
                    aTestString += line;
                }
            }

            if(aTestString.contains("CST") && aTestString.contains("Download:") && aTestString.contains("Upload:")) {
                testData.add(aTestString);
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Test[] test = parseTestStrings(testData);
        return test;

    }

    private static Test[] parseTestStrings(ArrayList<String> chunks) {
        SimpleDateFormat sdfmt1 = null;
        sdfmt1 = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);


        Date dateTime = null;
        double up = 0;
        double down = 0;

        String line;
        String dateString = "";
        Scanner scan;
        Scanner scan2;

        int n = chunks.size();
        int start = 0;
        int end = 0;

        Test[] t = new Test[n];

        for(int i=0; i<n; i++) {
            scan = new Scanner(chunks.get(i));

            while(scan.hasNextLine()) {
                line = scan.nextLine();

                dateString = line.substring(0, 28);

                try {
                    dateTime = sdfmt1.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                start = 10;
                end = 14;
                down = Double.parseDouble(line.substring(line.indexOf("Download:") + start, line.indexOf("Download:") + end));

                start = 8;
                end = 12;
                up = Double.parseDouble(line.substring(line.indexOf("Upload:") + start, line.indexOf("Upload:") + end));



                if(!line.contains("Download:")) {
                    System.out.println(line);
                }
            }

            t[i] = new Test(dateTime, down, up);
        }

        return t;
    }

}
