package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class LogoutPage {

	WebDriver driver;
	ConfigFileReader configFileReader;

	private String pageUrl;

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
		this.pageUrl = configFileReader.getHost() + configFileReader.getLoginPagePath();
	}

	@FindBy(css = "a[href^='index.php?ses_logout=1']")
	private WebElement buttonConfirmLogout;

	public void clickConfirmLogOut() {
		buttonConfirmLogout.click();
	}
}
