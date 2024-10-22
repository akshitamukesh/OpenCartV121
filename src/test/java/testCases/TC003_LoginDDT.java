package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


/* Data is valid -> login successful -> test pass -> logout
 * Data is valid -> login unsucessful -> test fail 
 * 
 * Data is invalid -> login successful -> test fail -> logout
 * Data is invalid -> login failed -> test pass
 * */

public class TC003_LoginDDT extends BaseClass {
	
	/* ->dataProviderClass is additional parameter needs to be added along with 
	 *  dataProvider when dataProviders are kept in different class/not in current class
	 * ->if dataProviders are kept in same same then dataProviderClass is not required*/
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "dataDriven")
	public void verify_loginDDT(String email, String pwd, String exp_res) {
		
		logger.info("******Starting TC003_LoginDDT*****");
		
		try {
			//Home page
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();
			
			//Login page
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmailAddress(email);
			loginPage.setPassword(pwd);
			loginPage.clickLoginButton();
			
			//MyAccount page
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean isPageExist = myAccountPage.isMyAccountPageExist();
			
			
			if (exp_res.equalsIgnoreCase("valid")) {
				//Data is valid -> login successful -> test pass -> logout
				if (isPageExist == true) {
					myAccountPage.clickLogout();
					Assert.assertTrue(true);
				} 
				//Data is valid -> login unsucessful -> test fail 
				else {
					Assert.assertTrue(false);
				}
			}
			if (exp_res.equalsIgnoreCase("invalid")) {
				//Data is invalid -> login successful -> test fail -> logout
				if (isPageExist == true) {
					myAccountPage.clickLogout();
					Assert.assertTrue(false);
				} 
				//Data is invalid -> login failed -> test pass
				else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("******Finished TC003_LoginDDT*****");
	}
	
}
