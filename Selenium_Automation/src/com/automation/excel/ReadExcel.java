package com.automation.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static Workbook createExcel(String filePath) throws IOException{
		
		//create a object of File class to open xlsx file
		
		File file = new File(filePath);
		
		//Create an object for FileInputStream class to read excel file
		
		FileInputStream inputStream = new FileInputStream(file);
		
		Workbook xlbook = null;
		
		//Find the file extension by spilting file name in substring and getting only extension name
		
		String fileName = filePath.split("/")[1];
		
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		//Check condition f the file is xlsx file.
		
		if(fileExtensionName.equals(".xlsx") || fileExtensionName.equals(".xlsm")) {
			
			//if it is xlsx file then create object of XSSWorkbook class
			xlbook = new XSSFWorkbook(inputStream);
			
		}else {
			
			//if it is xls file then create object of HSSFWorkbook
			xlbook = new HSSFWorkbook(inputStream);
		}
		
		return xlbook;
	}
	
	public static void writeExcel(Workbook wb, String filePath) {
		
		try {
			FileOutputStream outputStream = new FileOutputStream(filePath);
			wb.write(outputStream);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
