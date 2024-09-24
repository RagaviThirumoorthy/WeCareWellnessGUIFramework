package com.wecare.generic.fileUtility;

/**
 * @author RAGAVI
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromProperties(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/CommonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}

}
