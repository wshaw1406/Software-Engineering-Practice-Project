package software_eng;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskReports {

	private JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static JComboBox cmBxType;
	private static JComboBox cmBxComplete;
	private static JComboBox cmBxTaskAssigned;
	private static JComboBox cmBxWhoAssigned;
	private static JDatePickerImpl BeforeDatePicker;
	private static JDatePickerImpl AfterDatePicker;



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
		frame.setBounds(100, 100, 762, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog("Name File", "Type in file name:");
				try {
					download(fileName);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDownload.setBounds(321, 410, 97, 25);
		frame.getContentPane().add(btnDownload);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 744, 261);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task ID", "Task Name", "Task Type", "Task assigned?", "Task Duration", "Completed?", "Completed Date", "Due Date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 274, 732, 122);
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
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		
		JLabel lblCompletedBy = new JLabel("Assigned To");
		panel_7.add(lblCompletedBy);
		
		cmBxWhoAssigned = new JComboBox();
		cmBxWhoAssigned.setModel(new DefaultComboBoxModel(new String[] {"Any"}));
		cmBxWhoAssigned.setSelectedIndex(0);
		panel_7.add(cmBxWhoAssigned);
		
		for(User user : Main.users) {
			cmBxWhoAssigned.addItem(user.getUsername());
		}
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblCompletedAfter = new JLabel("Completed After");
		panel_4.add(lblCompletedAfter);
		
		UtilDateModel AfterDmodel = new UtilDateModel();

		Properties Ap = new Properties();
	    Ap.put("text.today", "Today");
	    Ap.put("text.month", "Month");
	    Ap.put("text.year", "Year");
	    JDatePanelImpl AfterDatePanel = new JDatePanelImpl(AfterDmodel, Ap);
		    
	    AfterDatePicker = new JDatePickerImpl(AfterDatePanel, new DateLabelFormatter());
	    AfterDatePicker.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		updateTable();
	    	}
	    });
		    
		panel_4.add(AfterDatePicker);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JLabel lblCompletedBefore = new JLabel("Completed Before");
		panel_5.add(lblCompletedBefore);
		
		UtilDateModel BeforeDmodel = new UtilDateModel();
		LocalDateTime now = LocalDateTime.now(); 
		String now2 = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();

		Properties Bp = new Properties();
	    Bp.put("text.today", "Today");
	    Bp.put("text.month", "Month");
	    Bp.put("text.year", "Year");
	    JDatePanelImpl BeforeDatePanel = new JDatePanelImpl(BeforeDmodel, Bp);
		    
	    BeforeDatePicker = new JDatePickerImpl(BeforeDatePanel, new DateLabelFormatter());
	    BeforeDatePicker.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		updateTable();
	    	}
	    });
		    
	    BeforeDatePicker.getJFormattedTextField().setText(now2);
		panel_5.add(BeforeDatePicker);		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				new taskInformation();
			}
		});
		btnBack.setBounds(33, 410, 97, 25);
		frame.getContentPane().add(btnBack);
		
		TaskReportsAL actionListener = new TaskReportsAL();
	    cmBxType.addItemListener(actionListener);
	    cmBxComplete.addItemListener(actionListener);
	    cmBxTaskAssigned.addItemListener(actionListener);
	    cmBxWhoAssigned.addItemListener(actionListener);
	    	    
	    updateTable();
	    
	}
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	public static void updateTable() {

		model = (DefaultTableModel) table.getModel();
		String complete = (String) cmBxComplete.getSelectedItem();
		String type = (String) cmBxType.getSelectedItem();
		String assigned = (String) cmBxTaskAssigned.getSelectedItem();
		String whoAssigned = (String) cmBxWhoAssigned.getSelectedItem();
		String completeBefore = (String) BeforeDatePicker.getJFormattedTextField().getText();
		String completeAfter = (String) AfterDatePicker.getJFormattedTextField().getText();
		
		clearTable();
		
		for(Task task : Main.tasks)
		{
			if(task.getTaskAssigned() == null) {
				model.addRow(new Object[]{task.getTaskID(), task.getTaskTitle(), task.getTaskType(), "None", task.getTaskDuration(), task.getTaskCompleted(), task.getTaskDateCompleted(), task.getDateDue()});	
			}
			else {
				model.addRow(new Object[]{task.getTaskID(), task.getTaskTitle(), task.getTaskType(), task.getTaskAssigned(),task.getTaskDuration(), task.getTaskCompleted(), task.getTaskDateCompleted(), task.getDateDue()});
			}
		}
		
		for(int i = model.getRowCount() - 1; i>= 0; i--) {
			if(model.getValueAt(i, 3).equals("null")) {
				model.setValueAt("None", i, 3);
			}
		}
		
		if(completeAfter != null) {
			for(int i = model.getRowCount() - 1; i>= 0; i--) {
				if(model.getValueAt(i, 6) != "0") {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if(!completeAfter.isEmpty()) {
							Date selectedDate = format.parse(completeAfter);
							Date taskDate = format.parse((String) model.getValueAt(i,6));
							if(taskDate.before(selectedDate)){
								model.removeRow(i);
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		if(completeBefore != null) {
			for(int i = model.getRowCount() - 1; i>= 0; i--) {
				if(model.getValueAt(i, 6) != "0") {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date selectedDate = format.parse(completeBefore);
						Date taskDate = format.parse((String) model.getValueAt(i,6));
						if(taskDate.after(selectedDate)){
							model.removeRow(i); 
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
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
					if(value.equals("Regular")) {
						model.removeRow(i);
					}
				};
			break;
		}
		
		switch(complete) {
			case "True":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					boolean value = (boolean) model.getValueAt(i, 5);
					if(value == false) {
						model.removeRow(i);
					}
				};
			break;
			case "False":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					boolean value = (boolean) model.getValueAt(i, 5);
					if(value == true) {
						model.removeRow(i);
					}
				};
			break;
		}
		
		switch(assigned) {
			case "True":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					String value = (String) model.getValueAt(i, 3);
					if(value.equals("None")) {
						model.removeRow(i);
					}
				};
			break;
			case "False":
				for (int i = model.getRowCount() - 1; i>= 0; i--) {
					String value = (String) model.getValueAt(i, 3);
					if(!value.equals("None")) {
						model.removeRow(i);
					}
				};
			break;
		}
		
		if(whoAssigned != "Any") {
			for (int i = model.getRowCount() - 1; i>= 0; i--) {
				String value = (String) model.getValueAt(i, 3);
				if(!value.equals("None")) {
					if(!value.equals(whoAssigned)) {
						model.removeRow(i);
					}
				}
				if(value.equals("None")) {
					model.removeRow(i);
				}
			}
		}
	}
	
	public static void clearTable() {
		model = (DefaultTableModel) table.getModel();

		for(int i = model.getRowCount() - 1; i >= 0 ; i--) {
			model.removeRow(i);
		}
	}
	
	private void download(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName + ".csv"));
        StringBuilder sb = new StringBuilder();
        
        //Set up csv format
        
		model = (DefaultTableModel) table.getModel();
        
        sb.append("Task ID");
        sb.append(',');
        sb.append("Task Name");
        sb.append(',');
        sb.append("Task Type");
        sb.append(',');
        sb.append("Task Assigned?");
        sb.append(',');
        sb.append("Task Duration");
        sb.append(',');
        sb.append("Completed By");
        sb.append(',');
        sb.append("Completed Date");
        sb.append(',');
        sb.append("Completed Time");
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

class TaskReportsAL implements ItemListener {
	  // This method is called only if a new item has been selected.
	  public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      // Item was just selected
		    	System.out.println("Updating table!");
		    	TaskReports.updateTable();
		    }
	  }
}