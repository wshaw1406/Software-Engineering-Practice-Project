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
                	+ "//localhost/software_eng", "root", "");
            statement = con.createStatement();
        }
	catch(SQLException sqlEx)
	{
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
}
    
