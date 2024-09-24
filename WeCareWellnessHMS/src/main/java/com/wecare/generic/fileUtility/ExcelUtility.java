package com.wecare.generic.fileUtility;

/**
 * @author RAGAVI
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName, int rowNumber, int cellNumber) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		Cell cell = sheet.getRow(rowNumber).getCell(cellNumber);
		DataFormatter df = new DataFormatter();
		String value = df.formatCellValue(cell);
		return value;
	}
	
	public int getNumberOfRows(String sheetName) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	
	public int getNumberOfCells(String sheetName, int rowNumber) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		int cellCount = workbook.getSheet(sheetName).getRow(rowNumber).getLastCellNum();
		return cellCount;
	}

	public void writeDataIntoExcel(String sheetName, int rowNumber, int cellNumber, String data) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		workbook.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestScriptData.xlsx");
		workbook.write(fos);
		workbook.close();
	}
}
