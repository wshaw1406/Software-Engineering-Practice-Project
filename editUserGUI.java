import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

	private JFrame frmEditUserGUI;
	private JTable table;
	private JTextField firstnameField;
	private JTextField surnameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Database db;
	private JTextField textField_3;
	private boolean passMatch= false;
	private boolean valid;
	private JTextField userIDField;

	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public editUserGUI() {
		db = new Database();
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmEditUserGUI = new JFrame();
		frmEditUserGUI.setBounds(100, 100, 487, 440);
		frmEditUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditUserGUI.setTitle("Edit User");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmEditUserGUI.getContentPane().add(panel, BorderLayout.CENTER);
		
		firstnameField = new JTextField();
		firstnameField.setColumns(10);
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
		
		JLabel label_1 = new JLabel("First name");
		label_1.setBounds(55, 114, 76, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Surname");
		label_2.setBounds(55, 150, 76, 14);
		panel.add(label_2);
		
		surnameField = new JTextField();
		surnameField.setColumns(10);
		surnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
			}
		});
		surnameField.setBounds(201, 150, 182, 20);
		panel.add(surnameField);
		
		JLabel label_4 = new JLabel("Account Type");
		label_4.setBounds(55, 184, 108, 14);
		panel.add(label_4);
		
		JComboBox accountTypeBox = new JComboBox();
		accountTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Admin"}));
		accountTypeBox.setBounds(200, 181, 98, 20);
		panel.add(accountTypeBox);
		
		JComboBox genderBox = new JComboBox();
		genderBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		genderBox.setBounds(200, 216, 98, 20);
		panel.add(genderBox);
		
		JLabel label_5 = new JLabel("Gender");
		label_5.setBounds(55, 219, 108, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Username");
		label_6.setBounds(55, 253, 76, 14);
		panel.add(label_6);
		
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setColumns(10);
		usernameField.setBounds(201, 250, 182, 20);
		panel.add(usernameField);
				
		JLabel validation = new JLabel("");
		validation.setBounds(201, 302, 435, 14);
		panel.add(validation);	
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 285, 182, 20);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().length() < 8){
				     validation.setText("Password must be 8 or more characters");
			} else {
				validation.setText("");
			}
			}
		});
		panel.add(passwordField);
		
		JLabel label_7 = new JLabel("Password");
		label_7.setBounds(55, 288, 76, 14);
		panel.add(label_7);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//
				System.exit(0);
			}			
		});
		cancel.setBounds(100, 363, 89, 23);
		panel.add(cancel);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a user to edit then, press submit to change the users information");
		lblPleaseSelectA.setBounds(10, 11, 558, 14);
		panel.add(lblPleaseSelectA);		
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setBounds(55, 319, 306, 14);
		panel.add(lblReenterPassword);
		
		JLabel rePassVal = new JLabel("");
		rePassVal.setBounds(201, 338, 380, 14);
		panel.add(rePassVal);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User updateUser = new User();	
				updateUser.setFirstName(firstnameField.getText());
				updateUser.setSurname(surnameField.getText());
				updateUser.setAccountType((String) accountTypeBox.getSelectedItem());
				updateUser.setGender((String) genderBox.getSelectedItem());
				updateUser.setPasswordHash("asdfasdf");
				db.updateUser(updateUser);
			}
		});
		submit.setBounds(256, 363, 89, 23);
		panel.add(submit);
				
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
		passwordField_1.setBounds(201, 316, 182, 20);
		panel.add(passwordField_1);		
		
		List<User> userList = db.pullUsers();
		
		String[] users = new String[userList.size()];
		int i = 0;
		for(User user: userList) {
			users[i] = user.getUsername();
			i++;
    	}
		
		JComboBox userDropDown = new JComboBox();
		userDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = db.pullSingleUser((String) userDropDown.getSelectedItem());
				firstnameField.setText(user.getFirstName());
				surnameField.setText(user.getSurname());
				accountTypeBox.setSelectedItem(user.getAccountType());
				genderBox.setSelectedItem(user.getGender());
				String userID = Integer.toString(user.getUserID());
				userIDField.setText(userID);
				usernameField.setText(surnameField.getText()+userIDField.getText());
			}
		});
		userDropDown.setModel(new DefaultComboBoxModel(users));
		userDropDown.setBounds(127, 49, 201, 20);
		panel.add(userDropDown);
		
		userIDField = new JTextField();
		userIDField.setEditable(false);
		userIDField.setBounds(201, 80, 180, 20);
		panel.add(userIDField);
		userIDField.setColumns(10);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setBounds(55, 83, 76, 14);
		panel.add(lblUserid);
		
		frmEditUserGUI.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(firstnameField.getText().equals("") || surnameField.getText().equals("")|| passMatch == false){
					submit.setEnabled(false);
					}
				else{submit.setEnabled(true);}
			}
		});		
	}
}

