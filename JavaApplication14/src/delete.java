
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class delete extends javax.swing.JFrame {

    public delete() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fullname", "Age", "Gender", "department", "GPA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select what you want to delete!");
               return;
        }
        
        Object id = model.getValueAt(selectedRow, 0); 
        
        String name = model.getValueAt(selectedRow, 1).toString(); 
        
         int confirm = JOptionPane.showConfirmDialog(
                this,
            "Are you sure you want to delete " + name + " (ID: " + id + ")?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
         );
         
          if (confirm == JOptionPane.YES_OPTION) {
              model.removeRow(selectedRow);
       DeletestudentbyID deleter = new DeletestudentbyID();

          boolean success = deleter.deleteStudentById((String) id);

        if (success) {
            JOptionPane.showMessageDialog(this, "Record deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete record from file.");
        }
    }
}
    }//GEN-LAST:event_jButton1ActionPerformed

   
   /* public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new delete().setVisible(true);
            }
        });
    }
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
