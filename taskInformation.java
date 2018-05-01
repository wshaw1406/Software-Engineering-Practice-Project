import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class taskInformation {

	private JFrame frame;
	private JTable table;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public taskInformation() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Database db = new Database();
		List<Task> tasks = db.pullTasks();
		String[] taskTitles = new String[tasks.size()];
		int i = 0;
		for(Task task: tasks)
		{
			taskTitles[i] = task.getTaskTitle();
			i++;
		}
		JLabel label = new JLabel("Add New Task");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(355, 119, 92, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateTaskGUI();
				frame.setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(466, 114, 92, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				row = table.getSelectedRow();
				new EditTaskGUI(table.getModel().getValueAt(row, 3).toString());
				frame.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(466, 144, 92, 23);
		frame.getContentPane().add(button_1);
		
		JLabel label_1 = new JLabel("Edit Task");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(355, 149, 92, 14);
		frame.getContentPane().add(label_1);
		
		JButton button_2 = new JButton("Remove");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				row = table.getSelectedRow();
				Task toRemove = db.pullSingleTask(table.getModel().getValueAt(row, 3).toString());
			    db.deleteTask(toRemove.getTaskID());
			    frame.setVisible(false);
			    new taskInformation();
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(466, 178, 92, 23);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("Remove Task");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(355, 183, 92, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Task Report");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(355, 213, 92, 23);
		frame.getContentPane().add(label_3);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new TaskReports();
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReport.setBounds(466, 212, 89, 23);
		frame.getContentPane().add(btnReport);
		
		JLabel lblTasks = new JLabel("Tasks");
		lblTasks.setBounds(127, 57, 46, 14);
		frame.getContentPane().add(lblTasks);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				new adminGUI();
			}
		});
		btnBack.setBounds(21, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		//JScrollPane for the table, so if too much data scroll bar can be used
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 82, 267, 246);
		frame.getContentPane().add(scrollPane_1);
				
		//Jtable table
		table = new JTable();		
		scrollPane_1.setViewportView(table);
				
		//Default table
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Time", "Priority", "Title", "Duration"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, 
                      "Are you sure you want to log out", "log out", 
                      JOptionPane.YES_NO_OPTION); 
				if (choice == JOptionPane.YES_OPTION) {
					frame.setVisible(false);
					new Main();
				}	
			}
		});
		btnLogOut.setBounds(469, 11, 89, 23);
		frame.getContentPane().add(btnLogOut);
				
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(79);
		table.getColumnModel().getColumn(4).setPreferredWidth(103);
		
	
		//Table for caretakers tasks
		DefaultTableModel model = (DefaultTableModel) table.getModel();
				
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
				 
		for(Task task: tasks) {
			model.addRow(new Object[]{task.getTaskID(), newTime, task.getTaskPriority(), task.getTaskTitle(), task.getTaskDuration()});
			
			cal.add(Calendar.MINUTE, task.getTaskDuration());
					newTime = df.format(cal.getTime());
		}
		
		JButton btnComplete = new JButton("Complete/edit task");
		//ActionListener for when button is clicked
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = " ";
				int row;
				row = table.getSelectedRow();
				value = table.getModel().getValueAt(row, 3).toString();
				Task task = db.pullSingleTask(value);
				if(task.getTaskCompleted() == false)
				{
					//Hides the frame
					frame.setVisible(false);
					//Creates TaskLogging 
					new TaskLogging(task);
					System.out.println("test");
				}
				else{
					//Hides the frame
					frame.setVisible(false);
					//Creates TaskLogging 
					new editRecord(task);
					System.out.println("test2");
				}
			}
		});
			
		btnComplete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnComplete.setBounds(355, 305, 203, 23);
		frame.getContentPane().add(btnComplete);
		
		JLabel lblNewLabel = new JLabel("Select a task before Complete");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(355, 283, 203, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel currentLbl = new JLabel("");
		currentLbl.setBounds(21, 340, 249, 14);
		currentLbl.setText("Currently logged in: "+ Main.user.getUsername());
		frame.getContentPane().add(currentLbl);		
	}
}