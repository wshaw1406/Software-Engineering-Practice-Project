import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class addNewTask {

	private JFrame frmAddNewTask;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addNewTask window = new addNewTask();
					window.frmAddNewTask.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addNewTask() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddNewTask = new JFrame();
		frmAddNewTask.setTitle("Add New Task");
		frmAddNewTask.setBounds(100, 100, 450, 366);
		frmAddNewTask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewTask.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(182, 60, 212, 20);
		frmAddNewTask.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTaskName = new JLabel("* Task Name");
		lblTaskName.setBounds(57, 63, 75, 14);
		frmAddNewTask.getContentPane().add(lblTaskName);
		
		JLabel lblTaskType = new JLabel("* Task Type");
		lblTaskType.setBounds(57, 88, 75, 14);
		frmAddNewTask.getContentPane().add(lblTaskType);
		
		JLabel lblTaskNotes = new JLabel("* Task Notes");
		lblTaskNotes.setBounds(57, 143, 75, 14);
		frmAddNewTask.getContentPane().add(lblTaskNotes);
		
		JLabel lblPleaseFillIn = new JLabel("Please fill in the form then, press submit to add the task");
		lblPleaseFillIn.setBounds(10, 11, 280, 14);
		frmAddNewTask.getContentPane().add(lblPleaseFillIn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(182, 85, 212, 20);
		frmAddNewTask.getContentPane().add(textField_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(182, 138, 212, 145);
		frmAddNewTask.getContentPane().add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(305, 294, 89, 23);
		frmAddNewTask.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(57, 294, 89, 23);
		frmAddNewTask.getContentPane().add(btnCancel);
		
		JLabel lblTaskDuration = new JLabel("* Task Duration");
		lblTaskDuration.setBounds(57, 113, 104, 14);
		frmAddNewTask.getContentPane().add(lblTaskDuration);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spinner.setBounds(182, 110, 108, 20);
		frmAddNewTask.getContentPane().add(spinner);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(296, 116, 46, 14);
		frmAddNewTask.getContentPane().add(lblHours);
		
		JLabel label = new JLabel("* These fields are required");
		label.setBounds(10, 35, 254, 14);
		frmAddNewTask.getContentPane().add(label);
	}
}
