package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateTaskGUI {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public CreateTaskGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 401, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsertTheDetails = new JLabel("Insert the details of the new task and submit to create\r\n");
		lblInsertTheDetails.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInsertTheDetails.setBounds(10, 11, 336, 14);
		frame.getContentPane().add(lblInsertTheDetails);
		
		JLabel lblTheseFields = new JLabel("* These fields are required");
		lblTheseFields.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTheseFields.setBounds(20, 36, 159, 14);
		frame.getContentPane().add(lblTheseFields);
		
		JLabel lblTaskTitle = new JLabel("* Task Title");
		lblTaskTitle.setBounds(30, 61, 86, 14);
		frame.getContentPane().add(lblTaskTitle);
		
		JLabel lblTaskType = new JLabel("* Task Type");
		lblTaskType.setBounds(30, 97, 86, 14);
		frame.getContentPane().add(lblTaskType);
		
		JLabel lblTaskPriority = new JLabel("* Task Priority");
		lblTaskPriority.setBounds(30, 129, 86, 14);
		frame.getContentPane().add(lblTaskPriority);
		
		JLabel lblTaskDuration = new JLabel("* Task Duration");
		lblTaskDuration.setBounds(30, 164, 86, 14);
		frame.getContentPane().add(lblTaskDuration);
		
		JLabel lblTaskNotes = new JLabel("Task Notes");
		lblTaskNotes.setBounds(30, 204, 86, 14);
		frame.getContentPane().add(lblTaskNotes);
		
		textField = new JTextField();
		textField.setBounds(126, 58, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnRegular = new JRadioButton("Regular");
		buttonGroup.add(rdbtnRegular);
		rdbtnRegular.setBounds(122, 93, 90, 23);
		frame.getContentPane().add(rdbtnRegular);
		
		JRadioButton rdbtnOneoff = new JRadioButton("One-Off");
		buttonGroup.add(rdbtnOneoff);
		rdbtnOneoff.setBounds(274, 93, 72, 23);
		frame.getContentPane().add(rdbtnOneoff);
		
		JSpinner prioritySpinner = new JSpinner();
		prioritySpinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		prioritySpinner.setBounds(126, 126, 53, 20);
		frame.getContentPane().add(prioritySpinner);
		
		JLabel lblPriority = new JLabel("1 = Low, 2 = Medium, 3 = High");
		lblPriority.setBounds(187, 129, 159, 14);
		frame.getContentPane().add(lblPriority);
		
		JSpinner durationSpinner = new JSpinner();
		durationSpinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		durationSpinner.setBounds(126, 161, 53, 20);
		frame.getContentPane().add(durationSpinner);
		
		JLabel lblHours = new JLabel("Minutes");
		lblHours.setBounds(187, 164, 60, 14);
		frame.getContentPane().add(lblHours);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(126, 199, 220, 56);
		frame.getContentPane().add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(286, 279, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new taskInformation();
			}
		});
		btnCancel.setBounds(10, 279, 89, 23);
		frame.getContentPane().add(btnCancel);
	}
}