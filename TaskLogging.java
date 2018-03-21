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
import java.util.*;
import java.util.Calendar;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.text.DateFormatter;



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
		
		JLabel lblCaretakerName = new JLabel("Caretaker Name:");
		lblCaretakerName.setBounds(25, 28, 117, 14);
		frmTaskLogging.getContentPane().add(lblCaretakerName);
		
		JLabel lblTimeOfCompletion = new JLabel("Time of Completion: ");
		lblTimeOfCompletion.setBounds(25, 53, 134, 14);
		frmTaskLogging.getContentPane().add(lblTimeOfCompletion);
		
		JLabel lblAdditionalComments = new JLabel("Additional Comments:");
		lblAdditionalComments.setBounds(25, 78, 134, 14);
		frmTaskLogging.getContentPane().add(lblAdditionalComments);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(171, 73, 206, 105);
		frmTaskLogging.getContentPane().add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(168, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(292, 181, 89, 23);
		frmTaskLogging.getContentPane().add(btnCancel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1521619200000L), new Date(1521619200000L), new Date(1521662400000L), Calendar.HOUR_OF_DAY));
		spinner.setBackground(new Color(240, 240, 240));
		spinner.setBounds(171, 49, 134, 22);
		frmTaskLogging.getContentPane().add(spinner);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Caretaker 1", "Caretaker 2", "Caretaker 3", "Caretaker 4", "Caretaker 5", "Caretaker 6"}));
		comboBox.setBounds(171, 24, 134, 22);
		frmTaskLogging.getContentPane().add(comboBox);
	}




} 
