import org.electro_grid.model.Connections;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Connections")
public class ConnectionService {
	
    Connections ConneObj = new Connections();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readCustomer()
	{
		return ConneObj.readConnection();
	}
	
	

}
