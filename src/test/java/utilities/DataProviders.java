package utilities;

import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testingData\\testData.xlsx";
		
		ExcelUtility xlUtil = new ExcelUtility(path);
		
		int totalRows = xlUtil.getRowCount("loginDataSheet");
		int totalCols = xlUtil.getCellCount("loginDataSheet", 1);
		
		String loginData[][] = new String[totalRows][totalCols];
		
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				loginData[i-1][j] = xlUtil.getCellData("loginDataSheet", i, j);
			}
		}
		return loginData;
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
