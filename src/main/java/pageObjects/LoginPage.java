package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends GeneralPage {
	private static final String PAGE_URL = "/index.php?page=login";

	@FindBy(id = "username")
	private WebElement txtbxUserName;

	@FindBy(css = "input[name='userpass']")
	private WebElement txtbxPassword;

	@FindBy(css = "input[name='ses_login']")
	private WebElement buttonLogin;

	@FindBy(id = "login-timer")
	private WebElement timerMessage;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/*
	Implementation from Home page abstract methods
	 */
	@Override
	public LoginPage open() {
		this.driver.get(this.configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Login");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Login Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	/*
	Text getters from Web Elements
	 */
	public String getErrorTime(){
		try {
			if(timerMessage.isDisplayed()){
				return timerMessage.getText();
			}
			else{
				return null;
			}
		} catch (Throwable e) {
			return null;
		}
	}

	/*
	Actions in this page
	 */
	public void clickLogIn() {
		buttonLogin.click();
	}

	public void login(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickLogIn();
	}

	public LoginPage enterUserName(String username) {
		txtbxUserName.sendKeys(username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		txtbxPassword.sendKeys(password);
		return this;
	}

	/*
	Checks for certain images, buttons if they are displayed
	 */
	public boolean timerIsDisplayed() {
		boolean result = false;
		try {
			result = timerMessage.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Login timer is not displayed and user is not logged in " + e.getMessage());
		}
		return result;
	}

}
