import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
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
		db = new Database();
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRemoveUserGUI = new JFrame();
		frmRemoveUserGUI.setTitle("Remove User");
		frmRemoveUserGUI.setBounds(100, 100, 358, 343);
		frmRemoveUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveUserGUI.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//
				System.exit(0);
			}			
		});
		btnCancel.setBounds(45, 242, 101, 32);
		frmRemoveUserGUI.getContentPane().add(btnCancel);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a user then, press 'Remove'");
		lblPleaseSelectA.setBounds(10, 0, 262, 34);
		frmRemoveUserGUI.getContentPane().add(lblPleaseSelectA);
		
		JLabel lblToDeleteThe = new JLabel(" to delete the User");
		lblToDeleteThe.setBounds(10, 28, 147, 14);
		frmRemoveUserGUI.getContentPane().add(lblToDeleteThe);
		
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setBounds(168, 103, 132, 20);
		frmRemoveUserGUI.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		firstName = new JTextField();
		firstName.setEditable(false);
		firstName.setBounds(168, 146, 132, 20);
		frmRemoveUserGUI.getContentPane().add(firstName);
		firstName.setColumns(10);
		
		surname = new JTextField();
		surname.setEditable(false);
		surname.setBounds(168, 187, 132, 20);
		frmRemoveUserGUI.getContentPane().add(surname);
		surname.setColumns(10);
		
		JLabel lblUserId = new JLabel("Username");
		lblUserId.setBounds(45, 106, 210, 14);
		frmRemoveUserGUI.getContentPane().add(lblUserId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(45, 149, 233, 14);
		frmRemoveUserGUI.getContentPane().add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(45, 190, 173, 14);
		frmRemoveUserGUI.getContentPane().add(lblSurname);
		
		List<User> test = db.pullUsers();
		
		String[] users = new String[test.size()];
		int i = 0;
		for(User user: test) {
			users[i] = user.getUsername();
			i++;
    	}
				
		JComboBox dropDown = new JComboBox();
		dropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User removeUser = db.pullSingleUser((String)dropDown.getSelectedItem());
				db.deleteUser(removeUser.getUsername());
				frmRemoveUserGUI.setVisible(false);
				new usersInformation();
			}
		});		
		removeButton.setBounds(199, 242, 101, 32);
		frmRemoveUserGUI.getContentPane().add(removeButton);
	}
}
