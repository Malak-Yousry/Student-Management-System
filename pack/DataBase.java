package pack;

import java.io.FileWriter;
import java.io.IOException;

public class DataBase extends StudentRecord {

	 public void addStudent(int studentID,String fullName,int age,String gender,String departement,float gpa){
	    StudentRecord student = new   StudentRecord (studentID,fullName,age,gender,departement,gpa);
		   setStudentID(studentID);
		   setFullName(fullName);
		   setAge(age);
		   setGender(gender);
		   setDepartement(departement); 
		   setGpa(gpa);
		   students.add(student);
		   saveToFile();

		 }
 	 public void saveToFile() {
		 
		try(FileWriter writer = new FileWriter(new File(filename))){
			for(StudentRecord s :students) {
			writer.write(student.get(i).lineRepresentation() + "\n");
			
		}}
		catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
 	 public String lineRepresentation() {
 		 return getStudentID() + "," + getFullName() + "," + getGender() + "," + getDepartement() + "," +  getGpa();
 	 }
}
