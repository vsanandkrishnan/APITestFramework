package com.qa.api.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.client.RestClient;
import com.qa.api.data.Users;
import com.qa.api.testbase.TestBase;

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
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");

		// Jackson API
		ObjectMapper mapper = new ObjectMapper();
		users = new Users("Anandkrishnan", "Manager");
		mapper.writeValue(new File("src//main//java//com//qa//api//data//users.json"),users);

	}

}
