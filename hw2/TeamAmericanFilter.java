package hw2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TeamAmericanFilter implements Filter{


	Date	start;

	public Date getStart()
	{
		return start;
	}

	public TeamAmericanFilter(Date start)
	{
		super();
		this.start = start;

	}

	@Override
	public List<LogEntry> filter(List<LogEntry> l)
	{
		float downloadTotal = 0;
		float uploadTotal = 0;
		float averageDownload;
		float averageUpload;
		Date iniDate = null;
		int iniHour = 0;
		int iniMin = 0;
		int count = 0;
		int entries = 0;
		int time1 = 0;
		int time2 = 15;
		Date replacer = null;
		boolean newHour = false;
		boolean goBack = false;
		List<LogEntry> result = new LinkedList<LogEntry>();
		for (LogEntry m : l)
		{
			//if (m.getTime().before(getEnd()) && m.getTime().after(getStart()))	
			//The condition above copied from Dr miller is not working so the condition below is alternative 
			if(m.getTime().getDate() == start.getDate())
			{
				iniDate = m.getTime();
				iniHour = iniDate.getHours();
				iniMin = iniDate.getMinutes();
				//Creating a do loop to move one entry back in order to not skip the entry when if condition is false
				do
				{
					if ((iniHour*60) + iniMin > time1 && (iniHour*60) + iniMin <= time2)
					{
						downloadTotal += m.getDownload();
						uploadTotal += m.getUpload();				
						entries++;
						goBack = false;
					}
					else
					{
						if (time2 % 60 == 0) {
							newHour = true;
						}
						
						averageDownload = downloadTotal/entries;
						averageUpload = uploadTotal/entries;
						iniDate.setMinutes(time2);
						iniDate.setSeconds(0);
						downloadTotal = 0;
						uploadTotal = 0;
						entries = 0;
						goBack = true;
						//if(time1 != 45 && time2 != 60 )
						//{
							time1 += 15;
							time2 += 15;
						//}
						//else
						//{
						//	time1 = 0;
						//	time2 = 15;
						//}
						if (newHour == true) {
							replacer = iniDate;
							replacer.setMinutes(replacer.getMinutes() - 60);
							result.add(new LogEntry(averageDownload, averageUpload, replacer));
							newHour = false;
						} else {
							result.add(new LogEntry(averageDownload, averageUpload, iniDate));
						}
					}

				}while(goBack == true);
			}
		}
		return result;
	}
}