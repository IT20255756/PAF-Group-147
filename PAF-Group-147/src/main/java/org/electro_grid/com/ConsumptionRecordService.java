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

@Path("/ConsumptionRecord")
public class ConsumptionRecordService {

ConsumptionRecord itemObj = new ConsumptionRecord();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readConsumptionRecord()
	{
		return itemObj.readConsumptionRecord();
	}
	
}
