package stepDefinitions;

import org.junit.Assert;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageObjectManager;

public class Steps {

	PageObjectManager pageObjectManager;

	@Given("^Login page is loaded$")
	public void Login_page_is_loaded() throws Throwable {
		pageObjectManager.getLoginPage().open();
	}

	@Then("^I should be successfully logged in$")
	public void I_should_be_successfully_logged_in() throws Throwable {
		Assert.assertTrue("Home page is not on focus", pageObjectManager.getHomePage().isOpen());
		Assert.assertFalse("Login button is displayed after login but it should not",
				pageObjectManager.getHomePage().isLoginButtonDisplayed());
		Assert.assertFalse("Register button is displayed after login but it should not",
				pageObjectManager.getHomePage().isRegisterButtonDisplayed());
		Assert.assertTrue("Logout button is not displayed after login but it should",
				pageObjectManager.getHomePage().isLogoutButtonDisplayed());
	}

	@Then("^I am not logged in the system$")
	public void I_am_not_logged_in_the_system() throws Throwable {
		Assert.assertTrue("Home page is not on focus", pageObjectManager.getLoginPage().isOpen());
	}

	@Then("^An error message is displayed$")
	public void An_error_message_is_displayed() throws Throwable {
		Assert.assertTrue("Timer is not displayed", pageObjectManager.getLoginPage().timerIsDisplayed());
	}

	@Given("^I am logged in with credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	@When("^I login with credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_am_logged_in_with_credentials_and(String username, String password) throws Throwable {
		pageObjectManager.getLoginPage().login(username, password);
	}

	@When("^I logout$")
	public void I_click_on_Logout() throws Throwable {
		pageObjectManager.getHomePage().clickLogOut();
		pageObjectManager.getLogoutPage().clickConfirmLogOut();
	}

	@Then("^I am successfully logged out$")
	public void I_am_successfully_logged_out() throws Throwable {
		Assert.assertTrue("Login button is not displayed after logout",
				pageObjectManager.getHomePage().isLoginButtonDisplayed());
		Assert.assertTrue("Register button is not displayed after logout",
				pageObjectManager.getHomePage().isRegisterButtonDisplayed());
		Assert.assertFalse("Logout button is displayed after logout",
				pageObjectManager.getHomePage().isLogoutButtonDisplayed());
	}

	// TC 20 Verify that links are redirecting to correct place

	@Given("^Home page is loaded$")
	public void Home_page_is_loaded() throws Throwable {
		pageObjectManager.getHomePage().open();
	}

	@When("^I click on a certain category \"([^\"]*)\"$")
	public void I_click_on_a_certain_category(String category) throws Throwable {
		if (category == "Books") {
			pageObjectManager.getHomePage().selectBookCategory();
		} else
			pageObjectManager.getHomePage().selectCdsCategory();
	}

	@Then("^I am redirected to the respective category \"([^\"]*)\"$")
	public void I_am_redirected_to_the_respective_category(String page) throws Throwable {
		if (page == "BooksPage") {
			Assert.assertTrue("Problems while verifying that Books Category Page is displayed",
					pageObjectManager.getBooksPage().isOpen());
		} else
			Assert.assertTrue("Problems while verifying that Cds Category Page is displayed",
					pageObjectManager.getCdsPage().isOpen());
	}

	@When("^I redirect to books page$")
	public void I_redirect_to_books_page() throws Throwable {
		pageObjectManager.getBooksPage().open();
	}

	@Then("^I should see the books page$")
	public void i_should_see_the_books_page() throws Throwable {
		Assert.assertTrue("Logo is not displayed on the page",pageObjectManager.getBooksPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",pageObjectManager.getBooksPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",pageObjectManager.getBooksPage().isSideMenuDisplayed());
		Assert.assertTrue("The Books Product list is not displayed on the page",pageObjectManager.getBooksPage().isProductListDisplayed());
		Assert.assertTrue("Search Form is not displayed on the page",pageObjectManager.getBooksPage().isSearchFormDisplayed());
	}

	@Then("^I should see all book filtering options$")
	public void i_should_see_all_book_filtering_options() throws Throwable {
		Assert.assertTrue("Author textbox in the Search Form is not displayed on the page",pageObjectManager.getBooksPage().isAuthorTextboxDisplayed());
		Assert.assertTrue("Title textbox in the Search Form is not displayed on the page",pageObjectManager.getBooksPage().isTitleTextboxDisplayed());
		Assert.assertTrue("Publisher textbox in the Search Form is not displayed on the page",pageObjectManager.getBooksPage().isPublisherTextboxDisplayed());
		Assert.assertTrue("ISBN textbox in the Search Form is not displayed on the page",pageObjectManager.getBooksPage().isIsbnTextboxDisplayed());   
	}
	
	@Before
	public void init() {
		PageObjectManager.init();
		pageObjectManager = PageObjectManager.getManager();
	}

	@After
	public void cleanUp() {
		pageObjectManager.quit();
	}

}