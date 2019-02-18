package managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dataProviders.ConfigFileReader;
import pageObjects.BooksPage;
import pageObjects.CdsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;

public class PageObjectManager {

	private WebDriver driver;
	private LoginPage loginPage;
	private BooksPage booksPage;
	private HomePage homePage;
	private LogoutPage logoutPage;
	private CdsPage cdsPage;

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

	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}

	public LogoutPage getLogoutPage() {
		return (logoutPage == null) ? logoutPage = new LogoutPage(driver) : logoutPage;
	}

	public BooksPage getBooksPage() {
		return (booksPage == null) ? booksPage = new BooksPage(driver) : booksPage;
	}

	public CdsPage getCdsPage() {
		return (cdsPage == null) ? cdsPage = new CdsPage(driver) : cdsPage;
	}

	public void quit() {
		this.driver.quit();
		pageObjectManager = null;
	}	
	}

