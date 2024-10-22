package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	//invoking super class constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Web elements/locators
	
	@FindBy(xpath = "//input[@name='firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@name='lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@name='telephone']")
	WebElement txtTelePhone;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@name='confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[.='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	
	//Action methods
	
	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		txtTelePhone.sendKeys(telephone);
	}
	
	public void setPassward(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}
	
	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}
	
	public void clickBtnContinue() {
		btnContinue.click();
	}
	
	//since validation should not be performed in POM classes
	//we are just extracting the string from web element and passing it via method to perform validation in test classes
	public String getConfirmationMsg() {
		try {
			return (confirmationMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
