package com.youtube.rest.status;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.youtube.util.ParseHttpHeader;

@Path("/v1/header")
public class V1_header {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts(@Context HttpServletRequest hsr) throws Exception {
		
		Response rb = null;	
		JSONObject json = new JSONObject();
		
		try {

			json = ParseHttpHeader.parseHSR(hsr);
			System.out.println("json header: " + json);

			String browser = json.optString("User-Agent", "NOT-FOUND");

			rb = Response.ok( browser ).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rb;
	}
	
}