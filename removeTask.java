import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class removeTask {

	private JFrame frmRemoveTask;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					removeTask window = new removeTask();
					window.frmRemoveTask.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public removeTask() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveTask = new JFrame();
		frmRemoveTask.setTitle("Remove Task");
		frmRemoveTask.setBounds(100, 100, 481, 370);
		frmRemoveTask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveTask.getContentPane().setLayout(null);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a task then, press 'Remove' to delete the Task");
		lblPleaseSelectA.setBounds(10, 11, 340, 14);
		frmRemoveTask.getContentPane().add(lblPleaseSelectA);
		
		JLabel lblTask = new JLabel("Task");
		lblTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblTask.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTask.setBounds(10, 39, 240, 23);
		frmRemoveTask.getContentPane().add(lblTask);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 73, 240, 248);
		frmRemoveTask.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"ID", "Name"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Remove");
		button.setBounds(322, 154, 101, 32);
		frmRemoveTask.getContentPane().add(button);
	}

}
