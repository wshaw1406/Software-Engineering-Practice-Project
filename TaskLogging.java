
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;

public class TaskLogging {

	private JFrame frmTaskLogging;
	public static JTable table;
	public static DefaultTableModel model;
	private JTextField txtTaskName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskLogging window = new TaskLogging();

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
		frmTaskLogging.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		//JFrame for GUI TaskLogging
		frmTaskLogging = new JFrame();
		frmTaskLogging.setTitle("Task Logging");
		frmTaskLogging.setBounds(100, 100, 434, 298);
		frmTaskLogging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTaskLogging.getContentPane().setLayout(null);
		
		//CONNECT TO DATABASE
		//CONNECT TO PREVIOUS PAGE
		
		//JLabel for Caretaker Name
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		//GET CARETAKER NAME WHO MEANT TO DO IT
		lblCaretakerName.setBounds(23, 62, 117, 14);
		frmTaskLogging.getContentPane().add(lblCaretakerName);
		
		//JLabel for TimeOfCompletion 
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(23, 87, 134, 14);
		frmTaskLogging.getContentPane().add(lblTimeOfCompletion);
		
		//JLabel for AdditionalComments
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setBounds(23, 112, 134, 14);
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
				      frmTaskLogging.setVisible(false); 
				      new CaretakerSchedule2();
				      //Updates database, task logged, takes user back to previous page
				      //UPDATE DATABASEs
				      //TAKE USER BACK TO PREVIOUS PAGE
				    } 
			}
		});
		btnSubmit.setBounds(169, 215, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
		
		//JButton for Cancel
		JButton btnCancel = new JButton("Cancel");
		//ActionLinstener for when it is clicked
		btnCancel.addActionListener(new ActionListener(){
			//If clicked, closes GUI
			public void actionPerformed(ActionEvent e){
				System.out.println("No task logged");
			    frmTaskLogging.setVisible(false); 
			    new CaretakerSchedule2();
				
			}
		});
		
		btnCancel.setBounds(290, 215, 89, 23);
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
		spinner.setBounds(169, 83, 134, 22);
		frmTaskLogging.getContentPane().add(spinner);
		
		//JComboBox for choosing caretakers
		JComboBox comboBox = new JComboBox();
		//List of Options
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker 1", "Caretaker 2", "Caretaker 3", "Caretaker 4", "Caretaker 5", "Caretaker 6"}));
		comboBox.setBounds(169, 58, 134, 22);
		frmTaskLogging.getContentPane().add(comboBox);
		
		//JScrollPane for JTextArea, so overflow of text can be seen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(169, 112, 210, 90);
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
		btnHelp.setBounds(23, 214, 89, 23);
		frmTaskLogging.getContentPane().add(btnHelp);
		
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(23, 33, 134, 16);
		frmTaskLogging.getContentPane().add(lblTaskName);
		
		txtTaskName = new JTextField();

		int column1 = 1;
		int row1 = 1;
		String value = "";
		
		
		row1 = CaretakerSchedule2.table.getSelectedRow();
		value = CaretakerSchedule2.table.getModel().getValueAt(row1, column1).toString();
		for(Task task : Main.tasks)
	    {
			if(task.getTaskTitle() == value) {
				txtTaskName.setText(task.getTaskTitle());
			}
	    }
			
		txtTaskName.setEditable(false);
		txtTaskName.setBounds(169, 30, 116, 22);
		frmTaskLogging.getContentPane().add(txtTaskName);
		txtTaskName.setColumns(10);
	}
} 
