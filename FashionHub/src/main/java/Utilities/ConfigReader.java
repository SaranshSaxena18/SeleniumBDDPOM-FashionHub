package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
 private Properties properties;
 public ConfigReader(String fileName)
 {	
	 String basePath = System.getProperty("user.dir"); // Project root folder at runtime
	 String propertyFilePath = basePath + "//src//main//java//Resources//configs//"+fileName;
	 try (FileInputStream input = new FileInputStream(propertyFilePath)) 
	 {
		 properties = new Properties();
		 properties.load(input);
	 } catch (IOException e) {
		e.printStackTrace();
	}
 }
 
 public String getProperty(String key)
 {
	 return properties.getProperty(key);
 }
}
