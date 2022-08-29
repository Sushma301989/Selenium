package com.automation.selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public static String CHROMEPROPERTYNAME;
	public static String CHROMEDRIVERPATH;
	public static String CHROMETASKKILL;
	public static String CHROMEOPTIONS;
	public static String APPURL;
	public static int WAIT;
	
	static {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("config/config.properties");
			Properties propertyFile = new Properties();
			propertyFile.load(fileInputStream);
			
			CHROMEPROPERTYNAME = propertyFile.getProperty("chromePropertyName");
			CHROMEDRIVERPATH = propertyFile.getProperty("chromeDriverPath");
			CHROMETASKKILL = propertyFile.getProperty("chromeTaskKill");
			CHROMEOPTIONS = propertyFile.getProperty("chromeOptions");
			APPURL = propertyFile.getProperty("applicationURL");
			WAIT = Integer.parseInt((propertyFile.getProperty("appLoadWaitTime")));
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
