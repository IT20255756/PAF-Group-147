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
	
	Feedback itemObj = new Feedback();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFeedback(@FormParam("feedbackType") String feedbackType,
					@FormParam("feedbackDate") String feedbackDate,
					@FormParam("feedbackDesc") String feedbackDesc)
	{
		String output = itemObj.insertFeedback(feedbackType, feedbackDate, feedbackDesc);
		return output;
	}

}
