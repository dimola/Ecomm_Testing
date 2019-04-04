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

		} else{
			Assert.assertTrue("Problems while verifying that Cds Category Page is displayed",
					pageObjectManager.getCdsPage().isOpen());

		}

	}

	@When("^I redirect to books page$")
	public void I_redirect_to_books_page() throws Throwable {
		pageObjectManager.getBooksPage().open();
	}

	@Then("^I should see the books page$")
	public void i_should_see_the_books_page() throws Throwable {
		Assert.assertTrue("Logo is not displayed on the page", pageObjectManager.getBooksPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",
				pageObjectManager.getBooksPage().isSideMenuDisplayed());
		Assert.assertTrue("The Books Product list is not displayed on the page",
				pageObjectManager.getBooksPage().isProductListDisplayed());
		Assert.assertTrue("Search Form is not displayed on the page",
				pageObjectManager.getBooksPage().isSearchFormDisplayed());
	}

	@Then("^I should see all book filtering options$")
	public void i_should_see_all_book_filtering_options() throws Throwable {
		Assert.assertTrue("Author textbox in the Search Form is not displayed on the page",
				pageObjectManager.getBooksPage().isAuthorTextboxDisplayed());
		Assert.assertTrue("Title textbox in the Search Form is not displayed on the page",
				pageObjectManager.getBooksPage().isTitleTextboxDisplayed());
		Assert.assertTrue("Publisher textbox in the Search Form is not displayed on the page",
				pageObjectManager.getBooksPage().isPublisherTextboxDisplayed());
		Assert.assertTrue("ISBN textbox in the Search Form is not displayed on the page",
				pageObjectManager.getBooksPage().isIsbnTextboxDisplayed());
	}

	@Given("^Books page is loaded$")
	public void books_page_is_loaded() throws Throwable {
		pageObjectManager.getBooksPage().open();
	}

	@When("^I search for a certain author \"([^\"]*)\"$")
	public void i_search_for_a_certain_author(String author) throws Throwable {
		pageObjectManager.getBooksPage().enterAuthor(author);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@Then("^All displayed books are written by this author \"([^\"]*)\"$")
	public void all_displayed_books_are_written_by_this_author(String author) throws Throwable {
		Assert.assertTrue("Displayed books are not written by this author",
				pageObjectManager.getBooksPage().areAllProductsWrittenBySearchedAuthorOrArtist(author));
	}

	@When("^I search for a certain Book by its title \"([^\"]*)\"$")
	public void i_search_for_a_certain_Book_by_its_title(String title) throws Throwable {
		pageObjectManager.getBooksPage().enterTitle(title);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@Then("^The book is displayed \"([^\"]*)\"$")
	public void the_book_is_displayed(String title) throws Throwable {
		Assert.assertTrue("Displayed book does not have the same title",
				pageObjectManager.getBooksPage().doesAllProductsContainSearchedTitle(title));
	}

	@When("^I search for a certain publisher \"([^\"]*)\"$")
	public void i_search_for_a_certain_publisher(String publisher) throws Throwable {
		pageObjectManager.getBooksPage().enterPublisher(publisher);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@Then("^All books from this publisher are displayed \"([^\"]*)\"$")
	public void all_books_from_this_publisher_are_displayed(String publisher) throws Throwable {
		Assert.assertTrue("Displayed books are not written by this publisher",
				pageObjectManager.getBooksPage().areAllBooksPublishedBySearchedPublisher(publisher));
	}

	@When("^I search for a certain ISBN \"([^\"]*)\"$")
	public void i_search_for_a_certain_ISBN(String ISBN) throws Throwable {
		pageObjectManager.getBooksPage().enterIsbn(ISBN);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@Then("^The book with that number is displayed \"([^\"]*)\"$")
	public void the_book_with_that_number_is_displayed(String ISBN) throws Throwable {
		Assert.assertTrue("Displayed books are not with this number",
				pageObjectManager.getBooksPage().doesAllProductsContainSearchedISBN(ISBN));
	}

	@When("^I search for more than one of the search criteria at the same time \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_search_for_more_than_one_of_the_search_criteria_at_the_same_time_and_and_and(String author,
			String title, String publisher, String ISBN) throws Throwable {
		pageObjectManager.getBooksPage().enterAuthor(author);
		pageObjectManager.getBooksPage().enterTitle(title);
		pageObjectManager.getBooksPage().enterPublisher(publisher);
		pageObjectManager.getBooksPage().enterIsbn(ISBN);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@Then("^The book answering to the respective criteria is displayed \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_book_answering_to_the_respective_criteria_is_displayed_and_and_and(String author, String title,
			String publisher, String ISBN) throws Throwable {
		Assert.assertTrue("Displayed books are not answering the respective criteria",
				pageObjectManager.getBooksPage().isTheResultAnsweringRespectiveSearchCriteria(author, title, publisher, ISBN));
	}

	@When("^I search with invalid criteria \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_search_with_invalid_criteria_and_and_and(String invalidAuthor, String invalidTitle,
			String invalidPublisher, String invalidISBN) throws Throwable {
		pageObjectManager.getBooksPage().enterAuthor(invalidAuthor);
		pageObjectManager.getBooksPage().enterTitle(invalidTitle);
		pageObjectManager.getBooksPage().enterPublisher(invalidPublisher);
		pageObjectManager.getBooksPage().enterIsbn(invalidISBN);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@Then("^An error message is displayed, stating that there are no such books in the system$")
	public void an_error_message_is_displayed_stating_that_there_are_no_such_books_in_the_system() throws Throwable {
		Assert.assertTrue("Books are displayed", pageObjectManager.getBooksPage().isErrorMessageDisplayed());
	}

	@Then("^I should see the home page$")
	public void i_should_see_the_home_page() throws Throwable {
		Assert.assertTrue("Problems while verifying that Home Page is displayed",
				pageObjectManager.getHomePage().isOpen());
		Assert.assertTrue("Logo is not displayed on the page", pageObjectManager.getHomePage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getHomePage().isViewBasketDisplayed());

	}

	@Then("^I should see Books and CDs categories$")
	public void i_should_see_Books_and_CDs_categories() throws Throwable {
		Assert.assertTrue("Books category button is not displayed on the page",
				pageObjectManager.getHomePage().isBooksCategoryButtonDisplayed());
		Assert.assertTrue("CDs category button is not displayed on the page",
				pageObjectManager.getHomePage().isCdsCategoryButtonDisplayed());
	}

	@When("^I redirect to cds page$")
	public void i_redirect_to_cds_page() throws Throwable {
		pageObjectManager.getCdsPage().open();
	}

	@Then("^I should see the cds page$")
	public void i_should_see_the_cds_page() throws Throwable {
		Assert.assertTrue("Problems while verifying that Cds Page is displayed",
				pageObjectManager.getCdsPage().isOpen());
		Assert.assertTrue("Logo is not displayed on the page", pageObjectManager.getCdsPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsPage().isViewBasketDisplayed());
	}

	@Then("^I should see all cds filtering options$")
	public void i_should_see_all_cds_filtering_options() throws Throwable {
		Assert.assertTrue("Author textbox in the Search Form is not displayed on the page",
				pageObjectManager.getCdsPage().isArtistTextboxDisplayed());
		Assert.assertTrue("Title textbox in the Search Form is not displayed on the page",
				pageObjectManager.getCdsPage().isTitleTextboxDisplayed());
		Assert.assertTrue("Publisher textbox in the Search Form is not displayed on the page",
				pageObjectManager.getCdsPage().isLabelTextboxDisplayed());
		Assert.assertTrue("ISBN textbox in the Search Form is not displayed on the page",
				pageObjectManager.getCdsPage().isComposerTextboxDisplayed());
	}

	@When("^I redirect to not empty \"([^\"]*)\" category of Books$")
	public void i_redirect_to_not_empty_category_of_Books(String bookCategory) throws Throwable {
		switch (bookCategory) {
			case "Art": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(0);
				break;
			}
			case "Biographies": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(1);
				break;
			}
			case "Children’s books": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(2);
				break;
			}
			case "Finance": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(3);
				break;
			}
			case "Computers": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(4);
				break;
			}
			case "Cooking, food & wine": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(5);
				break;
			}
			case "Entertainment": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(6);
				break;
			}
			case "Mind & body": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(7);
				break;
			}
			case "Hobbies": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(9);
				break;
			}
			case "Home & garden": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(10);
				break;
			}
			case "Science & nature": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(18);
				break;
			}
			case "Science fiction": {
				pageObjectManager.getBooksCategoryPage().openNotEmptyBookCategory(19);
				break;
			}
		}
	}

	@Then("^I should see the book category page$")
	public void i_should_see_the_book_category_page() throws Throwable {
		Assert.assertTrue(
				this.pageObjectManager.getBooksCategoryPage().getSelectedCategoryTitle() + " page is not displayed",
				this.pageObjectManager.getBooksCategoryPage().isSelectedCategoryOpen(
						this.pageObjectManager.getBooksCategoryPage().getSelectedCategoryTitle()));
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isSideMenuDisplayed());
		Assert.assertTrue("Product List is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isProductListDisplayed());
	}

	@Then("^I should see all books' elements in the category page$")
	public void i_should_see_all_books_elements_in_the_category_page() throws Throwable {
		Assert.assertTrue("Not all book images are displayed per category",
				pageObjectManager.getBooksCategoryPage().areTheProductsImagesDisplayed());
		Assert.assertTrue("Not all book titles are dispayed per category",
				pageObjectManager.getBooksCategoryPage().areTheProductsTitlesDisplayed());
		Assert.assertTrue("Not all book authors are displayed per category",
				pageObjectManager.getBooksCategoryPage().areTheProductsAuthorsDisplayed());
		Assert.assertTrue("Not all book prices are displayed per category",
				pageObjectManager.getBooksCategoryPage().areTheProductsPricesDisplayed());
		Assert.assertTrue("Not all book add to basket buttons are displayed per category",
				pageObjectManager.getBooksCategoryPage().areTheProductsAddToBasketButtonsDisplayed());
	}

	@When("^I redirect to not empty \"([^\"]*)\" category of CDs$")
	public void i_redirect_to_not_empty_category_of_CDs(String cdsCategory) throws Throwable {
		switch (cdsCategory) {
			case "Alternative": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(0);
				break;
			}
			case "Blues": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(1);
				break;
			}
			case "Children’s music": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(2);
				break;
			}
			case "Classical": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(3);
				break;
			}
			case "Country": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(4);
				break;
			}
			case "Dance & DJ": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(5);
				break;
			}
			case "Folk": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(6);
				break;
			}
			case "New age": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(11);
				break;
			}
			case "Pop": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(13);
				break;
			}
			case "Soul": {
				pageObjectManager.getCdsCategoryPage().openNotEmptyCdsCategory(16);
				break;
			}
		}
	}

	@Then("^I should see the cd category page$")
	public void i_should_see_the_cd_category_page() throws Throwable {
		Assert.assertTrue(
				this.pageObjectManager.getCdsCategoryPage().getSelectedCategoryTitle() + " page is not displayed",
				this.pageObjectManager.getCdsCategoryPage().isSelectedCategoryOpen(
						this.pageObjectManager.getCdsCategoryPage().getSelectedCategoryTitle()));
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isSideMenuDisplayed());
		Assert.assertTrue("Product List is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isProductListDisplayed());
	}

	@Then("^I should see all cds' elements in the category page$")
	public void i_should_see_all_cds_elements_in_the_category_page() throws Throwable {
		Assert.assertTrue("Not all CD images are displayed per category",
				pageObjectManager.getCdsCategoryPage().areTheProductsImagesDisplayed());
		Assert.assertTrue("Not all CD titles are dispayed per category",
				pageObjectManager.getCdsCategoryPage().areTheProductsTitlesDisplayed());
		Assert.assertTrue("Not all CD authors are displayed per category",
				pageObjectManager.getCdsCategoryPage().areTheProductsAuthorsDisplayed());
		Assert.assertTrue("Not all CD prices are displayed per category",
				pageObjectManager.getCdsCategoryPage().areTheProductsPricesDisplayed());
		Assert.assertTrue("Not all CD add to basket buttons are displayed per category",
				pageObjectManager.getCdsCategoryPage().areTheProductsAddToBasketButtonsDisplayed());
	}

	@When("^I redirect to empty \"([^\"]*)\" category of Books$")
	public void i_redirect_to_empty_category_of_Books(String bookCategory) throws Throwable {
		switch (bookCategory) {
			case "History": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(8);
				break;
			}
			case "Horror": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(11);
				break;
			}
			case "Literature & fiction": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(12);
				break;
			}
			case "Mystery & thrillers": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(13);
				break;
			}
			case "Non-fiction": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(14);
				break;
			}
			case "Professional & technical": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(15);
				break;
			}
			case "Reference": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(16);
				break;
			}
			case "Religion & spirituality": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(17);
				break;
			}
			case "Sports & outdoors": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(20);
				break;
			}
			case "Travel": {
				pageObjectManager.getBooksCategoryPage().openEmptyBookCategory(21);
				break;
			}
		}
	}

	@Then("^I should see the empty book category page$")
	public void i_should_see_the_empty_book_category_page() throws Throwable {
		Assert.assertTrue(
				this.pageObjectManager.getBooksCategoryPage().getSelectedCategoryTitle() + " page is not displayed",
				this.pageObjectManager.getBooksCategoryPage().isSelectedCategoryOpen(
						this.pageObjectManager.getBooksCategoryPage().getSelectedCategoryTitle()));
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isSideMenuDisplayed());
		Assert.assertTrue("Product List is not displayed on the page",
				pageObjectManager.getBooksCategoryPage().isProductListDisplayed());
		Assert.assertTrue("The Book category is not empty", pageObjectManager.getBooksCategoryPage().isCategoryEmpty());
	}

	@When("^I redirect to empty \"([^\"]*)\" category of CDs$")
	public void i_redirect_to_empty_category_of_CDs(String cdsCategory) throws Throwable {
		switch (cdsCategory) {
			case "Emerging artists": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(7);
				break;
			}

			case "International": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(8);
				break;
			}
			case "Jazz": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(9);
				break;
			}
			case "Miscellaneous": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(10);
				break;
			}
			case "Opera & vocal": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(12);
				break;
			}
			case "Rap & hip-hop": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(14);
				break;
			}
			case "R&B": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(15);
				break;
			}
			case "Soundtracks": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(17);
				break;
			}
			case "Vocalists & Broadway": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(18);
				break;
			}
			case "World": {
				pageObjectManager.getCdsCategoryPage().openEmptyCdsCategory(19);
				break;
			}
		}
	}

	@Then("^I should see the empty CDs category page$")
	public void i_should_see_the_empty_CDs_category_page() throws Throwable {
		Assert.assertTrue(
				this.pageObjectManager.getCdsCategoryPage().getSelectedCategoryTitle() + " page is not displayed",
				this.pageObjectManager.getCdsCategoryPage().isSelectedCategoryOpen(
						this.pageObjectManager.getCdsCategoryPage().getSelectedCategoryTitle()));
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isSideMenuDisplayed());
		Assert.assertTrue("Product List is not displayed on the page",
				pageObjectManager.getCdsCategoryPage().isProductListDisplayed());
		Assert.assertTrue("The Book category is not empty", pageObjectManager.getCdsCategoryPage().isCategoryEmpty());
	}

	@When("^I am on a product details page with \"([^\"]*)\" url$")
	public void i_am_on_a_product_details_page_with_url(String detailsPageURL) throws Throwable {
		this.pageObjectManager.getProductDetailsPage().openProductDetailsPage(detailsPageURL);
	}

	@Then("^I can see the product details page for \"([^\"]*)\" book/cd$")
	public void i_can_see_the_product_details_page_for_book_cd(String product) throws Throwable {
		Assert.assertTrue("Problems while verifying that Book Details page is open",
				this.pageObjectManager.getProductDetailsPage().isProductDetailsPageOpen(product));
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isViewBasketDisplayed());
		Assert.assertTrue("Side Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isSideMenuDisplayed());
	}

	@Then("^I can see the book details$")
	public void i_can_see_the_book_details() throws Throwable {
		Assert.assertTrue("The image is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isProductImageDisplayed());
		Assert.assertTrue("The author is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isProductAuthorDisplayed());
		Assert.assertTrue("The book publisher is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isProductPublisherDisplayed());
		Assert.assertTrue("The book ISBN is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isProductISBNDisplayed());
		Assert.assertTrue("The price is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isProductPriceDisplayed());
		Assert.assertTrue("The add to basket button is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isAddToBasketButtonDisplayed());
		Assert.assertTrue("The back to prodcut details link is not displayed on the book details page",
				this.pageObjectManager.getProductDetailsPage().isBackToProductListLinkDisplayed());
	}

	@Then("^I can see the CD details$")
	public void i_can_see_the_CD_details() throws Throwable {
		Assert.assertTrue("The image is not displayed on the CD details page",
				this.pageObjectManager.getProductDetailsPage().isProductImageDisplayed());
		Assert.assertTrue("The Artist is not displayed on the CD details page",
				this.pageObjectManager.getProductDetailsPage().isProductArtistDisplayed());
		Assert.assertTrue("The CD label is not displayed on the CD details page",
				this.pageObjectManager.getProductDetailsPage().isProductLabelDisplayed());
		Assert.assertTrue("The price is not displayed on the CD details page",
				this.pageObjectManager.getProductDetailsPage().isProductPriceDisplayed());
		Assert.assertTrue("The add to basket button is not displayed on the CD details page",
				this.pageObjectManager.getProductDetailsPage().isAddToBasketButtonDisplayed());
		Assert.assertTrue("The back to prodcut details link is not displayed on the CD details page",
				this.pageObjectManager.getProductDetailsPage().isBackToProductListLinkDisplayed());
	}

	@When("^I'm on empty shopping basket page$")
	public void i_m_on_empty_shopping_basket_page() throws Throwable {
		this.pageObjectManager.getBasketPage().open();
	}

	@Then("^I can see the shopping basket page$")
	public void i_can_see_the_shopping_basket_page() throws Throwable {
		Assert.assertTrue("Problems while verifying that Shopping Basket Page is open",
				this.pageObjectManager.getBasketPage().isOpen());
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isViewBasketDisplayed());
	}

	@Then("^I can see the following text displayed : \"([^\"]*)\"$")
	public void i_can_see_the_following_text_displayed(String arg1) throws Throwable {
		Assert.assertTrue("Problems while verifying that shopping basket is empty",
				this.pageObjectManager.getBasketPage().isEmtpyBasketDisplayed());

	}

	@When("^(\\d+) products are added to the basket$")
	public void products_are_added_to_the_basket(int number) throws Throwable {
		for (int i = 1; i <= number; i++) {
			if (this.pageObjectManager.getHomePage().getRandomCategoryName() == "Books") {
				this.pageObjectManager.getBooksPage().open();
				this.pageObjectManager.getBooksCategoryPage().getSideBarButtons()
						.get(this.pageObjectManager.getBooksCategoryPage().getRandomNotEmptyBookCategoryNumbers())
						.click();
				this.pageObjectManager.getBooksCategoryPage().addRandomProductToBasketFromProductList();
			} else {
				this.pageObjectManager.getCdsPage().open();
				this.pageObjectManager.getCdsCategoryPage().getSideBarButtons()
						.get(this.pageObjectManager.getCdsCategoryPage().getRandomNotEmptyCdCategoryNumbers()).click();
				this.pageObjectManager.getCdsCategoryPage().addRandomProductToBasketFromProductList();
			}
		}
	}

	@When("^I open the shopping basket$")
	public void i_open_the_shopping_basket() throws Throwable {
		this.pageObjectManager.getBasketPage().open();
	}

	@Then("^I should see the shopping basket page$")
	public void i_should_see_the_shopping_basket_page() throws Throwable {
		this.pageObjectManager.getBasketPage().isOpen();
		Assert.assertTrue("Logo is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isLogoDisplayed());
		Assert.assertTrue("Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isMainMenuDisplayed());
		Assert.assertTrue("Home button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isHomeButtonDisplayed());
		Assert.assertTrue("Books button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isBooksButtonDisplayed());
		Assert.assertTrue("Cds button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isCdsButtonDisplayed());
		Assert.assertTrue("Basket button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isBasketButtonDisplayed());
		Assert.assertTrue("Register button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isRegisterButtonDisplayed());
		Assert.assertTrue("Login button in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isLoginButtonDisplayed());
		Assert.assertTrue("View Basket in the Main Menu is not displayed on the page",
				pageObjectManager.getProductDetailsPage().isViewBasketDisplayed());
	}

	@Then("^I should see all (\\d+) added products$")
	public void i_should_see_all_added_products(int number) throws Throwable {
		Assert.assertTrue("Not all added products are displayed in the Basket",
				this.pageObjectManager.getBasketPage().areAllAddedProductsDisplayed(number));
	}

	@Then("^I should see all shopping basket information and buttons$")
	public void i_should_see_all_shopping_basket_information_and_buttons() throws Throwable {
		Assert.assertTrue("Basket Header is not displayed",
				this.pageObjectManager.getBasketPage().isBasketHeaderDisplayed());
		Assert.assertTrue("Remove Product button is not displayed for the products added in the basket",
				this.pageObjectManager.getBasketPage().isRemoveProductButtonDisplayed());
		Assert.assertTrue("Remove One Product button is not displayed for the products added in the basket",
				this.pageObjectManager.getBasketPage().isRemoveOneProductButtonDisplayed());
		Assert.assertTrue("Add One Product button is not displayed for the products added in the basket",
				this.pageObjectManager.getBasketPage().isAddOneProductButtonDisplayed());
		Assert.assertTrue("Product Count button is not displayed for the products added in the basket",
				this.pageObjectManager.getBasketPage().isProductCountDisplayed());
		Assert.assertTrue("Product Price is not displayed for the products added in the basket",
				this.pageObjectManager.getBasketPage().isProductPriceDisplayed());
		Assert.assertTrue("Product Subtotal is not displayed for the products added in the basket or is not correct",
				this.pageObjectManager.getBasketPage().isProductSubtotalDisplayed());
		Assert.assertTrue("Basket Total is not displayed for the products added in the basket or is not correct",
				this.pageObjectManager.getBasketPage().isBasketTotalDisplayed());
		Assert.assertTrue("Basket Total is not displayed for the products added in the basket or is not correct",
				this.pageObjectManager.getBasketPage().isCheckoutButtonDisplayed());
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