package software_eng;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;


public class CaretakerSchedule2 extends JFrame{

	private JFrame frame;
	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaretakerSchedule2 window = new CaretakerSchedule2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CaretakerSchedule2() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Caretaker Schedule");
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 366, 330, 49);
		frame.getContentPane().add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(330, 366, 330, 49);
		frame.getContentPane().add(btnSave);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(200, 86, 200, 214);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"7:12-8:00", "Test"},
				{"8:00-9:00", "dsfdsf"},
			},
			new String[] {
				"Time", "Desc."
			}
		));
	}
}
