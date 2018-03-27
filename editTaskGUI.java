import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editTaskGUI {

	private JFrame frmEditTaskGUI;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editTaskGUI window = new editTaskGUI();
					window.frmEditTaskGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public editTaskGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditTaskGUI = new JFrame();
		frmEditTaskGUI.setTitle("Edit Task");
		frmEditTaskGUI.setBounds(100, 100, 658, 376);
		frmEditTaskGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditTaskGUI.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 73, 217, 248);
		frmEditTaskGUI.getContentPane().add(scrollPane);
		
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
			},
			new String[] {
				"ID", "Name"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblTasks = new JLabel("Tasks");
		lblTasks.setHorizontalAlignment(SwingConstants.CENTER);
		lblTasks.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTasks.setBounds(10, 39, 217, 23);
		frmEditTaskGUI.getContentPane().add(lblTasks);
		
		JLabel label_1 = new JLabel("Please select a user to edit");
		label_1.setBounds(10, 11, 230, 14);
		frmEditTaskGUI.getContentPane().add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(371, 117, 212, 145);
		frmEditTaskGUI.getContentPane().add(textArea);
		
		JLabel label = new JLabel("Task Notes");
		label.setBounds(267, 119, 75, 14);
		frmEditTaskGUI.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("Task Duration");
		label_2.setBounds(267, 89, 104, 14);
		frmEditTaskGUI.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Task Type");
		label_3.setBounds(267, 64, 75, 14);
		frmEditTaskGUI.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Task Name");
		label_4.setBounds(267, 39, 75, 14);
		frmEditTaskGUI.getContentPane().add(label_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(371, 39, 212, 20);
		frmEditTaskGUI.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(371, 64, 212, 20);
		frmEditTaskGUI.getContentPane().add(textField_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(371, 89, 108, 20);
		frmEditTaskGUI.getContentPane().add(spinner);
		
		JLabel label_5 = new JLabel("Hours");
		label_5.setBounds(485, 95, 46, 14);
		frmEditTaskGUI.getContentPane().add(label_5);
		
		JButton button = new JButton("Submit");
		button.setBounds(494, 298, 89, 23);
		frmEditTaskGUI.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(267, 298, 89, 23);
		frmEditTaskGUI.getContentPane().add(button_1);
	}

}
