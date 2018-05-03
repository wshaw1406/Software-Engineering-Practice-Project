package software_eng;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class removeUserGUI {

	//create variables
	private JFrame frmRemoveUserGUI;
	private Database db;
	private JTextField surname;
	private JTextField firstName;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					removeUserGUI window = new removeUserGUI();
					window.frmRemoveUserGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public removeUserGUI() {
		//create new database variable
		db = new Database();
		//create the GUI
		initialize();
		frmRemoveUserGUI.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//initialse the JFrame
		frmRemoveUserGUI = new JFrame();
		//set its title
		frmRemoveUserGUI.setTitle("Remove User");
		//set its bounds
		frmRemoveUserGUI.setBounds(100, 100, 358, 343);
		frmRemoveUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveUserGUI.getContentPane().setLayout(null);
		
		//cancel button with action listener to open previous page and close this one
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//open new userInformation page
				new usersInformation();
				//close current page
				frmRemoveUserGUI.setVisible(false);
			}			
		});
		btnCancel.setBounds(45, 242, 101, 32);
		frmRemoveUserGUI.getContentPane().add(btnCancel);
		
		//label to instruct user
		JLabel lblPleaseSelectA = new JLabel("Please select a user then, press 'Remove'");
		lblPleaseSelectA.setBounds(10, 0, 262, 34);
		frmRemoveUserGUI.getContentPane().add(lblPleaseSelectA);
		
		//label to delete the user
		JLabel lblToDeleteThe = new JLabel(" to delete the User");
		lblToDeleteThe.setBounds(10, 28, 147, 14);
		frmRemoveUserGUI.getContentPane().add(lblToDeleteThe);
		
		//textfield to display the username
		usernameField = new JTextField();
		//set is as uneditable as it is only here to show who is being deleted
		usernameField.setEditable(false);
		usernameField.setBounds(168, 103, 132, 20);
		frmRemoveUserGUI.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		//textfield to display users firstname
		firstName = new JTextField();
		//set firstname to not be editable
		firstName.setEditable(false);
		firstName.setBounds(168, 146, 132, 20);
		frmRemoveUserGUI.getContentPane().add(firstName);
		firstName.setColumns(10);
		
		//textfield to display the users surname
		surname = new JTextField();
		//set its editable to false
		surname.setEditable(false);
		surname.setBounds(168, 187, 132, 20);
		frmRemoveUserGUI.getContentPane().add(surname);
		surname.setColumns(10);
		
		//label to show which textfield is username
		JLabel lblUserId = new JLabel("Username");
		lblUserId.setBounds(45, 106, 210, 14);
		frmRemoveUserGUI.getContentPane().add(lblUserId);
		
		//label to show which textfield is firstname
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(45, 149, 233, 14);
		frmRemoveUserGUI.getContentPane().add(lblFirstName);
		
		//label to show which textfield is surname
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(45, 190, 173, 14);
		frmRemoveUserGUI.getContentPane().add(lblSurname);
		
		//create a list which holds all users
		List<User> test = db.pullUsers();
		
		//for all of the users, get their username
		String[] users = new String[test.size()];
		int i = 0;
		for(User user: test) {
			users[i] = user.getUsername();
			i++;
    	}
		
		//a combobox which shows all of the usernames in the system
		//as these are unique rather than showing the user first names 
		JComboBox dropDown = new JComboBox();
		dropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//action listener which sets the textfields with information depending on which user is selected
				// in the drop down box
				User user = db.pullSingleUser((String) dropDown.getSelectedItem());
				String userID = String.valueOf(user.getUserID());
				usernameField.setText(user.getUsername());
				firstName.setText(user.getFirstName());
				surname.setText(user.getSurname());
			}
		});
		dropDown.setBounds(25, 53, 162, 20);
		dropDown.setModel(new DefaultComboBoxModel(users));
		frmRemoveUserGUI.getContentPane().add(dropDown);
		
		//button to remove users
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//option pane which makes sure the user wants to delete a user
				int choice = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to PERMANANTLY DELETE this user", "Remove user", 
                        JOptionPane.YES_NO_OPTION); 
				//if yes then pull the user that will be deleted from the database
				if (choice == JOptionPane.YES_OPTION) {
					User removeUser = db.pullSingleUser((String)dropDown.getSelectedItem());
					//call delete method from the database
					db.deleteUser(removeUser.getUsername());
					frmRemoveUserGUI.setVisible(false);
					new usersInformation();
				}								
			}
		});		
		removeButton.setBounds(199, 242, 101, 32);
		frmRemoveUserGUI.getContentPane().add(removeButton);
	}
}
