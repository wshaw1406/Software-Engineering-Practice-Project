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
import java.awt.event.ActionEvent;

public class editUserGUI {

	private JFrame frmEditUserGUI;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTable table_1;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditUserGUI = new JFrame();
		frmEditUserGUI.setTitle("Edit User");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmEditUserGUI.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblStaff = new JLabel("Staff");
		lblStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaff.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblStaff.setBounds(10, 39, 217, 23);
		panel.add(lblStaff);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 73, 217, 248);
		panel.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Firstname", "Surname"
			}
		));
		scrollPane.setViewportView(table_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(398, 43, 170, 20);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("First name");
		label_1.setBounds(276, 43, 76, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Surname");
		label_2.setBounds(276, 79, 76, 14);
		panel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(398, 79, 170, 20);
		panel.add(textField_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1522018800000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spinner.setBounds(398, 113, 121, 20);
		panel.add(spinner);
		
		JLabel label_3 = new JLabel("Date Of Birth");
		label_3.setBounds(276, 116, 76, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Account Type");
		label_4.setBounds(276, 156, 76, 14);
		panel.add(label_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Administrator "}));
		comboBox.setBounds(397, 153, 83, 20);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox_1.setBounds(397, 188, 83, 20);
		panel.add(comboBox_1);
		
		JLabel label_5 = new JLabel("Gender");
		label_5.setBounds(276, 191, 76, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Username");
		label_6.setBounds(276, 225, 76, 14);
		panel.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(398, 222, 170, 20);
		panel.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(398, 257, 170, 20);
		panel.add(passwordField);
		
		JLabel label_7 = new JLabel("Password");
		label_7.setBounds(276, 260, 76, 14);
		panel.add(label_7);
		
		JButton button = new JButton("Cancel");
		button.setBounds(276, 298, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Submit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(398, 298, 89, 23);
		panel.add(button_1);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a user to edit then, press submit to change the users information");
		lblPleaseSelectA.setBounds(10, 11, 441, 14);
		panel.add(lblPleaseSelectA);
		
		
		
	}
}

