package org.electro_grid.model;

import java.sql.*;

public class ConsumptionRecord {

	//A common method to connect to the DB
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
	
	public String readConsumptionRecord()
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
			output = "<table border='1'><tr><th>Record Date</th><th>Meter Number</th>" +
			"<th>Consumed Units</th>" +
			"<th>Pay Status</th>";
			
			String query = "select * from consumptionrecord";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String consumptionID = Integer.toString(rs.getInt("consumptionID"));
				String recordDate = rs.getString("recordDate");
				String meterNo = rs.getString("meterNo");
				String consumedUnits = Integer.toString(rs.getInt("consumedUnits"));
				String payStatus = rs.getString("payStatus");
				
				// Add into the html table
				output += "<tr><td>" + recordDate + "</td>";
				output += "<td>" + meterNo + "</td>";
				output += "<td>" + consumedUnits + "</td>";
				output += "<td>" + payStatus + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'"
				+ "class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='service.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove'"
				+ "class='btn btn-danger'>"
				+ "<input name='consumptionID' type='hidden' value='" + consumptionID
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
	
	public String insertConsumptionRecord(String recordDate, String meterNo, int consumedUnits, boolean payStatus)
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
			String query = " insert into consumptionrecord "
			+ "(`consumptionID`,`recordDate`,`meterNo`,`consumedUnits`,`payStatus`)"
			+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, recordDate);
			preparedStmt.setString(3, meterNo);
			preparedStmt.setInt(4, consumedUnits);
			preparedStmt.setBoolean(5, payStatus);
			
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
	
}
