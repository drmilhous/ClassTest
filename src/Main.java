import java.util.Date;
import java.util.List;

public class Main
	{

		public static void main(String[] args)
			{
				String path = "speed.txt";
				if (args.length > 0)
					{
						path = args[0];
					}
				ParseFile pf = new ParseFile(path);
				List<LogEntry> entries = pf.getEntries();
				System.out.println(entries.size());

				Date start = new Date();
				start.setHours(0);
				start.setMinutes(0);
				start.setSeconds(0);

				start.setDate(9);

				Date end = new Date();
				end.setHours(0);
				end.setMinutes(0);
				end.setSeconds(0);

				end.setDate(10);
				MillerFilter m = new MillerFilter(start, end);
				//Juicebox j = new JuicyFilter(start, end);
				List<LogEntry> millers = m.logLazer(entries);
				for (LogEntry l : millers)
					{
						System.out.println(l);
					}

			}

	}
