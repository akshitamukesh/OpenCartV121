package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = "sanity")
	public void verify_login() {
		logger.info("***Starting TC002_LoginTest***");
		
		try {
			//HomePage
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("clicked MyAccount link");
			Thread.sleep(2000);
			homePage.clickLogin();
			logger.info("clicked Login link");
			Thread.sleep(2000);
			
			//LoginPage
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmailAddress(properties.getProperty("email"));
			logger.info("email entered");
			loginPage.setPassword(properties.getProperty("password"));
			logger.info("pass entered");
			Thread.sleep(2000);
			loginPage.clickLoginButton();
			logger.info("clicked Login button");
			
			//MyAccountPage
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean targetPageReached = myAccountPage.isMyAccountPageExist();
			logger.info("successfully logged in");
			
			//Assert.assertEquals(targetPageReached, true, "Login Failed...");
			//or
			Assert.assertTrue(targetPageReached);
		} 
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("***Finished TC002_LoginTest***");
	}
}
