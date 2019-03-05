package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends GeneralPage {
	private static final String PAGE_URL = "/index.php?page=basket";
	
	@FindBy (css = "#main-big-col .err")
	private WebElement errorEmptyBasket;

	public BasketPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public BasketPage open() {
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Shopping Basket");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Basket Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isEmtpyBasketDisplayed() {
		boolean result = false;
		try {
			result = this.errorEmptyBasket.isDisplayed() && this.errorEmptyBasket.getText().equals("The Shopping Basket is empty!");
		} catch (Throwable e) {
			System.err.println("Problem while checking if the empty shooping basket error message is displayed: " + e.getMessage());
		}
		return result;
	}
}
