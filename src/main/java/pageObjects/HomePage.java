package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dataProviders.ConfigFileReader;

public class HomePage extends GeneralPage {
	private static final String PAGE_URL = "/index.php?page=home";

	@FindBy(css = "a[href^='index.php?page=books']")
	private WebElement booksCategoryButton;

	@FindBy(css = "a[href^='index.php?page=cds']")
	private WebElement cdsCategoryButton;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public HomePage open() {
		configFileReader = new ConfigFileReader();
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	//Methods
	public void selectBookCategory() {
		booksCategoryButton.click();
	}

	public void selectCdsCategory() {
		cdsCategoryButton.click();
	}

	@Override
	public boolean isOpen() {
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
}
