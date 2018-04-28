package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class CreateTaskGUI {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public CreateTaskGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 401, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setBounds(0, 0, 190, 23);
		datePicker.setBounds(126, 157, 163, 23);
		frame.getContentPane().add(datePicker);
		
		JLabel lblInsertTheDetails = new JLabel("Insert the details of the new task and submit to create\r\n");
		lblInsertTheDetails.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInsertTheDetails.setBounds(10, 11, 336, 14);
		frame.getContentPane().add(lblInsertTheDetails);
		
		JLabel lblTheseFields = new JLabel("* These fields are required");
		lblTheseFields.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTheseFields.setBounds(20, 36, 159, 14);
		frame.getContentPane().add(lblTheseFields);
		
		JLabel lblTaskTitle = new JLabel("* Task Title");
		lblTaskTitle.setBounds(30, 61, 86, 14);
		frame.getContentPane().add(lblTaskTitle);
		
		JLabel lblTaskType = new JLabel("* Task Type");
		lblTaskType.setBounds(30, 97, 86, 14);
		frame.getContentPane().add(lblTaskType);
		
		JLabel lblTaskPriority = new JLabel("* Task Priority");
		lblTaskPriority.setBounds(30, 129, 86, 14);
		frame.getContentPane().add(lblTaskPriority);
		
		JLabel lblTaskDuration = new JLabel("* Task Duration");
		lblTaskDuration.setBounds(30, 194, 86, 14);
		frame.getContentPane().add(lblTaskDuration);
		
		JLabel lblTaskNotes = new JLabel("Task Notes");
		lblTaskNotes.setBounds(30, 234, 86, 14);
		frame.getContentPane().add(lblTaskNotes);
		
		textField = new JTextField();
		textField.setBounds(126, 58, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnRegular = new JRadioButton("Regular");
		buttonGroup.add(rdbtnRegular);
		rdbtnRegular.setBounds(122, 93, 90, 23);
		frame.getContentPane().add(rdbtnRegular);
		
		JRadioButton rdbtnOneoff = new JRadioButton("One-Off");
		buttonGroup.add(rdbtnOneoff);
		rdbtnOneoff.setBounds(274, 93, 72, 23);
		frame.getContentPane().add(rdbtnOneoff);
		
		JSpinner prioritySpinner = new JSpinner();
		prioritySpinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		prioritySpinner.setBounds(126, 126, 53, 20);
		frame.getContentPane().add(prioritySpinner);
		
		JLabel lblPriority = new JLabel("1 = Low, 2 = Medium, 3 = High");
		lblPriority.setBounds(187, 129, 159, 14);
		frame.getContentPane().add(lblPriority);
		
		JSpinner durationSpinner = new JSpinner();
		durationSpinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		durationSpinner.setBounds(126, 191, 53, 20);
		frame.getContentPane().add(durationSpinner);
		
		JLabel lblHours = new JLabel("Minutes");
		lblHours.setBounds(187, 213, 60, 14);
		frame.getContentPane().add(lblHours);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(126, 229, 220, 56);
		frame.getContentPane().add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				Task task = new Task();
				task.setTaskID(task.generateTaskID());
				task.setTaskTitle(textField.getText());
				if(rdbtnRegular.isSelected())
				{
				    task.setTaskType("Regular");
				}
				else
				{
					task.setTaskType("One-off");
				}
				
				try {
				prioritySpinner.commitEdit();
				}
				catch(java.text.ParseException ex)
				{
					System.out.println(ex.toString());
				}
				if((int)prioritySpinner.getValue() == 1) 
				{
					task.setTaskPriority("Low");
				}
				else if((int)prioritySpinner.getValue() == 2)
				{
					task.setTaskPriority("Medium");
				}
				else
				{
					task.setTaskPriority("High");
				}
				
				try {
					durationSpinner.commitEdit();
				}
				catch(java.text.ParseException exc)
				{
					System.out.println(exc.toString());
				}
				task.setTaskDuration((int)durationSpinner.getValue());
				String strDate = datePicker.getJFormattedTextField().getText();
				Date dateDue = Date.valueOf(strDate);
				task.setDateDue(dateDue);
				task.setTaskNotes(textArea.getText());
				db.pushSingleTask(task);
			}
		});
		btnSubmit.setBounds(286, 309, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new taskInformation();
			}
		});
		btnCancel.setBounds(10, 309, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblDateDue = new JLabel("* Date Due");
		lblDateDue.setBounds(30, 157, 69, 14);
		frame.getContentPane().add(lblDateDue);
	}
}