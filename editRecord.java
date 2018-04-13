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

public class editRecord {

	private JFrame frmeditRecord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editRecord window = new editRecord();
					window.frmeditRecord.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public editRecord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//JFrame for GUI editRecord
		frmeditRecord = new JFrame();
		frmeditRecord.setTitle("Task Logging");
		frmeditRecord.setBounds(100, 100, 434, 268);
		frmeditRecord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmeditRecord.getContentPane().setLayout(null);
		
		//CONNECT TO DATABASE
		//CONNECT TO PREVIOUS PAGE
		
		//JLabel for Caretaker Name
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		//GET CARETAKER NAME WHO MEANT TO DO IT
		lblCaretakerName.setBounds(25, 28, 117, 14);
		frmeditRecord.getContentPane().add(lblCaretakerName);
		
		//JLabel for TimeOfCompletion 
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(25, 53, 134, 14);
		frmeditRecord.getContentPane().add(lblTimeOfCompletion);
		
		//JLabel for AdditionalComments
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setBounds(25, 78, 134, 14);
		frmeditRecord.getContentPane().add(lblAdditionalComments);
		
		//JButton for Submit
		JButton btnSubmit = new JButton("Submit");
		//ActionListener for when button is clicked
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//When clicked, shows Dialog confirming action 
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to re-Log this task?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      System.out.println("");
				    } else if (response == JOptionPane.YES_OPTION) {
				      System.out.println("Task has been re-logged");
				      //Updates database, task logged, takes user back to previous page
				      //UPDATE DATABASE
				      //TAKE USER BACK TO PREVIOUS PAGE
				    } 
			}
		});
		btnSubmit.setBounds(171, 181, 89, 23);
		frmeditRecord.getContentPane().add(btnSubmit);
		
		//JButton for Cancel
		JButton btnCancel = new JButton("Cancel");
		//ActionLinstener for when it is clicked
		btnCancel.addActionListener(new ActionListener(){
			//If clicked, closes GUI,  takes user back to previous page
			public void actionPerformed(ActionEvent e){
				btnCancel.addActionListener(new ActionListener(){
					//If clicked, closes GUI
					public void actionPerformed(ActionEvent e){
						frmeditRecord.hide();
						new CaretakerSchedule2();
					}
				});
			}
		});
		btnCancel.setBounds(292, 181, 89, 23);
		frmeditRecord.getContentPane().add(btnCancel);
		
		//Jspinner for TimeOfCompletion 
		//Gets time previously recorded at
		//GET PREVIOUS TIME RECORD
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1522364400000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBackground(new Color(240, 240, 240));
		spinner.setBounds(171, 49, 134, 22);
		frmeditRecord.getContentPane().add(spinner);
		
		//JComboBox for choosing caretakers
		JComboBox comboBox = new JComboBox();
		//List of Options
		// PRE-SELECTED CARETAKER WHO WAS RECRDED AS COMPLETED THE TASK
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker 1", "Caretaker 2", "Caretaker 3", "Caretaker 4", "Caretaker 5", "Caretaker 6"}));
		comboBox.setBounds(171, 24, 134, 22);
		frmeditRecord.getContentPane().add(comboBox);
		
		//JScrollPane for JTextArea, so overflow of text can be seen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 78, 210, 90);
		frmeditRecord.getContentPane().add(scrollPane);
		
		//JTextArea for Additional comments to be added
		JTextArea txtrTypeAnyComment = new JTextArea();
		scrollPane.setViewportView(txtrTypeAnyComment);
		//GET PREVIOUS COMMENTS
		txtrTypeAnyComment.setText("");
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
		btnHelp.setBounds(25, 181, 89, 23);
		frmeditRecord.getContentPane().add(btnHelp);
	}
} 
