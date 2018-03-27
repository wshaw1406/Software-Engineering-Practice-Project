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
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class addUserGUI {

	private JFrame frmAddNewUser;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

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
		frmAddNewUser = new JFrame();
		frmAddNewUser.setTitle("Add New User");
		frmAddNewUser.setBounds(100, 100, 450, 426);
		frmAddNewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewUser.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("* First name");
		lblNewLabel.setBounds(60, 60, 76, 14);
		frmAddNewUser.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(182, 60, 170, 20);
		frmAddNewUser.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSurname = new JLabel("* Surname");
		lblSurname.setBounds(60, 96, 76, 14);
		frmAddNewUser.getContentPane().add(lblSurname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(182, 96, 170, 20);
		frmAddNewUser.getContentPane().add(textField_1);
		
		JLabel lblDateOfBirth = new JLabel("* Date of Birth");
		lblDateOfBirth.setBounds(60, 133, 76, 14);
		frmAddNewUser.getContentPane().add(lblDateOfBirth);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinner.setModel(new SpinnerDateModel(new Date(1522018800000L), new Date(-2208988800000L), new Date(1522018800000L), Calendar.DAY_OF_YEAR));
		spinner.setBounds(182, 130, 121, 20);
		frmAddNewUser.getContentPane().add(spinner);
		
		JLabel lblAccountType = new JLabel("* Account Type");
		lblAccountType.setBounds(60, 173, 76, 14);
		frmAddNewUser.getContentPane().add(lblAccountType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Administrator"}));
		comboBox.setBounds(181, 170, 83, 20);
		frmAddNewUser.getContentPane().add(comboBox);
		
		JLabel lblUsername = new JLabel("* Username");
		lblUsername.setBounds(60, 242, 76, 14);
		frmAddNewUser.getContentPane().add(lblUsername);
		
		JLabel lblGender = new JLabel("* Gender");
		lblGender.setBounds(60, 208, 76, 14);
		frmAddNewUser.getContentPane().add(lblGender);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("");
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Not specified"}));
		comboBox_1.setBounds(181, 205, 83, 20);
		frmAddNewUser.getContentPane().add(comboBox_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(182, 239, 170, 20);
		frmAddNewUser.getContentPane().add(textField_2);
		
		JLabel lblPassword = new JLabel("* Password");
		lblPassword.setBounds(60, 277, 76, 14);
		frmAddNewUser.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 274, 170, 20);
		frmAddNewUser.getContentPane().add(passwordField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(234, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(119, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnCancel);
		
		JLabel lblPleaseFillIn = new JLabel("Please fill in the form the press submit to add the user");
		lblPleaseFillIn.setBounds(10, 11, 324, 14);
		frmAddNewUser.getContentPane().add(lblPleaseFillIn);
		
		JLabel lblreEnterPassword = new JLabel("* Re-enter \r\nPassword");
		lblreEnterPassword.setBounds(60, 310, 121, 14);
		frmAddNewUser.getContentPane().add(lblreEnterPassword);
		
		JLabel lblTheseFields = new JLabel("* These fields are required");
		lblTheseFields.setBounds(10, 35, 254, 14);
		frmAddNewUser.getContentPane().add(lblTheseFields);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(182, 305, 170, 20);
		frmAddNewUser.getContentPane().add(passwordField_1);
		
		
	}
}
