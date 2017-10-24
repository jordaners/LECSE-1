import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Sigrid Sandström
 */
public class Database {
	//Attributes for user name and major
	private String userName;
	private String major;

	//Create a file
	File lectureFile = new File("database.txt");
	File tempFile = new File("tempFile.text");

	//Files for user info storing
	File profileInfoFile = new File("userInfo.txt");
	File tempFile2 = new File("tempFile2.text");

	//Create an arraylist to hold lecture objects
	private ArrayList<Lecture> lectureArray = new ArrayList<Lecture>();

	//Create a 2D array to hold the course names and instructor names
	private String[][] course_instructor_array = new String[100][2];


	//Add a lecture to the ArrayList and to the file
	/**
	 * @param lecture is a Lecture object
	 * @throws IOException
	 * @ This method adds a Lecture object to an ArrayList and saves the state of the object to a text file
	 */
	public void addLecture(Lecture lecture) throws IOException{
		lectureArray.add(lecture);

		//Create the string to save to the text file
		String str = lecture.getInstructorName() + "," + lecture.getCourseName() + "," + lecture.getFileName() +
				"," + lecture.getTextPath() + ',' + lecture.getAudioName() + "," + lecture.getAudioPath() + "," + lecture.getDate();

		//Create a file writer
		FileWriter fw = new FileWriter(lectureFile.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str);
		bw.append(System.lineSeparator());
		bw.close();
	}


