package pack;
import java.io.*;
import java.util.ArrayList;
public class Database{
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