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

public class UserReports {

	private JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserReports window = new UserReports();
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
	public UserReports() {
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
				"User ID", "First Name", "Last Name", "# tasks completed", "# tasks assigned", "# late tasks"			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 274, 573, 85);
		frame.getContentPane().add(panel);
		
		UserReportsAL actionListener = new UserReportsAL();
	    
	    updateTable();
	    
	}
	
	public static void updateTable() {

		model = (DefaultTableModel) table.getModel();

		clearTable();
		
		for(User user : Main.users)
		{
			model.addRow(new Object[]{user.getUsername(), user.getFirstName(), user.getSurname(), "","","",});	
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
	      //  sb.append(task.getTaskTime());
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

class UserReportsAL implements ItemListener {
	  // This method is called only if a new item has been selected.
	  public void itemStateChanged(ItemEvent evt) {

		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      // Item was just selected
		    	TaskReports.updateTable();
		    }
	  }
	}
