package pack;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class SearchGUI extends JPanel{

    public SearchGUI(){
        JFrame searchFrame = new JFrame();
        searchFrame.setSize(400,400);
        searchFrame.setTitle("Search");
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                if(valid.validateFullName(userInput))
                    record = database.searchStudent(userInput);
                    
                else if (userInput.matches("\\d+"))       //else if(isNumber(userInput))    //
                    record = database.searchStudent(Integer.parseInt(userInput));

                else if(userInput.length() == 0){
                    JOptionPane.showMessageDialog(searchFrame, "Empty text!");
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(searchFrame, "No student found!");
                    return;
                }
                if(!record.isEmpty()) {
                    searchFrame.dispose();
                    ViewGUI viewTable = new ViewGUI(record, false);
                    JFrame resultFrame = new JFrame("Search Results");
                    resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    resultFrame.setSize(800, 400);
                    resultFrame.setLocationRelativeTo(null);
                    resultFrame.add(viewTable);
                    resultFrame.setVisible(true);
                    
                }
                else
                    JOptionPane.showMessageDialog(searchFrame, "No student found!");
                 
            }
        });
        searchFrame.setVisible(true);
    }
    


}