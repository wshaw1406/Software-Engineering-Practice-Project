/* This page logs incompleted tasks from CaretakerSchedule page. */
package software_eng;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;

public class TaskLogging {
	//Defines the private variables and public static
	private JFrame frmTaskLogging;
	private JTextField txtTaskName;
	private JTextField txtTaskID;
	private Task task;
	public static DefaultTableModel model;

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
		//ActionListener for when it is clicked
		btnCancel.addActionListener(new ActionListener(){
			//When JButton is clicked
			public void actionPerformed(ActionEvent e){
				//Prints out No task logged
				System.out.println("No task logged");
				//Sets Frames visibilty to false (hides it)
			    frmTaskLogging.setVisible(false); 
			    if(Main.user.getAccountType().equals("Caretaker"))
			    {
			    	//Opens new CaretakerSchedule2
				    new CaretakerSchedule2();
			    }
			    else {
			    	//Opens new taskInformation
				    new taskInformation();
			    }
			    
			}
		});		
		btnCancel.setBounds(290, 215, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
	
		//Jspinner for TimeOfCompletion 
		JSpinner spinnerTime = new JSpinner();
		//Gets current time
		Date datenow = Calendar.getInstance().getTime();
		SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
		//Displays hour, minute currently
		spinnerTime.setModel(smb);
		JSpinner.DateEditor de_spinnerTime = new JSpinner.DateEditor(spinnerTime, "dd-MM-yyyy HH:mm");
		spinnerTime.setEditor(de_spinnerTime);
		spinnerTime.setBackground(new Color(240, 240, 240));
		spinnerTime.setBounds(169, 83, 134, 22);
		frmTaskLogging.getContentPane().add(spinnerTime);	
		
		//Converts the Date to int for: year, month, day, hours and minutes
		int year = (int) (new Date().getYear());
		int month = (int) (new Date().getMonth());
		int day = (int) (new Date().getDate());
		int hours = (int) (new Date().getHours());
		int minutes = (int) (new Date().getMinutes());
		//Joins the newly converted integers together to create int time 
		int time = Integer.valueOf(String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(hours) + String.valueOf(minutes));
				
		//JComboBox for choosing caretakers
		JComboBox comboCaretakerName = new JComboBox();
		//List of Options of the Caretakers on the System 
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
		
		//JTextField for Task ID
		txtTaskID = new JTextField();		
		txtTaskID.setBounds(169, 0, 116, 22);
		frmTaskLogging.getContentPane().add(txtTaskID);
		txtTaskID.setColumns(10);
		//Hides the JTextField as user doesnt need to see it.
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
				    	//Gets the taskID and sets it as that
			    	    updateTask.setTaskID(task.getTaskID());
				    	//Gets the taskNotes and sets it as that
						updateTask.setTaskNotes(txtrNotes.getText());
				    	//Gets the variable time and sets the taskTimeCompleted as that
						updateTask.setTaskTimeCompleted(time);
						//Gets the selcted caretaker from the combo box and sets the task assigned to that person 
						updateTask.setTaskAssigned((String) comboCaretakerName.getSelectedItem());
						//Sets the the taskCompleted to true
						updateTask.setTaskCompleted(true);
						//Updates the database with the set data
						db.updateTask(updateTask);
						//Prints Task has been logged
				    	System.out.println("Task Logged ");
				    	//If the task that has been logged was regular, run code
						if (task.getTaskType().equals("Regular"))
						{
							//Makes an sql date that is equal to getting the dateDue
					    	java.sql.Date date= task.getDateDue();
					    	//Makes Calender of variable name c
							Calendar c = Calendar.getInstance(); 
							
							Task CreateTask = new Task();
							//Sets the task ID using the Task.java function generateTaskID
							CreateTask.setTaskID(task.generateTaskID());
							//Sets the taskTitle to the same name as the orginal task
							CreateTask.setTaskTitle(txtTaskName.getText());
							//Sets the taskNotes to the same name as the orginal task
							CreateTask.setTaskNotes(txtrNotes.getText());
							//Sets the taskType to Regular
							CreateTask.setTaskType("Regular");
							//Sets the task duration to the same name as the orginal task 
							CreateTask.setTaskDuration(task.getTaskDuration());
							//Sets the taskPriority to the same name as the orginal task 
							CreateTask.setTaskPriority(task.getTaskPriority());		
							//Sets the time to the varaible date
							c.setTime(date); 
							//Adds 7 days on to the Calender variable c
							c.add(Calendar.DATE, 7);
							//Gets the the time in Milliseconds 
							java.sql.Date sqlDate = new java.sql.Date(c.getTimeInMillis());	
							//Sets the DateDue to sqlDate variable
							CreateTask.setDateDue(sqlDate);
							//Sets the taskAssigned to null
							CreateTask.setTaskAssigned(null);
							//Pushes the new task to the database
							db.pushSingleTask(CreateTask);
					    	//Prints  New task created
					    	System.out.println("New task created");
					    }

				    	//Hides this JFrame
				    	frmTaskLogging.setVisible(false); 
				    	// If the User has there account type as Caretaker, run code
				    	if(Main.user.getAccountType().equals("Caretaker"))
						  {
						  	//Opens new CaretakerSchedule2
						    new CaretakerSchedule2();
						}
				    	//Else, Must be Administrator 
						else {
						  	//Opens new taskInformation
						    new taskInformation();
						}						    
				    } 
			}
		});
		btnSubmit.setBounds(169, 215, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
	}
} 