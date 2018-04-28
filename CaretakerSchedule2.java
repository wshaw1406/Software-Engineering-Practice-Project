import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		Database db = new Database();
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
				//Opens Task Reports
				new CaretakerSchedule2();
			}
		});
		btnUndo.setBounds(0, 366, 330, 49);
		frame.getContentPane().add(btnUndo);
			
		//JScrollPane for the table, so if too much data scroll bar can be used
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(101, 95, 463, 214);
		frame.getContentPane().add(scrollPane_1);
		
		//Jtable table
		table = new JTable();		
		scrollPane_1.setViewportView(table);
		
		//Default table
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Time", "Priority", "Title", "Time Allocated", "Notes", "Completed"
			}
		) {
			Class[] columnTypes = new Class[] {
					Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
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
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(103);;
		
		
		//Table for caretakers tasks
		model = (DefaultTableModel) table.getModel();
		
		// Defines String
		String startTime = "9:15";
		// Sets format of the date
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		// Sets d to NULL
		Date d = null;
		try {
			d = df.parse(startTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		 Calendar cal = Calendar.getInstance();
		 // Sets time
		 cal.setTime(d);
		 String newTime = df.format(cal.getTime());
		 
		table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());; 
		table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JTextField())); 
		 
		List<Task> tasks = db.pullUsersTasks(Main.user.getUsername());
		for(Task task: tasks) {
				model.addRow(new Object[]{task.getTaskID(), newTime, task.getTaskPriority(), task.getTaskTitle(), task.getTaskDuration(), "Notes", task.getTaskCompleted()  }); //DOESNT WORK-COMPLETED
			
			cal.add(Calendar.MINUTE, task.getTaskDuration());
			newTime = df.format(cal.getTime());
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
		


		
		//JButton for Complete
		JButton btnComplete = new JButton("Complete");

		//ActionListener for when button is clicked
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = " ";
				int row;
				row = table.getSelectedRow();
				value = table.getModel().getValueAt(row, 3).toString();
				Task task = db.pullSingleTask(value);
				//Hides the frame
				frame.setVisible(false);
				//Creates TaskLogging 
				new TaskLogging(task);

			}
		});
		btnComplete.setBounds(330, 366, 330, 49);
		frame.getContentPane().add(btnComplete);
		
		//JButton for Completed Tasks
		JButton btnEditCompletedTask = new JButton("Completed Tasks");
		//When clicked
		btnEditCompletedTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Hides frame
				frame.hide();
				//Opens Task Reports
				new TaskReports();
			}
		});
		btnEditCompletedTask.setBounds(503, 13, 148, 25);
		frame.getContentPane().add(btnEditCompletedTask);;
		
		//JButton for Help
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			//When clicked, displays message, telling user what to do
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				//Opens JOptionPane
				JOptionPane.showMessageDialog(frame,
						"To Log or Edit a task: Select a task, then press 'Complete', "
									+ "\n To add more tasks to your schedule, use 'Assign New Tasks',"
									+ "\n then, tick the tasks you want and press 'submit'.");
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
				new loginGUI();
			}
		});
		btnLogout.setBounds(12, 13, 97, 25);
		frame.getContentPane().add(btnLogout); 			
	}
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
			Database db = new Database();
			List<Task> tasks = db.pullUsersTasks(Main.user.getUsername());
			int column1 = 3;
			int row1;
			String value = "";

			if(clicked) {
					if(lbl == "Notes") {
						row1 = CaretakerSchedule2.table.getSelectedRow();
						value = CaretakerSchedule2.table.getModel().getValueAt(row1, column1).toString();
						for(Task task : tasks)
					    {
							System.out.printf("testing4");  
							if(task.getTaskTitle() == value) {	//DOESNT WORK							
								System.out.printf("testing5");  
								JOptionPane.showMessageDialog(btn, task.getTaskNotes());
							}
							//else {
							//	System.out.printf("testing6");  

							//}
							
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