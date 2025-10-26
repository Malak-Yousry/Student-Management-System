package pack;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UpdateGUI extends JPanel{
    private JFrame searchFrame = new JFrame();
    private JTextField text = new JTextField(15);
    private Validations valid = new Validations();
    private Database database = new Database();

    private JFrame resultFrame = new JFrame("Search Results");
    private int selectedRow;
    private ViewGUI viewTable;
    private StudentRecord student;
    JButton updateButton;

    public UpdateGUI(){
        
        searchFrame.setSize(400,400);
        searchFrame.setTitle("Update student");
        searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchFrame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(text);

        JButton submitButton = new JButton("Submit");
        searchPanel.add(submitButton);
        add(searchPanel, BorderLayout.NORTH);
        searchFrame.add(searchPanel);
        searchFrame.setVisible(true);

        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                search();
        }});

        updateButton = new JButton("Update");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                update();
        }});
        
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setSize(800, 400);
        resultFrame.setLocationRelativeTo(null);

        searchFrame.setVisible(true);
    }

    private void search(){
        String userInput = text.getText();
        if(userInput.length() == 0)
            JOptionPane.showMessageDialog(searchFrame, "Empty text!");

        else if(valid.validateFullName(userInput)){
            searchFrame.dispose();

            resultFrame.getContentPane().removeAll();
            resultFrame.setLayout(new BorderLayout());
            viewTable = new ViewGUI(database.searchStudent(userInput), true);
            resultFrame.add(viewTable,BorderLayout.CENTER);
            updateButton.setVisible(true);
            resultFrame.add(updateButton,BorderLayout.SOUTH);
            resultFrame.revalidate();
            resultFrame.repaint();
            resultFrame.setVisible(true);
        }
        
        else if (userInput.matches("\\d+")){
            searchFrame.dispose();
            resultFrame.getContentPane().removeAll();
            resultFrame.setLayout(new BorderLayout());
            viewTable = new ViewGUI(database.searchStudent(Integer.parseInt(userInput)), true);
            resultFrame.add(viewTable,BorderLayout.CENTER);
            updateButton.setVisible(true);
            resultFrame.add(updateButton,BorderLayout.SOUTH);
            resultFrame.revalidate();
            resultFrame.repaint();
            resultFrame.setVisible(true);
        }
        else 
            JOptionPane.showMessageDialog(searchFrame, "No student found!");
           
    }

    private void update(){
        selectedRow = viewTable.getSelectedRow();
        updateButton.setVisible(true);

        if(viewTable == null || selectedRow == -1)
            JOptionPane.showMessageDialog(resultFrame, "Please select a student first!");
        else{
            DefaultTableModel model = viewTable.getModel();
            int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            String name = model.getValueAt(selectedRow, 1).toString();
            int age = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
            String gender = model.getValueAt(selectedRow, 3).toString();
            String department = model.getValueAt(selectedRow, 4).toString();
            float gpa = Float.parseFloat(model.getValueAt(selectedRow, 5).toString());

            if(!valid.validateAge(age) || !valid.validateFullName(name) || !valid.validateGpa(gpa)){
                JOptionPane.showMessageDialog(resultFrame, "Invalid update! Check the values.");
                return;
            }
            student = new StudentRecord(id,name,age,gender,department,gpa);
            boolean isUpdated = database.updaterecords(student);
            if(isUpdated){
                JOptionPane.showMessageDialog(resultFrame, "Student updated successfully!");
                return;
            }
            else 
                JOptionPane.showMessageDialog(resultFrame, "Update failed. Please try again.");
 
        }

    }
}


