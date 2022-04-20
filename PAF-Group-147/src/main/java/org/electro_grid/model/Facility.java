package org.electro_grid.model;

import java.sql.*;

public class Facility
{	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "Hiranya123*");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	public String readFacility()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Service Name</th><th>Service Type</th>" +
			"<th>Unit Per Cost</th>" +
			"<th>Maximum Unit Limit</th>" +
			"<th>Additional Cost</th>" +
			"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from service";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String serviceID = Integer.toString(rs.getInt("serviceID"));
				String serviceName = rs.getString("serviceName");
				String serviceType = rs.getString("serviceType");
				String unitCost = Double.toString(rs.getDouble("unitCost"));
				String maxUnit = Integer.toString(rs.getInt("maxUnit"));
				String addCost = Double.toString(rs.getDouble("addCost"));
				
				// Add into the html table
				output += "<tr><td>" + serviceName + "</td>";
				output += "<td>" + serviceType + "</td>";
				output += "<td>" + unitCost + "</td>";
				output += "<td>" + maxUnit + "</td>";
				output += "<td>" + addCost + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'"
				+ "class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='service.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove'"
				+ "class='btn btn-danger'>"
				+ "<input name='serviceID' type='hidden' value='" + serviceID
				+ "'>" + "</form></td></tr>";
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
	
	public String insertFacility(String serviceName, String serviceType, String unitCost, String maxUnit, String addCost)
	{
		String output = "";
	
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
		
			
			// create a prepared statement
			String query = " insert into service "
						+ "(`serviceID`,`serviceName`,`serviceType`,`unitCost`,`maxUnit` ,`addCost`)"
						+ " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, serviceName);
			preparedStmt.setString(3, serviceType);
			preparedStmt.setDouble(4, Double.parseDouble(unitCost));
			preparedStmt.setInt(5, Integer.parseInt(maxUnit));
			preparedStmt.setDouble(6, Double.parseDouble(addCost));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateFacility(String serviceID, String serviceName, String serviceType, String unitCost, String maxUnit, String addCost)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
			
			// create a prepared statement
			String query = "UPDATE service SET serviceName=?,serviceType=?,unitCost=?,maxUnit=?, addCost=?"
			+ "WHERE serviceID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, serviceName);
			preparedStmt.setString(2, serviceType);
			preparedStmt.setDouble(3, Double.parseDouble(unitCost));
			preparedStmt.setInt(4, Integer.parseInt(maxUnit));
			preparedStmt.setDouble(5, Double.parseDouble(addCost));
			preparedStmt.setInt(6, Integer.parseInt(serviceID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;	
	}
	
	public String deleteFacility(String serviceID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			
			// create a prepared statement
			String query = "delete from items where serviceID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(serviceID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	
}
		

