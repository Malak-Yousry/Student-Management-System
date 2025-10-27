
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;



 public class delete extends javax.swing.JFrame {
       private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton;
    private final String FILE_PATH = "students.txt";




    public delete() {
        initComponents();
      loadStudentsFromFile();    
}

     private void loadStudentsFromFile() {
        model.setRowCount(0); // Clear existing data first
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Students file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines
                String[] data = line.split(",");
                if (data.length == 6) { // Ensure we have all 6 fields
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
            // Use the DeletestudentbyID class to delete from file
            boolean fileDeleted = deleteStudentById(studentId);
        if (fileDeleted) {
                model.removeRow(selectedRow); // Remove from table only if file deletion was successful
                JOptionPane.showMessageDialog(this, studentName + " has been deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to delete student from file. Student may not exist.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private boolean deleteStudentById(String studentId) {
        DeletestudentbyID deleter = new DeletestudentbyID();
        return deleter.deleteStudentById(studentId);
    }


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
            .addGroup(layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    deleteSelectedStudent();
}

      
       /*private void loadStudentsFromFile() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    }//GEN-LAST:event_jButton1ActionPerformed
*/
  
 /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new delete().setVisible(true));
    }
*/


  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
