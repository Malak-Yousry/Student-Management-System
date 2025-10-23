package pack;

public class DataBase extends StudentRecord {

	 public void addStudent(int studentID,String fullName,int age,String gender,String departement,float gpa){
	     
		   setStudentID(studentID);
		   setFullName(fullName);
		   setAge(age);
		   setGender(gender);
		   setDepartement(departement); 
		   setGpa(gpa);

		 }
}
