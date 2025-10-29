package pack;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddStudentGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtStudentFullName;
	private JComboBox<String> genderBox;
	private JTextField txtAge;
	private JComboBox<String> departementBox;
	private JTextField txtGpa;

	/**
	 * Create the panel.
	 */
	public AddStudentGUI() {
		setBackground(SystemColor.controlHighlight);
		setLayout(new BorderLayout(20,20));
		
		
		JLabel lblTitle = new JLabel("Add New Student",SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 35));
		lblTitle.setForeground(Color.DARK_GRAY);
		add(lblTitle,BorderLayout.NORTH);
		
	    JPanel panel = new JPanel(new GridLayout(6,2,15,15));
	    panel.setBackground(SystemColor.controlHighlight);
	    panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

	    
		JLabel label = new JLabel("  Student Full Name :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label);
		txtStudentFullName = new JTextField(15);
		txtStudentFullName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtStudentFullName.setBackground(SystemColor.inactiveCaption);
		panel.add(txtStudentFullName);
		
		JLabel label_1 = new JLabel("  Age : ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_1);
		txtAge = new JTextField(15);
		txtAge.setBackground(SystemColor.inactiveCaption);
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtAge);
		
		JLabel label_2 = new JLabel("  Departement : ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_2);
		departementBox = new JComboBox();
		departementBox.setToolTipText("");
		departementBox.setForeground(SystemColor.desktop);
		departementBox.setBackground(SystemColor.inactiveCaption);
		departementBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departementBox.setModel(new DefaultComboBoxModel(new String[] {"Computer and Communication", "Mechatronics and Robotics", "Electromechanics", "Biomedical", "Architecture and Conctruction", "Oil and Petrochemicals", "Aerospace"}));
		panel.add(departementBox);
		
		JLabel label_3 = new JLabel("  Gender : ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_3);
		genderBox = new JComboBox<>();
		genderBox.setBackground(SystemColor.inactiveCaption);
		genderBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		panel.add(genderBox);
		
		JLabel label_4 = new JLabel("  GPA : ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_4);
		txtGpa = new JTextField(15);
		txtGpa.setBackground(SystemColor.inactiveCaption);
		txtGpa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtGpa);
		
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		buttonPanel.setBackground(SystemColor.controlHighlight);
		
		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				int age=0;
				float gpa=0;
				String fullName=txtStudentFullName.getText().trim();
				if(txtAge.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null,"Enter Age,Please!");
					flag = false;
				}
				else{age=Integer.parseInt(txtAge.getText().trim());}
				String gender=(String)genderBox.getSelectedItem();
				String department=(String)departementBox.getSelectedItem();
				if(txtGpa.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null,"Enter GPA,Please!");
					flag = false;
				}
				else {gpa=Float.parseFloat(txtGpa.getText().trim());}
				Database newStudent=new Database();
				if(flag) newStudent.addStudent(fullName, age, gender, department, gpa);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		buttonPanel.add(btnNewButton);
		

		
		
        add(panel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        
	}

}
