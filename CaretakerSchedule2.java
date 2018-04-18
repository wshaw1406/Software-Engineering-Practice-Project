package software_eng;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.*;


public class CaretakerSchedule2 extends JFrame{

	private JFrame frame;
	public static JTable table;
	public static DefaultTableModel model;
	
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
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Caretaker Schedule");
		frame.getContentPane().setLayout(null);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				new Main();
			}
		});
		btnUndo.setBounds(0, 366, 330, 49);
		frame.getContentPane().add(btnUndo);
		
		JButton btnComplete = new JButton("Complete");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				model = (DefaultTableModel) table.getModel();
				for(int i = 0; i < model.getRowCount(); i++) {
					//Boolean value = (Boolean) table.getModel().getValueAt(i, 3);
					for(Task task : Main.tasks)
				    {
						//for each task that matches the tasks in the caretakers timetable
						if(task.getTaskTitle() == table.getModel().getValueAt(i, 1).toString() ) {
							new TaskLogging();
							task.setTaskCompleted(true);
							System.out.println(task.getTaskCompleted());
						}
				    }
				}
				// UPDATE DB WITH EACH ARRAY LIST ITEM


			}
		});
		
		frame.setVisible(true);
		btnComplete.setBounds(330, 366, 330, 49);
		frame.getContentPane().add(btnComplete);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(123, 86, 343, 214);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();		
		scrollPane_1.setViewportView(table);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Time", "Time allocated", "Title", "Notes", "Completed"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		
		
		JButton btnAssignNewTasks = new JButton("Assign new tasks");
		btnAssignNewTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TaskAllocation();
				frame.hide();
			}
		});
		btnAssignNewTasks.setBounds(187, 333, 213, 25);
		frame.getContentPane().add(btnAssignNewTasks);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				new adminGUI();
			}
		});
		btnNewButton.setBounds(56, 34, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEditCompletedTask = new JButton("Completed Tasks");
		btnEditCompletedTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.hide();
				
			}
		});
		btnEditCompletedTask.setBounds(554, 13, 97, 25);
		frame.getContentPane().add(btnEditCompletedTask);
		
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JTextField()));
		
		frame.setVisible(true);

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
		 		
		//If task is assigned to use show here
	
		for(Task task : Main.tasks)
	    {
			if(task.getTaskAssigned() == true) {
				model.addRow(new Object[]{newTime ,task.getTaskTime(), task.getTaskTitle(), "notes", task.getTaskCompleted()});
			}
			cal.add(Calendar.MINUTE, task.getTaskTime());
			newTime = df.format(cal.getTime());
	    }
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
		
		int column1 = 1;
		int row1 = 1;
		String value = "";
		
		if(clicked) {
			System.out.println(btn.getName());

			if(lbl == "notes") {
				row1 = CaretakerSchedule2.table.getSelectedRow();
				value = CaretakerSchedule2.table.getModel().getValueAt(row1, column1).toString();
				for(Task task : Main.tasks)
			    {
					if(task.getTaskTitle() == value) {
						JOptionPane.showMessageDialog(btn, task.getTaskNotes());
					}
			    }
			}
			else if(lbl == "assign") {
				row1 = TaskAllocation.table.getSelectedRow();
				value = TaskAllocation.table.getModel().getValueAt(row1, column1).toString();
				for(Task task : Main.tasks)
			    {
					if(task.getTaskTitle() == value) {
						JOptionPane.showMessageDialog(btn, "Task Assigned");
						task.setTaskAssigned(false);
					}
			    }
			}
			else if(lbl == "note") {
				row1 = TaskAllocation.table.getSelectedRow();
				value = TaskAllocation.table.getModel().getValueAt(row1, column1).toString();
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