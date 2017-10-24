
public class Lecture {

	private String audioName;
	private String audioPath;
	private String courseName;
	private String date;
	private String fileName;
	private String instructorName;
	private String textPath;

	public Lecture(){

	}

	public void finalize() throws Throwable {

	}
	public String getAudioName(){
		return audioName;
	}

	public String getAudioPath(){
		return audioPath;
	}

	public String getCourseName(){
		return courseName;
	}

	public String getFileName(){
		return fileName;
	}

	public String getInstructorName(){
		return instructorName;
	}

	public String getTextPath(){
		return textPath;
	}

	public String getDate(){
		return date;
	}

	/**
	 * 
	 * @param audioName
	 */
	public void setAudioName(String name){
		audioName = name;
	}

	/**
	 * 
	 * @param audioPath
	 */
	public void setAudioPath(String path){
		audioPath = path;
	}

	/**
	 * 
	 * @param courseName
	 */
	public void setCourseName(String name){
		courseName = name;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String d){
		date = d;
	}

	/**
	 * 
	 * @param fileName
	 */
	public void setFileName(String name){
		fileName = name;
	}

	/**
	 * 
	 * @param instructorName
	 */
	public void setInstructorName(String insName){
		instructorName = insName;
	}

	/**
	 * 
	 * @param textPath
	 */
	public void setTextPath(String path){
		textPath = path;
	}
}
