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
			+ "(`feedbackID`,`feedbackType`,`feedbackDate`,`feedbackDesc`)"
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
	
	//Update Function
	public String updateFeedback(String feedbackID, String feedbackType, String feedbackDate, String feedbackDesc)
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
			+ "WHERE feedbackID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setString(1, feedbackType);
			preparedStmt.setString(2, feedbackDate);
			preparedStmt.setString(2, feedbackDesc);
			preparedStmt.setInt(6, Integer.parseInt(feedbackID));
			
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
	public String deleteFeedback(String feedbackID)
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
			String query = "delete from feedback where feedbackID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values
			preparedStmt.setInt(1, Integer.parseInt(feedbackID));
			
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
