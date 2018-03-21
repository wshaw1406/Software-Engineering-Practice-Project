import java.awt.Rectangle;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

/**
 * @author Max
 * A GUI Example with Database connectivity
 * Shows different elements
 */

public class CaretakerSchedule extends JFrame{	

	private static final long serialVersionUID = 3114462981584318929L;

	//Connection object that will be used to create the SQL statements
	Connection connection;

	// Course codes
	ArrayList<Integer> courseCodes;

	//Combo box model
	DefaultComboBoxModel<String> mdlCombo;

	//All modules table model
	DefaultTableModel mdlAllModules;

	//Assigned modules table model
	DefaultTableModel mdlAssigned;

	// All modules table
	JTable tblAllModules;

	// Assigned modules table
	JTable tblAssignedModules;

	public CaretakerSchedule() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		courseCodes = new ArrayList<>();

		setResizable(false);
		setBounds(new Rectangle(200, 200, 600, 410));
		getContentPane().setLayout(new BorderLayout(0, 0));


		//Auto generated elements here		
		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(pnlTop, BorderLayout.NORTH);


		//Create a model for combo box to hold course data		
		mdlCombo = new DefaultComboBoxModel<String>();
		pnlTop.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomename = new JLabel("Welcome, #Name");
		lblWelcomename.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTop.add(lblWelcomename, BorderLayout.WEST);
		
		JButton btnLogOut = new JButton("Log Out");
		pnlTop.add(btnLogOut, BorderLayout.EAST);

		JPanel pnlBody = new JPanel();
		getContentPane().add(pnlBody, BorderLayout.CENTER);
		pnlBody.setLayout(null);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlLeft.setBounds(10, 11, 237, 320);
		pnlBody.add(pnlLeft);
		pnlLeft.setLayout(null);

		JLabel lblAllModules = new JLabel("Available Tasks");
		lblAllModules.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAllModules.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllModules.setBounds(10, 11, 217, 23);
		pnlLeft.add(lblAllModules);

		// Add table for all modules
		tblAllModules = new JTable();
		tblAllModules.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Create a string array to hold column names
		String tableHeader[] = new String[] {"Task Name", "Duration"};	

		//Create a table model for combo box to hold modules data	
		mdlAllModules = new DefaultTableModel(null, tableHeader);

		//Make the table use the model we created
		tblAllModules.setModel(mdlAllModules);

		JScrollPane scrLeft = new JScrollPane(tblAllModules);
		scrLeft.setFocusable(false);
		scrLeft.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrLeft.setBounds(10, 45, 217, 262);
		pnlLeft.add(scrLeft);

		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlRight.setBounds(347, 11, 237, 320);
		pnlBody.add(pnlRight);
		pnlRight.setLayout(null);

		JLabel lblAssignedModules = new JLabel("Assigned Tasks");
		lblAssignedModules.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblAssignedModules.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignedModules.setBounds(10, 11, 217, 23);
		pnlRight.add(lblAssignedModules);

		// Do similarly
		//Create a table model for combo box to hold assigned data	
		mdlAssigned = new DefaultTableModel(null, tableHeader);

		/// Add table for assigned modules
		tblAssignedModules = new JTable();
		tblAssignedModules.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Make the table use the model we created
		tblAssignedModules.setModel(mdlAssigned);

		JScrollPane scrRight = new JScrollPane(tblAssignedModules);
		scrRight.setFocusable(false);
		scrRight.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrRight.setBounds(10, 45, 217, 264);
		pnlRight.add(scrRight);

