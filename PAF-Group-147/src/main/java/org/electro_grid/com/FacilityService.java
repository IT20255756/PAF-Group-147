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
	

}
