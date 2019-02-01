package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

	public BooksPage open() {
		driver.get(pageUrl);
		return this;

	}

	public void checkHeaderData() {
		if (driver.findElements(By.id("logo")).size() != 0) {
			System.out.println("Logo is found");
		} else {
			System.out.println("logo is not found ");
		}
		if (driver.findElements(By.xpath("//a[contains(text(),'Home')]")).size() != 0) {
			System.out.println("Home button is found");
		} else {
			System.out.println("Home button is not found ");
		}
		if (driver.findElements(By.xpath("//a[contains(text(),'Books')]")).size() != 0) {
			System.out.println("Books button is found");
		} else {
			System.out.println("Books button is not found ");
		}
		if (driver.findElements(By.xpath("//a[contains(text(),'CDs')]")).size() != 0) {
			System.out.println("CDs button is found");
		} else {
			System.out.println("CDs button is not found ");
		}
		if (driver.findElements(By.xpath("//a[contains(text(),'Basket')]")).size() != 0) {
			System.out.println("Basket button is found");
		} else {
			System.out.println("Basket button is not found ");
		}
		if (driver.findElements(By.xpath("//a[contains(text(),'Register')]")).size() != 0) {
			System.out.println("Register button is found");
		} else {
			System.out.println("Register button is not found ");
		}
		if (driver.findElements(By.xpath("//a[contains(text(),'Login')]")).size() != 0) {
			System.out.println("Login button is found");
		} else {
			System.out.println("Login button is not found ");
		}
		if (driver.findElements(By.id("basket")).size() != 0) {
			System.out.println("Basket logo is found");
		} else {
			System.out.println("Basket logo is not found ");
		}
	}

	public void checkSideMenu() {
		if (driver.findElements(By.id("side-menu")).size() != 0) {
			System.out.println("Side menu is found");
		} else {
			System.out.println("Side menu is not found ");
		}
	}

	public void checkProductList() {
		if (driver.findElements(By.id("product-list")).size() != 0) {
			System.out.println("Product list is found");
		} else {
			System.out.println("Product list is not found ");
		}
	}

	public void checkSearchCriterias() {
		if (driver.findElements(By.xpath("//td[contains(text(),'Author')]")).size() != 0) {
			System.out.println("Author criteria is found");
		} else {
			System.out.println("Author criteria is not found ");
		}
		if (driver.findElements(By.xpath("//td[contains(text(),'Title')]")).size() != 0) {
			System.out.println("Title criteria is found");
		} else {
			System.out.println("Title criteria is not found ");
		}
		if (driver.findElements(By.xpath("//td[contains(text(),'Publisher')]")).size() != 0) {
			System.out.println("Publisher criteria is found");
		} else {
			System.out.println("Publisher criteria is not found ");
		}
		if (driver.findElements(By.xpath("//td[contains(text(),'ISBN')]")).size() != 0) {
			System.out.println("ISBN criteria is found");
		} else {
			System.out.println("ISBN criteria is not found ");
		}
	}

	public void checkData() {
		checkHeaderData();
		checkSideMenu();
		checkProductList();
		checkSearchCriterias();
	}

}
