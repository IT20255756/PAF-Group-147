package org.electro_grid.model;

import java.sql.*;

public class Feedback {

	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electro_grid", "root", "root");
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		return con;
	}

	//Insert Function
	public String insertFeedback(String feedbackType, String feedbackDate, String feedbackDesc)
	{
		String output = "";
	
		try
		{
			Connection con = connect();
		
			//Check DB Connection
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
		
		
			//Create a Prepared Statement
			String query = " insert into feedback "
			+ "(`feedbackId`,`feedbackType`,`feedbackDate`,`feedbackDesc`)"
			+ " values (?, ?, ?, ?, ?, ?)";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			//Binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, feedbackType);
			preparedStmt.setString(3, feedbackDate);
			preparedStmt.setString(3, feedbackDesc);
		
			//Execute the statement
			preparedStmt.execute();
			con.close();
		
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the feedback.";
			System.err.println(e.getMessage());
		}
	
		return output;
	}
	
	//Read Function
	public String readFeedback()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//Check DB Connection
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			//Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Feedback Type</th><th>Feedback Date</th>" +
			"<th>Feedback Date</th>" +
			"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from feedback";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while (rs.next())
			{
				String feedbackId = Integer.toString(rs.getInt("feedbackId"));
				String feedbackType = rs.getString("feedbackType");
				String feedbackDate = rs.getString("feedbackDate");
				String feedbackDesc = rs.getString("feedbackDesc");
				
				//Add into the html table
				output += "<tr><td>" + feedbackType + "</td>";
				output += "<td>" + feedbackDate + "</td>";
				output += "<td>" + feedbackDesc + "</td>";
				
				//Buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'"
				+ "class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='service.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove'"
				+ "class='btn btn-danger'>"
				+ "<input name='feedbackId' type='hidden' value='" + feedbackId
				+ "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			//Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	//Update Function
	public String updateFeedback(String feedbackId, String feedbackType, String feedbackDate, String feedbackDesc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//Check DB Connection
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
			
			//Create a Prepared Statement
			String query = "UPDATE feedback SET feedbackType=?,feedbackDate=?,feedbackDesc=?"
			+ "WHERE feedbackId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setString(1, feedbackType);
			preparedStmt.setString(2, feedbackDate);
			preparedStmt.setString(2, feedbackDesc);
			preparedStmt.setInt(6, Integer.parseInt(feedbackId));
			
			//Execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the feedback.";
			System.err.println(e.getMessage());
		}
		
		return output;	
	}
	
	//Delete Function
	public String deleteFeedback(String feedbackId)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//Check DB Connection
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			
			//Create a Prepared Statement
			String query = "delete from feedback where feedbackId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setInt(1, Integer.parseInt(feedbackId));
			
			//Execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the feedback.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
}
