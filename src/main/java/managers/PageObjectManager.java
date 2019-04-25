package managers;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import dataProviders.ConfigFileReader;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageObjects.*;

public class PageObjectManager {

	private WebDriver driver;
	private LoginPage loginPage;
	private BooksPage booksPage;
	private HomePage homePage;
	private LogoutPage logoutPage;
	private CdsPage cdsPage;
	private BooksCategoryPage booksCategoryPage;
	private CdsCategoryPage cdsCategoryPage;
	private ProductDetailsPage productDetailsPage;
	private BasketPage basketPage;
	private CheckoutPage checkoutPage;

	private static PageObjectManager pageObjectManager;

	public static void init() {
		if (pageObjectManager == null || pageObjectManager.driver == null) {
			ConfigFileReader configFileReader = new ConfigFileReader();

			if (System.getProperty("os.name").contains("Windows")){
				System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

				pageObjectManager = new PageObjectManager(driver);
			}
			else {
				WebDriver driver = new HtmlUnitDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

				pageObjectManager = new PageObjectManager(driver);
			}
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

	public BooksCategoryPage getBooksCategoryPage() {
		return (booksCategoryPage == null) ? booksCategoryPage = new BooksCategoryPage(driver) : booksCategoryPage;
	}
	
	public CdsCategoryPage getCdsCategoryPage() {
		return (cdsCategoryPage == null) ? cdsCategoryPage = new CdsCategoryPage(driver) : cdsCategoryPage;
	}
	
	public ProductDetailsPage getProductDetailsPage() {
		return (productDetailsPage == null) ? productDetailsPage = new ProductDetailsPage(driver) : productDetailsPage;
	}
	
	public BasketPage getBasketPage() {
		return (basketPage == null) ? basketPage = new BasketPage(driver) : basketPage;
	}

	public CheckoutPage getCheckoutPage() {
		return (checkoutPage == null) ? checkoutPage = new CheckoutPage(driver) : checkoutPage;
	}
	
	public void quit() {
		this.driver.quit();
		pageObjectManager = null;
	}

}
