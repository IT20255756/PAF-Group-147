public class Feedback {

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

	public String insertFeedback(String feedbackType, String feedbackDate, String feedbackDesc)
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
			String query = " insert into feedback "
			+ "(`feedbackID`,`feedbackType`,`feedbackDate`,`feedbackDesc`)"
			+ " values (?, ?, ?, ?, ?, ?)";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, feedbackType);
			preparedStmt.setString(3, feedbackDate);
			preparedStmt.setString(3, feedbackDesc);
		
			// execute the statement
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
	
	public String updateFeedback(String feedbackID, String feedbackType, String feedbackDate, String feedbackDesc)
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
			String query = "UPDATE feedback SET feedbackType=?,feedbackDate=?,feedbackDesc=?"
			+ "WHERE feedbackID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, feedbackType);
			preparedStmt.setString(2, feedbackDate);
			preparedStmt.setString(2, feedbackDesc);
			preparedStmt.setInt(6, Integer.parseInt(feedbackID));
			
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
	
	public String deleteFeedback(String feedbackID)
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
			String query = "delete from feedback where feedbackID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(feedbackID));
			
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
