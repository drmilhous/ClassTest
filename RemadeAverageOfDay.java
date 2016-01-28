import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class remade {

	//Accept as a command line argument the number of days to average and the number of days to go back.
	public static Integer[] Days(int totalDays)
	{

		boolean repeat= false;
		boolean again= false;
		Integer[]  numDays= new Integer[2];

		//command line argument the number of days to go back

		do
		{
			String numOFDaysToGoBack=JOptionPane.showInputDialog(null,"Enter the Number of days to Go Back:\n Only Choose Between [1-"+(totalDays)+"] \n Enter 0 for the same day ","NUM OF DAYS TO GO BACK",1);
			numDays[0]= Integer.parseInt(numOFDaysToGoBack);
			if(numDays[0]>= 0 && numDays[0]<=(totalDays))
			{
				again= false;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The number of Days has to be greater or equal to 0 and less than total number of days");
				again= true;
			}
		}while(again== true);

		//command line argument the number of days to average
		do
		{
			String numOFDaysToAvg=JOptionPane.showInputDialog(null,"Enter the Number of days to Average: \n Choose From "+"1 - "+ (numDays[0]+1),"NUM OF DAYS TO AVG",1);
			numDays[1]= Integer.parseInt(numOFDaysToAvg);
			if(numDays[1]>0 && numDays[1]<=(numDays[0]+1) )
			{
				repeat= false;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The number of days to average has to greater than 0 and less than or equal to total number of days");
				repeat= true;
			}
		}while(repeat==true);



		return numDays;
	}

	@SuppressWarnings("deprecation")
	public static boolean compareDay(Date input)
	{

		if(compareDay.getYear()>=input.getYear())
		{
			if(compareDay.getMonth()>=input.getMonth())
			{
				if(compareDay.getDate()>=input.getDate())
				{						
					return true;

				}else
					return false;	
			}else
				return false;	
		}else
			return false;
	}



	//method to read lines

	private static Date fileDate;
	private static String list[][];//0
	private static Date compareDay;

	public static int readLine(Scanner fileName) throws ParseException//count for number of the date range
	{
		int arrayRange=1; 
		String line=fileName.nextLine();
		SimpleDateFormat sdfmt1 = null;
		sdfmt1 = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		fileDate=sdfmt1.parse(line);

		while( fileName.hasNext())
		{
			line= fileName.nextLine();

			try {

				Date d = sdfmt1.parse(line);
				if(compareDate(d)==false)
				{
					arrayRange++;
				}


			} catch (ParseException e) {

			}

		}
		return arrayRange;

	}

	//If is equals return ture, if it is bigger turn false and replace the compareDate.
	public static boolean compareDate(Date input)
	{

		if(fileDate.getYear()>=input.getYear())
		{
			if(fileDate.getMonth()>=input.getMonth())
			{
				if(fileDate.getDate()>=input.getDate())
				{
					if(fileDate.getHours()>=input.getHours())
					{ 
						return true;
					}else
						return false; 
				}else
					return false; 
			}else
				return false; 
		}else
			return false;

	}

	public static String getRange(Date first,Date second)
	{
		String range="";

		range+=first.toString()+" to "+second;

		return range;
	}
	//Sat Jan 09 22:00:11 to 09 22:59:51 CST 2016


	public static void main(String[] args) throws ParseException {



		GetFile file1= new GetFile();
		File fileName= file1.getFile();
		//Scanning the file
		Scanner file = null;

		try {
			file = new Scanner(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Firstline
		String line=file.nextLine();
		SimpleDateFormat sdfmt1 = null;
		sdfmt1 = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		fileDate=sdfmt1.parse(line);
		int linecount=1;

		Scanner fileA= null;

		try {
			 fileA= new Scanner(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String iniDate= fileA.nextLine();
		Date initialDate=sdfmt1.parse(iniDate); 
		Date finalDate= null;
		while(fileA.hasNext())
		{
			try {
				String dateline= fileA.nextLine();
				
				finalDate=sdfmt1.parse(dateline);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		}
		System.out.println(finalDate);
		Integer [] hold= Days(finalDate.getDate()-initialDate.getDate());
		int noOfDaysToGoBack= hold[0]; 
		int noOfDaysToAvg= hold[1];

		// Command line input calculation
		

		int daytoBeginWith= finalDate.getDate()-noOfDaysToGoBack;
		
		int daytoEnd= daytoBeginWith + noOfDaysToAvg; 
 

		//Set up sum value
		double downloadAverage=0;
		double uploadAverage=0;
		int countNum=0;
		Date lastDate=null;
		list=new String [25][noOfDaysToAvg*2+1];
		clearWithZero(noOfDaysToAvg);
		compareDay=fileDate;
		
		list[0][linecount]=fileDate.getDate()+" Download";
		list[0][linecount+1]=fileDate.getDate()+" Upload";
		linecount+=2;
		//Reading through the file
		while( file.hasNext())
		{
			line= file.nextLine();

			try {
				Date d = sdfmt1.parse(line);
				if(d.getDate()>=daytoBeginWith && d.getDate()< daytoEnd)
				{

					if(compareDate(d))
					{

					}else{	

						if (!compareDay(d))
						{
							try {
								list[0][linecount]=d.getDate()+" Download";
								list[0][linecount+1]=d.getDate()+" Upload";
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							compareDay=d;
							linecount+=2;
						}
						fileDate=d;

					}
				}
				//docode()
				; } catch (ParseException e) {
					// TODO Auto-generated catch bloc
				}
		}


		try {
			file = new Scanner(fileName);
			//file = new Scanner(new File("speed.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Make the array
		informationRange(daytoBeginWith,daytoEnd,file);

		try {
			CSVMake(noOfDaysToAvg);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		file.close();

	}

	public static String[][] informationRange(int beginDate, int finishDate, Scanner x) throws ParseException
	{
		System.out.println(beginDate+" "+finishDate);
		String listInRange[][]=new String[24][(finishDate-beginDate)*2];
		//Firstline
		String line=x.nextLine();
		SimpleDateFormat sdfmt1 = null;
		sdfmt1 = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		
		int linecount=0;

		//Set up sum value
		double downloadAverage=0;
		double uploadAverage=0;
		int countNum=0;
		int countDate=0;
		Date lastTime=compareDay;
		

		while( x.hasNext())
		{
			line= x.nextLine();

			try {
				Date d = sdfmt1.parse(line);


				if(d.getDate()==beginDate)
				{
					//System.out.println(line);
					compareDay=d;
					fileDate=d;
					break;
				}
				//docode()
				;			} catch (ParseException e) {
					// TODO Auto-generated catch bloc
				}
		}


		while( x.hasNext())
		{
			
			line= x.nextLine();

			try{
				Date d = sdfmt1.parse(line);
				if(compareDate(d))
				{
					lastTime=d;
					
				}else{

					System.out.println(line);
					double dowoloadAverageSave=downloadAverage/countNum;
					double uploadAverageSave=uploadAverage/countNum;
					listInRange[lastTime.getHours()][linecount]=Double.toString(round(dowoloadAverageSave));
					listInRange[lastTime.getHours()][linecount+1]=Double.toString(round(uploadAverageSave));


					if (!compareDay(d))
					{
//						System.out.println(d.getDate());
						linecount+=2;
						compareDay=d;
					}
					fileDate=d;
					countNum=0;
					downloadAverage=0;
					uploadAverage=0;

//					System.out.println(listInRange[d.getHours()][linecount]);

				}//docode()
				;			} catch (ParseException e) {
					// TODO Auto-generated catch bloc
				}
			
		
			String[] splitData=line.split(" ");
			if(splitData[0].equalsIgnoreCase("Download:"))
			{
//				System.out.println(line);
				countNum++;
				Double input=Double.parseDouble(splitData[1]);
				downloadAverage+= input;
				x.nextLine();
				line=x.nextLine();
				splitData=line.split(" ");
				uploadAverage+=Double.parseDouble(splitData[1]);
			}
			
			if(lastTime.getDate()==finishDate)
			{
				
				break;
			}
		}


		for(int d=0;d<24;d++)
			for(int y=0;y<((finishDate-beginDate)*2);y++)
			{
				if(listInRange[d][y]==null)
				{
					list[d+1][y+1]="0";
				}else
					list[d+1][y+1]=listInRange[d][y];
			}
				

		return listInRange;

	}



	public static void clearWithZero(int z)
	{
	

		list[0][0]="Day range";
		for(int x=1;x<25;x++)
		{
			list[x][0]=(x-1)+":00 to "+x+":00";
		}


	}



	//Formatting all the decimals to two's place
	public static double round(double f)
	{
		if(Double.isNaN(f))
		{
			return 0;
		}else{
			BigDecimal   b   =   new   BigDecimal(f);
			double   f1   =   b.setScale(2, RoundingMode.HALF_UP).doubleValue();
			return f1;
		}
	}

	//Writing into a CSV File
	public static void CSVMake(int z) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("the-file-name.csv", "UTF-8");
		for(int x=0;x<25;x++)
		{
			for(int y=0;y<(z*2+1);y++)
			{
				writer.print(list[x][y]+",");
			}
			writer.println();
		}
		writer.close();
	}
}


