package com.test.automation.utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
public static String TEST_DATA="/Users/rakesh.behera/ATC_Test/API/Selenium_Automation_Practice/src/main/java/com/qa/testdata/StationData.xlsx";
public static Workbook book;
public static Sheet sheet;



public static Object[][] getTestData(String strSheetNAme) {
		FileInputStream file = null;

		try {
			file = new FileInputStream(TEST_DATA);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(strSheetNAme);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();

			}
		}

		return data;

	}

}
