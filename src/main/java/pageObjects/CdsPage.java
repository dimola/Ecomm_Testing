package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class CdsPage {
	WebDriver driver;
	ConfigFileReader configFileReader;
	private String pageUrl;

	@FindBy(css = "#main-big-col h3")
	private WebElement pageHeadingTitle;

	@FindBy(css = "#product-list h4")
	private WebElement productListTitle;

	public CdsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
		this.pageUrl = configFileReader.getHost() + configFileReader.getHomePagePath();
	}

	public boolean isCdsPageOnFocus() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("CDs")
					&& this.productListTitle.isDisplayed()
					&& this.productListTitle.getText().equals("Browse CDs by category:");
		} catch (Throwable e) {
			System.err.println("Problem while checking if CDs Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	public CdsPage open() {
		driver.get(pageUrl);
		return this;
	}

}