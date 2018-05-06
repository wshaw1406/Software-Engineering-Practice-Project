/* This page logs incompleted tasks from CaretakerSchedule. */
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class editRecord {

	private JFrame frmTaskLogging;
	public static DefaultTableModel model;
	private JTextField txtTaskName;
	private JTextField txtTaskID;
	private Task task;


	/**
	 * Create the application.
	 */
	public editRecord(Task task) {
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
		frmTaskLogging.setTitle("Edit Record");
		frmTaskLogging.setBounds(100, 100, 434, 338);
		frmTaskLogging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTaskLogging.getContentPane().setLayout(null);
		
		//Connects to the database
		Database db = new Database();
				
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
			    //If the user account type is 'Caretaker'
			    if(Main.user.getAccountType().equals("Caretaker"))
			    {
			    	//Hides frame
			    	frmTaskLogging.setVisible(false); 
			    	//Opens new CompleteTasks
				    new CompleteTasks();
			    }
			    //else, run code
			    else {
			    	//Hides frame
			    	frmTaskLogging.setVisible(false); 
			    	//Opens new taskInformation
				    new taskInformation();
			    }
			}
		});		
		btnCancel.setBounds(290, 255, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
	
		//Jspinner for TimeOfCompletion 
		JSpinner spinnerTime = new JSpinner();
		//Gets current time
		Date datenow = Calendar.getInstance().getTime();
		SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
		spinnerTime.setModel(smb);
		//Displays hour, minute currently
		JSpinner.DateEditor de_spinnerTime = new JSpinner.DateEditor(spinnerTime, "dd-MM-yyyy HH:mm");
		spinnerTime.setEditor(de_spinnerTime);
		spinnerTime.setBackground(new Color(240, 240, 240));
		spinnerTime.setBounds(169, 83, 134, 22);
		frmTaskLogging.getContentPane().add(spinnerTime);	
			
		//Converts the dates into a int for year, month, day, hours, minutes
		int year = (int) (new Date().getYear());
		int month = (int) (new Date().getMonth());
		int day = (int) (new Date().getDate());
		int hours = (int) (new Date().getHours());
		int minutes = (int) (new Date().getMinutes());
		// Joins all the ints together to which is set to int time
		int time = Integer.valueOf(String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(hours) + String.valueOf(minutes));
				
		//JComboBox for choosing caretakers
		JComboBox comboCaretakerName = new JComboBox();
		//List of Options of the Caretakers on the System 
		List<User> caretakers = db.pullCaretakers();
		String[] caretakersNames = new String[caretakers.size()];
		//Makes int variable z
		int z = 0;
		//For loop that run through all the users who are caretakers
		for(User user: caretakers)
		{
			//Gets the caretakers username
			caretakersNames[z] = user.getUsername();
			//Incriment by 1
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
						+ "\nThen add any additional comments in the box."
						+ "\nIf you want to uncomplete the task press the "
						+ "\n'Uncomplete Task button");
			}		
		});
		btnHelp.setBounds(23, 254, 89, 23);
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
		
		//JTextfield for task ID
		txtTaskID = new JTextField();		
		txtTaskID.setBounds(169, 0, 116, 22);
		frmTaskLogging.getContentPane().add(txtTaskID);
		txtTaskID.setColumns(10);
		//Hides the JTextfield 
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
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to Edit this task?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    //If, the user selects the 'No' option
				    if (response == JOptionPane.NO_OPTION) {
				    	//Prints nothing
				        System.out.println("");
				    //Else if, the user selects the 'yes' option
				    } else if (response == JOptionPane.YES_OPTION) {
				    	Task updateTask = task;
				    	//Sets the TaskID to the taskID
			    	    updateTask.setTaskID(task.getTaskID());
			    	    //Sets the notes to whatever was in the notes text area
						updateTask.setTaskNotes(txtrNotes.getText());
						//Sets the time completed to the timme in the JSpinner
						updateTask.setTaskTimeCompleted(time);
						//Sets the Task Assigned to the caretakers selcted in the drop down box 
						updateTask.setTaskAssigned((String) comboCaretakerName.getSelectedItem());
						//Updates the datebasee with the new data
						db.updateTask(updateTask);
						//Prints Task has been logged
				    	System.out.println("Task updated");					
				    	//Hides this JFrame
				    	frmTaskLogging.setVisible(false); 
						//Uses Main to pull the tasks form the database again, so table is up to date
				    	Main.tasks = (ArrayList<Task>) db.pullTasks();
				    	// If the User has there account type as Caretaker, run code
				    	if(Main.user.getAccountType().equals("Caretaker"))
						  {
						  	//Opens new CompleteTasks
						    new CompleteTasks();
						}
				    	//Else, Must be Administrator 
						else {
						  	//Opens new taskInformation
						    new taskInformation();
						}		
				    } 
			}
		});
		btnSubmit.setBounds(169, 255, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
		
		//JButton for uncompleting a task
		JButton btnUncomplete = new JButton("Uncomplete");
		btnUncomplete.addActionListener(new ActionListener() {
			//Action for when button is clicked
			public void actionPerformed(ActionEvent e) {
				//Sets the task to taskCompleted to false
				task.setTaskCompleted(false);
				//Sets the the time it was completed to 0
				task.setTaskTimeCompleted(0);
				//Updates the database
				db.updateTask(task);
				//Hides the frame
				frmTaskLogging.setVisible(false); 
				//Uses Main to pull the tasks form the database again, so table is up to date
		    	Main.tasks = (ArrayList<Task>) db.pullTasks();
		    	// If the User has there account type as Caretaker, run code
		    	if(Main.user.getAccountType().equals("Caretaker"))
				  {
				  	//Opens new CompleteTasks
				    new CompleteTasks();
				}
		    	//Else, Must be Administrator 
				else {
				  	//Opens new taskInformation
				    new taskInformation();
				}	
			}
		});
		btnUncomplete.setBounds(169, 217, 116, 25);
		frmTaskLogging.getContentPane().add(btnUncomplete);
		
		//JLabel for uncompletedTasks
		JLabel lblUncompleteTask = new JLabel("Uncomplete task:");
		//Sets the text to the right side
		lblUncompleteTask.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUncompleteTask.setBounds(23, 221, 134, 16);
		frmTaskLogging.getContentPane().add(lblUncompleteTask);
	}
} 