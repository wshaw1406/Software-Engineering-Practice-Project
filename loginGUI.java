import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.sql.Connection;

public class loginGUI extends JFrame {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

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
		initialize();
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
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//
				
			}			
		});
		btnSubmit.setBounds(222, 143, 89, 23);
		frmLogin.getContentPane().add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 94, 165, 20);
		frmLogin.getContentPane().add(passwordField);
		
		JButton btnForgot = new JButton("Forgotten Password");
		btnForgot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//
				
			}			
		});
		btnForgot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnForgot.setBounds(64, 143, 148, 23);
		frmLogin.getContentPane().add(btnForgot);
	}
}
