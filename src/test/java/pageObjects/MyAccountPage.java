package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[.='My Account']")//MyAccount page header
	WebElement msgHeader;
	
	@FindBy(linkText = "Logout")
	WebElement lnkLogout;
	
	//Action methods
	
	public boolean isMyAccountPageExist() {
		try {
			return (msgHeader.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}

}
