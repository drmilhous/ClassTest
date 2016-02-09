package HW2;

import java.util.LinkedList;
import java.util.List;

public class TeamNameFilter implements Filter
{
	private int hours;
	
	public int getHours() {
		return hours;
	}

	public TeamNameFilter(int userHours) {
		super();
		hours = userHours;
	}

	@Override
	public List<LogEntry> logLazer(List<LogEntry> l) {
		List<LogEntry> result = new LinkedList<LogEntry>();
		int hourCounter = -1;
		int previousHour = 0;
		
		for (LogEntry m : l) {
			if(hourCounter == getHours()) {
				break;
			}
			
			if(m.getTime().getHours() != previousHour) {
				hourCounter++;
			}
			
			previousHour = m.getTime().getHours();
			result.add(new LogEntry(m.getDownload(),m.getUpload(),m.getTime(),hourCounter+1));
		}
		
		//Deletes the start of the next hour only if the counter is equal to
		//the amount of hours the user wanted.
		if(hourCounter == getHours()) {
			result.remove(result.size()-1);
		}
		
		return result;
	}
}