		JButton btnMoveRight = new JButton("\u2192");
		btnMoveRight.setFocusPainted(false);
		btnMoveRight.setFont(new Font("Arial", Font.PLAIN, 23));
		btnMoveRight.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get the selected row number
				int selectedRow = tblAllModules.getSelectedRow();				
				if (selectedRow <0) {
					// Show error message if button is clicked before selecting a row				
					JOptionPane.showMessageDialog(getContentPane(), "Select a row from left first", 
							"Problem", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					// Create and execute SQL statement for module assignment
					try {
						PreparedStatement sqlInsert = connection.prepareStatement (
								"INSERT INTO ModuleAssignment (MCode,CCode) VALUES (?, ?)");
						sqlInsert.setInt(1, Integer.parseInt(mdlAllModules.getValueAt(selectedRow, 0).toString()));
						sqlInsert.setInt(2, courseCodes.get(cmbCourses.getSelectedIndex()));						
						sqlInsert.executeUpdate();

						// Update the GUI after the insertion 
						updateGUI();

					} catch (SQLException e) {
						e.printStackTrace();
					}				

				}		
			}
		});
		btnMoveRight.setBounds(257, 126, 80, 29);
		pnlBody.add(btnMoveRight);

		JButton btnMoveLeft = new JButton("\u2190");
		btnMoveLeft.setFocusPainted(false);
		btnMoveLeft.setFont(new Font("Arial", Font.PLAIN, 23));
		btnMoveLeft.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Similar implementation
				// get the selected row number
				int selectedRow = tblAssignedModules.getSelectedRow();				
				if (selectedRow <0) {
					// Show error message if button is clicked before selecting a row				
					JOptionPane.showMessageDialog(getContentPane(), "Select a row from right first",
							"Problem", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					// make and execute SQL to delete a module
					try {
						PreparedStatement sqlDelete = connection.prepareStatement (
								"DELETE FROM ModuleAssignment "
										+ "where MCode = ? and CCode = ?");
						sqlDelete.setInt(1, Integer.parseInt(mdlAssigned.getValueAt(selectedRow, 0).toString()));
						sqlDelete.setInt(2, courseCodes.get(cmbCourses.getSelectedIndex()));
						sqlDelete.executeUpdate();

						// Update the GUI
						updateGUI();

					} catch (SQLException e) {
						e.printStackTrace();
					}					}									

			}
		});


		btnMoveLeft.setBounds(257, 166, 80, 29);
		pnlBody.add(btnMoveLeft);

		// Just for looks 
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Try to connect to database
		if (connect()) {
			System.out.println("Successfully connected");
		}else{
			System.out.println("Connection issue");
			return;
		}	

		// Update GUI
		try {
			updateCombo();			 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}


		// Ask user to confirm exit		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(getContentPane(), 
						"Are you Sure to close this application?", 
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					
					// Close any connection if user decides to exit
					try {
						if (!connection.isClosed()) {
							connection.close();
							System.out.println("Disconnected");
						}
					} catch (SQLException e1) {						
					}
					System.exit(0);
				}
			}
		});

	}


	/**
	 *  Tries to connect to the database
	 * @return True if connection succeeds; false otherwise. 
	 * 
	 */
	private boolean connect()
	{
		String driver = "jdbc:ucanaccess://";
		String dBase = "db\\data.accdb";
		String url = driver + dBase;
		connection = null;		
		try {
			connection = DriverManager.getConnection (url);
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}		
		return true;
	}


	/**
	 *  Updates GUI from the database
	 * @throws SQLException 
	 */
	private void updateGUI() throws SQLException 
	{
		// If the combo box is empty skip updating
		if (mdlCombo.getSize() == 0) {
			return;			
		}

		// Create a string array to hold column names
		String tableHeader[] = new String[] {"Task Name", "Duration"};	


		// Update table models to hold just the table headers
		mdlAssigned = new DefaultTableModel(null,tableHeader);
		mdlAllModules = new DefaultTableModel(null,tableHeader);

		// Assign the models to the corresponding tables
		tblAllModules.setModel(mdlAllModules);
		tblAssignedModules.setModel(mdlAssigned);

		// Create a statement and execute the query
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery (query);

		// position to first record	    
		boolean moreRecords = rs.next ();
		// If there are no records skip
		if (!moreRecords) {
			return;
		}	    
		// Update the table model row by row
		do {
			mdlAllModules.addRow(new String[] {rs.getInt(1) + "", rs.getString(2) });  
		} while (rs.next ());

		rs = statement.executeQuery (query);
		// position to first record

		moreRecords = rs.next();
		// If there are no records skip
		if (!moreRecords) {
			return;
		}	    
		// Update the table model row by row
		do {
			mdlAssigned.addRow(new String[] {rs.getInt(1) + "", rs.getString(2) });  
		} while (rs.next ());
	}

	/**
	 *  Update the combo box and populated changes to the tables
	 * @throws SQLException
	 */
	private void updateCombo() throws SQLException	{

		// Empty the combo box
		mdlCombo.removeAllElements();
		courseCodes.clear();

		// Create a statement with corresponding SQL
		Statement statement = connection.createStatement ();
		String query = "SELECT * FROM Course";

		// Execute SQL query
		ResultSet rs = statement.executeQuery (query);

		// Position to first record
		boolean moreRecords = rs.next ();
		// If there are no records skip
		if (!moreRecords) {
			return;
		}	    
		// Add each row to combo box
		do {
			courseCodes.add(rs.getInt(1));
			mdlCombo.addElement(rs.getString(2));
		} while (rs.next ());

		// Update the GUI
		updateGUI();
	}


	/**
	 *  Starting point of the program
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		CaretakerSchedule = new CaretakerSchedule();
		gui.setVisible(true);

	}
}
