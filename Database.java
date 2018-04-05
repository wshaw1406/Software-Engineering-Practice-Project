/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software_eng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Will
 */
public class Database {
    
    private static Connection con;
    private static Statement statement;
    
    public void Database() {

    }
    
    public void connect()
    {
		try
		{
	            con = DriverManager.getConnection("jdbc:mysql:"
	                	+ "//localhost:3306/test", "root", "root");
	            statement = con.createStatement();
	            }
		catch(SQLException sqlEx)
		{
	            System.out.println(sqlEx.toString());
	            System.out.println("Cannot connect to database!");
	            System.exit(1);
		}
    }
    
    public void query() 
    {
        ResultSet rs = null;
        try
        {   
            rs = statement.executeQuery("SELECT * FROM users");
            while(rs.next())
            {
                    System.out.println(rs.getObject(2));

            }
        }
        catch(SQLException e)
        {
            e.toString();
        }
    }
    
    public void pullUsers()
    {
        connect();
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try
        {
            rs = statement.executeQuery("SELECT userID, username, accountType, name FROM users");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i=1; i<= rsmd.getColumnCount(); i++)
                {
                    System.out.println(rs.getObject(i));
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println("Failed to retrieve users.");
        }
    }
    
    public void pushNewUser(String userID, String username, String passwordHash, String accountType, String name)
    {
        connect();
        try
        {
            statement.executeUpdate("INSERT INTO users (userID, username, passwordHash, accountType, name)"
                    + " values(" + userID + ", " + username + ", " 
                    + passwordHash + ", " + accountType + ", " + name + ");");
        }
        catch(SQLException e)
        {
            System.out.println("Failed to save new user to database.");
        }
    }
    
    public void pushUsers(String userID, String username, String name)
    {
        connect();
        try
        {
            statement.executeUpdate("INSERT INTO users (userID, username, name) values(" + userID + ", " + username + ", " + name + ");");
        }
        catch(SQLException e)
        {
            System.out.println("Failed to save users to database.");
        }
    }
    
    public List<Task> pullTasks()
    {
        connect();
        ResultSet rs = null;
        List<Task> tasks = new ArrayList<Task>();
        try
        {
            rs = statement.executeQuery("SELECT * FROM task");
            while(rs.next())
            {
            	String id = rs.getString("taskID");
            	String type = rs.getString("taskType");
            	String title = rs.getString("taskTitle");
            	String notes = rs.getString("taskNotes");
            	
            	tasks.add(new Task(id,type,title,notes));
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        
        return tasks;
    }
    
    public void pushTask(String taskID, String taskType, String taskTitle, String taskNotes)
    {
        connect();
        try
        {
            statement.executeUpdate("INSERT INTO task (taskID, taskType, taskTitle, taskNotes)"
                    + " values(" + taskID + ", " + taskType + ", " + taskTitle + ", " + taskNotes + ");");
        }
        catch(SQLException e)
        {
            System.out.println("Failed to save tasks to database.");
        }
    }
    
    public void pushAllTasks(ArrayList<Task> tasks)
    {       
        connect();
        try
        {
            for(Task task : tasks)
            {
                statement.executeUpdate("INSERT INTO task (taskID, taskType, taskTitle, taskNotes)"
                    + " values(" + task.getTaskID() + ", " + task.getTaskType() 
                    + ", " + task.getTaskTitle() + ", " + task.getTaskNotes() + ");");
            }
            
        }
        catch(SQLException e)
        {
            System.out.println("Failed to save tasks to database.");
        }
    }
    
        
    public void pullCaretakers()
    {
        connect();
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try
        {
            rs = statement.executeQuery("SELECT * FROM caretaker");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i=1; i<= rsmd.getColumnCount(); i++)
                {
                    System.out.println(rs.getObject(i));
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println("Failed to retrieve caretakers.");
        }
    }
    
    public void pullAllocatedTasks()
    {
        connect();
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try
        {
            rs = statement.executeQuery("SELECT * FROM caretaker_task");
            rsmd = rs.getMetaData();
            while(rs.next())
            {
                for(int i=1; i<= rsmd.getColumnCount(); i++)
                {
                    System.out.println(rs.getObject(i));
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println("Failed to retrieve allocated tasks.");
        }
    }
}
    
