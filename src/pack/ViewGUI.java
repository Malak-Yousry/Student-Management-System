package pack;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;

public class ViewGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	//private JTable table;
	private DefaultTableModel model;
	private Database studentRecords;
	private boolean editable;
	private ArrayList<StudentRecord> records;
	private JTable table=new JTable(model);
	private JComboBox<String> sortChoice;
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
		public boolean isCellEditable(int row,int column) {
			if(column != 0)
				return editable;
			return false;
		}
		};
		// create table
		table=new JTable(model);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);

		studentRecords = new Database();
		if(list.size() > 1){
			sortChoice = new JComboBox<>();
			sortChoice.setBackground(SystemColor.inactiveCaption);
			sortChoice.setFont(new Font("Tahoma", Font.PLAIN, 12));
			sortChoice.setModel(new DefaultComboBoxModel(new String[] {"Sort by ID","Sort by GPA", "Sort by Name"}));
			add(sortChoice, BorderLayout.NORTH);

			studentRecords.sortById();
			records = studentRecords.readFromFile();
			studentRecords.viewAllrecords(model,records);

			sortChoice.addActionListener(e -> {
			String choice = (String)sortChoice.getSelectedItem();
			if(choice.equals("Sort by ID")){
				records = studentRecords.readFromFile();
				studentRecords.sortById();
			}
			else if (choice.equals("Sort by GPA")){
				records = studentRecords.readFromFile();
				studentRecords.sortByGPA();
			}
			else{
				records = studentRecords.readFromFile();
				studentRecords.sortByName();
			}
			studentRecords.viewAllrecords(model,records);
		});

		}
		add(new JScrollPane(table), BorderLayout.CENTER);
		// get students data
		studentRecords.sortById();
		studentRecords.viewAllrecords(model,records);
		

	}
	public int getSelectedRow(){
		return table.getSelectedRow();
	}
	public DefaultTableModel getModel() {
    	return model;
	}

}
