package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends GeneralPage {
	private static final String PAGE_URL = "/index.php?page=logout";

	@FindBy(css = "a[href^='index.php?ses_logout=1']")
	private WebElement buttonConfirmLogout;

	public LogoutPage(WebDriver driver) {
		super(driver);
	}

	//Methods
	@Override
	public LogoutPage open() {
		this.driver.get(this.configFileReader.getHost() + PAGE_URL);
		return this;
	}

	public void clickConfirmLogOut() {
		buttonConfirmLogout.click();
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Logout");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Logout Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

}
