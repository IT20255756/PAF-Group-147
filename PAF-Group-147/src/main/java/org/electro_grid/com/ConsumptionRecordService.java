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
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertConsumptionRecord(@FormParam("recordDate") String recordDate,
	@FormParam("meterNo") String meterNo,
	@FormParam("consumedUnits") int consumedUnits,
	@FormParam("payStatus") boolean payStatus)
	{
		String output = itemObj.insertConsumptionRecord(recordDate, meterNo, consumedUnits, payStatus);
		return output;
	}
	
}
