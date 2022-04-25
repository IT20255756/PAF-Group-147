package org.electro_grid.com;

import org.electro_grid.model.Customer;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customer")
public class CustomerService
{
	Customer customerObj = new Customer();
	
	//Read Function
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readCustomer()
	{
		return customerObj.readCustomer();
	}
	
	//Insert Function
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertCustomer(@FormParam("customerName") String customerName,
					@FormParam("nic") String nic,
					@FormParam("address") String address,
					@FormParam("mobileNo") String mobileNo,
					@FormParam("email") String email)
	{
		String output = customerObj.insertCustomer(customerName, nic, address, mobileNo, email);
		return output;
	}
	
	//Update Function
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateCustomer(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		String customerId = itemObject.get("customerId").getAsString();
		String customerName = itemObject.get("customerName").getAsString();
		String nic = itemObject.get("nic").getAsString();
		String address = itemObject.get("address").getAsString();
		String mobileNo = itemObject.get("mobileNo").getAsString();
		String email = itemObject.get("email").getAsString();
		String output = customerObj.updateCustomer(customerId, customerName, nic, address, mobileNo, email);
		
		return output;
	}
	
	//Delete Function
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteCustomer(String itemData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <customerId>
		String customerId = doc.select("customerId").text();
		String output = customerObj.deleteCustomer(customerId);
		
		return output;
	}
}
