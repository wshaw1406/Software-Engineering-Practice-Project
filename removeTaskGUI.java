package software_eng;

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

public class removeTaskGUI {

	private JFrame frmRemoveTaskGUI;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					removeTaskGUI window = new removeTaskGUI();
					window.frmRemoveTaskGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public removeTaskGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRemoveTaskGUI = new JFrame();
		frmRemoveTaskGUI.setTitle("Remove Task");
		frmRemoveTaskGUI.setBounds(100, 100, 481, 370);
		frmRemoveTaskGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveTaskGUI.getContentPane().setLayout(null);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a task then, press 'Remove' to delete the Task");
		lblPleaseSelectA.setBounds(10, 11, 445, 14);
		frmRemoveTaskGUI.getContentPane().add(lblPleaseSelectA);
		
		JLabel lblTask = new JLabel("Task");
		lblTask.setHorizontalAlignment(SwingConstants.CENTER);
		lblTask.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTask.setBounds(10, 39, 240, 23);
		frmRemoveTaskGUI.getContentPane().add(lblTask);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 73, 240, 248);
		frmRemoveTaskGUI.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name"
			}
		));
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for(Task task : Main.tasks)
	    {
			model.addRow(new Object[]{task.getTaskID(), task.getTaskTitle()});
	    }
		
		JButton button = new JButton("Remove");
		button.setBounds(322, 154, 101, 32);
		frmRemoveTaskGUI.getContentPane().add(button);
		frmRemoveTaskGUI.setVisible(true);
	}

}
