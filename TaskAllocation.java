package software_eng;

import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TaskAllocation {

	private JFrame frame;
	public static JTable table;
	public static DefaultTableModel model;
	private JButton btnSubmit;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskAllocation window = new TaskAllocation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TaskAllocation() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Time", "Name", "Due date", "Notes", "Assign"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		
		model = (DefaultTableModel) table.getModel();

		for(Task task : Main.tasks)
	    {
			if(task.getTaskAssigned() == false) {
				model.addRow(new Object[]{task.getTaskTime(), task.getTaskTitle(), "DSFSDF", "notes", false});
			}
	    }
		scrollPane.setViewportView(table);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) table.getModel();
				for(int i = 0; i < model.getRowCount(); i++) {
					Boolean value = (Boolean) table.getModel().getValueAt(i, 4);
					for(Task task : Main.tasks)
				    {
						if(task.getTaskTitle() == table.getModel().getValueAt(i, 1).toString() && value == true) {
							task.setTaskAssigned(true);
						}
				    }
					System.out.println(value);
				}
			}
		});
		frame.getContentPane().add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				new CaretakerSchedule2();
			}
		});
		frame.getContentPane().add(btnCancel);
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));
		frame.setVisible(true);
	}
}

