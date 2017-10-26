

/**
 * @author sigri
 * @version 1.0
 * @created 10-okt-2017 18:06:10
 */



import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MediaGUI extends JPanel
{
	public JTextField textField;
	public JTextArea textArea;

	public MediaGUI()
	{

	}

	public void finalize() throws Throwable
	{

	}
	
	public void helpPage()
	{

	}

	/**
	 * 
	 * @param index
	 */
	public void highlightWord(int index)
	{

	}

	/**
	 * 
	 * @param fileName
	 */
	public void playAudio(String fileName)
	{

	}

	public void profilePage()
	{

	}

	/**
	 * 
	 * @param keyWord
	 */
	public int searchWord(String keyWord)
	{
		return 0;
	}

	/**
	 * Reads the text from a text file and returns the words as a string
	 * @param fileName The name of the file to be read from
	 * @return Returns the words from the file as a string
	 * @throws IOException
	 */
	public String readText(String fileName) throws IOException
	{
        // This will reference one line at a time
        String line = null;
        String word = "";
        String newLine = "\n";
        //System.out.println("test");
       try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                word += line + newLine;
            }   
         //System.out.println(word);
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
               "Unable to open file '" + 
                fileName + "'");                
       }
      catch(IOException ex) {
           System.out.println(
               "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            //ex.printStackTrace();
        }
        return (word);
    }

    /**
     * Displays text onto text area.  User can edit text in the text box and then save the edited text back to the file
     */
public void showText(String fileName) throws IOException
    {
		// Frame
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Text");
    	frame.setLocation(500,400);
    	frame.setSize(500,500);

    	// Panel
    	JPanel panel = new JPanel(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = 0;
    	c.gridy = 0;
    	c.insets = new Insets(2,10,10,10);

    	// Label
    	JLabel label = new JLabel("Text Translation");
    	panel.add(label,c);

    	// Text Area
    	JTextArea  textarea = new JTextArea(readText(fileName));
    	textarea.setSize(150, 150);
    	panel.add(textarea);
    	textarea.setEditable(true);
    	textarea.setLineWrap(true);
    	      
    	// Scroll Bar for Text Area
    	JScrollPane scroll = new JScrollPane (textarea);
    	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	frame.add(scroll);

    	// Save Button
    	JButton button = new JButton("Save");
    	button.addActionListener(e->
    	{
    		String words = textarea.getText();
    		BufferedWriter bw = null;
    		FileWriter fw = null;

    		try
    		{
    			String content = words;
    			fw = new FileWriter("Test.txt");
    			bw = new BufferedWriter(fw);
    			bw.write(content);
    			System.out.println("Done");
    		}
    	
    		catch (IOException er)
    		{
    			er.printStackTrace();
    		}
    	
    		try
    		{
    			if (bw != null)
    			{
    				bw.close();
    			}
    	    
    			if (fw != null)
    			{
    				fw.close();
    			}
    		}
    	
    		catch (IOException ex)
    		{
    			ex.printStackTrace();
    		}

    	    	
    	});
    	c.gridx = 1;
    	c.gridy = 1;
    	panel.add(button,c);

    	// Display
    	frame.getContentPane().add(panel, BorderLayout.NORTH);
    	frame.setVisible(true);
    }

	public void stopAudio()
	{

	}

	
	/**
	 * Uploads an audio file to the system.
	 * @param fileName Name of the file to be uploaded.
	 * @return The clip is the audio from the file
	 */
	public Clip uploadAudio(String fileName)
	{
		Clip in = null;

	    try
	    {
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(fileName));
	        in = AudioSystem.getClip();
	        in.open( audioIn );
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return in;
	}
}