package pack;

public class StudentRecord{
 int studentID;
 String fullName;
 int age;
 String gender;
 String departement;
 float gpa;

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

}