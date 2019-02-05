package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class BooksPage {
	WebDriver driver;
	ConfigFileReader configFileReader;
	private String pageUrl;

	@FindBy(css = "#main-big-col h3")
	private WebElement pageHeadingTitle;
	
	@FindBy(css = "#product-list h4")
	private WebElement productListTitle; 
	
	public BooksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
		this.pageUrl = configFileReader.getHost() + configFileReader.getHomePagePath();
	}
	
	public boolean isBooksPageOnFocus() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Books") 
					&& this.productListTitle.isDisplayed() && this.productListTitle.getText().equals("Browse Books by category:");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books Heading is displayed: " + e.getMessage());
		}
		return result;
		}

	public BooksPage open() {
		driver.get(pageUrl);
		return this;
	}
	
	

}