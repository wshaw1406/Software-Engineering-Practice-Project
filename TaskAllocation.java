package software_eng;

import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TaskAllocation {

	private JFrame frame;
	public static JTable table;
	public static DefaultTableModel model;
	private JButton btnSubmit;
	private JButton btnCancel;
	private void sort() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
		table.setRowSorter(sorter);
	}
	

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
				"Priority", "Time", "Name", "Due date", "Notes","Assign"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		
		model = (DefaultTableModel) table.getModel();

		for(Task task : Main.tasks)
	    {
			//Fill out the table with tasks that are not assigned and tasks that are assigned to the user already
			if(task.getTaskAssigned() == null /* OR IF TASK IS ASSIGNED TO USER|| task.getTaskAssigned() == true*/) {
				model.addRow(new Object[]{task.getTaskPriority(), task.getTaskDuration(), task.getTaskTitle(), "DSFSDF", "notes", task.getTaskAssigned()});
			}
	    }
		scrollPane.setViewportView(createData((DefaultTableModel) table.getModel()));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) table.getModel();
				for(int i = 0; i < model.getRowCount(); i++) {
					Boolean value = (Boolean) table.getModel().getValueAt(i, 5);
					if (value == null) {
						value = false;
					}
					for(Task task : Main.tasks)
				    {
						if(task.getTaskTitle().equals(model.getValueAt(i, 2).toString()) && value == true) {
							//Set task assigned to the users id
							task.setTaskAssigned("123");
						}
						//Allow user to de-select a task
						if(task.getTaskTitle() == table.getModel().getValueAt(i, 2).toString() && value == false) {
							task.setTaskAssigned(null);
						}
				    }
				}
				frame.hide();
				new CaretakerSchedule2();
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
		sort();
	}
	
	static public JComponent createData(DefaultTableModel model)
	{
		JTable table = new JTable( model )
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);

				//  Color row based on a cell value

				if (!isRowSelected(row))
				{
					c.setBackground(getBackground());
					int modelRow = convertRowIndexToModel(row);
					String type = (String)getModel().getValueAt(modelRow, 0);
					if ("Low".equals(type)) c.setBackground(Color.GREEN);
					if ("Medium".equals(type)) c.setBackground(Color.YELLOW);
					if ("High".equals(type)) c.setBackground(Color.RED);

				}

				return c;
			}
		};

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
        table.setAutoCreateRowSorter(true);
		return new JScrollPane( table );
	}
}

