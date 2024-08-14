package com.automation.selenium;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntryClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static void main(String[] args) {
		
		try {
			//add commrng
			Runtime.getRuntime().exec(Config.CHROMETASKKILL);
			System.setProperty(Config.CHROMEPROPERTYNAME, Config.CHROMEDRIVERPATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments(Config.CHROMEOPTIONS);
			driver = new ChromeDriver(options);
			driver.get(Config.APPURL);
			wait = new WebDriverWait(driver, Config.WAIT);
			
			//Initialize the Workbooks and Sheets
			Initialization.initialize();
			
			//Automate
			Initialization.automation.process();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
