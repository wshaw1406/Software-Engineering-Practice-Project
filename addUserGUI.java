import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

	private JFrame frmAddNewUser;
	private JTextField firstnameField;
	private JTextField surnameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		User user = new User();
		frmAddNewUser = new JFrame();		
		frmAddNewUser.setTitle("Add New User");
		frmAddNewUser.setBounds(100, 100, 450, 426);
		frmAddNewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewUser.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("* First name");
		lblNewLabel.setBounds(59, 103, 76, 14);
		frmAddNewUser.getContentPane().add(lblNewLabel);
		
		JLabel validation = new JLabel("");
		validation.setBounds(182, 147, 129, 23);
		frmAddNewUser.getContentPane().add(validation);		
		
		JLabel validation2 = new JLabel("");
		validation2.setBounds(182, 291, 516, 14);
		frmAddNewUser.getContentPane().add(validation2);
		
		firstnameField = new JTextField();
		firstnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
			}
		});
		firstnameField.setBounds(182, 103, 184, 20);
		frmAddNewUser.getContentPane().add(firstnameField);
		firstnameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("* Surname");
		lblSurname.setBounds(59, 139, 76, 14);
		frmAddNewUser.getContentPane().add(lblSurname);
		
		surnameField = new JTextField();
		surnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
					String userID = Integer.toString(user.getUserID());
					usernameField.setText(surnameField.getText()+userIDField.getText());			}
		});
		surnameField.setColumns(10);
		surnameField.setBounds(182, 139, 184, 20);
		frmAddNewUser.getContentPane().add(surnameField);
		
		JLabel lblAccountType = new JLabel("* Account Type");
		lblAccountType.setBounds(59, 173, 110, 14);
		frmAddNewUser.getContentPane().add(lblAccountType);
		
		JComboBox accountTypeBox = new JComboBox();
		accountTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Administrator"}));
		accountTypeBox.setBounds(181, 170, 121, 20);
		frmAddNewUser.getContentPane().add(accountTypeBox);		
		
		JComboBox genderBox = new JComboBox();
		genderBox.setToolTipText("");
		genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female" }));
		genderBox.setBounds(181, 205, 121, 20);
		frmAddNewUser.getContentPane().add(genderBox);	
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(59, 242, 76, 14);
		frmAddNewUser.getContentPane().add(lblUsername);
		
		JLabel lblGender = new JLabel("* Gender");
		lblGender.setBounds(59, 208, 76, 14);
		frmAddNewUser.getContentPane().add(lblGender);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Database db = new Database();
				User newUser = new User();	
				int userID = Integer.parseInt(userIDField.getText());
				newUser.setFirstName(firstnameField.getText());
				newUser.setSurname(surnameField.getText());
				newUser.setAccountType((String) accountTypeBox.getSelectedItem());
				newUser.setGender((String) genderBox.getSelectedItem());
				newUser.setUsername(usernameField.getText());
				newUser.setUserID(userID);
				db.pushSingleUser(newUser);
			}
		});
		btnSubmit.setEnabled(false);

		btnSubmit.setBounds(234, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnSubmit);	
		
		usernameField = new JTextField();		
		usernameField.setEditable(false);
		usernameField.setColumns(10);
		usernameField.setBounds(182, 239, 184, 20);
		frmAddNewUser.getContentPane().add(usernameField);
		
		JLabel lblPassword = new JLabel("* Password");
		lblPassword.setBounds(59, 277, 76, 14);
		frmAddNewUser.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		String p2 = passwordField.getText();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().length() < 8){
				     validation2.setText("Password must be 8 or more characters");
					 btnSubmit.setEnabled(false);

			} else {
				validation2.setText("");
			}
			}
		});		
		passwordField.setBounds(182, 274, 184, 20);
		frmAddNewUser.getContentPane().add(passwordField);		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//
				System.exit(0);
			}			
		});
		
		btnCancel.setBounds(119, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnCancel);
		JLabel lblPleaseFillIn = new JLabel("Please fill in the form the press submit to add the user");
		lblPleaseFillIn.setBounds(10, 11, 324, 14);
		frmAddNewUser.getContentPane().add(lblPleaseFillIn);
		
		JLabel lblreEnterPassword = new JLabel("* Re-enter \r\nPassword");
		lblreEnterPassword.setBounds(59, 310, 121, 14);
		frmAddNewUser.getContentPane().add(lblreEnterPassword);
		
		JLabel lblTheseFields = new JLabel("* These fields are required");
		lblTheseFields.setBounds(10, 35, 254, 14);
		frmAddNewUser.getContentPane().add(lblTheseFields);
		
		JLabel rePassVal = new JLabel("");
		rePassVal.setBounds(182, 329, 398, 14);
		frmAddNewUser.getContentPane().add(rePassVal);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().equals(passwordField_1.getText())){
					rePassVal.setText("");
					passMatch = true;
				}
				else{
					rePassVal.setText("Passwords must match");
					passMatch = false;
				}
			}
		});
		passwordField_1.setBounds(182, 305, 184, 20);
		frmAddNewUser.getContentPane().add(passwordField_1);
		
		int userID = User.generateUserID();
		String userIDString = Integer.toString(userID);
		
		userIDField = new JTextField();
		userIDField.setText(userIDString);
		userIDField.setEditable(false);
		userIDField.setBounds(182, 70, 184, 20);
		frmAddNewUser.getContentPane().add(userIDField);
		userIDField.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(63, 73, 46, 14);
		frmAddNewUser.getContentPane().add(lblUserId);
		
		frmAddNewUser.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(firstnameField.getText().equals("") || surnameField.getText().equals("") || passMatch == false){
					btnSubmit.setEnabled(false);
					}
				else{btnSubmit.setEnabled(true);}
			}
		  });
		}	
	}

