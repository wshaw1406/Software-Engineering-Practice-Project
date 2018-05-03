package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChangePassword {

	private JFrame frmChangePassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Database db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword();
					window.frmChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangePassword() {
		db = new Database();
		initialize();
		frmChangePassword.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangePassword = new JFrame();
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 412, 191);
		frmChangePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		JLabel passVal = new JLabel("");
		passVal.setBounds(161, 87, 225, 14);
		frmChangePassword.getContentPane().add(passVal);
		
		JLabel passLen = new JLabel("");
		passLen.setBounds(161, 44, 206, 14);
		frmChangePassword.getContentPane().add(passLen);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User updateUser = db.pullSingleUser(ForgottenPassword.textField.getText());
				Security.main(passwordField_1.getText());
				updateUser.setPasswordHash(Security.getHashedPass());
				db.updateUser(updateUser);
				frmChangePassword.setVisible(false);
				ForgottenPassword.frmResetPassword.setVisible(false);
				new loginGUI();
			}
		});
		btnSubmit.setBounds(212, 108, 101, 23);
		frmChangePassword.getContentPane().add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				 if (passwordField.getText().length() < 8){
			     passLen.setText("Password must be 8 or more characters");
				 btnSubmit.setEnabled(false);
				 } else {
					 passLen.setText("");
				 }
			}	
			});
		passwordField.setBounds(171, 25, 196, 20);
		frmChangePassword.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().equals(passwordField_1.getText())){
					passVal.setText("");
					btnSubmit.setEnabled(true);
				}
				else{
					passVal.setText("Passwords must match");
					btnSubmit.setEnabled(false);
				}
			}
		});
		passwordField_1.setBounds(171, 68, 196, 20);
		frmChangePassword.getContentPane().add(passwordField_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(49, 28, 102, 14);
		frmChangePassword.getContentPane().add(lblPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setBounds(50, 71, 206, 14);
		frmChangePassword.getContentPane().add(lblReenterPassword);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginGUI();
				frmChangePassword.setVisible(false);
			}
		});
		btnCancel.setBounds(87, 108, 89, 23);
		frmChangePassword.getContentPane().add(btnCancel);
	}
}
