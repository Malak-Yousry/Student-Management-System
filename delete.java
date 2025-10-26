
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;



 public class Delete extends javax.swing.JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton jButton1;
    private final String FILE_PATH = "students.txt";


    public Delete() {
        initComponents();
      loadStudentsFromFile();    
}
    private void initComponents() {
        jButton1 = new JButton("Delete");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        model = new DefaultTableModel(new Object[]{"Student Name"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(scrollPane);
        add(jButton1);
        pack();
        setLocationRelativeTo(null);
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
                .addComponent(jButton1))
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
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow != -1) {
        // Get student ID and Name from the selected row
        String studentId = jTable1.getValueAt(selectedRow, 0).toString(); // Column 0 = ID
        String studentIName = jTable1.getValueAt(selectedRow, 1).toString(); // Column 1 = Name
        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + studentName + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Remove from table
            DefaultTableModel Table = (DefaultTableModel) jTable1.getModel();
            Table.removeRow(selectedRow);
            // Remove from file
            boolean deleted = DeleteStudentById(studentId);

            if (deleted) {
                JOptionPane.showMessageDialog(this, studentName + " has been removed.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove " + studentName + " from file.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a student to delete.");
    }
}



   private void loadStudentsFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(","); 
            model.addRow(data);
        }
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
}

 private void updateStudentFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                writer.write(model.getValueAt(i, j).toString());
                if (j < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing file: " + e.getMessage());
    }
}

        private boolean DeleteStudentById(String studentId) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }//GEN-LAST:event_jButton1ActionPerformed

   
/* public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
                new Delete().setVisible(true);
    });
}*/


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
