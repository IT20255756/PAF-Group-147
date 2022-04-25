package org.electro_grid.com;

import org.electro_grid.model.Payment;


import java.sql.Date;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
//import org.jsoup.*;
//import org.jsoup.parser.*;
//import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	
	Payment pay = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readPayment() {
		return pay.readPayment();
	}
	
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("payType") String payType, @FormParam("cardNo") String cardNo,
			@FormParam("expDate") Date expDate, @FormParam("cvv") String cvv, @FormParam("billId") String billId)

	{
		String output = pay.insertPayment(payType, cardNo, expDate, cvv, billId);
		return output;
	}





}
