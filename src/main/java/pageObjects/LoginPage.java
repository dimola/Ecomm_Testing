package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class LoginPage {
	WebDriver driver;
	ConfigFileReader configFileReader;

	private String pageUrl;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
		this.pageUrl = configFileReader.getHost() + configFileReader.getLoginPagePath();
	}

	@FindBy(id = "username")
	private WebElement txtbxUserName;

	@FindBy(css = "input[name='userpass']")
	private WebElement txtbxPassword;

	@FindBy(css = "input[name='ses_login']")
	private WebElement buttonLogin;
	
	@FindBy(id = "login-timer")
	private WebElement timerMessage;

	public LoginPage open() {
		driver.get(pageUrl);
		return this;
	}

	public LoginPage enterUserName(String username) {
		txtbxUserName.sendKeys(username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		txtbxPassword.sendKeys(password);
		return this;
	}

	public void clickLogIn() {
		buttonLogin.click();
	}

	public void enterUsernameAndPassword(String username, String password) {
		int counter = 0;
		do {
			enterUserName(username);
			enterPassword(password);
			clickLogIn();
			counter++;
		} while (counter < 3);
	}

	public void isLoginPageOnFocus() {
		if (driver.findElements(By.id("username")).size() != 0) {
			System.out.println("User is not logged in when using invalid data");
		} else {
			System.out.println("User is logged in successfully");
		}
	}

	public void timerIsDisplayed() {
		if (driver.findElements(By.id("login-timer")).size() != 0) {
			System.out.println("Too many unsuccessful login attempts! Please try again in 0:30!");
		} else {
			System.out.println("Login timer is not displayed and user is not logged in ");
		}
	}
}
