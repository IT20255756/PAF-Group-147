package org.electro_grid.model;

import java.sql.*;


public class Connections {
	

			//A common method to connect to the DB
			private Connection connect()
			{
				Connection con = null;
			
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					
					//Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_lab", "root", "maatha");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				return con;
			}
		
			
			//insert new connections
			public String insertConnection(String accountNo, String connectionName, String serviceId, String customerId)
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
				String query = " insert into connection (`accountNo`,`connectionName`, `serviceId`, `customerId`) values (?,?,?,?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				//preparedStmt.setInt(1, 0); 
				preparedStmt.setString(1, accountNo);
				preparedStmt.setString(2, connectionName);
				preparedStmt.setInt(3, Integer.parseInt(serviceId));
				preparedStmt.setInt(4, Integer.parseInt(customerId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the connection details.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


       //retrieve connections
		public String readConnection()
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
				output = "<table border='1'><tr><th>Account No</th><th>Connection Name</th><th>Connection Date</th></tr>";
				
				String query = "select * from connection";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String connectionId = Integer.toString(rs.getInt("connectionId"));
					String accountNo = Integer.toString(rs.getInt("accountNo"));
					String connectionName = rs.getString("connectionName");
					Date connectionDate = rs.getDate("connectionDate");
					
					// Add into the html table
					
					output += "<td>" + accountNo + "</td>";
					output += "<td>" + connectionName + "</td>";
					output += "<td>" + connectionDate + "</td></tr>";
			
					
					
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the connection list.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
		
		
		//update connection details
		public String updateConnection(String connectionId, String accountNo, String connectionName)
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
				String query = "UPDATE connection SET accountNo=?,connectionName=? WHERE connectionId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, accountNo);
				preparedStmt.setString(2, connectionName);
				preparedStmt.setString(3, connectionId);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the connection.";
				System.err.println(e.getMessage());
				
			}
			
			return output;	
		}

		

		//delete connection		
		public String deleteConnection(String connectionId)
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
				String query = "delete from connection where connectionId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(connectionId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the connection.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
		
	}
			