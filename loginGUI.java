import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.util.List;

public class loginGUI extends JFrame {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private Database db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginGUI window = new loginGUI();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public loginGUI() {
		db = new Database();
		initialize();
		frmLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Log-in");
		frmLogin.setBounds(100, 100, 396, 256);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(146, 55, 165, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(64, 56, 80, 14);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(64, 95, 70, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 94, 165, 20);
		frmLogin.getContentPane().add(passwordField);

		List<User> userList = db.pullUsers();		
		String[] users = new String[userList.size()];
		int i = 0;
		for(User user: userList) {
			users[i] = user.getUsername();
			i++;
    	}
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				User user = db.pullSingleUser(textField.getText());
				
				try{
					String retrievedHashedPass = user.getPasswordHash();	
					String enteredPassword = passwordField.getText();
					Security.main(enteredPassword);
						if(user.getUsername().equals(textField.getText()) && Security.getHashedPass().equals(retrievedHashedPass)){
							if(user.getAccountType().equals("Administrator")){
								new adminGUI();
								frmLogin.setVisible(false);
							}
							else{
								new CaretakerSchedule2();
								frmLogin.setVisible(false);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Incorrect Login details");
						}				
					}
					catch(NullPointerException e1){ 
						JOptionPane.showMessageDialog(null, "Incorrect Login details");
					}				
				}			
		});	
		btnSubmit.setBounds(222, 143, 89, 23);
		frmLogin.getContentPane().add(btnSubmit);
		
		JButton btnForgot = new JButton("Forgotten Password");
		btnForgot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ForgottenPassword();		
				frmLogin.setVisible(false);
			}			
		});
		btnForgot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnForgot.setBounds(64, 143, 148, 23);
		frmLogin.getContentPane().add(btnForgot);
	}
}
