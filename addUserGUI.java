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

public class addUserGUI {

	private JFrame frmAddNewUser;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	

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
		lblNewLabel.setBounds(59, 60, 76, 14);
		frmAddNewUser.getContentPane().add(lblNewLabel);
		
		JLabel validation = new JLabel("");
		validation.setBounds(182, 147, 129, 23);
		frmAddNewUser.getContentPane().add(validation);		
		
		JLabel validation2 = new JLabel("");
		validation2.setBounds(182, 291, 252, 14);
		frmAddNewUser.getContentPane().add(validation2);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
			}
		});		
		textField.setBounds(182, 60, 170, 20);
		frmAddNewUser.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSurname = new JLabel("* Surname");
		lblSurname.setBounds(59, 96, 76, 14);
		frmAddNewUser.getContentPane().add(lblSurname);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				    if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE)|| c==KeyEvent.VK_DELETE ))
				        e.consume();
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(182, 96, 170, 20);
		frmAddNewUser.getContentPane().add(textField_1);
		
		JLabel lblDateOfBirth = new JLabel("* Date of Birth");
		lblDateOfBirth.setBounds(59, 133, 121, 14);
		frmAddNewUser.getContentPane().add(lblDateOfBirth);
		
		JLabel lblAccountType = new JLabel("* Account Type");
		lblAccountType.setBounds(59, 173, 110, 14);
		frmAddNewUser.getContentPane().add(lblAccountType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Administrator"}));
		comboBox.setBounds(181, 170, 121, 20);
		frmAddNewUser.getContentPane().add(comboBox);
		
		JLabel lblUsername = new JLabel("* Username");
		lblUsername.setBounds(59, 242, 76, 14);
		frmAddNewUser.getContentPane().add(lblUsername);
		
		JLabel lblGender = new JLabel("* Gender");
		lblGender.setBounds(59, 208, 76, 14);
		frmAddNewUser.getContentPane().add(lblGender);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("");
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Not specified"}));
		comboBox_1.setBounds(181, 205, 121, 20);
		frmAddNewUser.getContentPane().add(comboBox_1);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textField_2.getText().length() >= 12)
		            e.consume();
			}
		});		
		textField_2.setColumns(10);
		textField_2.setBounds(182, 239, 170, 20);
		frmAddNewUser.getContentPane().add(textField_2);
		
		JLabel lblPassword = new JLabel("* Password");
		lblPassword.setBounds(59, 277, 76, 14);
		frmAddNewUser.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		String p2 = passwordField.getText();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (passwordField.getText().length() <= 5){
				     validation2.setText("Password must be more than 6 characters");
			} else {
				validation2.setText("");
			}
			}
		});
		
		passwordField.setBounds(182, 274, 170, 20);
		frmAddNewUser.getContentPane().add(passwordField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(234, 354, 89, 23);
		frmAddNewUser.getContentPane().add(btnSubmit);
		
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
		
		JLabel validation3 = new JLabel("");
		validation3.setBounds(182, 329, 347, 14);
		frmAddNewUser.getContentPane().add(validation3);
		
		passwordField_1 = new JPasswordField();
		String p1 = passwordField_1.getText();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (p2 == p1){
				     validation3.setText("Passwords do not match");
			} else {
				validation3.setText("");
			}
			}
		});		
		passwordField_1.setBounds(182, 305, 170, 20);
		frmAddNewUser.getContentPane().add(passwordField_1);	
		
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"}));
		comboBox_2.setBounds(288, 130, 64, 20);
		frmAddNewUser.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"}));
		comboBox_3.setBounds(236, 130, 44, 20);
		frmAddNewUser.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_4.setBounds(182, 130, 44, 20);
		frmAddNewUser.getContentPane().add(comboBox_4);


		String day = String.valueOf(comboBox_4.getSelectedItem());
		String month= String.valueOf(comboBox_3.getSelectedItem());
		String year= String.valueOf(comboBox_2.getSelectedItem());
		String DOB= year+ "" + month + ""+ day;
		}
	}

