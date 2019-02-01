package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.BooksPage;
import pageObjects.LoginPage;

public class PageObjectManager {
	private WebDriver driver;
	private LoginPage loginPage;
	private BooksPage booksPage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public BooksPage getBooksPage() {
		return (booksPage == null) ? booksPage = new BooksPage(driver) : booksPage;
	}

}
