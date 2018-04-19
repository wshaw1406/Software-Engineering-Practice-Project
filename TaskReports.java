package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;

public class TaskReports {

	private JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static JComboBox cmBxType;
	private static JComboBox cmBxComplete;
	private static JComboBox cmBxTaskAssigned;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskReports window = new TaskReports();
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
	public TaskReports() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					download();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDownload.setBounds(222, 377, 97, 25);
		frame.getContentPane().add(btnDownload);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 573, 261);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task ID", "Task Name", "Task Type", "Task assigned?", "Completed by", "Completed Date", "Completed Time"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 274, 549, 90);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblSrchCompleted = new JLabel("Completed?");
		panel_1.add(lblSrchCompleted);
		
		cmBxComplete = new JComboBox();
		cmBxComplete.setModel(new DefaultComboBoxModel(new String[] {"Any", "True", "False"}));
		cmBxComplete.setSelectedIndex(0);
		panel_1.add(cmBxComplete);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblSrchTaskType = new JLabel("Task Type");
		panel_2.add(lblSrchTaskType);
		
		cmBxType = new JComboBox();
		cmBxType.setModel(new DefaultComboBoxModel(new String[] {"Any", "Routine", "One-off"}));
		cmBxType.setSelectedIndex(0);
		panel_2.add(cmBxType);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		
		JLabel lblTaskAssigned = new JLabel("Task Assigned");
		panel_6.add(lblTaskAssigned);
		
		cmBxTaskAssigned = new JComboBox();
		cmBxTaskAssigned.setModel(new DefaultComboBoxModel(new String[] {"Any", "True", "False"}));
		cmBxTaskAssigned.setSelectedIndex(0);
		panel_6.add(cmBxTaskAssigned);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblSrchCompletedBy = new JLabel("Completed by");
		panel_3.add(lblSrchCompletedBy);
		
		JComboBox cmBxCompletedBy = new JComboBox();
		cmBxCompletedBy.setModel(new DefaultComboBoxModel(new String[] {"Any"}));
		cmBxCompletedBy.setSelectedIndex(0);
		panel_3.add(cmBxCompletedBy);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblCompletedBefore = new JLabel("Completed Before");
		panel_4.add(lblCompletedBefore);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JLabel lblCompletedAfter = new JLabel("Completed After");
		panel_5.add(lblCompletedAfter);
		
		MyItemListener actionListener = new MyItemListener();
	    cmBxType.addItemListener(actionListener);
	    cmBxComplete.addItemListener(actionListener);
	    cmBxTaskAssigned.addItemListener(actionListener);
	    
	    updateTable();
	    
	}
	
	public static void updateTable() {

		model = (DefaultTableModel) table.getModel();
		String complete = (String) cmBxComplete.getSelectedItem();
		String type = (String) cmBxType.getSelectedItem();
		String assigned = (String) cmBxTaskAssigned.getSelectedItem();

		clearTable();
		
		for(Task task : Main.tasks)
		{
			model.addRow(new Object[]{task.getTaskID(), task.getTaskTitle(), task.getTaskType(), task.getTaskAssigned(), task.getTaskCompleted(), "Completed date", "completed time"});
		}
		
		switch(type) {
			case "Routine": 
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					String value = (String) model.getValueAt(i, 2);
					if(value.equals("One-off")) {
						model.removeRow(i);
					}
				};
			break;
			case "One-off": 
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					String value = (String) model.getValueAt(i, 2);
					if(value.equals("Routine")) {
						model.removeRow(i);
					}
				};
			break;
		}
		
		switch(complete) {
			case "True":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					boolean value = (boolean) model.getValueAt(i, 4);
					if(value == false) {
						model.removeRow(i);
					}
				};
			break;
			case "False":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					boolean value = (boolean) model.getValueAt(i, 4);
					if(value == true) {
						model.removeRow(i);
					}
				};
			break;
		}
		
		switch(assigned) {
			case "True":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					boolean value = (boolean) model.getValueAt(i, 3);
					if(value == false) {
						model.removeRow(i);
					}
				};
			break;
			case "False":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					boolean value = (boolean) model.getValueAt(i, 3);
					if(value == true) {
						model.removeRow(i);
					}
				};
			break;
		}
	}
	
	public static void clearTable() {
		model = (DefaultTableModel) table.getModel();

		for(int i = model.getRowCount() - 1; i >= 0 ; i--) {
			model.removeRow(i);
		}
	}
	
	private void download() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("TaskReport.csv"));
        StringBuilder sb = new StringBuilder();
        
        //Set up csv format
        
        sb.append("Task ID");
        sb.append(',');
        sb.append("Task Name");
        sb.append(',');
        sb.append("Task Time");
        sb.append(',');
        sb.append("Task Assigned?");
        sb.append(',');
        sb.append("Task Completed?");
        sb.append('\n');
        
        for(Task task : Main.tasks)
	    {
			sb.append(task.getTaskID());
	        sb.append(',');
	        sb.append(task.getTaskTitle());
	        sb.append(',');
	        sb.append(task.getTaskTime());
	        sb.append(',');
	        sb.append(task.getTaskAssigned());
	        sb.append(',');
	        sb.append(task.getTaskCompleted());
	        sb.append('\n');
	    }

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
}

class MyItemListener implements ItemListener {
	  // This method is called only if a new item has been selected.
	  public void itemStateChanged(ItemEvent evt) {

		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      // Item was just selected
		    	TaskReports.updateTable();
		    }
	  }
	}
