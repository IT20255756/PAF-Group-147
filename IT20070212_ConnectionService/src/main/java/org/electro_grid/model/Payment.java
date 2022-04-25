package org.electro_grid.model;

import java.sql.*;
 
public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_lab", "root", "maatha");
			//System.out.println("Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String payType, String cardNo, Date expDate, String cvv, String billId)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement

			String query = "insert into payment (`payID`,`payType`,`cardNo`,`expDate`,`cvv`,`billID`)" + " values (?,?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, payType);
			preparedStmt.setDouble(3, Double.parseDouble(cardNo));
			preparedStmt.setDate(4, expDate);
			preparedStmt.setInt(5, Integer.parseInt(cvv));
			preparedStmt.setInt(6, Integer.parseInt(billId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}

		catch (Exception e)
		{
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment()
	{
		String output = "";
		
		try
		{

			Connection con = connect();
	 
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Payment Type</th><th>Card Number</th><th>Expiration</th><th>Security Code</th><th>Payment Date</th><th>Connection ID</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String payID = Integer.toString(rs.getInt("payID"));
				String payType = rs.getString("payType");
				long cardNo = rs.getLong("cardNo");
				Date expDate = rs.getDate("expDate");
				String cvv = rs.getString("cvv");
				Date payDate = rs.getDate("payDate");
				//String connectionId = rs.getString("connectionId");
	 
				// Add into the html table
				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + payType + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + expDate + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + payDate + "</td>";
				output += "</tr>";
				//output += "<td>" + connectionId + "</td>";
				
				
			}

			con.close();
			// Complete the html table
			
			output += "</table>";
	}
		catch (Exception e)
		{
			output = "Error while reading the payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	

}
