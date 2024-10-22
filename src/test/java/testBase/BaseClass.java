package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import freemarker.template.SimpleDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {
	
	//org.apache.logging.log4j.core.Logger;
	//for log4j2 logging process
	public Logger logger;
	public static WebDriver driver;
	public Properties properties;
	
	@BeforeClass(groups = {"sanity","regression","master","dataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		//Loading config.properties file
		FileReader fileReader = new FileReader(".//src//test//resources//config.properties");
		properties = new Properties();
		properties.load(fileReader);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch (browser.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("invalid browser name"); 
			return;//exits from complete method 
		
		}
		
		driver.manage().deleteAllCookies();//deletes all cookies
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(properties.getProperty("appURL"));//reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	//quite imp methods for dynamic generated data testing

	public String randomeString() {
		return (RandomStringUtils.randomAlphabetic(5).toLowerCase());//5 = no of characters to be generated
		//or
		//String generatedString = RandomStringUtils.randomAlphabetic(5);
		//return generatedString;
	}
	
	public String randomeNumber() {
		return (RandomStringUtils.randomNumeric(10));
	}
	
	public String randomeAlphanumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
	}
	
	public String captureScreen(String testMethodName) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testMethodName + "_" + timeStamp + ".png";
		//String targetFilePath = ".\\screenshots\\" + testMethodName + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
