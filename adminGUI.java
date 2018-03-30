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
		
		JButton btnSubmit = new JButton("Add");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit.setBounds(153, 74, 92, 23);
		frmAdministration.getContentPane().add(btnSubmit);
		
		JLabel lblAddNewUser = new JLabel("Add New User");
		lblAddNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddNewUser.setBounds(29, 77, 92, 14);
		frmAdministration.getContentPane().add(lblAddNewUser);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(153, 125, 92, 23);
		frmAdministration.getContentPane().add(btnEdit);
		
		JLabel lblEditUser = new JLabel("Edit User");
		lblEditUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditUser.setBounds(29, 128, 92, 14);
		frmAdministration.getContentPane().add(lblEditUser);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemove.setBounds(153, 181, 92, 23);
		frmAdministration.getContentPane().add(btnRemove);
		
		JLabel lblRemoveUser = new JLabel("Remove User");
		lblRemoveUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRemoveUser.setBounds(29, 184, 92, 14);
		frmAdministration.getContentPane().add(lblRemoveUser);
		
		JLabel lblAddNewTask = new JLabel("Add New Task");
		lblAddNewTask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddNewTask.setBounds(306, 79, 92, 14);
		frmAdministration.getContentPane().add(lblAddNewTask);
		
		JButton addUser = new JButton("Add");
		addUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addUser.setBounds(417, 75, 92, 23);
		frmAdministration.getContentPane().add(addUser);
		
		JLabel lblEditTask = new JLabel("Edit Task");
		lblEditTask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditTask.setBounds(306, 130, 92, 14);
		frmAdministration.getContentPane().add(lblEditTask);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit_1.setBounds(417, 126, 92, 23);
		frmAdministration.getContentPane().add(btnEdit_1);
		
		JButton button_1 = new JButton("Remove");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(417, 181, 92, 23);
		frmAdministration.getContentPane().add(button_1);
		
		JLabel lblRemoveTask = new JLabel("Remove Task");
		lblRemoveTask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRemoveTask.setBounds(306, 184, 92, 14);
		frmAdministration.getContentPane().add(lblRemoveTask);
		
		JLabel assignTask = new JLabel("Assign Task");
		assignTask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		assignTask.setBounds(306, 225, 92, 17);
		frmAdministration.getContentPane().add(assignTask);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Open window that will show all the available tasks, will have a dropdown 
				//beside it that will have all emp names, they can chose one and then submit
				new AvailableTasks();
			}
		});
		btnAssign.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAssign.setBounds(417, 225, 92, 23);
		frmAdministration.getContentPane().add(btnAssign);
		
		
		JButton btnLogout = new JButton("Log-out");
		btnLogout.setBounds(465, 11, 89, 23);
		frmAdministration.getContentPane().add(btnLogout);
		
		JButton btnNewButton = new JButton("Caretaker");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(153, 265, 89, 23);
		frmAdministration.getContentPane().add(btnNewButton);
		
		JButton btnTasks = new JButton("Tasks");
		btnTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTasks.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTasks.setBounds(420, 265, 89, 23);
		frmAdministration.getContentPane().add(btnTasks);
		
		JLabel lblReports = new JLabel("Caretaker Report");
		lblReports.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReports.setBounds(26, 266, 117, 18);
		frmAdministration.getContentPane().add(lblReports);
		
		JLabel lblTaskReport = new JLabel("Task Report");
		lblTaskReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTaskReport.setBounds(306, 264, 92, 23);
		frmAdministration.getContentPane().add(lblTaskReport);
	}

}
