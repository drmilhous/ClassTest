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
		public List<LogEntry> logLazer(List<LogEntry> l)
			{
				List<LogEntry> result = new LinkedList<LogEntry>();
				for (LogEntry m : l)
					{
						if (m.getTime().before(getEnd()) && m.getTime().after(getStart()))
							{
								result.add(m);
							}
					}

				return result;
			}

	}
