package org.electro_grid.com;

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
	
}
	