package com.qa.api.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1.GET Method

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);// http Get Request

		// Status code
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// HIT the Get url
//		int responseStatus = httpResponse.getStatusLine().getStatusCode();
//		System.out.println("Response status--->" + responseStatus);

	
         
		return httpResponse;
		
		//Status code
//		int responseStatus = httpResponse.getStatusLine().getStatusCode();
//		System.out.println("Response status--->" + responseStatus);
		
		// Json String
		//String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");// String reponse
		// Converting to JSON
//		JSONObject responseJson = new JSONObject(responseString);
//		System.out.println("reposnse JSON from API--->/n" + responseJson);
//
//		// All Header
//		Header[] headerArray = httpResponse.getAllHeaders();
//
//		HashMap<String, String> allHeaders = new HashMap<String, String>();
//
//		for (Header header : headerArray) {
//			allHeaders.put(header.getName(), header.getValue());
//
//		}
//
//		System.out.println("Headers  array---> " + allHeaders);

	}

}
