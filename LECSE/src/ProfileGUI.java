import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileGUI {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Database db = new Database();
		db.loadUserInfo();
		db.loadLectures();
		//db.removeLecture("Lecture9");
	    
		/*	
db.addCourse_Instructor("CS332", "Sigrid Sandstrom");
		db.addCourse_Instructor("SE300", "Keith Garfield");
		db.addCourse_Instructor("COM219", "David Keck");
		db.addCourse_Instructor("COM221", "Esbjorn Sandstrom");
		
		Lecture lec3 = new Lecture();
		lec3.setCourseName("COM219");
		lec3.setInstructorName("David Keck");	
		lec3.setFileName("Lecture4");
		db.addLecture(lec3);

		Lecture lec2 = new Lecture();
		lec2.setCourseName("CEC470");
		lec2.setInstructorName("Nick Brixius");	
		lec2.setFileName("Lecture5");
		db.addLecture(lec2);

		Lecture lec4 = new Lecture();
		lec4.setCourseName("CS332");
		lec4.setInstructorName("Sigrid Sandstrom"); 
		lec2.setFileName("Lecture");
		db.addLecture(lec4);

		Lecture lec5 = new Lecture();
		lec5.setCourseName("COM221");
		lec5.setInstructorName("Esbjorn Sandstrom"); 
		lec5.setFileName("Lecture6");
		db.addLecture(lec5);





		 */

	}
}

