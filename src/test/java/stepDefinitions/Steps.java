package stepDefinitions;
import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageObjectManager;

public class Steps {
	PageObjectManager pageObjectManager;

	@Before public void setUp(){ 
	}

	@Given("^Login page is loaded$")
	public void Login_page_is_loaded() throws Throwable {
		pageObjectManager = PageObjectManager.getManager();
		pageObjectManager.getLoginPage().open();
	}

	@When("^I type username \"([^\"]*)\"$")
	public void I_type_username(String username) throws Throwable {
		pageObjectManager.getLoginPage().enterUserName(username);
	}

	@And("^I type password \"(.*)\"$")
	public void I_type_password(String password) throws Throwable {
		pageObjectManager.getLoginPage().enterPassword(password);

	}

	@And("^I click on Login button$")
	public void I_click_on_Login_button() throws Throwable {
		pageObjectManager.getLoginPage().clickLogIn();
	}

	@Then("^I should be successfully logged in$")
	public void I_should_be_successfully_logged_in() throws Throwable {
		pageObjectManager.getHomePage().isHomePageOnFocus();
	}

	@When("^I try to login three times with invalid credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_try_to_login_three_times_with_invalid_credentials_and(String username, String password)
			throws Throwable {
		pageObjectManager.getLoginPage().enterUsernameAndPassword(username, password);
		Assert.assertTrue(pageObjectManager.getLoginPage().timerIsDisplayed());
	}

	@Then("^An error message is displayed$")
	public void An_error_message_is_displayed() throws Throwable {
		Assert.assertTrue(pageObjectManager.getLoginPage().timerIsDisplayed());

	}

	@Then("^I am not logged in the system$")
	public void I_am_not_logged_in_the_system() throws Throwable {
		Assert.assertTrue(pageObjectManager.getLoginPage().isLoginPageOnFocus());
	}

	@After
	public void cleanUp() {
		pageObjectManager.quit();
	}

	@Before
	public void init( ) {
		PageObjectManager.init();
	}
}