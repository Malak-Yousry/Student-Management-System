package pack;

public class StudentRecord{
 private int studentID;
 private String fullName;
 private int age;
 private String gender;
 private String departement;
 private float gpa;

 public void addStudent(int studentID,String fullName,int age,String gender,String departement,float gpa){
     
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
     if(age <= 0){
     System.out.println("Invalid Age!!");
     return;}
     
     if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")){
     System.out.println("Invalid gender!!");
     return;}

     if(gpa<0 || gpa>4){
    System.out.println("Invalid gpa!!");
    return;}

     
     this.studentID = studentID;
     this.fullName = fullName;
     this.age = age;
     this.gender = gender;
     this.departement = departement;
     this.gpa = gpa;

     System.out.println("Student Added Successfully");
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
	this.fullName = fullName;
 }

 public int getAge() {
	return age;
 }

 public void setAge(int age) {
	this.age = age;
 }

 public String getGender() {
	return gender;
 }

 public void setGender(String gender) {
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
	this.gpa = gpa;
 }
 

}