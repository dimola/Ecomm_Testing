package managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import pageObjects.BooksPage;
import org.openqa.selenium.chrome.ChromeDriver;

import dataProviders.ConfigFileReader;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class PageObjectManager {
	private WebDriver driver;
	private LoginPage loginPage;
	private BooksPage booksPage;
	private HomePage homePage;
	private static PageObjectManager pageObjectManager;

	public static void init() {
		if (pageObjectManager == null || pageObjectManager.driver == null) {
			ConfigFileReader configFileReader = new ConfigFileReader();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

			pageObjectManager = new PageObjectManager(driver);
		}
	}

	public static PageObjectManager getManager() {
		return pageObjectManager;
	}

	private PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}

	public BooksPage getBooksPage() {
		return (booksPage == null) ? booksPage = new BooksPage(driver) : booksPage;
	}
	
	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

	public void quit() {
		this.driver.quit();
		pageObjectManager = null;
	}

}
