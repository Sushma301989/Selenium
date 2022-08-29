package com.automation.selenium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Automation {

	public Map<String, String> resourceData = new HashMap<>();

	public Map<String, String> testCases = new HashMap<>();

	public static Sheet testCaseSheet;

	public static ArrayList<String> operationDetails;

	public void process() {

		Iterator<Row> iterator = Initialization.inputDataSheet.iterator();

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();

			if (currentRow.getRowNum() != 0) {

				if (currentRow.getCell(Initialization.primaryKey) != null) {

					Iterator<Cell> cellIterator = currentRow.cellIterator();

					testCaseSheet = Initialization.inputData
							.getSheet(currentRow.getCell(Initialization.primaryKey).getStringCellValue());

					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						resourceData.put(Initialization.inputDataSheet.getRow(0).getCell(currentCell.getColumnIndex())
								.getStringCellValue(), currentCell.getStringCellValue());
					}

					processOperations();

				}

			}

		}

	}

	public void processOperations() {

		operationDetails = new ArrayList<String>();

		Iterator<Row> iterator = testCaseSheet.iterator();

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();

			if (currentRow.getRowNum() != 0) {

				if (currentRow.getCell(0) != null && currentRow.getCell(0).getCellType() != CellType.BLANK) {

					Iterator<Cell> cellIterator = currentRow.cellIterator();

					while (cellIterator.hasNext()) {

						Cell currentCell = cellIterator.next();

						if(currentCell != null && currentCell.getCellType() != CellType.BLANK && currentCell.getCellType() != CellType.NUMERIC) {
							operationDetails.add(currentCell.getStringCellValue());
						}else if(currentCell.getCellType() == CellType.NUMERIC) {
							operationDetails.add(String.valueOf(currentCell.getNumericCellValue()));
						}

					}

					doOperation();

					operationDetails.clear();

				}

			}

		}
	}

	public void doOperation() {

		String[] objectProps = getObjectId(operationDetails.get(0));

		String objectValue = resourceData.get(operationDetails.get(0));

		switch (Operations.valueOf(operationDetails.get(1).toUpperCase())) {

		case SELECTDROPDOWN:
			selectDropDown(objectProps[1], objectProps[2], objectValue);
			break;

		case ENTERTEXT:
			enterText(objectProps[1], objectProps[2], objectValue);
			break;

		case CLICKLINK:
			clickLink(objectProps[1], objectProps[2], objectValue);
			break;

		case SELECTCHECKBOX:
			selectCheckBox(objectProps[1], objectProps[2], objectValue);
			break;

		case DELAY:
			delay();
			break;

		default:
			break;

		}

	}

	/**
	 * This is generic method used to select the drop down value based on the
	 * provided input
	 * 
	 * @param objectAttrVal
	 * @param objectID
	 * @param ObjectValue
	 */
	public void selectDropDown(String objectAttrVal, String objectID, String ObjectValue) {

		Select dropDown = null;

		if (ObjectValue != null && ObjectValue != "") {
			switch (objectAttrVal) {

			case "id":
				// Add WebDriverWait
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.id(objectID)));
				dropDown = new Select(EntryClass.driver.findElement(By.id(objectID)));
				break;
			case "name":
				// Add WebDriverWait
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.name(objectID)));
				dropDown = new Select(EntryClass.driver.findElement(By.name(objectID)));
				break;
			case "xpath":
				// Add WebDriverWait
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectID)));
				dropDown = new Select(EntryClass.driver.findElement(By.xpath(objectID)));
				break;

			}

			if (dropDown != null) {
				// TODO: Get dropdown value from Excel

				dropDown.selectByValue(ObjectValue);

			}

		}

	}

	/**
	 * This is generic method used to enter text based on the provided input
	 * 
	 * @param objectAttrVal
	 * @param objectID
	 * @param ObjectValue
	 */

	public void enterText(String objectAttrVal, String objectID, String ObjectValue) {

		if (ObjectValue != null && ObjectValue != "") {
			switch (objectAttrVal) {

			case "id":
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.id(objectID)));
				EntryClass.driver.findElement(By.id(objectID)).clear();
				EntryClass.driver.findElement(By.id(objectID)).sendKeys(ObjectValue);
				break;
			case "name":
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.name(objectID)));
				EntryClass.driver.findElement(By.name(objectID)).clear();
				EntryClass.driver.findElement(By.name(objectID)).sendKeys(ObjectValue);
				break;
			case "xpath":
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectID)));
				EntryClass.driver.findElement(By.xpath(objectID)).clear();
				EntryClass.driver.findElement(By.xpath(objectID)).sendKeys(ObjectValue);
				break;

			}

		}
	}

	/**
	 * This is generic method used to click link
	 * 
	 * @param objectAttrVal
	 * @param objectID
	 * @param ObjectValue
	 */

	public void clickLink(String objectAttrVal, String objectID, String ObjectValue) {

		switch (objectAttrVal) {

		case "id":
			EntryClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectID)));
			EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.id(objectID)));
			EntryClass.driver.findElement(By.id(objectID)).click();
			break;
		case "name":
			EntryClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectID)));
			EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.name(objectID)));
			EntryClass.driver.findElement(By.name(objectID)).click();
			break;
		case "xpath":
			EntryClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectID)));
			EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectID)));
			EntryClass.driver.findElement(By.xpath(objectID)).click();
			break;

		}

	}

	/**
	 * This is generic method used to click checkbox
	 * 
	 * @param objectAttrVal
	 * @param objectID
	 * @param ObjectValue
	 */

	public void selectCheckBox(String objectAttrVal, String objectID, String ObjectValue) {

		if (ObjectValue != null && ObjectValue != "") {

			switch (objectAttrVal) {

			case "id":
				EntryClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectID)));
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.id(objectID)));
				EntryClass.driver.findElement(By.id(objectID)).click();
				break;
			case "name":
				EntryClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectID)));
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.name(objectID)));
				EntryClass.driver.findElement(By.name(objectID)).click();
				break;
			case "xpath":
				EntryClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectID)));
				EntryClass.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectID)));
				EntryClass.driver.findElement(By.xpath(objectID)).click();
				break;

			}
		}

	}

	public String[] getObjectId(String objectName) {
		String[] objProps = new String[5];
		Iterator<Row> iterator = Initialization.pageObjectProperties.iterator();
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			if (currentRow.getRowNum() != 0) {
				if (objectName.equals(currentRow.getCell(0).getStringCellValue())) {
					objProps[0] = currentRow.getCell(1).getStringCellValue();
					objProps[1] = currentRow.getCell(2).getStringCellValue();
					objProps[2] = currentRow.getCell(3).getStringCellValue();
					break;
				}
			}
		}
		return objProps;
	}
	
	public void delay() {
		
		Long timeUnit = Long.valueOf(operationDetails.get(2));
		try {
			TimeUnit.MILLISECONDS.sleep(timeUnit);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
