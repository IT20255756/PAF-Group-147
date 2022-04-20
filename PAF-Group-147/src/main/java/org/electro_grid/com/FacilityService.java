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

@Path("/Facility")
public class FacilityService
{
	Facility itemObj = new Facility();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	{
		return itemObj.readFacility();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFacility(@FormParam("serviceName") String serviceName,
					@FormParam("serviceType") String serviceType,
					@FormParam("unitCost") String unitCost,
					@FormParam("maxUnit") String maxUnit,
					@FormParam("addCost") String addCost)
	{
		String output = itemObj.insertFacility(serviceName, serviceType, unitCost, maxUnit, addCost);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateFacility(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		String serviceID = itemObject.get("serviceID").getAsString();
		String serviceName = itemObject.get("serviceName").getAsString();
		String serviceType = itemObject.get("serviceType").getAsString();
		String unitCost = itemObject.get("unitCost").getAsString();
		String maxUnit = itemObject.get("maxUnit").getAsString();
		String addCost = itemObject.get("addCost").getAsString();
		String output = itemObj.updateFacility(serviceID, serviceName, serviceType, unitCost, maxUnit, addCost);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteFacility(String itemData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <serviceID>
		String serviceID = doc.select("serviceID").text();
		String output = itemObj.deleteFacility(serviceID);
		
		return output;
	}
	

}
