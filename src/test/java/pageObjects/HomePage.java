package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[.='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(linkText = "Register")
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")
	WebElement lnkLogin;
	
	//Action methods for above elements
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
}
