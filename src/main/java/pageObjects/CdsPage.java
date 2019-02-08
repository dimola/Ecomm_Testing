package pageObjects;

import org.openqa.selenium.WebDriver;

public class CdsPage extends CategoryPage {
	private static final String PAGE_URL = "/index.php?page=books";

	public CdsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public CdsPage open() {
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
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

}