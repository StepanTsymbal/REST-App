package com.youtube.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class HttpClient {

	private static Client httpClient = null;
	
	@SuppressWarnings("unused") 
	private static Client getHttpClient() throws Exception {
		
		try {
			if(httpClient != null) {
				return httpClient;
			}
			ClientConfig config = new DefaultClientConfig();
			httpClient = Client.create(config);
			return httpClient;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unused")
	private static Client getSslHttpClient() throws Exception {
		
		try {
			if(httpClient != null) {
				return httpClient;
			}
			
			ClientConfig config = new DefaultClientConfig(); 
			SSLContext ctx = SSLContext.getDefault();
			
		    config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(new HostnameVerifier() {
	            @Override
	            public boolean verify(String arg0, SSLSession arg1) {
	                return true;
	            }}, ctx));
			Client http_client = Client.create(config);
			
			httpClient = http_client;
			return httpClient;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}