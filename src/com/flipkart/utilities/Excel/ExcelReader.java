package com.flipkart.utilities.Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	XSSFWorkbook workbook;

	public ExcelReader() {
		FileInputStream file;
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"\\src\\testdata\\testData.xlsx");
			workbook = new XSSFWorkbook(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void read(StringBuilder uname, StringBuilder pass) {

		XSSFSheet spreadsheet = workbook.getSheetAt(0);

		Cell unamecell = spreadsheet.getRow(1).getCell(0);
		Cell passcell = spreadsheet.getRow(1).getCell(1);
		
		//23,444 --> 2.3444E4 ---> 23444
		DataFormatter df = new DataFormatter();
		String val = df.formatCellValue(unamecell);
		uname.append(val);
		pass.append(passcell.getStringCellValue());

		System.out.println("EXCEL "+uname + " " + pass);

//		
//		Iterator<Row> rowIterator = spreadsheet.iterator();
//
//		while (rowIterator.hasNext()) {
//			Row row = (XSSFRow) rowIterator.next();
//			Iterator<Cell> cellIterator = row.cellIterator();
//
//			while (cellIterator.hasNext()) {
//				Cell cell = cellIterator.next();
//
//				switch (cell.getCellType()) {
//				case NUMERIC:
//					System.out.print(cell.getNumericCellValue() + " \t\t ");
//					break;
//
//				case STRING:
//					System.out.print(cell.getStringCellValue() + " \t\t ");
//					break;
//				default:
//					break;
//				}
//			}
//			System.out.println();
//		}

	}

}
