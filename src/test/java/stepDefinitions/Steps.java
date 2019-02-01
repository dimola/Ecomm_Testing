package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.ConfigFileReader;
import managers.PageObjectManager;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.BooksPage;
import pageObjects.LoginPage;

public class Steps {
	WebDriver driver;
	LoginPage loginPage;
	BooksPage booksPage;
	PageObjectManager pageObjectManager;
	ConfigFileReader configFileReader;

	@Given("^Login page is loaded$")
	public void Login_page_is_loaded() throws Throwable {

		configFileReader = new ConfigFileReader();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageObjectManager = new PageObjectManager(driver);
		pageObjectManager.getLoginPage().open();
	}

	@When("^I type valid username \"(.*)\"$")
	public void I_type_valid_username(String username) throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(username);

	}

	@And("^I type valid password \"(.*)\"$")
	public void I_type_valid_password(String password) throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterPassword(password);
	}

	@And("^I click on Login button$")
	public void I_click_on_Login_button() throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.clickLogIn();
	}

	@Then("^I should be successfully logged in$")
	public void I_should_be_successfully_logged_in() throws Throwable {

		if (driver.findElements(By.xpath("//*[@id=\"main-menu\"]/a[6]")).size() != 0) {
			System.out.println("Logout button is Present and user is successfully logged in");
		} else {
			System.out.println("Login button is Absent and user is not logged in ");

		}

		driver.quit();
	}

	@Test

	@When("^I redirect to books page$")
	public void I_redirect_to_books_page() throws Throwable {
		configFileReader = new ConfigFileReader();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageObjectManager = new PageObjectManager(driver);
		pageObjectManager.getBooksPage().open();
	}

	@Then("^The header the books categories and the search field are displayed$")
	public void The_header_the_books_categories_and_the_search_field_are_displayed() throws Throwable {
		booksPage = new BooksPage(driver);
		booksPage.checkData();

	}

}
