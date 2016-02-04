package hw2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class Parse {

	private LinkedList <LogEntry> entries;
	private Date time;
	

	public Parse(String path)
	{
		File file = new File(path);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (scan != null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
			String scn = "";

			while(scan.hasNext())
			{
				 Float download = 0f;
				 Float upload = 0f;
				String yourLine = scan.nextLine(); //Stores a single line in variable

				if(yourLine.startsWith("Mon") || yourLine.startsWith("Tue") || 
						yourLine.startsWith("Wed") || yourLine.startsWith("Thu") || 
						yourLine.startsWith("Fri") || yourLine.startsWith("Sat") ||
						yourLine.startsWith("Sun"))
				{
					try
					{
						time=(dateFormat.parse(yourLine)); //Adds date and time to time ArrayList
						;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}

				//Adds Download value to download ArrayList
				if(yourLine.startsWith("Download: "))
				{
					String[] splitData=yourLine.split(" ");
					download = Float.parseFloat(splitData[1]) ;
					
					//Skips two line to get to the upload
					yourLine = scan.nextLine();
					yourLine = scan.nextLine();
				}
				
				
				
				//Adds Upload value to download ArrayList
				if(yourLine.startsWith("Upload: "))
				{
					String[] splitData=yourLine.split(" ");
					upload = Float.parseFloat(splitData[1]); 
//					System.out.println(time);
//					System.out.println(download);
//					System.out.println(upload);
					getEntries().add(new LogEntry( download, upload, time));
				}
				
			}

			scan.close();
		}
	}

	public LinkedList<LogEntry> getEntries() {
		return entries;
	}
	public void setEntries(LinkedList<LogEntry> entries) {
		this.entries = entries;
	}

}
