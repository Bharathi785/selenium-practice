package com.grip.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {
	public WebDriver driver;
	public FileInputStream fis;
	public HSSFWorkbook hssfbook;
	public XSSFWorkbook xssfbook;
	public HSSFSheet hssfSheet;
	public XSSFSheet xssfSheet;
	public XSSFRow xssfRow;
	public HSSFRow hssfRow;
	public HSSFCell hssfCell;
	public XSSFCell xssfCell;
	public int rowCount;
	public String value;
	public DataFormatter formatter = new DataFormatter();
	File file;

	public int getRownumber(String filePath, String columnHeader, int columnNum, String columnHeaderValue) throws Exception {
		int rownumber = 0;

		if (filePath.endsWith(".xlsx")) {
			rowCount = xssfSheet.getLastRowNum();

			for (int j = 1; j <= rowCount; j++) {
				xssfRow = xssfSheet.getRow(j);
				xssfCell = xssfRow.getCell(columnNum);

				if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					if (!xssfCell.getStringCellValue().trim().isEmpty()) {
						if (formatter.formatCellValue(xssfCell).equalsIgnoreCase(columnHeader)) {
							rownumber = j;
							break;
						}
					}
				}

				else {
					if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(xssfCell)) {
						if (formatter.formatCellValue(xssfCell).equalsIgnoreCase(columnHeader)) {
							rownumber = j;
							break;
						}
					}
				}
			}

			if (rownumber == 0)
				throw new Exception("Unable to get the row number");
		}

		else if (filePath.endsWith(".xls")) {
			rowCount = hssfSheet.getLastRowNum();
			for (int j = 1; j <= rowCount; j++) {
				hssfRow = hssfSheet.getRow(j);
				if (hssfRow.getCell(0).getStringCellValue().equalsIgnoreCase(columnHeader)) {
					rownumber = j;
					break;
				}
			}
			if (rownumber == 0) {
				throw new Exception("Unable to get the row number");
			}
		}
		return rownumber;
	}

	public int getColumnNumber(String filePath, String columnHeaderValue) throws Exception {
		int columnNumber = 0;
		int isValid = 0;

		if (filePath.endsWith(".xlsx")) {
			xssfRow = xssfSheet.getRow(0);
			for (int j = xssfSheet.getFirstRowNum(); j < xssfRow.getPhysicalNumberOfCells(); j++) {

				if (xssfRow.getCell(j).toString().trim().equalsIgnoreCase(columnHeaderValue)) {
					columnNumber = j;
					isValid = 1;
					break;
				}
			}
			if (isValid == 0) {
				throw new Exception("Column value not available in DataSheet");
			}
		}

		else if (filePath.endsWith(".xls")) {
			hssfRow = hssfSheet.getRow(0);
			for (int j = hssfSheet.getFirstRowNum(); j < hssfRow.getPhysicalNumberOfCells(); j++) {
				if (hssfRow.getCell(j).toString().trim().equalsIgnoreCase(columnHeaderValue)) {
					columnNumber = j;
					isValid = 1;
					break;
				}
			}
			if (isValid == 0) {
				throw new Exception("Column value not available in DataSheet");
			}
		}
		return columnNumber;

	}

	public String getValue(String filePath, int sheetNum, int columnNum,  String columnValue, String columnHeaderValue)
			throws Exception {
		try {

			if (filePath.endsWith(".xlsx")) {
				fis = new FileInputStream(new File(filePath));
				xssfbook = new XSSFWorkbook(fis);
				xssfSheet = xssfbook.getSheetAt(sheetNum);
				int rownumber = getRownumber(filePath, columnValue, columnNum, columnHeaderValue);
				int columnNumber = getColumnNumber(filePath, columnHeaderValue);
				xssfCell = xssfSheet.getRow(rownumber).getCell(columnNumber);

				if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					if (formatter.formatCellValue(xssfCell).isEmpty())
						throw new RuntimeException("Cell value is empty");
					else
						value = formatter.formatCellValue(xssfCell);
				}
				
				else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					if (formatter.formatCellValue(xssfCell).isEmpty())
						throw new RuntimeException("Cell value is empty");
					else
						value = formatter.formatCellValue(xssfCell);
				}
				
				else {
					if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(xssfCell)) {
						if (formatter.formatCellValue(xssfCell).isEmpty())
							throw new RuntimeException("Cell value is empty");
						else
							value = formatter.formatCellValue(xssfCell);
					}
				}
			}

			else if (filePath.endsWith(".xls")) {
				fis = new FileInputStream(new File(filePath));
				hssfbook = new HSSFWorkbook(fis);
				hssfSheet = hssfbook.getSheetAt(sheetNum);
				int rownumber = getRownumber(filePath,columnValue, columnNum, columnHeaderValue);
				int columnNumber = getColumnNumber(filePath, columnHeaderValue);
				hssfCell = hssfSheet.getRow(rownumber).getCell(columnNumber);
				if (hssfCell != null) {
					value = hssfCell.toString();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to fetch value from excel - " + e.getMessage());
		}
		return value;

	}
}
