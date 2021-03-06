package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends GeneralPage {
	private static final String PAGE_URL = "/index.php?page=logout";

	@FindBy(css = "a[href^='index.php?ses_logout=1']")
	private WebElement buttonConfirmLogout;

	@FindBy(className = "cntblock")
	private WebElement logoutText;

	public LogoutPage(WebDriver driver) {
		super(driver);
	}

	/*
	Implementation from Home page abstract methods
	 */
	@Override
	public LogoutPage open() {
		this.driver.get(this.configFileReader.getHost() + PAGE_URL);
		return this;
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

	/*
	Text getters from Web Elements
	 */
	public String getLogoutText(){
		try {
			if (this.logoutText.isDisplayed()){
				return this.logoutText.getText();
			}
			else{
				return null;
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return null;
		}
	}

	/*
	Actions in this page
	 */
	public void logout(){
		this.buttonConfirmLogout.click();
	}

	/*
	Checks for certain images, buttons if they are displayed
	 */


	/*
	Helper functions
	 */

}
