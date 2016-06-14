package com.youtube.util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONObject;

public class ParseHttpHeader {

	public static JSONObject parseHSR(HttpServletRequest hsr) throws Exception {
		
		JSONObject header_profile = new JSONObject();
		
		try {
			Enumeration<String> headerNames  = hsr.getHeaderNames();
			
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = hsr.getHeader(headerName);
				header_profile.put(headerName, headerValue);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return header_profile;
	}
	
}