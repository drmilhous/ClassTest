package hw2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TeamAmericanFilter implements Filter{


	Date	start;
	Date	end;

	public Date getStart()
	{
		return start;
	}

	public Date getEnd()
	{
		return end;
	}

	public TeamAmericanFilter(Date start, Date end)
	{
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public List<LogEntry> filter(List<LogEntry> l)
	{
		float downloadTotal = 0;
		float uploadTotal = 0;
		float averageDownload;
		float averageUpload;
		Date iniDate = null;
		int iniMin = 0;
		int count = 0;
		int entries = 0;
		int time1 = 0;
		int time2 = 20;
		List<LogEntry> result = new LinkedList<LogEntry>();
		for (LogEntry m : l)
		{
			//if (m.getTime().before(getEnd()) && m.getTime().after(getStart()))	
			//The condition above copied from Dr miller is not working so the condition below is alternative 
			if(m.getTime().getDate() == start.getDate())
			{
				iniDate = m.getTime();
				iniMin = iniDate.getMinutes();
				
				if (iniMin >= time1 && iniMin <= time2)
				{
					 //iniDate =  m.getTime();
				}
				downloadTotal += m.getDownload();
				uploadTotal += m.getUpload();
				count++;
				entries++;
				
				//if (count ==3)
				//{
					averageDownload = downloadTotal/entries;
					averageUpload = uploadTotal/entries;
					iniDate.setMinutes(time1);
					iniDate.setSeconds(0);
					downloadTotal = 0;
					uploadTotal = 0;
					count =0;
					entries = 0;
					time1 += 15;
					time2 += 15;
					result.add(new LogEntry(averageDownload, averageUpload, iniDate));
				//}
		
			}
		}

		return result;
	}

}