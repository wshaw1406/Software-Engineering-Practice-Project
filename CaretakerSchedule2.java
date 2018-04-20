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

public class CaretakerSchedule2 extends JFrame{
	
	//Define private and public variables 
	private JFrame frame;
	public static JTable table;
	public static DefaultTableModel model;
	private Database db;

	//Sorts rows in table
	private void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
		table.setRowSorter(sorter);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaretakerSchedule2 window = new CaretakerSchedule2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CaretakerSchedule2() {
		//Calls initialize function
		initialize();
		//Shows the frame
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Caretaker Schedule");
		frame.getContentPane().setLayout(null);
		
		//JButton for undo
		JButton btnUndo = new JButton("Undo");
		//ActionListener, when button is clicked
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hides frame and creates new Main
				frame.hide();
				new Main();
			}
		});
		btnUndo.setBounds(0, 366, 330, 49);
		frame.getContentPane().add(btnUndo);
		
		//JButton for Complete
		JButton btnComplete = new JButton("Complete");
		//ActionListener for when button is clicked
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hides the frame
				frame.hide();
				//Creates TaskLogging 
				new TaskLogging();

				// UPDATE DB WITH EACH ARRAY LIST ITEM


			}
		});
		btnComplete.setBounds(330, 366, 330, 49);
		frame.getContentPane().add(btnComplete);
		
		//JScrollPane for the table, so iff too much data scroll bar can be used
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
				"Time", "Priority", "Title", "Time Allocated", "Notes"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		
		
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
		frame.getContentPane().add(btnAssignNewTasks);
		
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
		frame.getContentPane().add(btnEditCompletedTask);
		
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
		
		//JButton for Help
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			//When clicked, displays message, telling user what to do
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				//Opens JOptionPane
				JOptionPane.showMessageDialog(frame,
						"To Log a task: Select a task, then press 'Complete', "
									+ "\nTo view Completed tasks: Select 'Completed Task', "
									+ "\nTo add more tasks to your schedule, use 'Assign New Tasks',"
									+ "\n then, tick the tasks you want and press 'submit'.");
			}		
		});
		btnHelp.setBounds(12, 13, 97, 25);
		frame.getContentPane().add(btnHelp);
		
		//Table for caretakers tasks
		model = (DefaultTableModel) table.getModel();
		
		String startTime = "9:15";
		 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		 Date d = null;
		try {
			d = df.parse(startTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 String newTime = df.format(cal.getTime());
		 			
		//For loop, runs through the tasks in Main
		for(Task task : Main.tasks)
	    {
			System.out.println(task.getTaskAssigned());
			//If, taskAssigned is true, run code
			if(task.getTaskAssigned() == null) {
				//If, taskCompleted is false, run code
				//DOESNT WORK initailly but should be okay hehe!!!!!!!!!!!1
				if(task.getTaskCompleted() == false) {
					//Fills in table with respective data
				model.addRow(new Object[]{newTime, task.getTaskPriority(), task.getTaskTitle(), task.getTaskDuration(), "Notes" });
				}
			}
			cal.add(Calendar.MINUTE, task.getTaskDuration());
			newTime = df.format(cal.getTime());
	    }
		
		// Runs sort function
		sort();
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
			
			int column1 = 2;
			int row1 = 1;
			String value = "";
			
			if(clicked) {
				//System.out.println(btn.getName());
				//System.out.printf("testing"); 

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