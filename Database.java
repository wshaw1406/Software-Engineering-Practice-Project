package software_eng;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class Database {
	private static Connection con;
	
	public Database() {

	}
	
	public static void connect() {
		try
		{
		con = DriverManager.getConnection("jdbc:sqlite:U://software_engineering//software_eng//src//software_eng//softwareEng.db");
		}
		catch(SQLException e) 
		{
			System.out.println(e.toString());
		}
	}
	
	public static void closeConnection() {
		try {
		con.close();
		}
		catch(SQLException closeCon) {
			System.out.println(closeCon.toString());
		}
	}
	//Task table methods
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
	            	String tCompleted = rs.getString("taskCompleted");
	            	boolean completed = Boolean.parseBoolean(tCompleted);

	            	int id = rs.getInt("taskID");
	            	String type = rs.getString("taskType");
	            	String title = rs.getString("taskTitle");
	            	String notes = rs.getString("taskNotes");
	            	int duration = rs.getInt("taskDuration");
	            	String assigned = rs.getString("taskAssigned");
	                String priority = rs.getString("taskPriority");
	                int taskTimeCompleted = rs.getInt("taskTimeCompleted");
	                String dateDue = rs.getString("dateDue");
	                Date date = Date.valueOf(dateDue);
	            	tasks.add(new Task(id, type, title, notes, duration, assigned, completed, priority, taskTimeCompleted, date));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	      
	        closeConnection();
	        return tasks;
	    }
	 
	 public List<Task> pullUsersTasks(String userID)
	    {
			connect();
	        ResultSet rs = null;
	        List<Task> tasks = new ArrayList<Task>();
	        
	        try
	        {
			 	Statement stmt = con.createStatement(); 
	            rs = stmt.executeQuery("SELECT * FROM task WHERE taskAssigned = '" + userID + "';");
	            while(rs.next())
	            {
	            	int id = rs.getInt("taskID");
	            	String type = rs.getString("taskType");
	            	String title = rs.getString("taskTitle");
	            	String notes = rs.getString("taskNotes");
	            	int duration = rs.getInt("taskDuration");
	            	String assigned = rs.getString("taskAssigned");
	            	boolean completed = rs.getBoolean("taskCompleted");
	                String priority = rs.getString("taskPriority");
	                int taskTimeCompleted = rs.getInt("taskTimeCompleted");
	                String dateDue = rs.getString("dateDue");
	                Date date = Date.valueOf(dateDue);
	            	System.out.println(dateDue);
	            	tasks.add(new Task(id, type, title, notes, duration, assigned, completed, priority, taskTimeCompleted, date));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	      
	        closeConnection();
	        return tasks;
	    }
	 
	 public Task pullSingleTask(int taskID) {
			connect();
		 ResultSet rs = null;
		 Task task;
		 String taskAssigned;
		 try
		 {
			 Statement stmt = con.createStatement(); 
			 rs = stmt.executeQuery("SELECT * FROM task WHERE taskID = '" + taskID + "'");
	            	String taskType = rs.getString("taskType");
	            	String taskTitle = rs.getString("taskTitle");
	            	String taskNotes = rs.getString("taskNotes");
	            	int taskDuration = rs.getInt("taskDuration");
	            	int taskTimeCompleted = rs.getInt("taskTimeCompleted");
	            	if(rs.getString("taskAssigned") == null) {
	            		taskAssigned = "null";
	            	}
	            	else
	            	{
		            	taskAssigned = rs.getString("taskAssigned");
	            	}

	            	String strComplete = rs.getString("taskCompleted");
	            	boolean taskCompleted = Boolean.parseBoolean(strComplete);
	            	String taskPriority = rs.getString("taskPriority");
	            	String dateDue = rs.getString("dateDue");
		            Date date = Date.valueOf(dateDue);
		            System.out.println(dateDue);
		            task = new Task(taskID, taskType, taskTitle, taskNotes, taskDuration, taskAssigned, taskCompleted, taskPriority, taskTimeCompleted, date);
	            	closeConnection();
	            	return task;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 closeConnection();
		 return null;
	 }
	 
	 public Task pullSingleTask(String taskTitleSearch) {
			connect();
		 ResultSet rs = null;
		 Task task;
		 String taskAssigned;
		 try
		 {
			 Statement stmt = con.createStatement(); 
			 rs = stmt.executeQuery("SELECT * FROM task WHERE taskTitle = '" + taskTitleSearch + "'");
			 		int taskID = rs.getInt("taskID");
	            	String taskType = rs.getString("taskType");
	            	String taskNotes = rs.getString("taskNotes");
	            	int taskDuration = rs.getInt("taskDuration");
	            	int taskTimeCompleted = rs.getInt("taskTimeCompleted");
	            	if(rs.getString("taskAssigned") == null) {
	            		taskAssigned = "null";
	            	}
	            	else
	            	{
		            	taskAssigned = rs.getString("taskAssigned");
	            	}
	            	
	            	String strComplete = rs.getString("taskCompleted");
	            	boolean taskCompleted = Boolean.parseBoolean(strComplete);
	            	String taskPriority = rs.getString("taskPriority");
	            	String dateDue = rs.getString("dateDue");
		            Date date = Date.valueOf(dateDue);
		            System.out.println(dateDue);
		            task = new Task(taskID, taskType, taskTitleSearch, taskNotes, taskDuration, taskAssigned, taskCompleted, taskPriority, taskTimeCompleted, date);
	            	closeConnection();
	            	return task;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 closeConnection();
		 return null;
	 }
	 public void pushSingleTask(Task task) {
			connect();
		 String sql = "INSERT INTO task (taskID, taskType, taskTitle, taskNotes, taskDuration, taskPriority, dateDue)"
		 		+ "VALUES ('" + task.getTaskID() + "', '" + task.getTaskType() + "', '" + task.getTaskTitle()
		 		+ "', '" + task.getTaskNotes() + "', '" + task.getTaskDuration() + "', '"
		 	    + task.getTaskPriority() + "', '"  + task.getDateDue() + "');";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 closeConnection();
		 }
		 catch(SQLException e) 
		 {
			 System.out.println(e.toString());
		 }
	 }
	 
	 public void deleteTask(int taskID) {
			connect();
		 String sql = "DELETE FROM task WHERE taskID = '" + taskID + "';";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 closeConnection();
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
	
	 public void updateTask(Task task)
	 {
			connect();
		 String sql = "UPDATE task SET taskTitle = '" + task.getTaskTitle() + "', taskNotes = '" + task.getTaskNotes() + "', taskPriority = '"
	     + task.getTaskPriority() + "', taskDuration = '" + task.getTaskDuration() + "', taskAssigned = '" + task.getTaskAssigned() 
	     + "', taskCompleted = '" + task.getTaskCompleted() + "', taskTimeCompleted = '" + task.getTaskTimeCompleted() + "', dateDue = '" + task.getDateDue() + "' WHERE taskID = " + task.getTaskID() + ";";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 closeConnection();
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
	 //User table methods
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
	            	int id = rs.getInt("userID");
	            	String username = rs.getString("username");
	            	String passwordHash = rs.getString("passwordHash");
	            	String firstname = rs.getString("firstname");
	            	String surname = rs.getString("surname");
	            	String accountType = rs.getString("accountType");
	            	String gender = rs.getString("gender");
	            	
	            	users.add(new User(id, username, passwordHash, firstname, surname, gender, accountType));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	        closeConnection();
	        return users;
	    }
	 
	 public List<User> pullCaretakers()
	    {
			connect();
	        ResultSet rs = null;
	        List<User> users = new ArrayList<User>();
	        try
	        {
			 	Statement stmt = con.createStatement(); 
	            rs = stmt.executeQuery("SELECT * FROM users WHERE accountType = 'Caretaker'");
	            while(rs.next())
	            {
	            	int id = rs.getInt("userID");
	            	String username = rs.getString("username");
	            	String passwordHash = rs.getString("passwordHash");
	            	String firstname = rs.getString("firstname");
	            	String surname = rs.getString("surname");
	            	String accountType = rs.getString("accountType");
	            	String gender = rs.getString("gender");
	            	
	            	users.add(new User(id, username, passwordHash, firstname, surname, gender, accountType));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	        closeConnection();
	        return users;
	    }
	 public User pullSingleUser(String usernameSearch) {
			connect();
		 ResultSet rs = null;
		 User user;
		 try
		 {
			 Statement stmt = con.createStatement(); 
			 rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + usernameSearch + "'");
			 		int id = rs.getInt("userID");
	            	String passwordHash = rs.getString("passwordHash");
	            	String firstname = rs.getString("firstname");
	            	String surname = rs.getString("surname");
	            	String accountType = rs.getString("accountType");
	            	String gender = rs.getString("gender");
	            	user = new User(id, usernameSearch, passwordHash, firstname, surname, gender, accountType);
	            	closeConnection();
	            	return user;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 closeConnection();
		 return null;
	 }
	 
	 public void pushSingleUser(User user) {
			connect();
		 String sql = "INSERT INTO users (userID, username, passwordHash, accountType, firstname, surname, gender)"
		 		+ "VALUES ('" + user.getUserID() + "', '" + user.getUsername() + "', '" + user.getPasswordHash() + "', '" + user.getAccountType()
		 		+ "', '" + user.getFirstName() + "', '" + user.getSurname() + "', '" + user.getGender() + "');";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 closeConnection();
		 }
		 catch(SQLException e) 
		 {
			 System.out.println(e.toString());
		 }
	 }
	 
	 public void deleteUser(String username) {
			connect();
		 String sql = "DELETE FROM users WHERE username = '" + username + "';";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 closeConnection();
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
	 
	 public void updateUser(User user)
	 {
			connect();
		 String sql = "UPDATE users SET passwordHash = '" + user.getPasswordHash() + "', firstname = '" + user.getFirstName() + "', surname = '"
	     + user.getSurname() + "', accountType = '" + user.getAccountType() + "', gender = '" + user.getGender() + "' WHERE userID = " + user.getUserID() + ";";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 closeConnection();
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
}