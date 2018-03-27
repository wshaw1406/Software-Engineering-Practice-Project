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

public class removeUserGUI {

	private JFrame frmRemoveUserGUI;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					removeUserGUI window = new removeUserGUI();
					window.frmRemoveUserGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public removeUserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveUserGUI = new JFrame();
		frmRemoveUserGUI.setTitle("Remove User");
		frmRemoveUserGUI.setBounds(100, 100, 490, 387);
		frmRemoveUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveUserGUI.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 90, 240, 248);
		frmRemoveUserGUI.getContentPane().add(scrollPane);
		
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
		frmRemoveUserGUI.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setBounds(309, 191, 101, 32);
		frmRemoveUserGUI.getContentPane().add(btnNewButton);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a user then, press 'Remove' to delete the User");
		lblPleaseSelectA.setBounds(10, 11, 340, 14);
		frmRemoveUserGUI.getContentPane().add(lblPleaseSelectA);
	}

}
