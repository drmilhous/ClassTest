package HW1;

public class Segment {
	
	double uploadTime;
	double downloadTime;
	
	boolean failedTest;
	
	public Segment(double upload, double download, boolean fail) {
		uploadTime = upload;
		downloadTime = download;
		failedTest = fail;
	}
	
	public Segment(double upload, double download) {
		uploadTime = upload;
		downloadTime = download;
	}

	public double getUploadTime() {
		return uploadTime;
	}

	public double getDownloadTime() {
		return downloadTime;
	}

	public boolean isFailedTest() {
		return failedTest;
	}
	
	
}
