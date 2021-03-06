package org.electro_grid.model;

import java.sql.*;

public class Customer
{	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, Customername, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electro_grid", "root", "root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	//Insert Function
	public String insertCustomer(String customerName, String nic, String address, String mobileNo, String email)
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
			String query = " insert into customer "
						+ "(`customerId`,`customerName`,`nic`,`address`,`mobileNo` ,`email`)"
						+ " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, mobileNo);
			preparedStmt.setString(6, email);
			
			//Execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting Customer Record.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Read Function
	public String readCustomer()
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
			output = "<table border='1'><tr><th>Customer Name</th><th>NIC</th>" +
			"<th>Address</th>" +
			"<th>Mobile Number</th>" +
			"<th>Email</th>" +
			"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while (rs.next())
			{
				String customerId = Integer.toString(rs.getInt("customerId"));
				String customerName = rs.getString("customerName");
				String nic = rs.getString("nic");
				String address = rs.getString("address");
				String mobileNo = rs.getString("mobileNo");
				String email = rs.getString("email");
				
				//Add into the html table
				output += "<tr><td>" + customerName + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + mobileNo + "</td>";
				output += "<td>" + email + "</td>";
				
				//Buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'"
				+ "class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='service.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove'"
				+ "class='btn btn-danger'>"
				+ "<input name='customerId' type='hidden' value='" + customerId
				+ "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			//Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the customer details.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	//Update Function
	public String updateCustomer(String customerId, String customerName, String nic, String address, String mobileNo, String email)
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
			String query = "UPDATE customer SET customerName=?,nic=?,address=?,mobileNo=?, email=?"
			+ "WHERE customerId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, mobileNo);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, customerId);
			
			//Execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}
		
		return output;	
	}
	
	//Delete Function
	public String deleteCustomer(String customerId)
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
			
			//Create a prepared statement
			String query = "delete from customer where customerId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setInt(1, Integer.parseInt(customerId));
			
			//Execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Customer.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
}
		
