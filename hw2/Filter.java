package hw2;


import java.util.List;

public interface Filter
	{
		public List<LogEntry> filter(List<LogEntry> l);
	}