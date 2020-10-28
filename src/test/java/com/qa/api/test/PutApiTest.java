package com.qa.api.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.client.RestClient;
import com.qa.api.data.Users;
import com.qa.api.testbase.TestBase;
import com.qa.api.util.JsonUtils;

public class PutApiTest extends TestBase {
	TestBase testBase;
	String apiurl;
	String serviceUrl;
	String URL;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	Users users;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		testBase = new TestBase();
		apiurl = prop.getProperty("url");
		serviceUrl = prop.getProperty("serviceurl");
		URL = apiurl + serviceUrl;

	}
	
	@Test
	public void putTest() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		HashMap<String, String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		
		httpResponse=restClient.filePut(URL, "put", headerMap);
		int statusCode=JsonUtils.responseStatusCode(httpResponse);
		System.out.println("The status response from the server:\t"+statusCode);
		JsonUtils.assertNormalStatusCode(statusCode);
		
		String responseString=JsonUtils.responseString(httpResponse);
		System.out.println("The response for the current get call:");
		System.out.println(responseString);
		
		
		
		//Copying the file
		JsonUtils.copyResponsetoFile(responseString, "PUT");
		
		
		
		
		
		
		
		
	}

}
