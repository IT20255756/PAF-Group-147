package org.electro_grid.com;

import org.electro_grid.model.*;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Billing")
public class BillService {

	Bill billObj = new Bill();
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	
	public String readBillingDetails(String accountNo)
	{
		//convert the input string into a JSON object
		JsonObject itemObject = new JsonParser().parse(accountNo).getAsJsonObject();
		
		//Read the values from the JSON object
		String accNo = itemObject.get("accNo").getAsString();
		
		String output = billObj.readBillingDetails(accountNo);
		return output;
		
		
	}
	
}