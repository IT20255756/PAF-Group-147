package org.electro_grid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Termination {

	//A common method to connect to the Database
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "Group20");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	//Read details for termination process
	public String readTerminationDetails()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Connection ID</th><th>Customer ID</th>" +
			"<th>Total Amount</th>" +
			"<th>Pay Status</th>" + 
			"<th>Bill Date</th>" + 
			"<th>Connection Status</th>" + 
			"<th>Difference</th>"; 
			
			String query = "SELECT n.connectionId, b.customerId, b.totAmount, c.payStatus, b.billDate, n.connectionStatus, datediff(CURDATE(), b.billDate) as difference\r\n"
					+ "FROM electro_grid.bill b, electro_grid.connection n, electro_grid.consumptionrecord c  \r\n"
					+ "WHERE b.customerId=n.customerId and c.connectionId=n.connectionId and c.payStatus='0' and datediff(CURDATE(), b.billDate)>=60 ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while (rs.next())
			{
				String connectionId = Integer.toString(rs.getInt("connectionId"));
				String customerId = Integer.toString(rs.getInt("customerId"));
				String totAmount = rs.getString("totAmount");
				String payStatus = rs.getString("payStatus");
				String billDate = rs.getString("billDate");
				String connectionStatus = rs.getString("connectionStatus");
				String difference = rs.getString("difference");
				
				// Add into the html table
				output += "<tr><td>" + connectionId + "</td>";
				output += "<td>" + customerId + "</td>";
				output += "<td>" + totAmount + "</td>";
				output += "<td>" + payStatus + "</td>";
				output += "<td>" + billDate + "</td>";
				output += "<td>" + connectionStatus + "</td>";
				output += "<td>" + difference + "</td></tr>";
					
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	
	}
	
	//Method to update the connection status
	public String updateConsumptionRecord(String connectionId, String connectionStatus)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE connection SET connectionStatus=? WHERE connectionId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setBoolean(1, Boolean.parseBoolean(connectionStatus));
			preparedStmt.setInt(2, Integer.parseInt(connectionId));
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Connection status is updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the connection status.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
