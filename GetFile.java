import java.io.File;

import javax.swing.JFileChooser;


public class GetFile {
	
	/**
	 * This method chooses the file to be opened
	 * opens the file and returns the file
	 * @return nameFile
	 */
	/**
	 * Default Constructor
	 */
	public GetFile()
	{
		
	}
	public File getFile()
	{
		JFileChooser chooser= new JFileChooser();
		int status= chooser.showOpenDialog(null);
		if (status != JFileChooser.APPROVE_OPTION)
		{
			System.out.println("No File Chosen");
			System.exit(0);
		}
		File nameFile=chooser.getSelectedFile();
		return nameFile;
	}
}
