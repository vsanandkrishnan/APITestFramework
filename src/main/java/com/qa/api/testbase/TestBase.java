package com.qa.api.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.qa.api.client.RestClient;



public class TestBase {

	public Properties prop;
	
	public static int RESPONSE_CODE_200=200;

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
	
	
	//To copy the reponse to a file
	public void copyFile(String response) {
		try {
		String path="response\\response.txt";
		Files.write( Paths.get(path), response.getBytes());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

	
	

}
