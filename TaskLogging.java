import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class TaskLogging {

	private JFrame frmTaskLogging;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskLogging window = new TaskLogging();
					window.frmTaskLogging.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TaskLogging() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTaskLogging = new JFrame();
		frmTaskLogging.setTitle("Task Logging");
		frmTaskLogging.setBounds(100, 100, 434, 268);
		frmTaskLogging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTaskLogging.getContentPane().setLayout(null);
		
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		lblCaretakerName.setBounds(25, 28, 89, 14);
		frmTaskLogging.getContentPane().add(lblCaretakerName);
		
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(25, 53, 117, 14);
		frmTaskLogging.getContentPane().add(lblTimeOfCompletion);
		
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setBounds(25, 78, 117, 14);
		frmTaskLogging.getContentPane().add(lblAdditionalComments);
		
		textField = new JTextField();
		textField.setBounds(171, 50, 86, 20);
		frmTaskLogging.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(171, 25, 160, 20);
		frmTaskLogging.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(171, 73, 206, 105);
		frmTaskLogging.getContentPane().add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(168, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(292, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
	}
}
