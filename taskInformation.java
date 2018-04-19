package software_eng;

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

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public taskInformation() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 571, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Add New Task");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(319, 115, 92, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateTaskGUI();
				frame.setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(430, 110, 92, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditTaskGUI();
				frame.setVisible(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(430, 160, 92, 23);
		frame.getContentPane().add(button_1);
		
		JLabel label_1 = new JLabel("Edit Task");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(319, 165, 92, 14);
		frame.getContentPane().add(label_1);
		
		JButton button_2 = new JButton("Remove");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(430, 209, 92, 23);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("Remove Task");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(319, 214, 92, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Task Report");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(319, 260, 92, 23);
		frame.getContentPane().add(label_3);
		
		JButton btnReport = new JButton("Report");
		btnReport.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReport.setBounds(430, 259, 89, 23);
		frame.getContentPane().add(btnReport);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 50, 299, 306);
		frame.getContentPane().add(panel);
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
		frame.getContentPane().add(lblTasks);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(456, 11, 89, 23);
		frame.getContentPane().add(btnBack);
	}

}
