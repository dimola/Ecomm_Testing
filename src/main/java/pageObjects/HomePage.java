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

	@FindBy(xpath = "//*[@id='product-list']/div")
	private WebElement bookCategoryOption;

	public void isHomePageOnFocus() {
		if (driver.findElements(By.xpath("//*[@id=\"main-big-col\"]/h3")).size() != 0) {
			System.out.println("User is not logged in when using invalid data");
		} else {
			System.out.println("User is logged in successfully");

		}
	}
}
