package pack;

public class Validations {

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
	public boolean validateAge(int age) {
		 if(age <= 0)
			 return false;
		 else 
			 return true;
		 
	}
	public boolean validateGender(String gender) {
		 if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"))
			 return false;
		 else 
			 return true;
		 
	}
	public boolean validateGpa(float gpa) {
		if(gpa<0.0 || gpa>4.0)
			return false;
	else
		return true;
	}
}
