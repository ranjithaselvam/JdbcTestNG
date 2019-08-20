package com.databaseTestNG;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase {
	private Connection connection;
	 private static Statement statement;
	 private static ResultSet rs;

	 @BeforeClass
	public void setup()
	{
		String databaseURL = "jdbc:sqlserver://ATMECSINDT-088\\SQLEXPRESS;database=project;integratedSecurity=true";
		 
		 connection = null;
        try {
        	
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
	}
	 @Test
	public void getperson()
	{
		 try {
			 java.sql.Statement statement=null;
	            String query = "select * from persons";
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);

	            while(rs.next()){
	                int id= rs.getInt(1);
	                String firstName= rs.getString(2);
	                String lastname=rs.getString(3);
	                String age=rs.getString(4);
	                String address= rs.getString(5);
	                System.out.println(id+"\t"+firstName+"\t"+lastname+"\t"+age+"\t"+address);
	            }
	        } catch (SQLException ex) {
	           ex.printStackTrace();
	        }
	}
		 @AfterClass
		    public void closing() {
		      if (connection != null) {
		                try {
		                    System.out.println("Closing Database Connection...");
		                    connection.close();
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		            }
		 }
}