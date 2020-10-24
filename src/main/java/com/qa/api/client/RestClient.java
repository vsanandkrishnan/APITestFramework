package com.qa.api.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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

}
