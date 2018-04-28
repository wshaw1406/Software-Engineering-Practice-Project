package software_eng;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class usersInformation {

	private JFrame frame;
	private JTable userTable;    

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
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Database db = new Database();
		List<User> users = db.pullUsers();
		String[] userList = new String[users.size()];
		int i = 0;
		for(User user: users)
		{
			userList[i] = user.getUsername();
			i++;
		}
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addUserGUI();
				frame.setVisible(false);
			}
		});
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
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new editUserGUI();
				frame.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(395, 153, 92, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Remove");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new removeUserGUI();
				frame.setVisible(false);
			}
		});
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
		lblUsers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsers.setBounds(100, 53, 275, 14);
		frame.getContentPane().add(lblUsers);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 242, 177);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 222, 157);
		panel.add(scrollPane);
				
		userTable = new JTable();
		scrollPane.setViewportView(userTable);

		userTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"UserID", "Firstname", "Surname"
			}
		){ 
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			};		
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		userTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		userTable.getColumnModel().getColumn(0).setMinWidth(30);
		userTable.getColumnModel().getColumn(0).setMaxWidth(30);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(79);
		
		DefaultTableModel model = (DefaultTableModel) userTable.getModel();
		
		for(User user: users) {
			model.addRow(new Object[]{user.getUserID(), user.getFirstName(), user.getSurname()});
    	}
		
		JButton button_3 = new JButton("Back");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new adminGUI();
			}
		});
		button_3.setBounds(416, 11, 89, 23);
		frame.getContentPane().add(button_3);
	}
}