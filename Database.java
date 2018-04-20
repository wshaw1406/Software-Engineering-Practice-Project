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
	        connect();
	        ResultSet rs = null;
	        List<Task> tasks = new ArrayList<Task>();
	        
	        try
	        {
			 	Statement stmt = con.createStatement(); 
	            rs = stmt.executeQuery("SELECT * FROM task SORT BY taskID");
	            while(rs.next())
	            {
	            	int id = rs.getInt("taskID");
	            	String type = rs.getString("taskType");
	            	String title = rs.getString("taskTitle");
	            	String notes = rs.getString("taskNotes");
	            	int duration = rs.getInt("taskDuration");
	            	String assigned = rs.getString("taskAssigned");
	            	boolean completed = rs.getBoolean("taskCompleted");
	                int priority = rs.getInt("taskPriority");
	            	
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
	            	if(rs.getString("taskAssigned") == null) {
	            		taskAssigned = "null";
	            	}
	            	taskAssigned = rs.getString("taskAssigned");
	            	boolean taskCompleted = rs.getBoolean("taskCompleted");
	            	int taskPriority = rs.getInt("taskPriority");
	            	task = new Task(taskID, taskType, taskTitle, taskNotes, taskDuration, taskAssigned, taskCompleted, taskPriority);
	            	return task;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
		 return null;
	 }
	 
	 public void pushSingleTask(Task task) {
		 connect();
		 String sql = "INSERT INTO task (taskID, taskType, taskTitle, taskNotes, taskDuration, taskAssigned, taskCompleted, taskPriority)"
		 		+ "VALUES ('" + task.getTaskID() + "', '" + task.getTaskType() + "', '" + task.getTaskTitle()
		 		+ "', '" + task.getTaskNotes() + "', '" + task.getTaskDuration() + "', '" + task.getTaskAssigned() + "', '" + task.getTaskCompleted()
		 		+ "', '" + task.getTaskPriority() + "');";
		 
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
		 connect();
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
	            	return user;

		 }
		 catch(SQLException e)
		 {
			 System.out.println(e.toString());
		 }
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
		 }
		 catch(SQLException e) {
			 System.out.println(e.toString());
		 }
	 }
}