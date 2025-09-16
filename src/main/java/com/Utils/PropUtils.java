package com.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
	
	
	public static String readProperty(String key) {
	    String baseDir = System.getProperty("user.dir");// current working directory
		String envFilePath = "/src/main/resources/env.properties";
		final String filepath = baseDir + envFilePath;
		String value = null;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filepath);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	

}
