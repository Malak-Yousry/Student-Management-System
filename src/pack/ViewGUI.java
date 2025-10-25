package pack;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ViewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private Database studentRecords;
	private JTable table_1;
	private boolean editable;
	private ArrayList<StudentRecord> records;
	/**
	 * Create the panel.
	 */
	public ViewGUI(ArrayList<StudentRecord> list,boolean edit) {
		records = list;
		editable = edit;
		setLayout(new BorderLayout(0, 0));
		
		// column names
		String[] columns= {"Student ID", "Full Name", "Age", "Gender", "Department", "GPA"};
		// columns types
		model=new DefaultTableModel(columns,0){
		Class[] columnTypes = new Class[] {
			Integer.class, String.class, Integer.class, String.class, String.class, Float.class
					};
		public Class<?> getColumnClass(int columnIndex){
			return columnTypes[columnIndex];
		}
		public boolean isCellEditable(int row,int column) {
			return editable;
		}
		};
		// create table
		table=new JTable(model);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		add(new JScrollPane(table), BorderLayout.CENTER);
		// get students data
		studentRecords=new Database();
		studentRecords.viewAllrecords(model,records);
		
		JLabel lblNewLabel = new JLabel("Students Records");
		lblNewLabel.setBackground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setLabelFor(lblNewLabel);
		add(lblNewLabel, BorderLayout.NORTH);
	

	}

}
