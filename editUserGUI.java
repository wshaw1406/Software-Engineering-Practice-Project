package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class editUserGUI {

	//variable initialise
	private JFrame frmEditUserGUI;
	private JTable table;
	private JTextField firstnameField;
	private JTextField surnameField;
	private JTextField usernameField;
	private Database db;
	private JTextField textField_3;
	private boolean valid;
	boolean resetPass =false;
	private JTextField userIDField;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editUserGUI window = new editUserGUI();
					window.frmEditUserGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the UI window
	 * connect to the database
	 */
	public editUserGUI() {
		db = new Database();
		initialize();
		frmEditUserGUI.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		//initialise the frame
		frmEditUserGUI = new JFrame();
		frmEditUserGUI.setBounds(100, 100, 487, 441);
		frmEditUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditUserGUI.setTitle("Edit user");
		
		//create the panel to store other ui elements
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmEditUserGUI.getContentPane().add(panel, BorderLayout.CENTER);
		
		//firstname textfield to type the first name
		firstnameField = new JTextField();
		firstnameField.setColumns(10);
		//action listener to ensure on letters are input
		firstnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
			}
		});
		firstnameField.setBounds(201, 114, 182, 20);
		panel.add(firstnameField);

		//first name label on the ui
		JLabel label_1 = new JLabel("First name");
		label_1.setBounds(55, 114, 76, 14);
		panel.add(label_1);
		
		//surname label on the ui
		JLabel label_2 = new JLabel("Surname");
		label_2.setBounds(55, 150, 76, 14);
		panel.add(label_2);
		
		//surname textfield where surname will be input
		surnameField = new JTextField();
		surnameField.setColumns(10);
		//action listener to ensure only letters are input
		surnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				    {
				        e.consume();
					}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				usernameField.setText(surnameField.getText()+userIDField.getText());
			}
		});
		surnameField.setBounds(201, 150, 182, 20);
		panel.add(surnameField);
		
		//label for Account type
		JLabel label_4 = new JLabel("Account Type");
		label_4.setBounds(55, 184, 108, 14);
		panel.add(label_4);
		
		//combobox that shows the account types
		JComboBox accountTypeBox = new JComboBox();
		accountTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Administrator"}));
		accountTypeBox.setBounds(200, 181, 128, 20);
		panel.add(accountTypeBox);
		
		//combo box that shows the gender
		JComboBox genderBox = new JComboBox();
		genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		genderBox.setBounds(200, 216, 76, 20);
		panel.add(genderBox);
		
		//label for gender on ui
		JLabel label_5 = new JLabel("Gender");
		label_5.setBounds(55, 219, 108, 14);
		panel.add(label_5);
		
		//label for username
		JLabel label_6 = new JLabel("Username");
		label_6.setBounds(55, 253, 76, 14);
		panel.add(label_6);
		
		//textfield for usernames to be input
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setColumns(10);
		usernameField.setBounds(201, 250, 182, 20);
		panel.add(usernameField);
		
		//cancel button that takes the user back to the user information page
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new usersInformation();
				frmEditUserGUI.setVisible(false);
			}			
		});
		cancel.setBounds(104, 369, 89, 23);
		panel.add(cancel);
		
		//label for the heading at the top of the page to tell users how to edit
		JLabel lblPleaseSelectA = new JLabel("Please select a user to edit then, press submit to change the users information");
		lblPleaseSelectA.setBounds(10, 11, 558, 14);
		panel.add(lblPleaseSelectA);
		
		//label that is blank, but is updated with a confirmation message if a the reset password button is pressed
		JLabel resetConfirm = new JLabel("");
		resetConfirm.setBounds(201, 318, 230, 14);
		panel.add(resetConfirm);
		
		//create a list of users from the database
		List<User> userList = db.pullUsers();
		
		//get all of the usernames for each user
		String[] users = new String[userList.size()];
		int i = 0;
		for(User user: userList) {
			users[i] = user.getUsername();
			i++;
    	}
				
		//fill the drop down box with usernames retrieved from above
		JComboBox userDropDown = new JComboBox();
		//when an action is performed the rest of the fields will be filled with data from the database
		userDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//pull the single user whos username is selected
				User user = db.pullSingleUser((String) userDropDown.getSelectedItem());
				firstnameField.setText(user.getFirstName());
				surnameField.setText(user.getSurname());
				accountTypeBox.setSelectedItem(user.getAccountType());
				String userID = Integer.toString(user.getUserID());
				userIDField.setText(userID);
				usernameField.setText(surnameField.getText()+userIDField.getText());
			}
		});
		userDropDown.setModel(new DefaultComboBoxModel(users));
		userDropDown.setBounds(59, 36, 201, 20);
		userDropDown.setSelectedItem("-");
		panel.add(userDropDown);
		
		//reset button which sets a bool to true, meaning pass will be reset on submit
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			//if button pressed resetPass=true
			public void actionPerformed(ActionEvent arg0) {
				resetPass=true;				
				resetConfirm.setText("Password Reset to 'password'");
			}
		});
		btnReset.setBounds(201, 295, 89, 23);
		panel.add(btnReset);
		
		//submit button
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			//action listener which sets the database fields with information from the form
			public void actionPerformed(ActionEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to change this users details?", "Change details", 
                        JOptionPane.YES_NO_OPTION); 
				//if they choose yes run main which runs log in ui
				if (choice == JOptionPane.YES_OPTION) {							
				//create a new user object
				User updateUser = db.pullSingleUser((String) userDropDown.getSelectedItem());
				//convert the userID string from the textfield into an int
				int userID = Integer.parseInt(userIDField.getText());
				//use setters to update the user object
				updateUser.setFirstName(firstnameField.getText());
				updateUser.setSurname(surnameField.getText());
				updateUser.setAccountType((String) accountTypeBox.getSelectedItem());
				updateUser.setGender((String) genderBox.getSelectedItem());
				updateUser.setUserID(userID);
				updateUser.setUsername(usernameField.getText()); 
				if(resetPass==true){
					//if reset button was pressed, reset the password
					Security.main("password");
					updateUser.setPasswordHash(Security.getHashedPass());
				}
				db.updateUser(updateUser);
				frmEditUserGUI.setVisible(false);
				new usersInformation();
				}
			}
		});
		submit.setBounds(259, 369, 89, 23);
		panel.add(submit);
		
		//textfield for the userID
		userIDField = new JTextField();
		userIDField.setEditable(false);
		userIDField.setBounds(201, 80, 180, 20);
		panel.add(userIDField);
		userIDField.setColumns(10);
		
		
		//label for user ID
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setBounds(55, 83, 76, 14);
		panel.add(lblUserid);
		
		//label for reset password
		JLabel lblResetPassword = new JLabel("Reset Password");
		lblResetPassword.setBounds(55, 299, 108, 14);
		panel.add(lblResetPassword);
		
		JLabel lblNewLabel = new JLabel("Ensure all fields are completed in order to click submit");
		lblNewLabel.setBounds(55, 330, 435, 14);
		panel.add(lblNewLabel);
		
		//mouse movement listener which checks that all of the fields are filled in to prevent empty fields being accidently submitted
		frmEditUserGUI.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//checks that the first name field and surname field are no empty
				if(firstnameField.getText().equals("") || surnameField.getText().equals("")){
					//set the submit button as false if the form is not valid
					submit.setEnabled(false);
					}
				else{submit.setEnabled(true);}
			}
		});		
	}
}

