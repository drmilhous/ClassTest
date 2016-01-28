import java.util.Date;

public class LogEntry
	{
		private Float	download;
		private Float	upload;
		private Date	time;

		public LogEntry(Float download, Float upload, Date time)
			{
				super();
				this.download = download;
				this.upload = upload;
				this.time = time;
			}

		public Float getDownloadSTuff()
			{
				return download;
			}

		public void setDownload(Float download)
			{
				this.download = download;
			}

		@Override
		public String toString()
			{
				return "LogEntry [download=" + download + ", upload=" + upload + ", time=" + time + "]";
			}

		public Float getUpload()
			{
				return upload;
			}

		public void setUpload(Float upload)
			{
				this.upload = upload;
			}

		public Date getTime()
			{
				return time;
			}

		public void setTime(Date time)
			{
				this.time = time;
			}
	}
