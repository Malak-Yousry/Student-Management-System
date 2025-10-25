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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JTextField txtPassword;
	

	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		setBackground(SystemColor.controlHighlight);
		setLayout(new BorderLayout(20,20));
		//setBackground(new Color(240,240,240));
		
		JLabel lblTitle = new JLabel("Login",SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 35));
		lblTitle.setForeground(Color.DARK_GRAY);
		add(lblTitle,BorderLayout.NORTH);
		
	    JPanel panel = new JPanel(new GridLayout(6,2,15,15));
	    panel.setBackground(SystemColor.controlHighlight);
	    panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
	    

	    
		JLabel label = new JLabel("  User Name :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label);
		txtUserName = new JTextField(15);
		txtUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUserName.setBackground(SystemColor.inactiveCaption);
		panel.add(txtUserName);
	
		
		JLabel label_4 = new JLabel("  GPA : ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_4);
		txtPassword = new JTextField(15);
		txtPassword.setBackground(SystemColor.inactiveCaption);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(txtPassword);
		
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		buttonPanel.setBackground(SystemColor.controlHighlight);
		
		JButton btnNewButton = new JButton("Login in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=txtUserName.getText().trim();
				String password=txtPassword.getText().trim();
				Validations user=new Validations();
				user.validateUser(userName,password);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		buttonPanel.add(btnNewButton);
		

		
		
        add(panel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        
	

	}

}
