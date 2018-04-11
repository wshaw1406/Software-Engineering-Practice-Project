package software_eng;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserReports {

	private JFrame frame;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAllUsrReport = new JButton("All user report");
		btnAllUsrReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				new MultiUserReport();
			}
		});
		btnAllUsrReport.setBounds(133, 184, 148, 25);
		frame.getContentPane().add(btnAllUsrReport);
		
		JButton btnSngUsrReport = new JButton("Open user report");
		btnSngUsrReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If no option picked show error
				//else
				frame.hide();
				new SingleUserReport();
			}
		});
		btnSngUsrReport.setBounds(133, 128, 148, 25);
		frame.getContentPane().add(btnSngUsrReport);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(133, 68, 148, 22);
		frame.getContentPane().add(comboBox);
	}
}
