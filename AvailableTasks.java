package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class AvailableTasks {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvailableTasks window = new AvailableTasks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AvailableTasks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox cmBxTask = new JComboBox();
		cmBxTask.setBounds(152, 13, 101, 22);
		frame.getContentPane
		
		
		().add(cmBxTask);
		
		//Fill combo with all the avaiable tasks
		
		JComboBox cmBxEmp = new JComboBox();
		cmBxEmp.setBounds(152, 79, 101, 22);
		frame.getContentPane().add(cmBxEmp);
		
		//Fill the combo with all the emps
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(156, 180, 97, 25);
		
		//Update the db with the new emp
		
		frame.getContentPane().add(btnSubmit);
		frame.setVisible(true);
	}
}
