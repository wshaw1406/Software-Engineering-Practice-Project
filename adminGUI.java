import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class adminGUI {

	private JFrame frmAdministration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminGUI window = new adminGUI();
					window.frmAdministration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public adminGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//new jframe variable
		frmAdministration = new JFrame();
		frmAdministration.setTitle("Administration");
		frmAdministration.setBounds(100, 100, 580, 381);
		frmAdministration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministration.getContentPane().setLayout(null);
		
		//log out button
		JButton btnLogout = new JButton("Log-out");
		btnLogout.addActionListener(new ActionListener() {
			//action listener that asks if user is sure 
			public void actionPerformed(ActionEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to log out", "log out", 
                        JOptionPane.YES_NO_OPTION); 
				//if they choose yes run main which runs log in ui
				if (choice == JOptionPane.YES_OPTION) {
					frmAdministration.setVisible(false);
					new Main();
				}				
			}
		});
		btnLogout.setBounds(465, 11, 89, 23);
		frmAdministration.getContentPane().add(btnLogout);
		
		//users information button
		JButton btnUsers = new JButton("User Information");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//when pressed it takes you to new usersInformaiton ui
				//sets current page to not visible
				frmAdministration.setVisible(false);
				new usersInformation();
			}			
		});
		btnUsers.setBounds(55, 129, 167, 72);
		frmAdministration.getContentPane().add(btnUsers);
		
		//task information button
		JButton btnTaskInformation = new JButton("Task Information");
		btnTaskInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set current page to not visible
				//open a new task information page
				frmAdministration.setVisible(false);
				new taskInformation();
			}
		});
		btnTaskInformation.setBounds(326, 129, 167, 72);
		frmAdministration.getContentPane().add(btnTaskInformation);
		
		//welcome label 
		JLabel welcomeLbl = new JLabel("");
		welcomeLbl.setBounds(24, 15, 65, 14);
		frmAdministration.getContentPane().add(welcomeLbl);
		welcomeLbl.setText("Welcome, ");
		
		//label that calls the user's get username and displays it
		JLabel usernameLbl = new JLabel("");
		usernameLbl.setBounds(84, 15, 89, 14);
		frmAdministration.getContentPane().add(usernameLbl);
		usernameLbl.setText(Main.user.getUsername());
		frmAdministration.setVisible(true);
		
	}
}