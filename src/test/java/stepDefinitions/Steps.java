package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.ConfigFileReader;
import managers.PageObjectManager;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class Steps {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	PageObjectManager pageObjectManager;
	ConfigFileReader configFileReader;

	// TC 1 Login with valid credentials

	@Test
	@BeforeClass
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
		homePage = new HomePage(driver);
		homePage.isHomePageOnFocus();

		driver.close();
	}

	// TC 2 Login with invalid credentials

	@Test

	@When("^I type invalid username \"(.*)\"$")
	public void I_type_invalid_username(String username) throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(username);
	}

	@When("^I type invalid password \"(.*)\"$")
	public void I_type_invalid_password(String password) throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterPassword(password);
	}

	@Then("^I am not logged in the system$")
	public void I_am_not_logged_in_the_system() throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.isLoginPageOnFocus();
		driver.quit();
	}

}
