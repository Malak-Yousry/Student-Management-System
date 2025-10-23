package pack;

public class StudentRecord{
 private int studentID;
 private String fullName;
 private int age;
 private String gender;
 private String departement;
 private float gpa;


public StudentRecord(int studentID, String fullName, int age, String gender, String departement, float gpa) {
	this.studentID = studentID;
	this.fullName = fullName;
	this.age = age;
	this.gender = gender;
	this.departement = departement;
	this.gpa = gpa;
}
public String generateID() {
	 return String.format("%04d",(System.currentTimeMillis()%1000));
}
 public int getStudentID() {
	return studentID;
 }

 public void setStudentID(int studentID) {
	this.studentID = studentID;
 }

 public String getFullName() {
	return fullName;
 }

 public void setFullName(String fullName) {
     if(fullName == null){
         System.out.println("Please Enter Your FullName!!");
         return;
        }
         else {
             String[] name = fullName.trim().split(" ");
             if(name.length < 5){
              System.out.println("Please Enter Your FullName!!");
              return;
             }
         }
	this.fullName = fullName;
 }

 public int getAge() {
	return age;
 }

 public void setAge(int age) {
     if(age <= 0){
     System.out.println("Invalid Age!!");
     return;}
	this.age = age;
 }

 public String getGender() {
	return gender;
 }

 public void setGender(String gender) {
     if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")){
     System.out.println("Invalid gender!!");
     return;}
	this.gender = gender;
 }

 public String getDepartement() {
	return departement;
 }

 public void setDepartement(String departement) {
	this.departement = departement;
 }

 public float getGpa() {
	return gpa;
 }

 public void setGpa(float gpa) {
     if(gpa<0 || gpa>4){
    System.out.println("Invalid gpa!!");
    return;}
	this.gpa = gpa;
 }
 

}