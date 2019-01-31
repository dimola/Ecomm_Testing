package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProviders.ConfigFileReader;
import managers.PageObjectManager;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.LoginPage;

public class Steps {
	WebDriver driver;
	LoginPage loginPage;
	PageObjectManager pageObjectManager;
	ConfigFileReader configFileReader;

	@Before public void setUp(){ 
		driver = new ChromeDriver(); 
	}
	
	@Test // Login with valid credentials

	@Given("^Login page is loaded$")
	public void Login_page_is_loaded() throws Throwable {

		configFileReader = new ConfigFileReader();
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
		loginPage.isLoginPageOnFocus();
	}

	@Test // Leaving the fields empty results in an error message

	@When("^I type invalid username \"(.*)\"$")
	public void I_type_invalid_username(String username) throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterUserName(username);

	}

	@And("^I type invalid password \"(.*)\"$")
	public void I_type_invalid_password(String password) throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterPassword(password);
	}

	@Then("^I am not logged in the system$")
	public void I_am_not_logged_in_the_system() throws Throwable {
		loginPage.isLoginPageOnFocus();
	}

	@Test // Try to login 3 times with invalid credentials results in an error message

	@When("^I try to login three times with invalid credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_try_to_login_three_times_with_invalid_credentials_and(String username, String password)
			throws Throwable {
		loginPage = new LoginPage(driver);
		loginPage.enterUsernameAndPassword(username, password);
	}

	@Then("^An error message is displayed$")
	public void An_error_message_is_displayed() throws Throwable {
		loginPage.timerIsDisplayed();
	}
	
	@After public void cleanUp(){ 
		driver.quit(); 
	} 

}
