import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MillerFilter implements Filter
	{
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

		public MillerFilter(Date start, Date end)
			{
				super();
				this.start = start;
				this.end = end;
			}

		@Override
		public List<Interval> logLazer(List<LogEntry> l)
			{
				List<Interval> result = new LinkedList<Interval>();
				for (LogEntry m : l)
					{
					}

				return result;
			}

	}
