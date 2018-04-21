package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class taskInformation {

	private JFrame frame;
	private JTextField txtHi;

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
		frame.setBounds(100, 100, 327, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		Database db = new Database();
		List<Task> tasks = db.pullTasks();
		String[] taskTitles = new String[tasks.size()];
		int i = 0;
		for(Task task: tasks)
		{
			taskTitles[i] = task.getTaskTitle();
			i++;
		}
		
		comboBox.setModel(new DefaultComboBoxModel(taskTitles));
		comboBox.setBounds(83, 129, 140, 20);
		frame.getContentPane().add(comboBox);
		JLabel label = new JLabel("Add New Task");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(46, 55, 92, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateTaskGUI();
				frame.setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(157, 50, 92, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditTaskGUI((String)comboBox.getSelectedItem());
				frame.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(157, 191, 92, 23);
		frame.getContentPane().add(button_1);
		
		JLabel label_1 = new JLabel("Edit Task");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(46, 196, 92, 14);
		frame.getContentPane().add(label_1);
		
		JButton button_2 = new JButton("Remove");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Task toRemove = db.pullSingleTask((String)comboBox.getSelectedItem());
			    db.deleteTask(toRemove.getTaskID());
			    frame.setVisible(false);
			    new taskInformation();
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(157, 225, 92, 23);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("Remove Task");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(46, 230, 92, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Task Report");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(46, 260, 92, 23);
		frame.getContentPane().add(label_3);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				//new TaskReports();
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReport.setBounds(157, 259, 89, 23);
		frame.getContentPane().add(btnReport);
		
		JLabel lblTasks = new JLabel("Tasks");
		lblTasks.setBounds(127, 25, 46, 14);
		frame.getContentPane().add(lblTasks);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(212, 352, 89, 23);
		frame.getContentPane().add(btnBack);
		

		
		JLabel lblSelectATask = new JLabel("Select a task to edit, remove or generate a report on\r\n");
		lblSelectATask.setBounds(28, 104, 262, 14);
		frame.getContentPane().add(lblSelectATask);
		
	}
}