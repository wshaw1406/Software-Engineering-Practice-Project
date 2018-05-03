import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.util.*;
import java.util.List;

public class CompleteTasks extends JFrame{
	
	//Define private and public variables 
	private JFrame frmCompletedTasks;
	public static JTable table;
	public static DefaultTableModel model;

	//Sorts rows in table
	private void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
		table.setRowSorter(sorter);
	}
	
	public CompleteTasks() {
		//Calls initialize function
		initialize();
		//Shows the frame
		frmCompletedTasks.setVisible(true);
	}
	
	private void initialize() {
		
		//Connects to the database
		Database db = new Database();
		
		//Makes the JFrame called frame
		frmCompletedTasks = new JFrame();
		frmCompletedTasks.setBounds(100, 100, 681, 463);
		frmCompletedTasks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompletedTasks.setTitle("Completed Tasks");
		frmCompletedTasks.getContentPane().setLayout(null);
				
		//JScrollPane for the table, so if too much data scroll bar can be used
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(57, 95, 549, 214);
		frmCompletedTasks.getContentPane().add(scrollPane_1);
		
		//Jtable table
		table = new JTable();		
		scrollPane_1.setViewportView(table);
		
		//Default table
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			//Titles of the columns in the table
				"ID", "Priority", "Title", "Time Allocated", "Notes", "Date Due"
			}
		) {
			//The column types
			Class[] columnTypes = new Class[] {
					Integer.class,  Object.class, Object.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				//Whether or not column is editable
				boolean[] columnEditables = new boolean[] {
					false,  false, false, false, true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		
		//Table for caretakers tasks
		model = (DefaultTableModel) table.getModel();
		
		//Gets column 4 whihc is Notes and calls functions
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());; 
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField())); 
		
		//For loop that runs through the tasks in Main
		for(Task task: Main.tasks) {
			//Makes String varaible of assign and makes it an empty string
			String assign = "";
			//If the task assigned is null, run code
			if(task.getTaskAssigned() == null) {
				//Sets teh the string to 0
				assign = "0";
			}
			//Else, cun code
			else {
				//Gets the TaskAssigned and makes it the string assign
				assign = task.getTaskAssigned();
			}
			//If the new string, assign, equlas the username of the person logged in AND if the task completed is true, run code
			if(assign.equals(Main.user.getUsername()) && task.getTaskCompleted() == true) {
				//Adds rows to the table. This includes ID, Priority, Title, Duration, "Notes" and Date Due by getting them from task
				model.addRow(new Object[]{task.getTaskID(), task.getTaskPriority(), task.getTaskTitle(), task.getTaskDuration(), "Notes", task.getDateDue()  }); 
			}			
		}		
		//Runs sort function
		sort();;
				
		//JButton for Complete
		JButton btnEditTask = new JButton("Edit Task");
		//ActionListener for when button is clicked
		btnEditTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Makes interger varaibles value and row
				int value;
				int row;
				//Sets row to the selected row in the table
				row = table.getSelectedRow();
				//Sets value to the ID of the row selected 
				value = (int) table.getModel().getValueAt(row, 0);
				//Pulls that task from the database
				Task task = db.pullSingleTask(value);
				//Hides the frame
				frmCompletedTasks.setVisible(false);
				//Creates editRecord 
				new editRecord(task);
			}
		});
		btnEditTask.setBounds(330, 366, 330, 49);
		frmCompletedTasks.getContentPane().add(btnEditTask);;
		
		//JButton for Help
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			//When clicked, displays message, telling user what to do
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				//Opens JOptionPane
				JOptionPane.showMessageDialog(frame,
						"To Edit a task: Select a task, then press 'Edit Task'");
			}		
		});
		btnHelp.setBounds(0, 366, 330, 49);
		frmCompletedTasks.getContentPane().add(btnHelp);
		
		//JButton for logout
		JButton btnLogout = new JButton("Log-out");
		//When clicked
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Hides frame
				frmCompletedTasks.hide();
				//Opens Task Reports
				new Main();
			}
		});
		btnLogout.setBounds(554, 8, 97, 25);
		frmCompletedTasks.getContentPane().add(btnLogout);

		//JLabel that calls the user's get username and displays it
		JLabel usernameLbl = new JLabel("");
		usernameLbl.setBounds(12, 39, 152, 14);
		frmCompletedTasks.getContentPane().add(usernameLbl);
		usernameLbl.setText(Main.user.getUsername());
		
		//JLabel for Ensure a task is selected
		JLabel lblEnsureATask = new JLabel("*Ensure a task is selected");
		lblEnsureATask.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnsureATask.setBounds(487, 350, 164, 14);
		frmCompletedTasks.getContentPane().add(lblEnsureATask);
		
		//JButton for Back
		JButton btnBack = new JButton("Back");
		//When clicked
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Hides the frame
				frmCompletedTasks.setVisible(false);
				//Opens Caretaker Schedule2
				new CaretakerSchedule2();
			}
		});
		btnBack.setBounds(12, 8, 97, 25);
		frmCompletedTasks.getContentPane().add(btnBack);
		frmCompletedTasks.setVisible(true);
	}

//This class is for getting the notes column to display a button and when clicked to display the notes from the database
class ButtonRenderer extends JButton implements TableCellRenderer
{

	public ButtonRenderer() {
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		setText((obj==null) ? "":obj.toString());

		return this;
	} 
}

class ButtonEditor extends DefaultCellEditor
{
	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	
	public ButtonEditor(JTextField txt) {
		super(txt);
		
		btn = new JButton();
		btn.setOpaque(true);
		

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
			
		});

	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) {
		lbl=(obj==null) ? "":obj.toString();
		btn.setText(lbl);
		
		clicked = true;
		
		return btn;
	}
	@Override
	public Object getCellEditorValue() {
			int column1 = 2;
			int row1;
			String value = "";

			if(clicked) {
					if(lbl == "Notes") {
						row1 = CompleteTasks.table.getSelectedRow();
						value = CompleteTasks.table.getModel().getValueAt(row1, column1).toString();
						for(Task task : Main.tasks)
					    {
							if(task.getTaskTitle() == value) {							
								JOptionPane.showMessageDialog(btn, task.getTaskNotes());
							}

							
					    }
				}
			}
			clicked = false;
			return new String(lbl);
		}
	
		@Override
		public boolean stopCellEditing() {
			clicked = false;
			return super.stopCellEditing();
		}
		
		@Override
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}
}