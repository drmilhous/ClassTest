import java.io.*;
import java.util.Scanner;

/**
 * Created by Zach on 1/22/2016.
 */
public class reader {
    public static void ReadinFile(){

        File file = new File("speed.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    public static void main(String[] args){
        ReadinFile();
    }
}
