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
	

}
