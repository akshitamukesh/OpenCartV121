package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	String reportName;
	
	@Override
	public void onStart(ITestContext context) {
		
		 /*	SimpleDateFormat dFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date date = new Date();
			String currentDataTimeStamp = df.format(dt);
		 */
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-Report-" + timeStamp + ".html";
		
		extentSparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);//location of the report
		
	    //extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");//location of the report
	    
	    
	    extentSparkReporter.config().setDocumentTitle("OpenCart Automation Report");
	    extentSparkReporter.config().setReportName("OpenCart Functional Testing");
	    //extentSparkReporter.config().setTheme(Theme.DARK);
	    extentSparkReporter.config().setTheme(Theme.STANDARD);
	    
	    extentReports = new ExtentReports();
	    
	    extentReports.attachReporter(extentSparkReporter);
	    
	    extentReports.setSystemInfo("Application","OpenCart");
	    extentReports.setSystemInfo("Module", "Admin");
	    extentReports.setSystemInfo("Sub module", "Customers");
	    extentReports.setSystemInfo("Computer Name", "localhost");
	    extentReports.setSystemInfo("Environment", "QA");
	    extentReports.setSystemInfo("Tester Name", System.getProperty("user.name"));//returns current user of the system
	    extentReports.setSystemInfo("Browser Name", "Chrome");
	    
	    String os = context.getCurrentXmlTest().getParameter("os");
	    extentReports.setSystemInfo("Operating system", os);
	    
	    String browser = context.getCurrentXmlTest().getParameter("browser");
	    extentReports.setSystemInfo("Browser", browser);
	    
	    List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
	    
	    if (!(includedGroups.isEmpty())) {
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}
	    
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
	    extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, "Test case PASSED is: "+result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
	    extentTest.assignCategory(result.getMethod().getGroups()); 
	    
	    extentTest.log(Status.FAIL, "Test case FAILED is :"+result.getName());
	    extentTest.log(Status.INFO, "Test case FAILURE cause is :"+result.getThrowable().getMessage());
	    
	    try {
			
	    	BaseClass baseClass = new BaseClass();
	    	String imgpath = baseClass.captureScreen(result.getName());
	    	extentTest.addScreenCaptureFromPath(imgpath);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
	    
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getAfterGroups());
		extentTest.log(Status.SKIP, "test case SKIPPED is: "+result.getName());
		extentTest.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		    
		extentReports.flush();
		
		//////////////additional////////////////
		
//		String extentReportPath = System.getProperty("user.dir") + "\\reports" + reportName;
//		File extentReport = new File(extentReportPath);
//		
//		try {
//			Desktop.getDesktop().browse(extentReport.toURI());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}

