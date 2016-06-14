package com.youtube.rest.inventory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.json.JSONWithPadding;

@Path("/v4/inventory")
public class V4_inventory {

	@GET
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({"application/x-javascript", MediaType.APPLICATION_JSON})
	public JSONWithPadding jsonpPost(
				@QueryParam("callback") String callback,
				@QueryParam("data") String data,
				@Context HttpServletRequest clientRequest)
				throws Exception {
		
		String returnString = null;
		
		try {
			
			JSONObject obj = new JSONObject(data);
			System.out.println("obj: " + obj);

			returnString = obj.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			
			JSONObject errorObj = new JSONObject();
			errorObj.put("code", "500");
			errorObj.put("message", "Internal Server Error");
			errorObj.put("error", e.getMessage());
			returnString = errorObj.toString();
			return new JSONWithPadding(returnString, callback);
		}
		return new JSONWithPadding (returnString, callback);
	}
	
}