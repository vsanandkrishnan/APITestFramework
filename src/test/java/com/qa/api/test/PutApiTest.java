package com.qa.api.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.client.RestClient;
import com.qa.api.data.Users;
import com.qa.api.testbase.TestBase;

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
	public void putTest() {
		
	}

}
