import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class DeleteGUIS extends javax.swing.JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton;
    private final String FILE_PATH = "students.txt";

  public DeleteGUIS() {
        initialise();
        loadStudentsFromFile(); 
        /*  debugInfo(); // Add this line for testing*/

    }
private void initialise() {
        //  Initialize table model with column names
        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
    table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
      deleteButton = new JButton("Delete");
        deleteButton.addActionListener(evt -> deleteSelectedStudent());

        // Layout
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Delete Student Records");
        
        //  Proper layout including table
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null); // Center the window
    }
 private void loadStudentsFromFile() {
        //  model is now properly initialized
        model.setRowCount(0); // Clear existing data
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Students file not found.");
            return;
        }
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length == 6) {
                    model.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage());
        }
    }
 

private void deleteSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }
    String studentId = table.getValueAt(selectedRow, 0).toString();
        String studentName = table.getValueAt(selectedRow, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + studentName + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean fileDeleted = deleteStudentById(studentId);
            if (fileDeleted) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, studentName + " has been deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to delete student from file.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

 private boolean deleteStudentById(String studentId) {
        //  Basic file deletion implementation
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File("temp_students.txt");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String currentLine;
            boolean found = false;
            
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().isEmpty()) {
                    String[] data = currentLine.split(",");
                    if (data.length > 0 && !data[0].equals(studentId)) {
                        writer.write(currentLine + System.lineSeparator());
                    } else {
                        found = true; // Student found and skipped (deleted)
                    }
                }
            }
            
            writer.close();
            reader.close();
            
            if (found) {
                // Replace original file with temp file
                if (!inputFile.delete()) {
                    return false;
                }
                return tempFile.renameTo(inputFile);
            } else {
                tempFile.delete(); // Student not found, delete temp file
                return false;
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage());
            return false;
        }
    }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteSelectedStudent();
    }//GEN-LAST:event_jButton1ActionPerformed

       // Uncommented and corrected main method
   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DeleteGUIS().setVisible(true);
        });
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        System.out.println("=== Starting DeleteGUIS Test ===");
        
        // Test 1: Check if students.txt exists
        File testFile = new File("students.txt");
        if (!testFile.exists()) {
            System.out.println("❌ students.txt not found - creating sample file...");
            createSampleDataFile();
        } else {
            System.out.println("✅ students.txt found");
        }
        
        // Test 2: Launch the GUI
        System.out.println("🚀 Launching Delete Student GUI...");
        DeleteGUIS deleteGUI = new DeleteGUIS();
        deleteGUI.setVisible(true);
        
        // Test 3: Print debug info after GUI loads
        System.out.println("📊 GUI launched successfully");
        System.out.println("📍 Window location: " + deleteGUI.getLocation());
        System.out.println("📏 Window size: " + deleteGUI.getSize());
    });
}

// Helper method to create sample data for testing
private static void createSampleDataFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
        String[] sampleStudents = {
            "1001,John Doe,20,Male,Computer Science,3.8",
            "1002,Jane Smith,21,Female,Mathematics,3.9", 
            "1003,Bob Johnson,22,Male,Physics,3.5",
            "1004,Alice Brown,19,Female,Chemistry,3.7",
            "1005,Charlie Wilson,20,Male,Biology,3.6"
        };
        
        for (String student : sampleStudents) {
            writer.write(student);
            writer.newLine();
        }
        System.out.println("✅ Sample students.txt created with 5 students");
        System.out.println("📝 Sample data:");
        for (String student : sampleStudents) {
            System.out.println("   " + student);
        }
    } catch (IOException e) {
        System.out.println("❌ Error creating sample file: " + e.getMessage());
    }
}
// Add this method to your DeleteGUIS class for debugging
private void debugInfo() {
    System.out.println("=== DEBUG INFORMATION ===");
    System.out.println("Table rows: " + (model != null ? model.getRowCount() : "Model is null"));
    System.out.println("Table object: " + (table != null ? "Created" : "Null"));
    System.out.println("File exists: " + new File(FILE_PATH).exists());
    
    if (model != null && model.getRowCount() > 0) {
        System.out.println("First student: " + model.getValueAt(0, 0) + " - " + model.getValueAt(0, 1));
        System.out.println("Last student: " + model.getValueAt(model.getRowCount()-1, 0) + " - " + model.getValueAt(model.getRowCount()-1, 1));
    }
    
    System.out.println("========================");
}
  */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}