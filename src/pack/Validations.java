package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Validations {
	String userName;
	String password;

	public Validations() {
	}
	
	public Validations(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	//To ensure that at least 3 names are entered
	public boolean validateFullName(String fullName) {
	     if(fullName == null){
	         return false;
	        }
	         else {
	             String[] name = fullName.trim().split(" ");
	             if(name.length < 3)
	              return false;
	         }
	     return true;
	}
	//To ensure that the age is positive
	public boolean validateAge(int age) {
		 if(age <=16 || age>=35)
			 return false;
		 else 
			 return true;
	}

	//To ensure that GPA is within the correct range
	public boolean validateGpa(float gpa) {
		if(gpa<0.0 || gpa>4.0)
			return false;
	else
		return true;
	}
	//validation for the departement
	public boolean validateDepartement(String departement) {
		if(departement.equalsIgnoreCase("Computer and Communication") || 
				departement.equalsIgnoreCase("Mechatronics and Robotics") || 
				departement.equalsIgnoreCase("Electromechanics") || 
				departement.equalsIgnoreCase("Biomedical") || 
				departement.equalsIgnoreCase("Architecture and Conctruction") || 
				departement.equalsIgnoreCase("Oil and Petrochemicals") || 
				departement.equalsIgnoreCase("Aerospace"))
			return true;
		else
			return false;
	}
	public boolean validateGender(String gender) {
		if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))
			return true;
		else 
			return false;
	}
	//For user login information
	public boolean validateUser(String userName,String password) {

	     try(Scanner read = new Scanner(new File("Users.txt"))){	
		 while(read.hasNextLine()) {
		 String info = read.nextLine().trim();
		 if(info.isEmpty())continue;
		 String[] parts = info.split(",");
		
		 String user = parts[0];
		 String pass =parts[1];
		
		 if(user.equals(userName) && pass.equals(password)) {
		
		 return true;
		 }
		 }
	     }
		catch(FileNotFoundException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
				}
	  
		 return false;
	          
	}
}