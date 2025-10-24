package pack;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Database {
    ArrayList<StudentRecord> students;

    public Database(){
        students = new ArrayList<>();
    }
    public StudentRecord searchStudent(String name){
        for(int i = 0; i < students.size(); i++)
            if(students.get(i).getFullName().equals(name))
                return students.get(i);
        return null;
    }
    //overload
    public StudentRecord searchStudent(int id){
        for(int i = 0; i < students.size(); i++)
            if(students.get(i).getStudentID() == id)
                return students.get(i);
        return null;
    }
    public boolean updateStudents(int id){
        StudentRecord student = searchStudent(id);
        if(student != null){//student found
            //menu
            Scanner scanner = new Scanner(System.in);
            System.out.println("Menu");
            System.out.println("1.Update department.");
            System.out.println("2.Update GPA.");
            System.out.println("3.Update age.");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the department: ");
                    String dep = scanner.nextLine();
                    student.setDepartement(dep);
                    break;
                case 2:
                    System.out.println("Enter the GPA: ");
                    float gpa = scanner.nextFloat();
                    student.setGpa(gpa);
                    break;
                case 3:
                    System.out.println("Enter the age: ");
                    int age = scanner.nextInt();
                    student.setAge(age);
                    break;
            
                default:
                    System.out.println("invalid choice.");
                    break;
            }
            return true;
        }
        return false;     
    }

    public ArrayList<StudentRecord> readFromFile() {
     ArrayList<StudentRecord> records=new ArrayList<>();
     try(Scanner read = new Scanner(new File(filename))){	
	 while(read.hasNextLine()) {
	 String info = read.nextLine().trim();
	 if(info.isEmpty())continue;
	 String[] parts = info.split(",");
	 int j=0;
     int id =Integer.parseInt(parts[j++]);
	 String fullName = parts[j++];
     int age=Integer.parseInt(parts[j++]);
	 String gender= parts[j++];
	 String department =parts[j++];
	float gpa=Float.parseFloat(parts[j++]);	
	
	StudentRecord record=new StudentRecord(id,fullName,age,gender,department,gpa);
	 records.add(record);
		}
	}
	catch(FileNotFoundException e) {
		System.out.println("An error occurred");
		e.printStackTrace();
			}
            return records;
     }
     
     public void viewAllStudents(){
    ArrayList<StudentRecord> records=readFromFile();
    for(StudentRecord e : records){
        System.out.println(e);
    }

    }
    public ArrayList<StudentRecord> sortById(){
    ArrayList<StudentRecord> records=readFromFile();
    for(int i = 0;i<records.size();i++){
       int index=i;
       for(int j=i+1;j<records.size();j++) {
        if(records.get(j).getID()<records.get(index).getID())
        index=j;
    }
    if(index!=i) {
	StudentRecord temp = records.get(i);
	records.set(i,records.get(index));
	records.set(index,temp);
			}}
    return records;
    }
    public ArrayList<StudentRecord> sortByName(){
    ArrayList<StudentRecord> records=readFromFile();
    for(int i = 0;i<records.size();i++){
       int index=i;
       for(int j=i+1;j<records.size();j++) {
        if(records.get(j).getName().compareToIgnoreCase(records.get(index).getName()<0))
        index=j;
          }
    if(index!=i) {
	StudentRecord temp = records.get(i);
	records.set(i,records.get(index));
	records.set(index,temp);
		}
     }
    return records;
     }
    }
