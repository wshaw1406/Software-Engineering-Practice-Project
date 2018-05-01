import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;

public class addUserGUI {

	//create new ui variables and database variables for the ui
	private JFrame frmAddNewUser;
	private JTextField firstnameField;
	private JTextField surnameField;
	private JTextField usernameField;
	static JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Database db;
	private boolean passMatch= false;
	private JTextField userIDField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addUserGUI window = new addUserGUI();
					window.frmAddNewUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addUserGUI() {
		initialize();
		frmAddNewUser.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//create a new empty User to add to the database
		User user = new User();
		//create the frame in which all elements will be stored
		frmAddNewUser = new JFrame();		
		frmAddNewUser.setTitle("Add New User");
		frmAddNewUser.setBounds(100, 100, 450, 426);
		frmAddNewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewUser.getContentPane().setLayout(null);
		
		//label to show which box is first name
		JLabel lblNewLabel = new JLabel("* First name");
		lblNewLabel.setBounds(59, 103, 76, 14);
		frmAddNewUser.getContentPane().add(lblNewLabel);
		
		//label which is changed when validation is incorrect
		JLabel validation = new JLabel("");
		validation.setBounds(182, 147, 129, 23);
		frmAddNewUser.getContentPane().add(validation);		
		
		//label which is empty but changed when validation is incorrect
		JLabel validation2 = new JLabel("");
		validation2.setBounds(182, 291, 516, 14);
		frmAddNewUser.getContentPane().add(validation2);
		
		//textfield for first name
		firstnameField = new JTextField();
		firstnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//action listener which checks when a key is typed that it is not a number or character
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
			}
		});
		firstnameField.setBounds(182, 103, 184, 20);
		frmAddNewUser.getContentPane().add(firstnameField);
		firstnameField.setColumns(10);
		
		//label to show the user where to enter surname
		JLabel lblSurname = new JLabel("* Surname");
		lblSurname.setBounds(59, 139, 76, 14);
		frmAddNewUser.getContentPane().add(lblSurname);
		
		//surname textfield
		surnameField = new JTextField();
		surnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c=e.getKeyChar();
				//actionlistener to check that only letters are entered
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
					String userID = Integer.toString(user.getUserID());
					//automatically set the username field with surname + auto generated user ID
					usernameField.setText(surnameField.getText()+userIDField.getText());		
					}
		});
		surnameField.setColumns(10);
		surnameField.setBounds(182, 139, 184, 20);
		frmAddNewUser.getContentPane().add(surnameField);
		
		//label to show where the user where account type is entered
		JLabel lblAccountType = new JLabel("* Account Type");
		lblAccountType.setBounds(59, 173, 110, 14);
		frmAddNewUser.getContentPane().add(lblAccountType);
		
		//combo box to display different types of account type
		JComboBox accountTypeBox = new JComboBox();
		accountTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Administrator"}));
		accountTypeBox.setBounds(181, 170, 121, 20);
		frmAddNewUser.getContentPane().add(accountTypeBox);		
		
		//combobox to show the different types of gender 
		JComboBox genderBox = new JComboBox();
		genderBox.setToolTipText("");
		genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female" }));
		genderBox.setBounds(181, 205, 121, 20);
		frmAddNewUser.getContentPane().add(genderBox);	
		
		//label to show where the username is 
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(59, 242, 76, 14);
		frmAddNewUser.getContentPane().add(lblUsername);
		
		//label to show where the gender is entered
		JLabel lblGender = new JLabel("* Gender");
		lblGender.setBounds(59, 208, 76, 14);
		frmAddNewUser.getContentPane().add(lblGender);
		
		//submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			//action listener which adds all of the entered fields into the new user
			public void actionPerformed(ActionEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to add a user with these details?", "Add user", 
                        JOptionPane.YES_NO_OPTION); 
				//if they choose yes run main which runs log in ui
				if (choice == JOptionPane.YES_OPTION) {		
				Database db = new Database();
				User newUser = new User();	
				//gets the automatically generated userID and turns it into an int
				int userID = Integer.parseInt(userIDField.getText());
				//gets the entered info and gives it to the new user
				newUser.setFirstName(firstnameField.getText());
				newUser.setSurname(surnameField.getText());
				newUser.setAccountType((String) accountTypeBox.getSelectedItem());
				newUser.setGender((String) genderBox.getSelectedItem());
				newUser.setUsername(usernameField.getText());
				newUser.setUserID(userID);
				//hashes the entered password
				Security.main(passwordField.getText());
				newUser.setPasswordHash(Security.getHashedPass());
				//push the new user to the database
				db.pushSingleUser(newUser);
				frmAddNewUser.setVisible(false);
				//create a new users information page
				new usersInformation();
			    }
		    }
		});
		btnSubmit.setEnabled(false);

		btnSubmit.setBounds(234, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnSubmit);	
		
		//uneditable username field 
		usernameField = new JTextField();		
		usernameField.setEditable(false);
		usernameField.setColumns(10);
		usernameField.setBounds(182, 239, 184, 20);
		frmAddNewUser.getContentPane().add(usernameField);
		
		//label to show where the password is entered
		JLabel lblPassword = new JLabel("* Password");
		lblPassword.setBounds(59, 277, 76, 14);
		frmAddNewUser.getContentPane().add(lblPassword);
		
		//password field
		passwordField = new JPasswordField();
		String p2 = passwordField.getText();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//checks that the password is longer than 8 characters
				if (passwordField.getText().length() < 8){
				     //sets the validation label to say the following
				     validation2.setText("Password must be 8 or more characters");
				     //and disable the submit button
					 btnSubmit.setEnabled(false);

			} else {
				//sets validation label to empty if correct
				validation2.setText("");
			}
			}
		});		
		passwordField.setBounds(182, 274, 184, 20);
		String plainTextPass = passwordField.getText();
		frmAddNewUser.getContentPane().add(passwordField);	
		
		//cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//opens previous page
				new usersInformation();
				frmAddNewUser.setVisible(false);
			}			
		});		
		btnCancel.setBounds(119, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnCancel);
		
		//label which tells the user what to do
		JLabel lblPleaseFillIn = new JLabel("Please fill in the form the press submit to add the user");
		lblPleaseFillIn.setBounds(10, 11, 324, 14);
		frmAddNewUser.getContentPane().add(lblPleaseFillIn);
		
		//label to show where to re-enter password
		JLabel lblreEnterPassword = new JLabel("* Re-enter \r\nPassword");
		lblreEnterPassword.setBounds(59, 310, 121, 14);
		frmAddNewUser.getContentPane().add(lblreEnterPassword);
		
		//label that tells the user * fields are required
		JLabel lblTheseFields = new JLabel("* These fields are required");
		lblTheseFields.setBounds(10, 35, 254, 14);
		frmAddNewUser.getContentPane().add(lblTheseFields);
		
		//label that is filled with validation message if needed
		JLabel rePassVal = new JLabel("");
		rePassVal.setBounds(182, 329, 398, 14);
		frmAddNewUser.getContentPane().add(rePassVal);
		
		//re enter password field 
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//needs to equal password that is already entered
				if (passwordField.getText().equals(passwordField_1.getText())){
					rePassVal.setText("");
					passMatch = true;
				}
				else{
					//if passwords dont match
					rePassVal.setText("Passwords must match");
					passMatch = false;
				}
			}
		});
		passwordField_1.setBounds(182, 305, 184, 20);
		frmAddNewUser.getContentPane().add(passwordField_1);
		
		//generates a new user ID and turns it into a string
		int userID = User.generateUserID();
		String userIDString = Integer.toString(userID);
		
		//new userID Field that gets the generated ID
		userIDField = new JTextField();
		userIDField.setText(userIDString);
		userIDField.setEditable(false);
		userIDField.setBounds(182, 70, 184, 20);
		frmAddNewUser.getContentPane().add(userIDField);
		userIDField.setColumns(10);
		
		//label to show which field is user ID
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(63, 73, 46, 14);
		frmAddNewUser.getContentPane().add(lblUserId);
		
		//disables submit button if certain requirements are not met
		frmAddNewUser.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//checks if firstname surname are empty, and that passwords match
				if(firstnameField.getText().equals("") || surnameField.getText().equals("") || passMatch == false){
					btnSubmit.setEnabled(false);
					}
				else{btnSubmit.setEnabled(true);}
			}
		  });
		}	
	}

