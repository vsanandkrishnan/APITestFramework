package com.qa.api.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBase {

	public Properties prop;

	public TestBase() {
		try {

			prop = new Properties();
			FileInputStream fin = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\api\\configuration\\config.properties");
			prop.load(fin);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//In Progress
	public void formatJSON(JSONObject JSON) {
		
		String jsonString= JSON.toString();
		
		
//		ObjectMapper mapper = new ObjectMapper();
//		Object json = mapper.readValue(jsonString, Object.class);

//		String indented =  mapper.defaultPrettyPrintingWriter().writeValueAsString(json);

		
		
	}
	
	

}
