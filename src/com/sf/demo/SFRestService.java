package com.sf.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 

@Path("/")
public class SFRestService {
	@POST
	@Path("/sfRestService")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response SfRestService(InputStream inputData) {
		StringBuilder sfStrBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputData));
			String line = null;
			while ((line = in.readLine()) != null) {
				sfStrBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error json Parsing: - "+e.getMessage());
		}
		System.out.println("Json Object  Received: " + sfStrBuilder.toString());
		return Response.status(200).entity(sfStrBuilder.toString()).build();
	}

	
 
	  
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "SFRestService  started::::::";
		return Response.status(200).entity(result).build();
	}

}