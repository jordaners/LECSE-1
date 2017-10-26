import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * @author sigri
 * @version 1.0
 * @created 10-okt-2017 18:06:10
 */

public class AudioConverter
{
	public AudioConverter()
	{

	}

	public void finalize() throws Throwable
	{

	}
	
	
	public String getAudioFile()
	{
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "WAV files", "wav");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	return chooser.getSelectedFile().getName();
	    }
		 
		return null;
	}

}//end AudioConverter