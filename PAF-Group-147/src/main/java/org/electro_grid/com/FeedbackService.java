import com.electro_grid.model.Feedback;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Feedback")
public class FeedbackService {
	
	Feedback feedbackObj = new Feedback();
	
	//Insert Function 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFeedback(@FormParam("feedbackType") String feedbackType,
					@FormParam("feedbackDate") String feedbackDate,
					@FormParam("feedbackDesc") String feedbackDesc)
	{
		String output = feedbackObj.insertFeedback(feedbackType, feedbackDate, feedbackDesc);
		return output;
	}

	
	//Update Function
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateFeedback(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		String feedbackID = itemObject.get("feedbackID").getAsString();
		String feedbackType = itemObject.get("feedbackType").getAsString();
		String feedbackDate = itemObject.get("feedbackDate").getAsString();
		String feedbackDesc = itemObject.get("feedbackDesc").getAsString();
		String output = feedbackObj.updateFeedback(feedbackID, feedbackType, feedbackDate, feedbackDesc);
		
		return output;
	}
	
	//Delete Function
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteFeedback(String itemData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <feedbackID>
		String feedbackID = doc.select("feedbackID").text();
		String output = feedbackObj.deleteFeedback(feedbackID);
		
		return output;
	}
}
