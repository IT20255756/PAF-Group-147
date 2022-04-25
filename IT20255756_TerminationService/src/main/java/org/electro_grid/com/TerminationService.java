package org.electro_grid.com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.electro_grid.model.Termination;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Termination")
public class TerminationService {
	
	//Creating an object from termination class
	Termination termObj = new Termination();
	
	//read method
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readConsumptionRecord()
	{
		return termObj.readTerminationDetails();
	}

	//update method
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateConsumptionRecord(String connectionData)
	{
		//Convert the input string to a JSON object
		JsonObject termObject = new JsonParser().parse(connectionData).getAsJsonObject();
		
		//Read the values from the JSON object
		String connectionStatus = termObject.get("connectionStatus").getAsString();
		String connectionId = termObject.get("connectionId").getAsString();
		
		String output = termObj.updateConsumptionRecord(connectionId,connectionStatus);
		return output;
	}
	
}
