import org.electro_grid.model.Connections;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Connections")
public class ConnectionService {
	
    Connections ConneObj = new Connections();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readCustomer()
	{
		return ConneObj.readConnection();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertConnection(@FormParam("accountNo") String accountNo,
					@FormParam("connectionName") String connectionName)
	{
		String output = ConneObj.insertConnection(accountNo, connectionName);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateConnection(String connectionData)
	{
		//Convert the input string to a JSON object
		JsonObject ConneObject = new JsonParser().parse(connectionData).getAsJsonObject();
		
		//Read the values from the JSON object
		String connectionID = ConneObject.get("connectionID").getAsString();
		String accountNo = ConneObject.get("accountNo").getAsString();
		String connectionName = ConneObject.get("connectionName").getAsString();
		
		String output = ConneObj.updateConnection(connectionID, accountNo,connectionName);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteConnection(String connectionData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(connectionData, "", Parser.xmlParser());
		
		//Read the value from the element <customerID>
		String connectionID = doc.select("connectionID").text();
		String output = ConneObj.deleteConnection(connectionID);
		
		return output;
	}

}
