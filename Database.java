package software_eng;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Database {
	private Connection con;
	
	public Database() {
	}
	
	public void connect() {
		try
		{
		con = DriverManager.getConnection("jdbc:sqlite:U:\\SQLite\\softwareEng.db");
		}
		catch(SQLException e) 
		{
			System.out.println(e.toString());
		}
	}
	
	 public List<Task> pullTasks()
	    {
	        connect();
	        ResultSet rs = null;
	        List<Task> tasks = new ArrayList<Task>();
	        try
	        {
			 	Statement stmt = con.createStatement(); 
	            rs = stmt.executeQuery("SELECT * FROM task");
	            while(rs.next())
	            {
	            	String id = rs.getString("taskID");
	            	String type = rs.getString("taskType");
	            	String title = rs.getString("taskTitle");
	            	String notes = rs.getString("taskNotes");
	            	int duration = rs.getInt("taskDuration");
	            	boolean assigned = rs.getBoolean("taskAssigned");
	            	boolean completed = rs.getBoolean("taskCompleted");
	            	String priority = rs.getString("taskPriority");
	            	
	            	tasks.add(new Task(id, type, title, notes, duration, assigned, completed, priority));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	        
	        return tasks;
	    }
	
	 //User methods
	 public List<User> pullUsers()
	    {
	        connect();
	        ResultSet rs = null;
	        List<User> users = new ArrayList<User>();
	        try
	        {
			 	Statement stmt = con.createStatement(); 
	            rs = stmt.executeQuery("SELECT * FROM users");
	            while(rs.next())
	            {
	            	String id = rs.getString("username");
	            	String passwordHash = rs.getString("passwordHash");
	            	String firstname = rs.getString("firstname");
	            	String surname = rs.getString("surname");
	            	String accountType = rs.getString("accountType");
	            	String gender = rs.getString("gender");
	            	
	            	users.add(new User(id, passwordHash, firstname, surname, gender, accountType));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	        
	        return users;
	    }
	 
	 public User pullSingleUser(String username) {
		 connect();
		 ResultSet rs = null;
		 User user;
		 try
		 {
			 Statement stmt = con.createStatement(); 
			 rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
	           		String id = rs.getString("username");
	            	String passwordHash = rs.getString("passwordHash");
	            	String firstname = rs.getString("firstname");
	            	String surname = rs.getString("surname");
	            	String accountType = rs.getString("accountType");
	            	String gender = rs.getString("gender");
	            	user = new User(id, passwordHash, firstname, surname, gender, accountType);
	            	return user;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 return null;
	 }
}
