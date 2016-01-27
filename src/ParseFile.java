import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class ParseFile
	{
		private LinkedList<LogEntry> entries;

		public ParseFile(String path)
			{
				setEntries(new LinkedList<LogEntry>());
				float Download = 0;
				float UploadXX = 0;
				File f = new File(path);
				Scanner s = null;
				try
					{
						s = new Scanner(f);

					}
				catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
				if (s != null)
					{
						String line = s.nextLine();
						Date now = new Date();
						now.setTime(now.getTime() - 60 * 60 * 24 * 1000);
						Date d = new Date();

						while (s.hasNextLine())
							{
								int space1 = line.indexOf(" ");
								int space2 = line.indexOf(" ", space1 + 1);
								if (line.contains("Upload:"))
									{
										UploadXX = Float.parseFloat(line.substring(space1, space2));
										// System.out.println(Download + "," + Upload + "," + d.getHours() + ":" + d.getMinutes());
										getEntries().add(new LogEntry(Download, UploadXX, d));

									}
								else if (line.contains("Download"))
									{
										Download = Float.parseFloat(line.substring(space1, space2));

									}
								else if (line.contains("CST"))
									{
										SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM dd HH:mm:ss yyyy");
										sdfmt1 = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
										try
											{
												d = sdfmt1.parse(line);
											}
										catch (ParseException e)
											{
												e.printStackTrace();
											}
									}

								line = s.nextLine();
							}
						s.close();
					}
			}

		private LinkedList<LogEntry> getEntries()
			{
				return entries;
			}

		public void setEntries(LinkedList<LogEntry> entries)
			{
				this.entries = entries;
			}
	}
