package pack;

import java.io.BufferedReader;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Database extends Validations {
   private ArrayList<StudentRecord> records;
   private final String FILE_NAME="students.txt";

   //constructor 
   public Database(){
        records = readFromFile();
    }
   //search methods
    public ArrayList<StudentRecord> searchStudent(String name){
        ArrayList<StudentRecord> result = new ArrayList<>();
        records = this.readFromFile();
        for(int i = 0; i < records.size(); i++)
            if(records.get(i).getFullName().equalsIgnoreCase(name))
                result.add(records.get(i));
        return result;
    }
    //overload
    public ArrayList<StudentRecord> searchStudent(int id){
        ArrayList<StudentRecord> result = new ArrayList<>();
        records = this.readFromFile();
        for(int i = 0; i < records.size(); i++)
            if(records.get(i).getStudentID() == id)
                result.add(records.get(i));
        return result;
    }
    //update method
    public boolean updaterecords(StudentRecord student){
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getStudentID() == student.getStudentID()) {
                records.set(i, student);
                saveToFile(); // save updated list
                return true;
            }
        }
        return false;
    }
//read from file
    public ArrayList<StudentRecord> readFromFile() {
     records = new ArrayList<>();
     try(Scanner read = new Scanner(new File(FILE_NAME))){	
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
    //save to file
    public void saveToFile() {
		 
		try(FileWriter writer = new FileWriter(new File(FILE_NAME))){
			for(int i = 0; i < records.size(); i++) {
			writer.write(records.get(i).lineRepresentation() + "\n");
		    }
        }catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
    // view method
    public void viewAllrecords(DefaultTableModel model, ArrayList<StudentRecord> record){
     // clear any old data in table
        model.setRowCount(0);
        if(record.isEmpty()) {
        	return;
        }
        for(StudentRecord e : record){
            Object[]  row= {
            e.getStudentID(),
            e.getFullName(),
            e.getAge(),
            e.getGender(),
            e.getDepartement(),
            e.getGpa()};
           model.addRow(row);
            }
        }
    
     // sorting methods
    public void sortById(){
        records=readFromFile();
        for(int i = 0;i<records.size();i++){
            int index=i;
            for(int j=i+1;j<records.size();j++) {
                if(records.get(j).getStudentID()<records.get(index).getStudentID())
                index=j;
            }
            if(index!=i) {
                StudentRecord temp = records.get(i);
                records.set(i,records.get(index));
                records.set(index,temp);
            }
        }
        saveToFile();
    }
    public void sortByName(){
     records=readFromFile();
    for(int i = 0;i<records.size();i++){
       int index=i;
       for(int j=i+1;j<records.size();j++) {
        if(records.get(j).getFullName().compareToIgnoreCase(records.get(index).getFullName()) < 0)
        index=j;
          }
    if(index!=i) {
	StudentRecord temp = records.get(i);
	records.set(i,records.get(index));
	records.set(index,temp);
		}
     }
    saveToFile();
     }
    // delete methods
    public boolean deleteStudentById(String studentId, boolean confirmDeletion) {
        List<String> records = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(studentId + ",")) {
                    found = true;
                    if (!confirmDeletion) {
                        records.add(line); 
                    }
                    continue; 
                }
                records.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!found) {
            return false; 
        }


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String s : records) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            return false;
        }

        return confirmDeletion;
    }
// add records method
	 public void addStudent(String fullName,int age,String gender,String departement,float gpa){
	    records=readFromFile();
		  if(!validateFullName(fullName)) {
			  JOptionPane.showMessageDialog(null,"Please Enter Your FullName!!");
			return;  
		  }
		 
		  if(!validateAge(age)) {
			  JOptionPane.showMessageDialog(null,"Invalid Age!!");
			  return;
		  }
		  
		  if(!validateGpa(gpa)) {
			  JOptionPane.showMessageDialog(null,"Invalid gpa!!");
			  return;
		  }
	
		  if((searchStudent(fullName)) != null) {
			  JOptionPane.showMessageDialog(null,"Student already exists!");
			  return;
			  }
		 int studentID= generateID();
			 StudentRecord student = new   StudentRecord (studentID,fullName,age,gender,departement,gpa);
		 
	       records.add(student);
		   saveToFile();
		   
		   }
		  

	 public int generateID() {
		 int id ;
		 do{ 
			 id = (int)((Math.random()*9000)+1000);
	 }while(searchStudent(id) != null) ;
			
		 return id;
	}
	 

    }
