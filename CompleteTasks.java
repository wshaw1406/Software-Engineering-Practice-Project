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
		
		//JButton for undo
		JButton btnUndo = new JButton("Undo");
		//ActionListener, when button is clicked
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Hides frame
				frmCompletedTasks.hide();
				//Refill the task list with the one from the db that has had no changes to it
			    Main.tasks = (ArrayList<Task>) db.pullTasks();
				//Opens Task Reports
				new CompleteTasks();
			}
		});
		btnUndo.setBounds(0, 366, 330, 49);
		frmCompletedTasks.getContentPane().add(btnUndo);
			
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
				"ID", "Priority", "Title", "Time Allocated", "Notes", "Date Due"
			}
		) {
			Class[] columnTypes = new Class[] {
					Integer.class,  Object.class, Object.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false,  false, false, false, true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});

		table.getColumnModel().getColumn(3).setPreferredWidth(103);;
		
		
		//Table for caretakers tasks
		model = (DefaultTableModel) table.getModel();
		
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());; 
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField())); 
		 
		for(Task task: Main.tasks) {
			String ass = "";
			if(task.getTaskAssigned() == null) {
				ass = "0";
			}
			else {
				ass = task.getTaskAssigned();
			}
			if(ass.equals(Main.user.getUsername()) && task.getTaskCompleted() == true) {
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
				int value;
				int row;
				row = table.getSelectedRow();
				value = (int) table.getModel().getValueAt(row, 0);
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
		btnHelp.setBounds(12, 328, 97, 25);
		frmCompletedTasks.getContentPane().add(btnHelp);
		

		JButton btnLogout = new JButton("Log-out");
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

		//label that calls the user's get username and displays it
		JLabel usernameLbl = new JLabel("");
		usernameLbl.setBounds(137, 13, 152, 14);
		frmCompletedTasks.getContentPane().add(usernameLbl);
		usernameLbl.setText(Main.user.getUsername());
		
		JLabel lblEnsureATask = new JLabel("*Ensure a task is selected");
		lblEnsureATask.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnsureATask.setBounds(487, 350, 164, 14);
		frmCompletedTasks.getContentPane().add(lblEnsureATask);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCompletedTasks.setVisible(false);
				new CaretakerSchedule2();
			}
		});
		btnBack.setBounds(12, 8, 97, 25);
		frmCompletedTasks.getContentPane().add(btnBack);
		frmCompletedTasks.setVisible(true);
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