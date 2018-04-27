/* This page logs incompleted tasks from CaretakerSchedule. */
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.*;
import java.util.Calendar;


import javax.swing.table.DefaultTableModel;

public class TaskLogging {

	private JFrame frmTaskLogging;
	public static DefaultTableModel model;
	private JTextField txtTaskName;
	private JTextField txtTaskID;
	private Task task;


	/**
	 * Create the application.
	 */
	public TaskLogging(Task task) {
		this.task = task;
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
		
		Database db = new Database();
		//Task task = db.pullSingleTask(taskTitle);
				
		//JLabel for Caretaker Name
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		lblCaretakerName.setBounds(23, 62, 117, 14);
		frmTaskLogging.getContentPane().add(lblCaretakerName);
			
		//JLabel for TimeOfCompletion 
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(23, 87, 134, 14);
		frmTaskLogging.getContentPane().add(lblTimeOfCompletion);
		
		//JLabel for AdditionalComments
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setBounds(23, 112, 134, 14);
		frmTaskLogging.getContentPane().add(lblNotes);
	
		//JButton for Cancel
		JButton btnCancel = new JButton("Cancel");
		//ActionLinstener for when it is clicked
		btnCancel.addActionListener(new ActionListener(){
			//When JButton is clicked
			public void actionPerformed(ActionEvent e){
				//Prints out No task logged
				System.out.println("No task logged");
				//Sets Frames visibilty to false (hides it)
			    frmTaskLogging.setVisible(false); 
			    //Opens new CaretakerSchedule2
			    new CaretakerSchedule2();
			}
		});		
		btnCancel.setBounds(290, 215, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
	
		//Jspinner for TimeOfCompletion 
		JSpinner spinnerTime = new JSpinner();
		//Gets current time
		Date datenow = Calendar.getInstance().getTime();
		SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
		spinnerTime.setModel(smb);
		//Displays hour, minute currently
		JSpinner.DateEditor de_spinnerTime = new JSpinner.DateEditor(spinnerTime, "HH:mm");
		spinnerTime.setEditor(de_spinnerTime);
		spinnerTime.setBackground(new Color(240, 240, 240));
		spinnerTime.setBounds(169, 83, 134, 22);
		frmTaskLogging.getContentPane().add(spinnerTime);	
				
		//JComboBox for choosing caretakers
		JComboBox comboCaretakerName = new JComboBox();
		//List of Option
		List<User> caretakers = db.pullCaretakers();
		String[] caretakersNames = new String[caretakers.size()];
		int z = 0;
		for(User user: caretakers)
		{
			caretakersNames[z] = user.getUsername();
			z++;
		}
		comboCaretakerName.setModel(new DefaultComboBoxModel(caretakersNames));
		comboCaretakerName.setBounds(169, 58, 134, 22);
		frmTaskLogging.getContentPane().add(comboCaretakerName);
				
		//JButton for Help
		JButton btnHelp = new JButton("Help");
		//ActionListener for when clicked
		btnHelp.addActionListener(new ActionListener() {
			//When clicked, displays message, telling user what to do
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				//Opens JOptionPane
				JOptionPane.showMessageDialog(frame,
						"Select the Caretaker that completed the job, "
									+ "\nSelect the time it was completed at, "
									+ "\nThen add any additional comments in the box.");
			}		
		});
		btnHelp.setBounds(23, 214, 89, 23);
		frmTaskLogging.getContentPane().add(btnHelp);
		
		//JLabel for TaskName
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(23, 33, 134, 16);
		frmTaskLogging.getContentPane().add(lblTaskName);
			
		//JScrollPane for JTextArea, so overflow of text can be seen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(169, 112, 210, 90);
		frmTaskLogging.getContentPane().add(scrollPane);
		
		//JTextArea for Additional comments to be added
		JTextArea txtrNotes = new JTextArea();
		txtrNotes.setText(task.getTaskNotes());
		scrollPane.setViewportView(txtrNotes);
		txtrNotes.setLineWrap(true);
		
		txtTaskID = new JTextField();		
		txtTaskID.setBounds(169, 0, 116, 22);
		frmTaskLogging.getContentPane().add(txtTaskID);
		txtTaskID.setColumns(10);
		txtTaskID.setVisible(false);
		
		
		//JTextField for txtTaskName
		txtTaskName = new JTextField();
		txtTaskName.setText(task.getTaskTitle());
		
	   //Makes textbox uneditable 
		txtTaskName.setEditable(false);
				txtTaskName.setBounds(169, 33, 116, 22);
				frmTaskLogging.getContentPane().add(txtTaskName);
				txtTaskName.setColumns(10);
				
			
				//JButton for Submit
				JButton btnSubmit = new JButton("Submit");
				//ActionListener for when button is clicked
				btnSubmit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0){
						//When clicked, shows Dialog confirming action 
						int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to Log this task?", "Confirm",
						        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						    //If, the user selects the 'No' option
						    if (response == JOptionPane.NO_OPTION) {
						    	//Prints nothing
						        System.out.println("");
						    //Else if, the user selects the 'yes' option
						    } else if (response == JOptionPane.YES_OPTION) {
						    	Task updateTask = task;
					    	    updateTask.setTaskID(task.getTaskID());
								updateTask.setTaskNotes(txtrNotes.getText());
								updateTask.setTaskTimeCompleted((int)spinnerTime.getValue());
								updateTask.setTaskAssigned((String) comboCaretakerName.getSelectedItem());
								updateTask.setTaskCompleted(true);
								task.setTaskCompleted(true);
								db.updateTask(updateTask);

						    	//Prints Task has been logged
						    	System.out.println("Task has been logged");
						    	//Hides this JFrame
						    	frmTaskLogging.setVisible(false); 
						    	//Opens CaretakerSchedule2
						    	new CaretakerSchedule2();
						    } 
					}
				});
				btnSubmit.setBounds(169, 215, 89, 23);
				frmTaskLogging.getContentPane().add(btnSubmit);
				


	}
} 
