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
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
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
    boolean validrow;
    DefaultTableModel model;

    public UpdateGUI(){

        searchFrame.setTitle("Update student");
        searchFrame.setSize(400,400);
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
            JOptionPane.showMessageDialog(searchFrame, "Empty text!", "Error", JOptionPane.ERROR_MESSAGE);

        else if(valid.validateFullName(userInput)){
            searchFrame.dispose();
            resultFrame.getContentPane().removeAll();
            resultFrame.setLayout(new BorderLayout());
            viewTable = new ViewGUI(database.searchStudent(userInput), true);
            model = viewTable.getModel();
            model.addTableModelListener(e -> {
                validrow = validRow(selectedRow, model);
                updateButton.setEnabled(validrow);
            });
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
            model = viewTable.getModel();
            model.addTableModelListener(e -> {
                selectedRow = viewTable.getSelectedRow();
                if(selectedRow != -1){
                    validrow = validRow(selectedRow, model);
                    updateButton.setEnabled(validrow);
                }

            });
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

        if(selectedRow == -1)
            JOptionPane.showMessageDialog(resultFrame, "Please select a student first!", "Error", JOptionPane.ERROR_MESSAGE);
        else if (viewTable == null)
            JOptionPane.showMessageDialog(resultFrame, "No students found!", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            if(!validRow(selectedRow, model)){
                JOptionPane.showMessageDialog(resultFrame, "Invalid data.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            String name = model.getValueAt(selectedRow, 1).toString();
            int age = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
            String gender = model.getValueAt(selectedRow, 3).toString();
            String department = model.getValueAt(selectedRow, 4).toString();
            float gpa = Float.parseFloat(model.getValueAt(selectedRow, 5).toString());

            if(validrow){
                updateButton.setEnabled(true);
                student = new StudentRecord(id,name,age,gender,department,gpa);
                boolean isUpdated = database.updaterecords(student);
                if(isUpdated){
                    JOptionPane.showMessageDialog(resultFrame, "Student updated successfully!");
                    resultFrame.dispose();
                }
                else 
                    JOptionPane.showMessageDialog(resultFrame, "Update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);            
            }
        }
    }
    
    boolean validRow(int selectedRow, DefaultTableModel model){
        boolean isValid = true;
        Object name = model.getValueAt(selectedRow, 1);
        String str = name.toString().trim();
        if (str.isEmpty()) {
            JOptionPane.showMessageDialog(resultFrame, "Name cell Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }
        Object gender = model.getValueAt(selectedRow, 3);
        str = gender.toString().trim();
        if(str.isEmpty()){
            JOptionPane.showMessageDialog(resultFrame, "Gender cell Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }
        Object department = model.getValueAt(selectedRow, 4);
        str = department.toString().trim();
        if(str.isEmpty()){
            JOptionPane.showMessageDialog(resultFrame, "Department cell Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }
        Object ageObject = model.getValueAt(selectedRow, 2);
        if(ageObject == null){
            JOptionPane.showMessageDialog(resultFrame, "Age cell Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }
        else{
            str = ageObject.toString().trim();
            try{
                int age = Integer.parseInt(str);
                if(!valid.validateAge(age))
                    isValid = false;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(resultFrame, "Not valid", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }
        }
        Object gpaObject = model.getValueAt(selectedRow, 5);
        if(gpaObject == null){
            JOptionPane.showMessageDialog(resultFrame, "GPA cell Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }
        else{
            str = gpaObject.toString().trim();
            try{
                float gpa = Float.parseFloat(str);
                if(!valid.validateGpa(gpa))
                    isValid = false;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(resultFrame, "Not valid", "Error", JOptionPane.ERROR_MESSAGE);
                isValid = false;
            }
        }
        return isValid;
    }
}


