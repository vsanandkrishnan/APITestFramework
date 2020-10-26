package com.qa.api.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

public class JsonUtils {

	public static JSONObject responsejson;
	public static String JSON_REQUEST_PATH = "apirequest//";
	public static final int STATUS_CODE_POST=201;
	public static final int STATUS_CODE_OUTPUT_WITHOUT_ERROR=200;
	public static String JSON_RESPONSE_PATH="response//";
	public static File fJSON;

	public static String getValueByJPath(JSONObject responsejson, String jpath) {
		Object obj = responsejson;
		for (String s : jpath.split("/"))
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if (s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}

	public static String fileReturnasString(String fileName) throws IOException {
		String filePath = JSON_REQUEST_PATH + fileName + ".json";
		fJSON = new File(filePath);
		String returnJSONString = FileUtils.readFileToString(fJSON);
		return returnJSONString;
	}

	public static int responseStatusCode(CloseableHttpResponse response) {
		int statusCode = response.getStatusLine().getStatusCode();
		return statusCode;
	}

	public static String responseString(CloseableHttpResponse response) throws ParseException, IOException {
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		responsejson = new JSONObject(responseString);
		String formattedOutput = responsejson.toString(4);
		return formattedOutput;
	}
	
	public static void copyResponsetoFile(CloseableHttpResponse response,String responseFileName) throws ParseException, IOException {
//		String reposnetobecopied=responseString(response);
		String pathname = JSON_RESPONSE_PATH+responseFileName+"Response.json";
		System.out.println(pathname);
		FileWriter fRESPONSE = new FileWriter(pathname);
		
		String json= EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject jsonObject = new JSONObject(json);
		
		BufferedWriter bufResponse = new BufferedWriter(fRESPONSE);
		
		bufResponse.write(jsonObject.toString());	
	}
	
	public static void assertPOSTStatusCode(int statusCodeForPost) {
		Assert.assertEquals(statusCodeForPost, STATUS_CODE_POST,"Status code does not match " );	
	}
	
	
	public static void compareResponse(CloseableHttpResponse reponse) {
		//Code to be written for this;
	}

}
