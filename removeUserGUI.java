import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		frmRemoveUserGUI.setBounds(100, 100, 288, 460);
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
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//
				System.exit(0);
			}			
		});
		btnCancel.setBounds(132, 362, 101, 32);
		frmRemoveUserGUI.getContentPane().add(btnCancel);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a user then, press 'Remove'");
		lblPleaseSelectA.setBounds(10, 0, 262, 34);
		frmRemoveUserGUI.getContentPane().add(lblPleaseSelectA);
		
		JLabel lblToDeleteThe = new JLabel(" to delete the User");
		lblToDeleteThe.setBounds(10, 28, 147, 14);
		frmRemoveUserGUI.getContentPane().add(lblToDeleteThe);
		
		JButton button = new JButton("Remove");
		button.setBounds(22, 362, 101, 32);
		frmRemoveUserGUI.getContentPane().add(button);
	}
}
