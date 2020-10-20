package com.qa.api.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//GET Method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);// http Get Request
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// HIT the Get url       
		return httpResponse;
	}
	
	
	// GET Method with headers
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);// http Get Request
		
		
		//Adding the headers
		for(Map.Entry<String, String> entry:headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue() );
		}
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// HIT the Get url       
		return httpResponse;
	}

}
