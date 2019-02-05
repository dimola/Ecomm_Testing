package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;
import managers.PageObjectManager;

public class BooksPage {

	WebDriver driver;
	PageObjectManager pageObjectManager;
	ConfigFileReader configFileReader;

	private String pageUrl;

	public BooksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		configFileReader = new ConfigFileReader();
		this.pageUrl = configFileReader.getHost() + configFileReader.getBooksPagePath();
	}
	
	@FindBy(id = "logo")
	private WebElement logo;

	@FindBy(xpath = ("//a[contains(text(),'Home')]"))
	private WebElement btnHome;
	
	@FindBy(xpath = ("//a[contains(text(),'Books')]"))
	private WebElement btnBooks;

	@FindBy(xpath = ("//a[contains(text(),'CDs')]"))
	private WebElement btnCds;
	
	@FindBy(xpath = ("//a[contains(text(),'Basket')]"))
	private WebElement btnBasket;
	
	@FindBy(xpath = ("//a[contains(text(),'Register')]"))
	private WebElement btnRegister;
	
	@FindBy(xpath = ("//a[contains(text(),'Login')]"))
	private WebElement btnLogin;
	
	@FindBy(xpath = ("//a[contains(text(),'Basket')]"))
	private WebElement basket;
	
	@FindBy(id = "side-menu")
	private WebElement sideMenu;
	
	@FindBy(id = "product-list")
	private WebElement productList;
	
	@FindBy(xpath = ("//td[contains(text(),'Author')]"))
	private WebElement author;
	
	@FindBy(xpath = ("//td[contains(text(),'Title')]"))
	private WebElement title;
	
	@FindBy(xpath = ("//td[contains(text(),'Publisher')]"))
	private WebElement publisher;
	
	@FindBy(xpath = ("//td[contains(text(),'ISBN')]"))
	private WebElement isbn;
	
	public BooksPage open() {
		driver.get(pageUrl);
		return this;
	}
	
	public boolean checkHeaderData() {
		boolean result = false;
		try {
			result = this.logo.isDisplayed() 
					&& this.btnHome.isDisplayed()
					&& this.btnBooks.isDisplayed()
					&& this.btnCds.isDisplayed()
					&& this.btnBasket.isDisplayed()
					&& this.btnRegister.isDisplayed()
					&& this.btnLogin.isDisplayed()
					&& this.basket.isDisplayed();			
		} catch (Throwable e) {
			System.out.println("An element in the header is missing" + e.getMessage());
		}
		return result;
	}

	public boolean checkSideMenu() {
		boolean result = false;
		try {
			result = this.sideMenu.isDisplayed();			
		} catch (Throwable e) {
			System.out.println("The side menu is missing" + e.getMessage());
		}
		return result;
	}

	public boolean checkProductList() {
		boolean result = false;
		try {
			result = this.productList.isDisplayed();			
		} catch (Throwable e) {
			System.out.println("The product list is missing" + e.getMessage());
		}
		return result;
	}

	public boolean checkSearchCriterias() {
		boolean result = false;
		try {
			result = this.author.isDisplayed() 
					&& this.title.isDisplayed()
					&& this.publisher.isDisplayed()
					&& this.isbn.isDisplayed();	
		} catch (Throwable e) {
			System.out.println("An element in the search criteria is missing" + e.getMessage());
		}
		return result;
	}

}
