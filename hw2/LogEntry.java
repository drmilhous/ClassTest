package hw2;

import java.util.Date;

public class LogEntry {

	private Float upload;
	private Float download;
	private Date time;

	public LogEntry(Float download, Float upload, Date time)
	{
		super();
		this.download = download;
		this.upload =  upload;
		this.time = time;
	}
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


	public Float getUpload() {
		return upload;
	}

	public void setUpload(Float upload) {
		this.upload = upload;
	}

	public Float getDownload() {
		return download;
	}

	public void setDownload(Float download) {
		this.download = download;
	}
	@Override
	public String toString()
	{
		return "LogEntry [download=" + download + ", upload=" + upload + ", time=" + time + "]";
	}

}
