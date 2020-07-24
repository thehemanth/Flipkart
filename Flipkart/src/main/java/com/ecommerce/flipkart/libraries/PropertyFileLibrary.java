package com.ecommerce.flipkart.libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileLibrary
{
	public static String getValue(String filePath, String key)
	{
		String value="";
		FileInputStream fin;
		try
		{
			fin = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(fin);
			value =prop.getProperty(key);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return value;
	}
}
