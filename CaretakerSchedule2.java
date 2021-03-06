package software_eng;

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

public class CaretakerSchedule2 extends JFrame{
	
	//Define private and public variables 
	private JFrame frame;
	public static JTable table;
	public static DefaultTableModel model;

	//Sorts rows in table
	private void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
		table.setRowSorter(sorter);
	}
	
	public CaretakerSchedule2() {
		//Calls initialize function
		initialize();
		//Shows the frame
		frame.setVisible(true);
	}
	
	private void initialize() {
		
		//Connects to the database
		Database db = new Database();
		
		//Makes the JFrame called frame
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Caretaker Schedule");
		frame.getContentPane().setLayout(null);
		
		//JButton for undo
		JButton btnUndo = new JButton("Undo");
		//ActionListener, when button is clicked
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Hides frame
				frame.hide();
				//Refill the task list with the one from the db that has had no changes to it
			    Main.tasks = (ArrayList<Task>) db.pullTasks();
				//Opens Task Reports
				new CaretakerSchedule2();
			}
		});
		btnUndo.setBounds(0, 366, 330, 49);
		frame.getContentPane().add(btnUndo);
			
		//JScrollPane for the table, so if too much data scroll bar can be used
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(57, 95, 549, 214);
		frame.getContentPane().add(scrollPane_1);
		
		//Jtable table
		table = new JTable();		
		scrollPane_1.setViewportView(table);
		
		//Default table
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Time", "Priority", "Title", "Time Allocated", "Notes", "Date Due"
			}
		) {
			Class[] columnTypes = new Class[] {
					Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					true, false, false, false, false, true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		//Hide the task ID column from the user as they do not need it but the system does
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(103);;
		
		
		//Table for caretakers tasks
		model = (DefaultTableModel) table.getModel();
		
		// Defines start time of day, for the purpose of the task we assume its 915 am
		String startTime = "9:15";
		// Sets format of the date
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		// Sets d to NULL
		Date d = null;
		try {
			//Try to format the date to the start time
			d = df.parse(startTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		Calendar cal = Calendar.getInstance();
		// Sets time
		cal.setTime(d);
		String newTime = df.format(cal.getTime());
		 
		//Add the button to the notes table
		table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());; 
		table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JTextField())); 
		 
		for(Task task: Main.tasks) {
			//String ass stores the task assigned value from the db
			String ass = "";
			//If the value of task assigned is null set it to a string
			if(task.getTaskAssigned() == null) {
				ass = "0";
			}
			//If task assigned is not null set it to ass string
			else {
				ass = task.getTaskAssigned();
			}
			//If the username of the logged in user matches that of the user who's been assigned a task and the task isnt complete then show that task
			if(ass.equals(Main.user.getUsername()) && task.getTaskCompleted() == false) {
				//Add the task information to the table and calculate the time at which it needs complete
				model.addRow(new Object[]{task.getTaskID(), newTime, task.getTaskPriority(), task.getTaskTitle(), task.getTaskDuration(), "Notes", task.getDateDue()  }); 
				cal.add(Calendar.MINUTE, task.getTaskDuration());
				newTime = df.format(cal.getTime());
			}			
		}		
		//Runs sort function
		sort();
		
		//JButton for Assign New Tasks	
		JButton btnAssignNewTasks = new JButton("Assign New Tasks");
		//When button is clicked
		btnAssignNewTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creates TaskAllocation
				new TaskAllocation();
				//Hides frame
				frame.hide();
			}
		});
				
		btnAssignNewTasks.setBounds(225, 328, 213, 25);
		frame.getContentPane().add(btnAssignNewTasks);;
				
		//JButton for Complete Task
		JButton btnComplete = new JButton("Complete Task");
		//ActionListener for when button is clicked
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value;
				int row;
				row = table.getSelectedRow();
				value = (int) table.getModel().getValueAt(row, 0);
				Task task = db.pullSingleTask(value);
				//Hides the frame
				frame.setVisible(false);
				//Creates TaskLogging 
				new TaskLogging(task);	
				
			}
		});
		btnComplete.setBounds(57, 57, 213, 25);
		frame.getContentPane().add(btnComplete);;
		
		//JButton for Help
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			//When clicked, displays message, telling user what to do
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				//Opens JOptionPane
				JOptionPane.showMessageDialog(frame,
						"To Log a task: Select a task, then press 'Complete', "
									+ "\n To add more tasks to your schedule, use 'Assign New Tasks',"
									+ "\n then, tick the tasks you want and press 'submit'."
									+ "\n To view tasks that have been completed, press 'View Completed tasks.");
			}		
		});
		btnHelp.setBounds(12, 328, 97, 25);
		frame.getContentPane().add(btnHelp);
		

		JButton btnLogout = new JButton("Log-out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Hides frame
				frame.hide();
				//Opens Task Reports
				new Main();
			}
		});
		btnLogout.setBounds(554, 8, 97, 25);
		frame.getContentPane().add(btnLogout); 		
		
		//welcome label 
		JLabel welcomeLbl = new JLabel("");
		welcomeLbl.setBounds(12, 8, 65, 14);
		frame.getContentPane().add(welcomeLbl);
		welcomeLbl.setText("Welcome, ");

		//label that calls the user's get username and displays it
		JLabel usernameLbl = new JLabel("");
		usernameLbl.setBounds(72, 8, 152, 14);
		frame.getContentPane().add(usernameLbl);
		usernameLbl.setText(Main.user.getUsername());
		
		JLabel lblEnsureATask = new JLabel("*Ensure a task is selected");
		lblEnsureATask.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnsureATask.setBounds(57, 40, 213, 14);
		frame.getContentPane().add(lblEnsureATask);
		
		JButton btnCompletedTasks = new JButton("View Completed Tasks");
		btnCompletedTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hides the frame
				frame.setVisible(false);
				//Creates CompleteTasks 
				new CompleteTasks();	
				
			}
		});
		btnCompletedTasks.setBounds(393, 57, 213, 25);
		frame.getContentPane().add(btnCompletedTasks);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Update the db with any changes to each task
				Database db = new Database();
				for(Task task:Main.tasks) {
					db.updateTask(task);
				}
			}
		});
		btnSave.setBounds(333, 366, 330, 49);
		frame.getContentPane().add(btnSave);;
}

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
			int column1 = 3;
			int row1;
			String value = "";

			if(clicked) {
					if(lbl == "Notes") {
						row1 = CaretakerSchedule2.table.getSelectedRow();
						value = CaretakerSchedule2.table.getModel().getValueAt(row1, column1).toString();
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