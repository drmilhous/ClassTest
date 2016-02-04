package hw2;

import java.util.List;

public class TeamAmericanFilter implements Filter{

	private int date;
	private String fileName;
	
	//Constructor for this class
	public TeamAmericanFilter(int date)
	{
		super();
		this.date = date;
		
	}
	public List<LogEntry> filter(List<LogEntry> l) {
		
		return null;
	}

}
