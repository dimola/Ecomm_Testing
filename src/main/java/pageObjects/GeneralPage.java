package pageObjects;

import dataProviders.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class GeneralPage {
	protected WebDriver driver;
	protected ConfigFileReader configFileReader;

	@FindBy(id = "logo")
	protected WebElement logo;

	@FindBy(css = "a[href^='index.php?page=home']")
	protected WebElement homeMenuElement;

	@FindBy(css = "a[href^='index.php?page=books']")
	protected WebElement booksMenuElement;

	@FindBy(css = "a[href^='index.php?page=cds']")
	protected WebElement cdsMenuElement;

	@FindBy(css = "a[href^='index.php?page=basket']")
	protected WebElement basketMenuElement;

	@FindBy(css = "a[href^='index.php?page=register']")
	protected WebElement registerMenuElement;

	@FindBy(css = "a[href^='index.php?page=login']")
	protected WebElement loginMenuElement;

	@FindBy(css = "a[href^='index.php?page=logout']")
	protected WebElement logoutMenuElement;

	@FindBy(css = "a[href^='index.php?page=basket']")
	protected WebElement viewbasketMenuElement;

	@FindBy(css = "#main-big-col h3")
	protected WebElement pageHeadingTitle;

	public GeneralPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
	}

	public abstract GeneralPage open();

	public abstract boolean isOpen();

	@SuppressWarnings("finally")
	public boolean isLoginButtonDisplayed() {
		boolean result = false;
		try {
			result = loginMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if loginMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isLogoutButtonDisplayed() {
		boolean result = false;
		try {
			result = logoutMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if logoutMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isRegisterButtonDisplayed() {
		boolean result = false;
		try {
			result = registerMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if registerMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	public void clickLogOut() {
		logoutMenuElement.click();
	}

	public boolean checkHeaderData() {
		boolean result = false;
		try {
			result = this.logo.isDisplayed() && homeMenuElement.isDisplayed() && booksMenuElement.isDisplayed()
					&& cdsMenuElement.isDisplayed() && basketMenuElement.isDisplayed()
					&& registerMenuElement.isDisplayed() && loginMenuElement.isDisplayed()
					&& viewbasketMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("An element in the header is missing" + e.getMessage());
		}
		return result;
	}
}
