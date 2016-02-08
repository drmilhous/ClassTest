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
		List<LogEntry> result = new LinkedList<LogEntry>();
		for (LogEntry m : l)
		{
			//if (m.getTime().before(getEnd()) && m.getTime().after(getStart()))	
			//The condition above copied from Dr miller is not working so the condition below is alternative 
			if(m.getTime().getDate() == start.getDate())
			{

				//We can add out averaging code here guys!!
				
				result.add(m);
			}
		}

		return result;
	}

}