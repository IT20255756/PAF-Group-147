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