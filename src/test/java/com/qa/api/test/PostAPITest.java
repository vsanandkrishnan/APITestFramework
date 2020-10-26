package com.qa.api.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.client.RestClient;
import com.qa.api.data.Users;
import com.qa.api.testbase.TestBase;
import com.qa.api.util.JsonUtils;

public class PostAPITest extends TestBase {

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
	public void postAPITest() throws  JsonMappingException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");

		// Jackson API
		ObjectMapper mapper = new ObjectMapper();
		users = new Users("Anandkrishnan", "Manager");
		mapper.writeValue(new File("src//main//java//com//qa//api//data//users.json"),users);
		
		//Mapper to JSON string//Martialing
		String jsonString=mapper.writeValueAsString(users);
		//System.out.println(jsonString);
		
		//Calling POST call
		httpResponse=restClient.post(URL, jsonString, headerMap);
		
		//Check the status code
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is equal to  \n"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_CODE_201);
		
		//Check the JSON
		String jsonOutput= EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		
		//Converting JSON
		JSONObject responseJSON = new JSONObject(jsonOutput);
		System.out.println("The response code is \n"+ responseJSON.toString(4));
		
		
		//Validating JSON
		Users usersResponse=mapper.readValue(jsonOutput, Users.class);
		System.out.println(usersResponse);
		
		
		boolean nameCheck =users.getName().equals(usersResponse.getName());
		boolean jobCheck= users.getJob().equals(usersResponse.getJob());
		
		Assert.assertTrue(nameCheck);
		Assert.assertTrue(jobCheck);
		
		

	}
	
	@Test
	public void newPOSTtest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		httpResponse=restClient.filePost(URL, "post", headerMap);
		
		//status code assertion
		int statusCode= JsonUtils.responseStatusCode(httpResponse);
		System.out.println("The Status code  \n"+ statusCode);
		JsonUtils.assertPOSTStatusCode(statusCode);
        
//		String json= EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
//		JSONObject jsonObject = new JSONObject(json);
		
		//getting the response
		String jsonResponse = JsonUtils.responseString(httpResponse);
		System.out.println("The json output \n"+jsonResponse);
		
		//Writing to an outputFile
	//	JsonUtils.copyResponsetoFile(httpResponse, "NewPOST");
		
	}

}
