import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class removeUser {

	private JFrame frmRemoveUser;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					removeUser window = new removeUser();
					window.frmRemoveUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public removeUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveUser = new JFrame();
		frmRemoveUser.setTitle("Remove User");
		frmRemoveUser.setBounds(100, 100, 490, 387);
		frmRemoveUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveUser.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 90, 240, 248);
		frmRemoveUser.getContentPane().add(scrollPane);
		
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
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"User ID", "Firstname", "Surname"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Staff");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label.setBounds(10, 56, 240, 23);
		frmRemoveUser.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setBounds(309, 191, 101, 32);
		frmRemoveUser.getContentPane().add(btnNewButton);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a user then, press 'Remove' to delete the User");
		lblPleaseSelectA.setBounds(10, 11, 340, 14);
		frmRemoveUser.getContentPane().add(lblPleaseSelectA);
	}

}
