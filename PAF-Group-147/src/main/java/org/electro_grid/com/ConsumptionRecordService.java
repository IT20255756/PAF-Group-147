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
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateConsumptionRecord(String consumptionData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(consumptionData).getAsJsonObject();
		
		//Read the values from the JSON object
		String consumptionID = itemObject.get("consumptionID").getAsString();
		String recordDate = itemObject.get("recordDate").getAsString();
		String meterNo = itemObject.get("meterNo").getAsString();
		String consumedUnits = itemObject.get("consumedUnits").getAsString();
		String payStatus = itemObject.get("payStatus").getAsString();
		
		String output = itemObj.updateConsumptionRecord(consumptionID, recordDate, meterNo, consumedUnits, payStatus);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteConsumptionRecord(String consumptionData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser());
		
		//Read the value from the element <serviceID>
		String consumptionID = doc.select("consumptionID").text();
		String output = itemObj.deleteConsumptionRecord(consumptionID);
		
		return output;
	}
	
}
