import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForgottenPassword {

	private JFrame frmResetPassword;
	private JTextField textField;
	private Database db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgottenPassword window = new ForgottenPassword();
					window.frmResetPassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ForgottenPassword() {
		db = new Database();
		initialize();
		frmResetPassword.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResetPassword = new JFrame();
		frmResetPassword.setTitle("Reset Password");
		frmResetPassword.setBounds(100, 100, 450, 196);
		frmResetPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResetPassword.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(127, 80, 167, 20);
		frmResetPassword.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your username to reset your password");
		lblPleaseEnterYour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPleaseEnterYour.setBounds(57, 23, 338, 55);
		frmResetPassword.getContentPane().add(lblPleaseEnterYour);
		
		JButton btnNewButton = new JButton("Reset\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User updateUser = db.pullSingleUser(textField.getText());
				try{
				if(updateUser.getUsername().equals(textField.getText())){
				Security.main("password");
				updateUser.setPasswordHash(Security.getHashedPass());
				db.updateUser(updateUser);
				JOptionPane.showMessageDialog(null, "Password reset to 'password'");
				new loginGUI();
				frmResetPassword.setVisible(false);
				}
				}
				catch(NullPointerException e1){ 
					JOptionPane.showMessageDialog(null, "username not found");
				}				
			}
		});
		btnNewButton.setBounds(222, 111, 89, 23);
		frmResetPassword.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginGUI();
				frmResetPassword.setVisible(false);
			}
		});
		btnBack.setBounds(106, 111, 89, 23);
		frmResetPassword.getContentPane().add(btnBack);
	}
}
