
package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JTextField txtPassword;
	

	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		setLocationRelativeTo(null);
		//setContentPane(HomePage);
        getContentPane().setBackground(SystemColor.controlHighlight);
        getContentPane().setLayout(new BorderLayout(20,20));
		//setBackground(new Color(240,240,240));
		
		JLabel lblTitle = new JLabel("Login",SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 35));
		lblTitle.setForeground(Color.DARK_GRAY);
		getContentPane().add(lblTitle,BorderLayout.NORTH);
		
	    JPanel panel = new JPanel(new GridLayout(2,2,20,20));
	    panel.setBackground(SystemColor.controlHighlight);
	    panel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
	    

	    
		JLabel label = new JLabel("  User Name :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label);
		txtUserName = new JTextField(15);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUserName.setBackground(SystemColor.inactiveCaption);
		panel.add(txtUserName);
	
		
		JLabel label_2 = new JLabel("  Password : ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_2);
		txtPassword = new JTextField(15);
		txtPassword.setBackground(SystemColor.inactiveCaption);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtPassword);
		
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		buttonPanel.setBackground(SystemColor.controlHighlight);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=txtUserName.getText().trim();
				String password=txtPassword.getText().trim();
				Validations user=new Validations();
				boolean valid = user.validateUser(userName,password);
	
				if(valid) {
					JOptionPane.showMessageDialog(null,"Login Successfully");
					dispose();
					HomePage home = new HomePage();
					home.setVisible(true);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Incorrect UserName or Password");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		buttonPanel.add(btnNewButton);
		

		getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		
		getContentPane().add(panel,BorderLayout.CENTER);
       // add(buttonPanel,BorderLayout.SOUTH);
        
	

	}

}