package com.qa.api.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.client.RestClient;
import com.qa.api.testbase.TestBase;

public class GetApiTest extends TestBase {

	TestBase testBase;
	String apiurl;
	String serviceUrl;
	String URL;
	RestClient restClient;
    CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		testBase = new TestBase();
		apiurl = prop.getProperty("url");
		serviceUrl = prop.getProperty("serviceurl");
		URL = apiurl + serviceUrl;



	}

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		httpResponse=restClient.get(URL);
		
		//Status Code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code output is  "+statusCode);
		
		//Asseting Status code
		Assert.assertEquals(statusCode, RESPONSE_CODE_200,"The JSON response failed");
		
		//Response
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		//Converting to JSON
		JSONObject jsonResponse= new JSONObject(responseString);
		
		
		
		
		//Formating JSON
		
//		System.out.println("The JSON reponse is  \n" +jsonResponse);
		String formattedJSON= jsonResponse.toString(4);//Adds 4 indentations to each line.
		System.out.println("The JSON response formatted \n"+formattedJSON);
		testBase.copyFile(formattedJSON);
		
		
		//All Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		
		
		HashMap<String , String> headerMap= new HashMap<String,String>();
		
		for(Header header:headerArray) {
			headerMap.put(header.getName(), header.getValue());
		}
		
		System.out.println("The headers are "+headerMap);
		

	}
	
	
	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		
		
		HashMap<String,String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		
		httpResponse=restClient.get(URL,headerMap);
		
		//Status Code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code output is  "+statusCode);
		
		//Asseting Status code
		Assert.assertEquals(statusCode, RESPONSE_CODE_200,"The JSON response failed");
		
		//Response
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		//Converting to JSON
		JSONObject jsonResponse= new JSONObject(responseString);
		
		
		
		
		//Formating JSON
		
//		System.out.println("The JSON reponse is  \n" +jsonResponse);
		String formattedJSON= jsonResponse.toString(4);//Adds 4 indentations to each line.
		System.out.println("The JSON response formatted \n"+formattedJSON);
		testBase.copyFile(formattedJSON);
		
		
		//All Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		
		
		HashMap<String , String> headerMapContent= new HashMap<String,String>();
		
		for(Header header:headerArray) {
			headerMapContent.put(header.getName(), header.getValue());
		}
		
		System.out.println("The headers are "+headerMap);
		

	}

}
