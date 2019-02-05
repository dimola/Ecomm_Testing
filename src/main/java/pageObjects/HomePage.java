package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class HomePage {
	WebDriver driver;
	ConfigFileReader configFileReader;
	private String pageUrl;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
		this.pageUrl = configFileReader.getHost() + configFileReader.getHomePagePath();
	}

	@FindBy(css = "#main-big-col h3")
	private WebElement pageHeadingTitle;

	@FindBy(css = "a[href^='index.php?page=logout']")
	private WebElement buttonLogout;

	@FindBy(css = "#main-menu > a:nth-child(6)")
	private WebElement buttonLogin;

	@FindBy(css = "a[href^='index.php?page=register']")
	private WebElement buttonRegister;

	public boolean isHomePageOnFocus() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Home");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Home Page Heading is displayed: " + e.getMessage());

		}

		return result;
	}

	public boolean logOutButtonIsNotPresent() {
		if (driver.getPageSource().contains("Logout")) {
			return false;
		} else {
			return true;
		}
	}

	public void clickLogOut() {
		buttonLogout.click();
	}

	public boolean isLoggedOut() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Home")
					&& this.buttonLogin.isDisplayed() && this.buttonRegister.isDisplayed()
					&& this.logOutButtonIsNotPresent();
		} catch (Throwable e) {
			System.err.println(
					"Problem while checking if Home Page for logged out user is displayed so buttons Register and Login exist and button Logout does not :"
							+ e.getMessage());

		}

		return result;
	}

}
