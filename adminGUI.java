package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

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
		frmAdministration = new JFrame();
		frmAdministration.setTitle("Administration");
		frmAdministration.setBounds(100, 100, 580, 381);
		frmAdministration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministration.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("Log-out");
		btnLogout.setBounds(465, 11, 89, 23);
		frmAdministration.getContentPane().add(btnLogout);
		
		JButton btnUsers = new JButton("User Information");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUsers.setBounds(95, 129, 127, 61);
		frmAdministration.getContentPane().add(btnUsers);
		
		JButton btnTaskInformation = new JButton("Task Information");
		btnTaskInformation.setBounds(326, 129, 127, 61);
		frmAdministration.getContentPane().add(btnTaskInformation);
		frmAdministration.setVisible(true);
	}
}