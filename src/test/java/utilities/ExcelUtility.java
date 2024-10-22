package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		
		workbook.close();
		fis.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlFile = new File(path);
		if (!xlFile.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		
		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}
		sheet = workbook.getSheet(sheetName);
		
		if (sheet.getRow(rowNum) == null) {
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	
	public void fillGreenColor(String sheetname, int rownum, int colNum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		
		row = sheet.getRow(rownum);
		cell = row.getCell(colNum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
}
