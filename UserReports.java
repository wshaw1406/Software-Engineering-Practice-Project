package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;

public class UserReports {

	private JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;


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
				//Allow the user to input a name of the file
				String fileName = JOptionPane.showInputDialog("Name File", "Type in file name:");
				try {
					download(fileName);
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
				"User ID", "First Name", "Last Name", "# tasks completed", "# tasks assigned", "# late tasks"
				}
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new usersInformation();
			}
		});
		btnBack.setBounds(31, 377, 97, 25);
		frame.getContentPane().add(btnBack);
			    
	    updateTable();
	    
	}
	
	public static void updateTable() {

		model = (DefaultTableModel) table.getModel();

		clearTable();
		//Clears the table and adds all the tasks to it
		for(User user : Main.users)
		{
			model.addRow(new Object[]{user.getUsername(), user.getFirstName(), user.getSurname(), user.getNumberComplete(),user.getNumberAssigned(),user.getNumberLate()});	
		}
		
	}
	
	public static void clearTable() {
		model = (DefaultTableModel) table.getModel();
		//Remove all tasks from the table
		for(int i = model.getRowCount() - 1; i >= 0 ; i--) {
			model.removeRow(i);
		}
	}
	
	private void download(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName + ".csv"));
        StringBuilder sb = new StringBuilder();
        
        //Set up csv format to print out all the information in the table
        
		model = (DefaultTableModel) table.getModel();
        
        sb.append("User ID");
        sb.append(',');
        sb.append("First Name");
        sb.append(',');
        sb.append("Last Name");
        sb.append(',');
        sb.append("# Tasks completed");
        sb.append(',');
        sb.append("# Tasks assigned");
        sb.append(',');
        sb.append("# last Tasks");
        sb.append('\n');
        
        for(int i = 0; i < model.getRowCount() ; i++) {
        	for(int j = 0; j < model.getColumnCount(); j++) {
        		sb.append(model.getValueAt(i, j));
        		sb.append(",");
        	}
        	sb.append("\n");
        }
        
        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
}