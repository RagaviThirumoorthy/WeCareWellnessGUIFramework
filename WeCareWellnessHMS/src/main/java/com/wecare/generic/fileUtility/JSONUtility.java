package com.wecare.generic.fileUtility;

/**
 * @author RAGAVI
 */

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility {

	public String getDataFromJSON(String key) throws IOException, ParseException {

		FileInputStream fis = new FileInputStream("./src/main/resources/CommonData.json");
		
		FileReader fir = new FileReader("./src/main/resources/CommonData.json");
		JSONParser parser  = new JSONParser();
		Object obj = parser.parse(fir);
		JSONObject jObj = (JSONObject)obj;
		String value = jObj.get(key).toString();
		return value;
	}
}
