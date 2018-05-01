import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		frame.setBounds(100, 100, 583, 362);
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
		button.setBounds(454, 100, 92, 23);
		frame.getContentPane().add(button);
		
		
		JLabel label = new JLabel("Add New User");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(336, 104, 92, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Edit User");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(336, 138, 92, 14);
		frame.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("Edit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new editUserGUI();
				frame.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(454, 134, 92, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Remove");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new removeUserGUI();
				frame.setVisible(false);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBounds(454, 168, 92, 23);
		frame.getContentPane().add(button_2);
		
		
		JLabel label_2 = new JLabel("Remove User");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(336, 172, 92, 14);
		frame.getContentPane().add(label_2);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new UserReports();
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReport.setBounds(454, 202, 92, 23);
		frame.getContentPane().add(btnReport);
		
		JLabel label_3 = new JLabel("Caretaker Report");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(336, 204, 117, 18);
		frame.getContentPane().add(label_3);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsers.setBounds(116, 69, 275, 14);
		frame.getContentPane().add(lblUsers);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 316, 177);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 296, 155);
		panel.add(scrollPane);
				
		userTable = new JTable();
		scrollPane.setViewportView(userTable);

		userTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Firstname", "Surname", "AccountType"
			}
		){ 
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class
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
			model.addRow(new Object[]{user.getUserID(), user.getFirstName(), user.getSurname(), user.getAccountType()});
    	}
		
		JButton button_3 = new JButton("Back");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new adminGUI();
			}
		});
		button_3.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(button_3);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to log out", "log out", 
                        JOptionPane.YES_NO_OPTION); 
				if (choice == JOptionPane.YES_OPTION) {
					frame.setVisible(false);
					new Main();
				}	
			}
		});
		btnLogOut.setBounds(457, 11, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		JLabel currentLbl = new JLabel("");
		currentLbl.setBounds(20, 288, 251, 14);
		currentLbl.setText("Currently logged in: "+ Main.user.getUsername());
		frame.getContentPane().add(currentLbl);
	}
}