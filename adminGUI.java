package software_eng;

import java.awt.EventQueue;



import javax.swing.JFrame;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;



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
		frmAdministration.setVisible(true);

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

		

		JButton btnViewUsers = new JButton("View Users");

		btnViewUsers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				frmAdministration.hide();
				new usersInformation();
				//GO TO userInformation Page
			}

		});

		btnViewUsers.setBounds(95, 129, 154, 61);

		frmAdministration.getContentPane().add(btnViewUsers);

		

		JButton btnViewTasks = new JButton("View Tasks");
		btnViewTasks.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				frmAdministration.hide();
				new taskInformation();
			}

		});

		btnViewTasks.setBounds(326, 129, 154, 61);

		frmAdministration.getContentPane().add(btnViewTasks);
		
		JLabel lblUserInfromation = new JLabel("User Infromation");
		lblUserInfromation.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserInfromation.setBounds(95, 111, 154, 16);
		frmAdministration.getContentPane().add(lblUserInfromation);
		
		JLabel lblTaskInfromation = new JLabel("Task Infromation");
		lblTaskInfromation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaskInfromation.setBounds(326, 111, 154, 16);
		frmAdministration.getContentPane().add(lblTaskInfromation);
		
		JButton btnUserReports = new JButton("User Reports");
		btnUserReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAdministration.hide();
				new UserReports();
			}
		});
		btnUserReports.setBounds(95, 239, 154, 61);
		frmAdministration.getContentPane().add(btnUserReports);
		
		JButton btnTaskReports = new JButton("Task Reports");
		btnTaskReports.setBounds(326, 239, 154, 61);
		frmAdministration.getContentPane().add(btnTaskReports);

	}
}