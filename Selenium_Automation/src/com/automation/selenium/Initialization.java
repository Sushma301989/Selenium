package com.automation.selenium;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.automation.excel.ReadExcel;

public class Initialization {

	public static Automation automation;
	
	public static Workbook inputDriver;
	
	public static Sheet pageObjectProperties;
	
	public static Workbook inputData;
	
	public static Sheet inputDataSheet;
	
	public static Sheet dropDowns;
	
	public static int primaryKey;
	
	public static void initialize() {
		
		//This Method sets the corresponding Object
		selectDriverSheet();
		
		//Select input driver workbook
		selectInputSheet();		
		
	}
	
	public static void selectDriverSheet() {
		
		try {
			
			inputDriver = ReadExcel.createExcel("drivers/drivers.xlsx");
			
			pageObjectProperties = inputDriver.getSheet("Page Object Properties");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void selectInputSheet() {
		
		try {
			
			automation = new Automation();
			
			inputData = ReadExcel.createExcel("input/DataInput.xlsx");
			
			inputDataSheet = inputData.getSheet("data");
			
			dropDowns = inputData.getSheet("dropdowns");
			
			primaryKey = 0;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
