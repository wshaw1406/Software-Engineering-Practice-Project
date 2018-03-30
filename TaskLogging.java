import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Calendar;
import javax.swing.SpinnerDateModel;
import javax.swing.JOptionPane;

public class TaskLogging {

	private JFrame frmTaskLogging;

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
		//JFrame for GUI TaskLogging
		frmTaskLogging = new JFrame();
		frmTaskLogging.setTitle("Task Logging");
		frmTaskLogging.setBounds(100, 100, 434, 268);
		frmTaskLogging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTaskLogging.getContentPane().setLayout(null);
		
		//CONNECT TO DATABASE
		//CONNECT TO PREVIOUS PAGE
		
		//JLabel for Caretaker Name
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		//GET CARETAKER NAME WHO MEANT TO DO IT
		lblCaretakerName.setBounds(25, 28, 117, 14);
		frmTaskLogging.getContentPane().add(lblCaretakerName);
		
		//JLabel for TimeOfCompletion 
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(25, 53, 134, 14);
		frmTaskLogging.getContentPane().add(lblTimeOfCompletion);
		
		//JLabel for AdditionalComments
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setBounds(25, 78, 134, 14);
		frmTaskLogging.getContentPane().add(lblAdditionalComments);
		
		//JButton for Submit
		JButton btnSubmit = new JButton("Submit");
		//ActionListener for when button is clicked
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//When clicked, shows Dialog confirming action 
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to Log this task?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      System.out.println("");
				    } else if (response == JOptionPane.YES_OPTION) {
				      System.out.println("Task has been logged");
				      //Updates database, task logged, takes user back to previous page
				      //UPDATE DATABASE
				      //TAKE USER BACK TO PREVIOUS PAGE
				    } 
			}
		});
		btnSubmit.setBounds(171, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
		
		//JButton for Cancel
		JButton btnCancel = new JButton("Cancel");
		//ActionLinstener for when it is clicked
		btnCancel.addActionListener(new ActionListener(){
			//If clicked, closes GUI
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		btnCancel.setBounds(292, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
		
		//Jspinner for TimeOfCompletion 
		JSpinner spinner = new JSpinner();
		//Gets current time
		Date datenow = Calendar.getInstance().getTime();
		SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
		spinner.setModel(smb);
		//Displays day, month, year, hour, minute currently
		JSpinner.DateEditor d = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
		spinner.setEditor(d);
		spinner.setBackground(new Color(240, 240, 240));
		spinner.setBounds(171, 49, 134, 22);
		frmTaskLogging.getContentPane().add(spinner);
		
		//JComboBox for choosing caretakers
		JComboBox comboBox = new JComboBox();
		//List of Options
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker 1", "Caretaker 2", "Caretaker 3", "Caretaker 4", "Caretaker 5", "Caretaker 6"}));
		comboBox.setBounds(171, 24, 134, 22);
		frmTaskLogging.getContentPane().add(comboBox);
		
		//JScrollPane for JTextArea, so overflow of text can be seen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 78, 210, 90);
		frmTaskLogging.getContentPane().add(scrollPane);
		
		//JTextArea for Additional comments to be added
		JTextArea txtrTypeAnyComment = new JTextArea();
		scrollPane.setViewportView(txtrTypeAnyComment);
		txtrTypeAnyComment.setText("#Type any comments here");
		txtrTypeAnyComment.setLineWrap(true);
		
		//JButton for Help
		JButton btnHelp = new JButton("Help");
		//ActionListener for when clicked
		btnHelp.addActionListener(new ActionListener() {
			//When clicked, displays message, telling user what to do
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
						"Select the Caretaker that completed the job, "
									+ "\nSelect the time it was completed at, "
									+ "\nThen add any additional comments in the box.");
			}		
		});
		btnHelp.setBounds(25, 180, 89, 23);
		frmTaskLogging.getContentPane().add(btnHelp);
	}
} 
