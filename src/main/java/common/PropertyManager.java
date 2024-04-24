package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	static Properties properties;
	private static final String propertyFileName = "environment.properties";
	
	public static Properties getPropertiesInstance() throws FileNotFoundException, IOException {
		if(properties == null) {
			properties = new Properties();
			properties.load(new FileInputStream(propertyFileName));
		}
		return properties;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(getPropertiesInstance().getProperty("UniversityAPI_BaseURI"));
	}
	
}
