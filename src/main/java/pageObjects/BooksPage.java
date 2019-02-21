package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BooksPage extends CategoryPage {

	private static final String PAGE_URL = "/index.php?page=books";
	
	@FindBy(name = "Author")
	private WebElement authorTextbox;
	
	@FindBy(name = "Title")
	private WebElement titleTextbox;
	
	@FindBy(name = "Publisher")
	private WebElement publisherTextbox;
	
	@FindBy(name = "ISBN")
	private WebElement isbnTextbox;
		
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

	public boolean isAuthorTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.authorTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if authorTextbox is displayed: " + e.getMessage());
		}
		return result;
	}
	
	public boolean isTitleTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.titleTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if titleTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isPublisherTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.publisherTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if publisherTextbox is displayed: " + e.getMessage());
		}
		return result;
	}
	
	public boolean isIsbnTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.isbnTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if isbnTextbox is displayed: " + e.getMessage());
		}
		return result;
	}
}


