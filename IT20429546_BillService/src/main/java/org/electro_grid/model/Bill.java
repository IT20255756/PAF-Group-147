package org.electro_grid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "Group20");
			
			System.out.println("Connected");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String readBillingDetails(String accNo)
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
			output = "<table border='1'><tr><th>Bill Date</th><th>Total Amount</th>" +
			"<th>Dues</th>" +
			"<th>Customer ID</th>";
			
			
			String query = "SELECT b.billDate, b.totAmount, b.dues, b.customerID FROM bill b, connection n, customer c WHERE b.customerID=c.customerID and n.customerID=n.customerID and n.accountNo='?'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String billID = Integer.toString(rs.getInt("billId"));
				String billDate = rs.getString("billDate");
				String totAmount = rs.getString("totAmount");
				String dues = rs.getString("dues");
				String customerID = rs.getString("customerId");
				
				// Add into the html table
				output += "<tr><td>" + billDate + "</td>";
				output += "<td>" + totAmount + "</td>";
				output += "<td>" + dues + "</td>";
				output += "<td>" + customerID + "</td></tr>";
				
				// buttons
				/*output += "<td><form method='post'>"
				+ "<input name='btnPay' type='submit' value='Pay now'"
				+ "class='btn btn-danger'>"
				+ "<input name='billID' type='hidden' value='" + billID
				+ "'>" + "</form></td></tr>";*/
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

}