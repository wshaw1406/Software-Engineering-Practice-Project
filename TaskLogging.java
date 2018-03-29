import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Calendar;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.text.DateFormatter;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class TaskLogging {

	private JFrame frmTaskLogging;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskLogging window = new TaskLogging();
					window.frmTaskLogging.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TaskLogging() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTaskLogging = new JFrame();
		frmTaskLogging.setTitle("Task Logging");
		frmTaskLogging.setBounds(100, 100, 434, 268);
		frmTaskLogging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTaskLogging.getContentPane().setLayout(null);
		
		//CONNECT TO DATABASE
		//CONNECT TO PREVIOUS PAGE
		
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		//GET CARETAKER NAME WHO MEANT TO DO IT
		lblCaretakerName.setBounds(25, 28, 117, 14);
		frmTaskLogging.getContentPane().add(lblCaretakerName);
		
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(25, 53, 134, 14);
		frmTaskLogging.getContentPane().add(lblTimeOfCompletion);
		
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setBounds(25, 78, 134, 14);
		frmTaskLogging.getContentPane().add(lblAdditionalComments);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to Log this task?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      System.out.println("");
				    } else if (response == JOptionPane.YES_OPTION) {
				      System.out.println("Task has been logged");
				    //UPDATE DATABASE
				    } 
			}
		});
	    

		btnSubmit.setBounds(168, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		btnCancel.setBounds(292, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
		
		JSpinner spinner = new JSpinner();
		Date datenow = Calendar.getInstance().getTime();
		SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
		spinner.setModel(smb);
		JSpinner.DateEditor d = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
		spinner.setEditor(d);
		spinner.setBackground(new Color(240, 240, 240));
		spinner.setBounds(171, 49, 134, 22);
		frmTaskLogging.getContentPane().add(spinner);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker 1", "Caretaker 2", "Caretaker 3", "Caretaker 4", "Caretaker 5", "Caretaker 6"}));
		comboBox.setBounds(171, 24, 134, 22);
		frmTaskLogging.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 78, 210, 90);
		frmTaskLogging.getContentPane().add(scrollPane);
		
		JTextArea txtrTypeAnyComment = new JTextArea();
		scrollPane.setViewportView(txtrTypeAnyComment);
		txtrTypeAnyComment.setText("#Type any comments here");
		txtrTypeAnyComment.setLineWrap(true);
	}
} 
