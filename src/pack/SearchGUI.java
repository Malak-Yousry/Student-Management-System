package pack;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchGUI{

    public SearchGUI(){
        JFrame searchFrame = new JFrame();
        searchFrame.setSize(400,400);
        searchFrame.setTitle("Search");
        searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchFrame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        JTextField text = new JTextField(15);
        searchPanel.add(text);
        JButton submitButton = new JButton("Submit");
        searchPanel.add(submitButton);
        searchFrame.add(searchPanel);

        Validations valid = new Validations();
        Database database = new Database();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String userInput = text.getText();
                ArrayList<StudentRecord> record = new ArrayList<>();
                if(valid.validateFullName(userInput)){
                    record = database.searchStudent(userInput);
                    ViewGUI viewTable = new ViewGUI(record, false);
                }
                    
                else if (userInput.matches("\\d+")){//else if(isNumber(userInput))    //
                    record = database.searchStudent(Integer.parseInt(userInput));
                    ViewGUI viewTable = new ViewGUI(record, false);
                } 
                    
            }
        });
        searchFrame.setVisible(true);
    }
    


}