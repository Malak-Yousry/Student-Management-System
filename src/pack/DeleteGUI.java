package pack;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class DeleteGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private Database studentRecords;

	/**
	 * Create the panel.
	 */
	public DeleteGUI() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("                       Delete Student");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.NORTH);
		model=new DefaultTableModel(
		/*table = new JTable();
		table.setModel(new DefaultTableModel(*/
			new Object[][] {
			},
			new String[] {
				"Student ID", "Student Name", "Age", "Gender", "Department", "GPA", "Delete"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class, String.class, Float.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		table = new JTable(model);
		add(new JScrollPane(table), BorderLayout.CENTER);
		studentRecords=new Database();
	//	model=(DefaultTableModel) table.getModel();
		studentRecords = new Database();
		loadData();
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent action) 
			{
				int selectedR=table.getSelectedRow();
				int selectedC=table.getSelectedColumn();
				if(selectedR == -1)
					return;
				int confirm=JOptionPane.showConfirmDialog(DeleteGUI.this,"Are you sure you want to delete this student?","Confirm",JOptionPane.YES_NO_OPTION);
			    if(confirm == JOptionPane.YES_OPTION) {
			    	int studentId = (int)model.getValueAt(selectedR, 0);
			    	boolean isDeleted=studentRecords.deleteStudentById(studentId);
			    	if(isDeleted) {
			    		JOptionPane.showMessageDialog(DeleteGUI.this,"Student Deleted Successfully");
			    		loadData();
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(DeleteGUI.this,"Student not Found!");
			    		
			    	}
			    	
			    }
			
			}

		});
		

	}
	public void loadData() {
		ArrayList<StudentRecord> records=studentRecords.readFromFile();
		studentRecords.viewAllrecords(model, records);
		for(int i=0;i<model.getRowCount();i++) {
		model.setValueAt("Delete", i, 6);
		}
	}
	

}