	//Remove a lecture from the array list
	/**
	 * @param lectureID is the filename of the lecture that will be removed
	 * @throws IOException
	 * @ This method removes a Lecture object from the ArrayList and from the text file
	 */
	public void removeLecture(String lectureID) throws IOException{
		int lineToRemove = 0;
		
		for(int i = 0; i < lectureArray.size(); i++) {
			if(lectureID.equals(lectureArray.get(i).getFileName())) {
				//Get the course name  and instructor name of the lecture to remove
				String courseName = lectureArray.get(i).getCourseName();
				String instructorName = lectureArray.get(i).getInstructorName();
				//Remove the lecture from the ArrayList
				lectureArray.remove(i);
				
				//If this is the last lecture associated with a course, delete the course and instructor
				System.out.println(this.getCourseArrayList(courseName).size() == 0);
				if(this.getCourseArrayList(courseName).size() == 0) {
					this.removeCourseAndInstructor(courseName, instructorName);
				}
				lineToRemove = i;
				//End loop
				i = lectureArray.size();
			}

		}
		
		//Remove lecture from text file
		BufferedReader reader = new BufferedReader(new FileReader(lectureFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
		int count = 0;
		String line;
		while((line = reader.readLine()) != null) {
			if(count != lineToRemove) {
				writer.write(line);
				writer.newLine();		
			}
			count++;
		}
		
		reader.close();
		writer.close();
		lectureFile.delete();
		tempFile.renameTo(lectureFile);
		

	}



	//Populate the arraylist with saved Lectures
	/**
	 * @throws IOException
	 * @ This method populates the ArrayList with Lecture objects saved in the text file
	 */
	public void loadLectures() throws IOException {		
		// if file doesn't exists, then create it
		if (!lectureFile.exists()) {
			lectureFile.createNewFile();
		}

		// Read data from file
		try (BufferedReader br = new BufferedReader(new FileReader(lectureFile))) {

			// Read file line by line
			String line;
			while ((line = br.readLine()) != null) {
				//Use a comma to distinguish where to split the string
				String delims = "[,]";
				//Split the string where there is a comma
				String[] data = line.split(delims);

				// Create new Lecture object
				Lecture lecture = new Lecture();
				lecture.setInstructorName(data[0]);
				lecture.setCourseName(data[1]);
				lecture.setFileName(data[2]);
				lecture.setTextPath(data[3]);
				lecture.setAudioName(data[4]);
				lecture.setAudioPath(data[5]);
				lecture.setDate(data[6]);

				// Add object to list
				lectureArray.add(lecture);

			}

		}
	}

	//Get an ArrayList of lectures with the same instructor
	/**
	 * @param instructorName is the name of an instructor
	 * @return This method returns an ArrayList of Lecture objects
	 * @ This method locates all Lecture objects associated with the instructor passed as a parameter and saves them in an ArrayList
	 */
	public ArrayList<Lecture> getInstructorArrayList(String instructorName){
		ArrayList<Lecture> instructorArray = new ArrayList<Lecture>();
		for(int i = 0; i < lectureArray.size(); i++) {
			if(instructorName.equals(lectureArray.get(i).getInstructorName())) {
				instructorArray.add(lectureArray.get(i));
			}
		}
		return instructorArray;	
	}

	//Get an ArrayList of lectures with the same class Name
	/**
	 * @param courseName is the name of a course
	 * @return This method returns an ArrayList of Lecture objects.
	 * @ This method locates all Lecture objects associated with the course passed as a parameter and saves them in an ArrayList
	 */
	public ArrayList<Lecture> getCourseArrayList(String courseName){
		ArrayList<Lecture> courseArray = new ArrayList<Lecture>();
		for(int i = 0; i < lectureArray.size(); i++) {
			if(courseName.equals(lectureArray.get(i).getCourseName())) {
				courseArray.add(lectureArray.get(i));
			}
		}
		return courseArray;	
	}

	//Add a course and instructor to the array and save them to a file
	/**
	 * @param course is the name of a course 
	 * @param instructor is the name of an instructor
	 * @return This method returns true if the adding was successful and false if the adding failed.
	 * @throws IOException
	 * @ This method adds the instructor name and course name to a 2D array and saves them to a text file
	 */
	public boolean addCourse_Instructor(String course, String instructor) throws IOException {
		boolean space = false;
		for(int i = 0; i<course_instructor_array.length; i++) {
			//find the first available row of array
			if(course_instructor_array[i][0] == null) {
				//Add the course to the first column of the matrix
				course_instructor_array[i][0] = course;
				//Add the instructor to the second column of the matrix
				course_instructor_array[i][1] = instructor;
				//End the loop
				i = course_instructor_array.length;

				//Create a string to store in the file
				String str = course + "," + instructor;
				//Add the string to the file
				FileWriter fw = new FileWriter(profileInfoFile.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(str);
				bw.append(System.lineSeparator());
				bw.close();

				//Signal that the array isn't full
				space = true;
			} 
		}
		//Return if the adding was successful or not
		return space;
	}


	//Set the user name and major and save them to the file to the file
	/**
	 * @param name is the name of the user
	 * @param majorName is the name of the user's major
	 * @throws IOException
	 * @ This method sets the name and major of the user and saves the strings to a text file
	 */
	public void setName_Major(String name, String majorName) throws IOException {
		//Set userName and major
		userName = name;
		major = majorName;

		//Create a string to store in the file
		String str = userName + "," + major;
		//Add the string to first line of the a temporary file
		BufferedReader reader = new BufferedReader(new FileReader(profileInfoFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile2));
		writer.write(str);
		writer.newLine();

		//Read first line with old name and major and don't save it
		reader.readLine();
		//Read original file line by line
		String line;
		while ((line = reader.readLine()) != null) {
			//add the courses and instructors to the temporary file
			writer.write(line);
			writer.newLine();
		}

		reader.close();
		writer.close();
		//delete the original file
		profileInfoFile.delete();
		//rename the temporary file to the name of the original file
		tempFile2.renameTo(profileInfoFile);
	}

	//Load the variables
	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @ This method loads the saved data into the PersonInfo object when the program is started
	 */
	public void loadUserInfo() throws FileNotFoundException, IOException{
		// if file doesn't exists, then create it
		if (!profileInfoFile.exists()) {
			profileInfoFile.createNewFile();
		}

		// Read data from file
		try (BufferedReader br = new BufferedReader(new FileReader(profileInfoFile))) {
			//count variable
			int count = 0;

			// Read file line by line
			String line;
			while ((line = br.readLine()) != null) {
				//Use a comma to distinguish where to split the string
				String delims = "[,]";
				//Split the string where there is a comma
				String[] data = line.split(delims);
				//the user name and major is stored in the first line of the file
				if(count == 0) {
					userName = data[0];
					major = data[1];
				}else {
					//Add the course names to the first column
					course_instructor_array[count][0] = data[0];
					//Add the instructor names to second column
					course_instructor_array[count][1] = data[1];
				}
				//increment count
				count++;
			}
		}
	}

	/**
	 * @param cn is the name of a course
	 * @param in is the name an instructor
	 * @throws IOException
	 * @ This method deletes a course and instructor from the array, and from the text file
	 */
	public void removeCourseAndInstructor(String cn, String in) throws IOException {
		//String to delete
		String str = cn + "," + in;

		BufferedReader reader = new BufferedReader(new FileReader(profileInfoFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile2));
		//Read the first line with user name and major and write it to the temp file
		String nameLine = reader.readLine();
		writer.write(nameLine);
		writer.newLine();

		//Delete line from file
		String line;
		while ((line = reader.readLine()) != null) {
			if(!line.equals(str)) {
				//Write to file
				writer.write(line);
				writer.newLine();
			}
		}

		reader.close();
		writer.close();
		//Delete the original file and rename the temp file
		profileInfoFile.delete();
		tempFile2.renameTo(profileInfoFile);

		//Delete course and instructor from the array
		for(int i = 0; i < course_instructor_array.length; i++) {
			if(cn.equals(course_instructor_array[i][0])) {
				//Delete course and instructor from the array
				course_instructor_array[i][0] = null;
				course_instructor_array[i][1] = null;
			}
		}
	}


	/**
	 * @return This method returns the user's major
	 */
	public String getMajor(){
		return major;
	}

	/**
	 * @return This method returns the user's name
	 */
	public String getName(){
		return userName;
	}

	public String[][] getCourseInstructorArray(){
		return course_instructor_array;
	}


}


