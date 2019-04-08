package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public abstract class GeneralPage {
	protected WebDriver driver;
	protected ConfigFileReader configFileReader;

	@FindBy(id = "logo")
	protected WebElement logo;

	@FindBy(id = "main-menu")
	protected WebElement mainMenuElement;

	@FindBy(css = "a[href^='index.php?page=home']")
	protected WebElement homeMenuElement;

	@FindBy(css = "a[href^='index.php?page=books']")
	protected WebElement booksMenuElement;

	@FindBy(css = "a[href^='index.php?page=cds']")
	protected WebElement cdsMenuElement;

	@FindBy(css = "#main-menu > a:nth-child(4)")
	protected WebElement basketMenuElement;

	@FindBy(css = "a[href^='index.php?page=register']")
	protected WebElement registerMenuElement;

	@FindBy(css = "a[href^='index.php?page=login']")
	protected WebElement loginMenuElement;

	@FindBy(css = "a[href^='index.php?page=logout']")
	protected WebElement logoutMenuElement;

	@FindBy(id = "basket")
	protected WebElement viewBasketMenuElement;

	@FindBy(id = "product-list")
	protected WebElement productList;

	@FindBy(css = "#product-list h4")
	protected WebElement productListTitle;

	@FindBy(css = "#main-big-col h3")
	protected WebElement pageHeadingTitle;

	public GeneralPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
	}

	public abstract GeneralPage open();

	public abstract boolean isOpen();

	public void clickLogOut() {
		logoutMenuElement.click();
	}

	public String getPageTitle(){
		try {
			if (this.pageHeadingTitle.isDisplayed()){
				return this.pageHeadingTitle.getText();
			}
			else{
				return "Can't find page title";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find page title";
		}
	}

	public String getHomeButtonText(){
		try {
			if (this.homeMenuElement.isDisplayed()){
				return this.homeMenuElement.getText();
			}
			else{
				return "Can't find Home button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find Home button";
		}
	}

	public String getBooksButtonText(){
		try {
			if (this.booksMenuElement.isDisplayed()){
				return this.booksMenuElement.getText();
			}
			else{
				return "Can't find Books button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find Books button";
		}
	}

	public String getCDsButtonText(){
		try {
			if (this.cdsMenuElement.isDisplayed()){
				return this.cdsMenuElement.getText();
			}
			else{
				return "Can't find Cds button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find Cds button";
		}
	}

	public String getViewBasketButtonText(){
		try {
			if (this.viewBasketMenuElement.isDisplayed()){
				return this.viewBasketMenuElement.getText();
			}
			else{
				return "Can't find Basket button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find Basket button";
		}
	}

	public String getRegisterButtonText() {
		try {
			if (this.registerMenuElement.isDisplayed()){
				return this.registerMenuElement.getText();
			}
			else{
				return "Can't find Register button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find Register button";
		}
	}

	public String getBasketIconText(){
		try {
			if (this.basketMenuElement.isDisplayed()){
				return this.basketMenuElement.getText();
			}
			else{
				return "Can't find basket icon";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find basket icon";
		}
	}

	public String getLoginButtonText(){
		try {
			if (this.loginMenuElement.isDisplayed()){
				return this.loginMenuElement.getText();
			}
			else{
				return "Can't find login button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find login button";
		}
	}

	public String getLogoutButtonText(){
		try {
			if (this.logoutMenuElement.isDisplayed()){
				return this.logoutMenuElement.getText();
			}
			else{
				return "Can't find logout button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find logout button";
		}
	}

	@SuppressWarnings("finally")
	public boolean isMainMenuDisplayed() {
		boolean result = false;
		try {
			result = mainMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if the Main Menu is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isLogoDisplayed() {
		boolean result = false;
		try {
			result = logo.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if logo is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isHomeButtonDisplayed() {
		boolean result = false;
		try {
			result = homeMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if the homeMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isBooksButtonDisplayed() {
		boolean result = false;
		try {
			result = booksMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if the booksMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isCdsButtonDisplayed() {
		boolean result = false;
		try {
			result = cdsMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if the cdsMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean isBasketButtonDisplayed() {
		boolean result = false;
		try {
			result = basketMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if the basketMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

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

	@SuppressWarnings("finally")
	public boolean isViewBasketDisplayed() {
		boolean result = false;
		try {
			result = viewBasketMenuElement.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if viewbasketMenuElement is displayed: " + e.getMessage());
		} finally {
			return result;
		}
	}

	public boolean isProductListDisplayed() {
		boolean result = false;
		try {
			result = this.productList.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The product list is missing" + e.getMessage());
		}
		return result;
	}

}
