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
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				return con;
			}
			
		//insert new connections
		public String insertConnection(String accountNo, String connectionName)
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
				String query = " insert into connection "
							+ "(`connectionID`,`accountNo`,`connectionName`)"
							+ " values (?,?,?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, accountNo);
				preparedStmt.setString(3, connectionName);
				
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the connection.";
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
				output = "<table border='1'><tr><th>Account No</th><th>Connection Name</th>"
						 + "<th>Update</th><th>Remove</th> </tr>";
				
				String query = "select * from connection";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String connectionID = Integer.toString(rs.getInt("connectionID"));
					String accountNo = Integer.toString(rs.getInt("accountNo"));
					String connectionName = rs.getString("connectionName");
					
					
					// Add into the html table
					
					output += "<td>" + accountNo + "</td>";
					output += "<td>" + connectionName + "</td>";
			
					
					// buttons
					output += "<td> <input name='btnUpdate' type='button' value='Update'"
					+ "class='btn btn-secondary'></td>"
					+ "<td><form method='post' action='connection.jsp'>"
					+ "<input name='btnRemove' type='submit' value='Remove'"
					+ "class='btn btn-danger'>"
					+ "<input name='connectionID' type='hidden' value='" + connectionID
					+ "'>" + "</form></td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the connection.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}		
			

}
