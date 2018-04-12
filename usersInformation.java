import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class usersInformation {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usersInformation window = new usersInformation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public usersInformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 537, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Add");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(395, 102, 92, 23);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("Add New User");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(277, 106, 92, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Edit User");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(277, 157, 92, 14);
		frame.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("Edit");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(395, 153, 92, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Remove");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBounds(395, 209, 92, 23);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("Remove User");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(277, 213, 92, 14);
		frame.getContentPane().add(label_2);
		
		JButton btnReport = new JButton("Report");
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReport.setBounds(395, 268, 89, 23);
		frame.getContentPane().add(btnReport);
		
		JLabel label_3 = new JLabel("Caretaker Report");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(277, 270, 117, 18);
		frame.getContentPane().add(label_3);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setBounds(116, 26, 27, 14);
		frame.getContentPane().add(lblUsers);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 46, 242, 331);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 222, 309);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID", "First Name", "Surname"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton button_3 = new JButton("Back");
		button_3.setBounds(422, 11, 89, 23);
		frame.getContentPane().add(button_3);
	}
}
