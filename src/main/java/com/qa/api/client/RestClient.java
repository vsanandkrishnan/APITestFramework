package com.qa.api.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.qa.api.util.JsonUtils;

public class RestClient {

	// GET Method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);// http Get Request
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// HIT the Get url
		return httpResponse;
	}

	// GET Method with headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);// http Get Request
		// Adding the headers
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// HIT the Get url
		return httpResponse;
	}

	// POST Method with headers
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headers)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(entityString));

		// Headers
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}

	// POST Call using file
	public CloseableHttpResponse filePost(String url, String fileName, HashMap<String, String> headers)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String entityString = JsonUtils.fileReturnasString(fileName);
		httpPost.setEntity(new StringEntity(entityString));
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}

	// PUT Call Using Normal Entity String
	public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headers)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entityString));

		for (Map.Entry<String, String> mapHeader : headers.entrySet()) {
			httpPut.addHeader(mapHeader.getKey(), mapHeader.getValue());

		}
		
		CloseableHttpResponse  closeableHttpResponse= httpClient.execute(httpPut);
		return closeableHttpResponse;

	}
	
	//PUT calling done using file
	public CloseableHttpResponse filePut(String url,String fileName, HashMap<String, String> headers)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		String entityString=JsonUtils.fileReturnasString(fileName);
		httpPut.setEntity(new StringEntity(entityString));

		for (Map.Entry<String, String> mapHeader : headers.entrySet()) {
			httpPut.addHeader(mapHeader.getKey(), mapHeader.getValue());

		}
		
		CloseableHttpResponse  closeableHttpResponse= httpClient.execute(httpPut);
		return closeableHttpResponse;

	}
	
	
	//DELETE Test done without using header file
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		CloseableHttpResponse httpResponse=httpClient.execute(httpDelete);
		return httpResponse;		
		
	}

}
