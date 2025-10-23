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

    
    
}
