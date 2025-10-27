import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class DeleteGUI extends javax.swing.JFrame {
    private JTable table;
    private DefaultTableModel model;
    private final String FILE_PATH = "students.txt";
    
    public DeleteGUI() {
        initComponents(); // Fixed typo and call the auto-generated method
        initializeCustomComponents(); // Add this call
        loadStudentsFromFile(); 
    }
    
    private void initializeCustomComponents() {
        // Initialize table model with column names
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
        
        // Use the existing jButton1 instead of creating a new deleteButton
        jButton1.setText("Delete");
        // Remove the old action listener and add the correct one
        for (java.awt.event.ActionListener al : jButton1.getActionListeners()) {
            jButton1.removeActionListener(al);
        }
        jButton1.addActionListener(evt -> deleteSelectedStudent());

        // Replace the layout with your custom layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null); // Center the window
    }
    
    private void loadStudentsFromFile() {
        if (model == null) {
            // Model not initialized yet
            return;
        }
        
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
        if (table == null) {
            JOptionPane.showMessageDialog(this, "Table not initialized.");
            return;
        }
        
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
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File("temp_students.txt");
            
            boolean found;
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                
                String currentLine;
                found = false;
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
            }
            
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

    // Remove the problematic intitcomponents() method that you added
    // Keep only the auto-generated initComponents() method below

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}