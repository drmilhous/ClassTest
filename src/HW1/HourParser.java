package HW1;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class HourParser {
	
	public static void main(String[] args) {
		int hours = 0;
		
		String input = "";
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the amount of hours to average out: ");
		
		input = scan.next();
		scan.close();
		
		hours = Integer.parseInt(input);
		
		
	}
}