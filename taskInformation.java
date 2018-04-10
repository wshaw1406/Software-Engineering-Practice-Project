import java.awt.EventQueue;



import javax.swing.JFrame;

import javax.swing.JTable;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class taskInformation {



	private JFrame frmTaskInformation;

	private JTable table;



	/**

	 * Launch the application.

	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					taskInformation window = new taskInformation();

					window.frmTaskInformation.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}



	/**

	 * Create the application.

	 */

	public taskInformation() {

		initialize();

	}



	/**

	 * Initialize the contents of the frame.

	 */

	private void initialize() {

		frmTaskInformation = new JFrame();
		frmTaskInformation.setTitle("Task Information");

		frmTaskInformation.setBounds(100, 100, 571, 405);

		frmTaskInformation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmTaskInformation.getContentPane().setLayout(null);

		

		JLabel label = new JLabel("Add New Task");

		label.setFont(new Font("Tahoma", Font.PLAIN, 11));

		label.setBounds(319, 55, 92, 14);

		frmTaskInformation.getContentPane().add(label);

		

		JButton btnAdd = new JButton("Add");

		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnAdd.setBounds(430, 50, 92, 23);

		frmTaskInformation.getContentPane().add(btnAdd);

		

		JButton btnEdit = new JButton("Edit");

		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnEdit.setBounds(430, 100, 92, 23);

		frmTaskInformation.getContentPane().add(btnEdit);

		

		JLabel label_1 = new JLabel("Edit Task");

		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		label_1.setBounds(319, 105, 92, 14);

		frmTaskInformation.getContentPane().add(label_1);

		

		JButton btnRemove = new JButton("Remove");

		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnRemove.setBounds(430, 149, 92, 23);

		frmTaskInformation.getContentPane().add(btnRemove);

		

		JLabel label_2 = new JLabel("Remove Task");

		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));

		label_2.setBounds(319, 154, 92, 14);

		frmTaskInformation.getContentPane().add(label_2);

		

		JLabel label_3 = new JLabel("Task Report");

		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));

		label_3.setBounds(319, 244, 92, 23);

		frmTaskInformation.getContentPane().add(label_3);

		

		JButton btnReport = new JButton("Report");

		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnReport.setBounds(430, 243, 89, 23);

		frmTaskInformation.getContentPane().add(btnReport);

		

		JPanel panel = new JPanel();

		panel.setBounds(10, 50, 299, 306);

		frmTaskInformation.getContentPane().add(panel);

		panel.setLayout(null);

		

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 11, 279, 284);

		panel.add(scrollPane);

		

		table = new JTable();

		table.setModel(new DefaultTableModel(

			new Object[][] {

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

				{null, null, null, null},

			},

			new String[] {

				"Name", "Duration", "Importance", "Frequency"

			}

		));

		scrollPane.setViewportView(table);

		

		JLabel lblTasks = new JLabel("Tasks");

		lblTasks.setBounds(127, 25, 46, 14);

		frmTaskInformation.getContentPane().add(lblTasks);

		

		JButton btnBack = new JButton("Back");
			// go to AdminGUI page 
		btnBack.setBounds(456, 11, 89, 23);

		frmTaskInformation.getContentPane().add(btnBack);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAssign.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAssign.setBounds(430, 289, 92, 23);
		frmTaskInformation.getContentPane().add(btnAssign);
		
		JLabel label_4 = new JLabel("Assign Task");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(319, 292, 92, 17);
		frmTaskInformation.getContentPane().add(label_4);
		
		JLabel lblCompleteTask = new JLabel("Complete Task");
		lblCompleteTask.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCompleteTask.setBounds(319, 199, 109, 16);
		frmTaskInformation.getContentPane().add(lblCompleteTask);
		
		JButton btnCompleteTask = new JButton("Complete");
		btnCompleteTask.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCompleteTask.setBounds(430, 194, 92, 23);
		frmTaskInformation.getContentPane().add(btnCompleteTask);

	}
}