package software_eng;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Database {
	private Connection con;
	
	public Database() {
		connect();
	}
	
	public void connect() {
		try
		{
		con = DriverManager.getConnection("jdbc:sqlite:F:\\Uni Work\\Software Development Practice\\softwareEng.db");
		}
		catch(SQLException e) 
		{
			System.out.println(e.toString());
		}
	}
	
	//Task table methods
	 public List<Task> pullTasks()
	    {
	        ResultSet rs = null;
	        List<Task> tasks = new ArrayList<Task>();
	        
	        try
	        {
			 	Statement stmt = con.createStatement(); 
	            rs = stmt.executeQuery("SELECT * FROM task");
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
	            	
	            	tasks.add(new Task(id, type, title, notes, duration, assigned, completed, priority));
	            }
	        }
	        catch(Exception exc)
	        {
	            exc.printStackTrace();
	        }
	      
	        return tasks;
	    }
	 
	 public Task pullSingleTask(int taskID) {
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
	            	if(rs.getString("taskAssigned") == null) {
	            		taskAssigned = "null";
	            	}
	            	else
	            	{
		            	taskAssigned = rs.getString("taskAssigned");
	            	}

	            	boolean taskCompleted = rs.getBoolean("taskCompleted");
	            	String taskPriority = rs.getString("taskPriority");
	            	task = new Task(taskID, taskType, taskTitle, taskNotes, taskDuration, taskAssigned, taskCompleted, taskPriority);
	            	return task;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 return null;
	 }
	 
	 public Task pullSingleTask(String taskTitleSearch) {
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
	            	if(rs.getString("taskAssigned") == null) {
	            		taskAssigned = "null";
	            	}
	            	else
	            	{
		            	taskAssigned = rs.getString("taskAssigned");
	            	}

	            	boolean taskCompleted = rs.getBoolean("taskCompleted");
	            	String taskPriority = rs.getString("taskPriority");
	            	task = new Task(taskID, taskType, taskTitleSearch, taskNotes, taskDuration, taskAssigned, taskCompleted, taskPriority);
	            	return task;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 return null;
	 }
	 public void pushSingleTask(Task task) {
		 String sql = "INSERT INTO task (taskID, taskType, taskTitle, taskNotes, taskDuration, taskPriority)"
		 		+ "VALUES ('" + task.getTaskID() + "', '" + task.getTaskType() + "', '" + task.getTaskTitle()
		 		+ "', '" + task.getTaskNotes() + "', '" + task.getTaskDuration() + "', '"
		 	    + task.getTaskPriority() + "');";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		 }
		 catch(SQLException e) 
		 {
			 System.out.println(e.toString());
		 }
	 }
	 
	 public void deleteTask(int taskID) {
		 String sql = "DELETE FROM task WHERE taskID = '" + taskID + "';";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
	
	 public void updateTask(Task task)
	 {
		 String sql = "UPDATE task SET taskTitle = '" + task.getTaskTitle() + "', taskNotes = '" + task.getTaskNotes() + "', taskPriority = '"
	     + task.getTaskPriority() + "', taskDuration = '" + task.getTaskDuration() + "' WHERE taskID = " + task.getTaskID() + ";";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
	 //User table methods
	 public List<User> pullUsers()
	    {
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
	        
	        return users;
	    }
	 
	 public User pullSingleUser(String usernameSearch) {
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
	            	return user;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 return null;
	 }
	 
	 public void pushSingleUser(User user) {
		 String sql = "INSERT INTO users (userID, username, passwordHash, accountType, firstname, surname, gender)"
		 		+ "VALUES ('" + user.getUserID() + "', '" + user.getUsername() + "', '" + user.getPasswordHash() + "', '" + user.getAccountType()
		 		+ "', '" + user.getFirstName() + "', '" + user.getSurname() + "', '" + user.getGender() + "');";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		 }
		 catch(SQLException e) 
		 {
			 System.out.println(e.toString());
		 }
	 }
	 
	 public void deleteUser(String username) {
		 String sql = "DELETE FROM users WHERE username = '" + username + "';";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
	 
	 public void updateUSer(User user)
	 {
		 String sql = "UPDATE users SET passwordHash = '" + user.getPasswordHash() + "', firstname = '" + user.getFirstName() + "', surname = '"
	     + user.getSurname() + "', accountType = '" + user.getAccountType() + "', gender = '" + user.getGender() + "' WHERE userID = " + user.getUserID() + ";";
		 
		 try
		 {
			 Statement stmt = con.createStatement();
			 stmt.executeUpdate(sql);
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
}