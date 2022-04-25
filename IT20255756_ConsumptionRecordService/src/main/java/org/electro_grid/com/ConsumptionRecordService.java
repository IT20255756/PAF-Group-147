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

	ConsumptionRecord conObj = new ConsumptionRecord();
	
	//Read method
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readConsumptionRecord()
	{
		return conObj.readConsumptionRecord();
	}
	
	//Insert method
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertConsumptionRecord(@FormParam("recordDate") String recordDate,
	@FormParam("meterNo") String meterNo,
	@FormParam("consumedUnits") int consumedUnits,
	@FormParam("payStatus") boolean payStatus,
	@FormParam("connectionId") int connectionId,
	@FormParam("tariffId") int tariffId)
	{
		String output = conObj.insertConsumptionRecord(recordDate, meterNo, consumedUnits, payStatus, connectionId, tariffId);
		return output;
	}
	
	//Update method
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
		
	public String updateConsumptionRecord(String consumptionData)
	{
		//Convert the input string to a JSON object
		JsonObject consObject = new JsonParser().parse(consumptionData).getAsJsonObject();
			
		//Read the values from the JSON object
		String consumptionId = consObject.get("consumptionId").getAsString();
		String recordDate = consObject.get("recordDate").getAsString();
		String meterNo = consObject.get("meterNo").getAsString();
		String consumedUnits = consObject.get("consumedUnits").getAsString();
		String payStatus = consObject.get("payStatus").getAsString();
			
		String output = conObj.updateConsumptionRecord(consumptionId, recordDate, meterNo, consumedUnits, payStatus);
		return output;
	}
	
	//Delete method
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteConsumptionRecord(String consumptionData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser());
		
		//Read the value from the element <serviceID>
		String consumptionId = doc.select("consumptionId").text();
		String output = conObj.deleteConsumptionRecord(consumptionId);
		
		return output;
	}

}