package pageObjects;

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

	public boolean isHomePageOnFocus() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Home");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Home Page Heading is displayed: " + e.getMessage()); 
																												
		}

		return result;
	}

}
