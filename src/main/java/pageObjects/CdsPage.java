package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CdsPage extends CategoryPage {
	private static final String PAGE_URL = "/index.php?page=books";


	@FindBy (name = "Artist")
	private WebElement artistTextbox;

	@FindBy(name = "Title")
	private WebElement titleTextbox;

	@FindBy(name = "Composer")
	private WebElement composerTextbox;


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

	//To be deleted?
	public boolean isArtistTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.artistTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if artistTextbox is displayed: " + e.getMessage());
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
	}public boolean isComposerTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.composerTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if composerTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

}