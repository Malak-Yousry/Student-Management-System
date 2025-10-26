import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class DeletestudentbyID {
    private static final String FILE_NAME = "students.txt"; // Replace with your actual file name

    public boolean deleteStudentById(String studentId) {
        List<String> records = new ArrayList<>();
        boolean found = false;

        // Read all lines and filter out the one to delete
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(studentId + ",")) {
                    found = true; 
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
            e.printStackTrace();
            return false;
        }

        return true; 
    }
}