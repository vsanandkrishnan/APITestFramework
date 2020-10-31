package com.qa.api.test;

import java.io.IOException;
import com.qa.api.testbase.TestBase;
import com.qa.api.util.JsonUtils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.client.RestClient;
import com.qa.api.data.Users;


public class DeleteApiTest extends TestBase{
	
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
	public void deleteTest() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		httpResponse=restClient.delete(URL);
		int statusCode=JsonUtils.responseStatusCode(httpResponse);
		System.out.println("The status code from delete " +statusCode);
		Assert.assertEquals(statusCode, 204);
		
	}
	
	

}
