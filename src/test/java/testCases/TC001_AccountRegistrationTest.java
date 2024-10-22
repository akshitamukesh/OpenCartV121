package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups = {"regression", "master"})
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
			HomePage homePage = new HomePage(driver);
			
			Thread.sleep(2000);
			homePage.clickMyAccount();
			logger.info("clicked on MyAccount");
			Thread.sleep(2000);
			
			homePage.clickRegister();
			logger.info("clicked on Register");
			Thread.sleep(2000);
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			
			logger.info("providing customer details");
			
			regPage.setFirstName(randomeString().toUpperCase());
			regPage.setLastName(randomeString().toUpperCase());
			regPage.setEmail(randomeString()+"@gmail.com");
			Thread.sleep(2000);
			regPage.setTelephone(randomeNumber());
			
			String password = randomeAlphanumeric();
			//randomeAlphanumeric() cannot be called directly inside setPassword() & setConfirmPassword()
			//bcz each time we call, the value generated will be different
			//so we need to call it only once and store it to pass it in later method
			
			regPage.setPassward(password);
			regPage.setConfirmPassword(password);
			regPage.setPrivacyPolicy();
			Thread.sleep(2000);
			regPage.clickBtnContinue();
			Thread.sleep(2000);
			
			logger.info("validating expected message");
			
			String cnfMsg = regPage.getConfirmationMsg();
			
			if (cnfMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed...");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
			
//			Assert.assertEquals(cnfMsg, "Your Account Has Been Created!");
			
		} catch (Throwable e) {
//			logger.catching(e);//for capture the error in the log file
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest ****");
	}
}
