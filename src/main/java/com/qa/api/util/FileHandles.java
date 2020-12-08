package com.qa.api.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import io.restassured.path.json.JsonPath;
//File handling for dynamically loading the parameters to json
public class FileHandles {

	public static void main(String[] args) {
		String[] array = {"Learn Appium Automation with Java","bcd","227","John Foe"};
		System.out.println(getJsonInput("dynamic", array));
	}
    
	//JSON file name and the array containing the values of parameter to be replaced
	//Testing completed for the method
	public static String getJsonInput(String jsonName, String[] values) {
		try {
			File file = new File("jsonfiles\\" + jsonName + ".json");
			BufferedReader buffReader = new BufferedReader(new FileReader(file));
			String st;
			String fullFile = "";
			while ((st = buffReader.readLine()) != null) {
				fullFile += st;
			}

			String replaced = fullFile;
			if(values.length!=0) {
			
			for (int i = 0; i < values.length; i++) {
				String replaceQuery = "QUERY_00" + i;
				replaced = replaced.replace(replaceQuery, values[i]);
			}
			}else {
				replaced=fullFile;
			}
			buffReader.close();
			return replaced;

		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();

		}

	}

	public static String getJsonResponseValue(String response, String path) {

		JsonPath jsp = new JsonPath(response);
		return jsp.getString(path);

	}

	public static JsonPath getJsonResponse(String response) {
		return new JsonPath(response);
	}

}
