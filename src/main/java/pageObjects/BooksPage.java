package pageObjects;

import org.openqa.selenium.WebDriver;

public class BooksPage extends CategoryPage {
	private static final String PAGE_URL = "/index.php?page=books";

	public BooksPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public BooksPage open() {
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Books")
					&& this.productListTitle.isDisplayed()
					&& this.productListTitle.getText().equals("Browse Books by category:");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books Heading is displayed: " + e.getMessage());
		}
		return result;
	}

}