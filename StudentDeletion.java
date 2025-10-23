
package studentss;

import java.io.*;
import java.util.*;

public class StudentDeletion {

    private String fileName;

    public StudentDeletion(String fileName) {
        this.fileName = fileName;
    }

    
    public boolean deleteStudentById(String studentId, boolean confirmDeletion) {
        List<String> students = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(studentId + ",")) {
                    found = true;
                    if (!confirmDeletion) {
                        students.add(line); 
                    }
                    continue; 
                }
                students.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!found) {
            return false; 
        }


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : students) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            return false;
        }

        return confirmDeletion;
    }
}
