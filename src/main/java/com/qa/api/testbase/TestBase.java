package com.qa.api.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
	
	

}
