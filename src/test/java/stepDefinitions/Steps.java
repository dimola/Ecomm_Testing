package stepDefinitions;

import org.assertj.core.api.SoftAssertions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageObjectManager;
import pageObjects.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class Steps {

	PageObjectManager pageObjectManager;

	//Variable used in some tests
	int productQuantity = 0;

	/*
		Given statements
	 */
	@Given("^Login page is loaded$")
	public void Login_page_is_loaded(){
		pageObjectManager.getLoginPage().open();
	}

	@Given("^I am logged in with credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	@When("^I login with credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_am_logged_in_with_credentials_and(String username, String password){
		pageObjectManager.getLoginPage().login(username, password);
	}

	@When("^I redirect to home page$")
	@Given("^Home page is loaded$")
	public void Home_page_is_loaded(){
		pageObjectManager.getHomePage().open();
	}

	@Given("^Books page is loaded$")
	public void books_page_is_loaded(){
		pageObjectManager.getBooksPage().open();
	}

	@Given("^I am not logged in$")
	public void i_am_not_logged_in(){
		pageObjectManager.getHomePage().open();
		if (pageObjectManager.getHomePage().isLogoutButtonDisplayed()){
			pageObjectManager.getLogoutPage().logout();
		}
	}

	@Given("^I am logged in$")
	public void i_am_logged_in(){
		pageObjectManager.getHomePage().open();
		if (pageObjectManager.getHomePage().isLoginButtonDisplayed()){
			pageObjectManager.getLoginPage().open();
			pageObjectManager.getLoginPage().login("student1", "stpass1");
		}
	}

	@Given("^I am on the shopping basket page$")
	@When("^I open the shopping basket$")
	public void i_open_the_shopping_basket(){
		this.pageObjectManager.getBasketPage().open();
	}

	@Given("^One product is already added in the basket$")
	public void one_product_is_already_added_in_the_basket(){
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

	@Given("^I am on the Checkout page$")
	public void i_am_on_the_checkout_page(){
		pageObjectManager.getBasketPage().open();
		pageObjectManager.getBasketPage().checkoutBasket();
	}

	/*
		When statements
	 */
	@When("^I logout$")
	public void I_click_on_Logout(){
		pageObjectManager.getHomePage().clickLogOut();
		pageObjectManager.getLogoutPage().logout();
	}

	@When("^I click on a certain category \"([^\"]*)\"$")
	public void I_click_on_a_certain_category(String category){
		if (category == "Books") {
			pageObjectManager.getHomePage().selectBookCategory();
		} else
			pageObjectManager.getHomePage().selectCdsCategory();
	}

	@When("^I redirect to books page$")
	public void I_redirect_to_books_page(){
		pageObjectManager.getBooksPage().open();
	}

	@When("^I search for a certain author \"([^\"]*)\"$")
	public void i_search_for_a_certain_author(String author){
		pageObjectManager.getBooksPage().enterAuthor(author);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@When("^I search for a certain Book by its title \"([^\"]*)\"$")
	public void i_search_for_a_certain_Book_by_its_title(String title){
		pageObjectManager.getBooksPage().enterTitle(title);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@When("^I search for a certain publisher \"([^\"]*)\"$")
	public void i_search_for_a_certain_publisher(String publisher){
		pageObjectManager.getBooksPage().enterPublisher(publisher);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@When("^I search for a certain ISBN \"([^\"]*)\"$")
	public void i_search_for_a_certain_ISBN(String ISBN){
		pageObjectManager.getBooksPage().enterIsbn(ISBN);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@When("^I search for more than one of the search criteria at the same time \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_search_for_more_than_one_of_the_search_criteria_at_the_same_time_and_and_and(String author,
																							   String title, String publisher, String ISBN){
		pageObjectManager.getBooksPage().enterAuthor(author);
		pageObjectManager.getBooksPage().enterTitle(title);
		pageObjectManager.getBooksPage().enterPublisher(publisher);
		pageObjectManager.getBooksPage().enterIsbn(ISBN);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@When("^I search with invalid criteria \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_search_with_invalid_criteria_and_and_and(String invalidAuthor, String invalidTitle,
														   String invalidPublisher, String invalidISBN){
		pageObjectManager.getBooksPage().enterAuthor(invalidAuthor);
		pageObjectManager.getBooksPage().enterTitle(invalidTitle);
		pageObjectManager.getBooksPage().enterPublisher(invalidPublisher);
		pageObjectManager.getBooksPage().enterIsbn(invalidISBN);
		pageObjectManager.getBooksPage().clickSubmit();
	}

	@When("^I redirect to cds page$")
	public void i_redirect_to_cds_page(){
		pageObjectManager.getCdsPage().open();
	}

	@When("^I redirect to not empty \"([^\"]*)\" category of Books$")
	public void i_redirect_to_not_empty_category_of_Books(String bookCategory) {

		pageObjectManager.getBooksCategoryPage().openCategory(bookCategory);
	}

	@When("^I redirect to not empty \"([^\"]*)\" category of CDs$")
	public void i_redirect_to_not_empty_category_of_CDs(String cdsCategory){

		pageObjectManager.getCdsCategoryPage().openCategory(cdsCategory);
	}

	@When("^I redirect to empty \"([^\"]*)\" category of Books$")
	public void i_redirect_to_empty_category_of_Books(String bookCategory){

		pageObjectManager.getBooksCategoryPage().openCategory(bookCategory);

	}

	@When("^I redirect to empty \"([^\"]*)\" category of CDs$")
	public void i_redirect_to_empty_category_of_CDs(String cdsCategory){
		pageObjectManager.getCdsCategoryPage().openCategory(cdsCategory);

	}

	@When("^I am on a product details page with \"([^\"]*)\" url$")
	public void i_am_on_a_product_details_page_with_url(String detailsPageURL){
		this.pageObjectManager.getProductDetailsPage().openProductDetailsPage(detailsPageURL);
	}

	@When("^I'm on empty shopping basket page$")
	public void i_m_on_empty_shopping_basket_page(){
		this.pageObjectManager.getBasketPage().open();
	}

	@When("^(\\d+) products are added to the basket$")
	public void products_are_added_to_the_basket(int number){
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

	@When("^I tap (-?\\d+) times on add one button in counter$")
	public void i_tap_on_add_one_button_in_counter(int tapTimes){
		productQuantity = pageObjectManager.getBasketPage().getProductCount(1);
		while(tapTimes != 0){
			pageObjectManager.getBasketPage().addOneQuantity(1);
			tapTimes -= 1;
		}
	}

	@When("^I tap (-?\\d+) times on remove one button in counter$")
	public void i_tap_on_remove_one_button_in_counter(int tapTimes){
		productQuantity = pageObjectManager.getBasketPage().getProductCount(1);
		while(tapTimes != 0){
			pageObjectManager.getBasketPage().removeOneQuantity(1);
			tapTimes -= 1;
		}
	}

	@When("^I click on remove button for product number (-?\\d+)$")
	public void i_click_on_remove_button_for_product_number(int productNumber){
		productQuantity = pageObjectManager.getBasketPage().getProductsCount();
		pageObjectManager.getBasketPage().removeProduct(productNumber);
	}

	@When("^I click on checkout button$")
	public void i_click_on_checkout_button(){
		assertThat(pageObjectManager.getBasketPage().isCheckoutButtonDisplayed())
				.as("Checkout button is not displayed!")
				.isTrue();
		pageObjectManager.getBasketPage().checkoutBasket();
	}

	@When("^I log in$")
	public void i_log_in(){
		pageObjectManager.getCheckoutPage().login("student1", "stpass1");
	}

	@When("^I confirm purchase$")
	public void i_confirm_purchase(){
		pageObjectManager.getCheckoutPage().confirmPurchase();
	}

	@When("^I cancel purchase$")
	public void i_cancel_purchase(){
		pageObjectManager.getCheckoutPage().cancelPurchase();
	}

	/*
		Then statements
	 */
	@Then("^I should be successfully logged in$")
	public void I_should_be_successfully_logged_in(){
		HomePage homePage = pageObjectManager.getHomePage();
		assertThat(homePage.isOpen()).as("Home page is not open!").isTrue();
		assertThat(homePage.getPageTitle()).as("Expected title: \"Home\". Actual title: %s.", homePage.getPageTitle())
				.isEqualTo("Home");
		assertThat(homePage.getLoginButtonText()).as("Expected: Login button not present. Actual: Login button present with text %s.", homePage.getLoginButtonText())
				.contains("Can't find");
		assertThat(homePage.getLogoutButtonText()).as("Expected logout button text: Logout. Actual: %s.", homePage.getLogoutButtonText())
				.isEqualTo("Logout");
		assertThat(homePage.getRegisterButtonText()).as("Expected: Register button not present. Actual: Register button present with text %s.", homePage.getRegisterButtonText())
				.contains("Can't find");

	}

	@Then("^I am not logged in the system$")
	public void I_am_not_logged_in_the_system(){
		String pageTitle = pageObjectManager.getLoginPage().getPageTitle();
		assertThat(pageTitle).as("Expected title: Login. Actual title: %s .", pageTitle).isEqualTo("Login");
	}

	@Then("^An error message is displayed$")
	public void An_error_message_is_displayed(){
		assertThat(pageObjectManager.getLoginPage().getErrorTime()).as("Error message timer is not displayed.").isNotNull();
	}

	@Then("^I am successfully logged out$")
	public void I_am_successfully_logged_out(){
		HomePage homePage = pageObjectManager.getHomePage();
		assertThat(homePage.isOpen()).as("Home page is not open!").isTrue();
		assertThat(homePage.getPageTitle()).as("Expected title: \"Home\". Actual title: %s.", homePage.getPageTitle())
				.isEqualTo("Home");
		assertThat(homePage.getLoginButtonText()).as("Expected login button text: Login. Actual: %s.", homePage.getLoginButtonText())
				.isEqualTo("Login");
		assertThat(homePage.getLogoutButtonText()).as("Expected logout button to be not present. Actual: button is present with text %s.", homePage.getLogoutButtonText())
				.isNull();
		assertThat(homePage.getRegisterButtonText()).as("Expected register button text: Register. Actual: %s.", homePage.getRegisterButtonText())
				.isEqualTo("Register");
	}

	@Then("^I am redirected to the respective category \"([^\"]*)\"$")
	public void I_am_redirected_to_the_respective_category(String page){
		if (page == "BooksPage") {
			assertThat(pageObjectManager.getBooksPage().isOpen()).as("Books page is not open!").isTrue();
			assertThat(pageObjectManager.getBooksPage().getPageTitle()).as("Expected to be on Books page. Actual on page %s", pageObjectManager.getBooksPage().getPageTitle()).isEqualTo("Books");
		} else{
			assertThat(pageObjectManager.getCdsPage().isOpen()).as("CDs page is not open!").isTrue();
			assertThat(pageObjectManager.getCdsPage().getPageTitle()).as("Expected to be on Cds page. Actual on page %s", pageObjectManager.getBooksPage().getPageTitle()).isEqualTo("CDs");
		}

	}

	@Then("^I should see the books page$")
	public void i_should_see_the_books_page(){
		BooksPage booksPage = pageObjectManager.getBooksPage();

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(booksPage.isOpen()).as("Books page is not open!").isTrue();
		softly.assertThat(booksPage.getPageTitle()).as("Title expected: Books. Actual title: %s", booksPage.getPageTitle()).isEqualTo("Books");
		softly.assertThat(booksPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"Books\" Actual: Side menu title is \"%s\".").isEqualTo("Books");
		softly.assertThat(booksPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", booksPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(booksPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", booksPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(booksPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", booksPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(booksPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", booksPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(booksPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();
		softly.assertThat(booksPage.getSearchBarFieldsLabels()).as("Search bar is empty.").isNotEmpty()
																									.contains("Author", "Title", "Publisher", "ISBN");
		softly.assertThat(booksPage.getMainMenuLinksText()).as("Main menu is empty.").isNotEmpty();
		softly.assertAll();
	}

	@Then("^I should see all book filtering options$")
	public void i_should_see_all_book_filtering_options(){
		BooksPage booksPage = pageObjectManager.getBooksPage();

		assertThat(booksPage.getSearchBarFieldsLabels())
				.as("Some of the filtering options is missing.").isNotEmpty()
				.contains("Author")
				.contains("Title")
				.contains("Publisher")
				.contains("ISBN");
	}

	@Then("^All displayed books are written by this author \"([^\"]*)\"$")
	public void all_displayed_books_are_written_by_this_author(String author){
		List<String> listAllAuthors = pageObjectManager.getBooksPage().getAllProductsAuthors();
		String authorLowCase = author.toLowerCase();
		for(int i = 0; i<listAllAuthors.size(); i++){
			assertThat(listAllAuthors.get(i).toLowerCase()).as("Found products not matching search criteria (author).").contains(authorLowCase);
		}
	}

	@Then("^The book is displayed \"([^\"]*)\"$")
	public void the_book_is_displayed(String title){
		List<String> listAllTitles = pageObjectManager.getBooksPage().getAllProductTitles();
		String titleLowCase = title.toLowerCase();
		for(int i =0; i<listAllTitles.size(); i++){
			assertThat(listAllTitles.get(i).toLowerCase()).as("Found products not matching search criteria (title).").contains(titleLowCase);
		}
	}

	@Then("^All books from this publisher are displayed \"([^\"]*)\"$")
	public void all_books_from_this_publisher_are_displayed(String publisher){
		List<String> listAllPublishers = pageObjectManager.getBooksPage().getAllProductsPublishers();
		String publisherLowCase = publisher.toLowerCase();
		for(int i =0; i<listAllPublishers.size(); i++){
			assertThat(listAllPublishers.get(i).toLowerCase()).as("Found products not matching search criteria (publisher).").contains(publisherLowCase);
		}
	}

	@Then("^The book with that number is displayed \"([^\"]*)\"$")
	public void the_book_with_that_number_is_displayed(String ISBN){
		List<String> listAllIsbns = pageObjectManager.getBooksPage().getAllProductsISBNs();
		String isbnLowCase = ISBN.toLowerCase();
		for(int i =0; i<listAllIsbns.size(); i++){
			assertThat(listAllIsbns.get(i).toLowerCase()).as("Found products not matching search criteria (ISBN).").contains(isbnLowCase);
		}
	}

	@Then("^The book answering to the respective criteria is displayed \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_book_answering_to_the_respective_criteria_is_displayed_and_and_and(String author, String title,
			String publisher, String ISBN){
		SoftAssertions softly = new SoftAssertions();

		if(author != null && author != ""){
			List<String> listAllAuthors = pageObjectManager.getBooksPage().getAllProductsAuthors();
			String authorLowCase = author.toLowerCase();
			for(int i =0; i<listAllAuthors.size(); i++){
				softly.assertThat(listAllAuthors.get(i).toLowerCase()).as("Found products not matching search criteria (author).").contains(authorLowCase);
			}
		}

		if(title != null && title != ""){
			List<String> listAllTitles = pageObjectManager.getBooksPage().getAllProductTitles();
			String titleLowCase = title.toLowerCase();
			for(int i =0; i<listAllTitles.size(); i++){
				softly.assertThat(listAllTitles.get(i).toLowerCase()).as("Found products not matching search criteria (title).").contains(titleLowCase);
			}
		}

		if(publisher != null && publisher != ""){
			List<String> listAllPublishers = pageObjectManager.getBooksPage().getAllProductsPublishers();
			String publisherLowCase = publisher.toLowerCase();
			for(int i =0; i<listAllPublishers.size(); i++){
				softly.assertThat(listAllPublishers.get(i).toLowerCase()).as("Found products not matching search criteria (publisher).").contains(publisherLowCase);
			}
		}

		if(ISBN != null && ISBN != ""){
			List<String> listAllIsbns = pageObjectManager.getBooksPage().getAllProductsISBNs();
			String isbnLowCase = ISBN.toLowerCase();
			for(int i =0; i<listAllIsbns.size(); i++){
				softly.assertThat(listAllIsbns.get(i).toLowerCase()).as("Found products not matching search criteria (ISBN).").contains(isbnLowCase);
			}
		}

		softly.assertAll();
	}

	@Then("^An error message is displayed, stating that there are no such books in the system$")
	public void an_error_message_is_displayed_stating_that_there_are_no_such_books_in_the_system(){
		String errorMsg = pageObjectManager.getBooksPage().getErrorMsgText();
		assertThat(errorMsg).as("Expected error message for books, received: \"%s\" ", errorMsg).isEqualTo("There are no Books matching the search criteria...");
	}

	@Then("^I should see the home page$")
	public void i_should_see_the_home_page(){
		HomePage homePage = pageObjectManager.getHomePage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(homePage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", homePage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(homePage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", homePage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(homePage.getCDsButtonText()).as("Expected: CDs button text \"Cds\". Actual: Cds button text \"%s\" ", homePage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(homePage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", homePage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(homePage.getRegisterButtonText()).as("Expected: Register button text \"Register\". Actual: Register button text \"%s\" ", homePage.getRegisterButtonText()).isEqualTo("Register");
		softly.assertThat(homePage.getLoginButtonText()).as("Expected: Login button text \"Login\". Actual: Login button text \"%s\" ", homePage.getLoginButtonText()).isEqualTo("Login");
		softly.assertThat(homePage.getBasketIconText()).as("Missing basket icon.", homePage.getBooksButtonText()).isNotNull();

		softly.assertAll();
	}

	@Then("^I should see Books and CDs categories$")
	public void i_should_see_Books_and_CDs_categories(){
		HomePage homePage = pageObjectManager.getHomePage();
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(homePage.getBooksButtonText()).as("Can't find books button.").isNotNull();
		softly.assertThat(homePage.getCDsButtonText()).as("Can't find cds button.").isNotNull();
		softly.assertThat(homePage.getBooksButtonText()).as("Expected: Books button text  \"Books\". Actual: button text: \"%s\" ", homePage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(homePage.getCDsButtonText()).as("Expected: CDs button text  \"CDs\". Actual: button text: \"%s\" ", homePage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertAll();
	}

	@Then("^I should see the cds page$")
	public void i_should_see_the_cds_page() {
		CdsPage cdsPage = pageObjectManager.getCdsPage();

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(cdsPage.getPageTitle()).as("Title expected: Cds. Actual title: %s", cdsPage.getPageTitle()).isEqualTo("CDs");
		softly.assertThat(cdsPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"Cds\" Actual: Side menu title is \"%s\".").isEqualTo("CDs");
		softly.assertThat(cdsPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", cdsPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(cdsPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", cdsPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(cdsPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", cdsPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(cdsPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", cdsPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(cdsPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();
		softly.assertThat(cdsPage.getSearchBarFieldsLabels()).as("Search bar is empty.").isNotEmpty();
		softly.assertThat(cdsPage.getMainMenuLinksText()).as("Main menu is empty.").isNotEmpty();
		softly.assertAll();

	}

	@Then("^I should see all cds filtering options$")
	public void i_should_see_all_cds_filtering_options(){
		CdsPage cdsPage = pageObjectManager.getCdsPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(cdsPage.isArtistTextboxDisplayed()).as("Artist textbox is not displayed.").isTrue();
		softly.assertThat(cdsPage.isComposerTextboxDisplayed()).as("Composer textbox is not displayed.").isTrue();
		softly.assertThat(cdsPage.isLabelTextboxDisplayed()).as("Label textbox is not displayed.").isTrue();
		softly.assertThat(cdsPage.isTitleTextboxDisplayed()).as("Title textbox is not displayed.").isTrue();
		softly.assertThat(cdsPage.getArtistLabelText()).as("Wrong label for Artist textbox.").isEqualTo("Artist");
		softly.assertThat(cdsPage.getComposerLabelText()).as("Wrong label for Composer textbox.").isEqualTo("Composer");
		softly.assertThat(cdsPage.getLabelLabelText()).as("Wrong label for Label textbox.").isEqualTo("Label");
		softly.assertThat(cdsPage.getTitleLabelText()).as("Wrong label for Title textbox.").isEqualTo("Title");
	}

	@Then("^I should see the book category page$")
	public void i_should_see_the_book_category_page(){
		BooksPage booksPage = pageObjectManager.getBooksPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(booksPage.getEmptyErrorMsg()).as("Error msg is displayed").isNull();
		softly.assertThat(booksPage.getPageTitle()).as("You are not redirected to a book category page.", booksPage.getPageTitle()).matches("Books (.*)");
		softly.assertThat(booksPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"Books\" Actual: Side menu title is \"%s\".").isEqualTo("Books");
		softly.assertThat(booksPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", booksPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(booksPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", booksPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(booksPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", booksPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(booksPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", booksPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(booksPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();
		softly.assertThat(booksPage.getMainMenuLinksText()).as("Main menu is empty.").isNotEmpty();

		softly.assertAll();

	}

	@Then("^I should see all books' elements in the category page$")
	public void i_should_see_all_books_elements_in_the_category_page(){
		BooksCategoryPage booksCategoryPage = pageObjectManager.getBooksCategoryPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(booksCategoryPage.getAllProductsAuthors()).as("Some product authors are not displayed").isNotEmpty();
		softly.assertThat(booksCategoryPage.getAllProductTitles()).as("Some product titles are not displayed").isNotEmpty();
		softly.assertThat(booksCategoryPage.getAllProductsPrices()).as("Some product prices are not displayed").isNotEmpty();
		softly.assertThat(booksCategoryPage.areTheProductsAddToBasketButtonsDisplayed()).as("Some add to basket buttons are not displayed.").isTrue();
		softly.assertThat(booksCategoryPage.areTheProductsImagesDisplayed()).as("Some product images are not displayed.").isTrue();

		softly.assertAll();

	}

	@Then("^I should see the cd category page$")
	public void i_should_see_the_cd_category_page(){
		CdsPage cdsPage = pageObjectManager.getCdsPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(cdsPage.getEmptyErrorMsg()).as("Error msg is displayed").isNull();
		softly.assertThat(cdsPage.getPageTitle()).as("You are not redirected to a Cds category page.", cdsPage.getPageTitle()).matches("CDs (.*)");
		softly.assertThat(cdsPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"CDs\" Actual: Side menu title is \"%s\".", cdsPage.getSideMenuTitle()).isEqualTo("CDs");
		softly.assertThat(cdsPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", cdsPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(cdsPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", cdsPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(cdsPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", cdsPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(cdsPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", cdsPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(cdsPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();
		softly.assertThat(cdsPage.getMainMenuLinksText()).as("Main menu is empty.").isNotEmpty();

		softly.assertAll();

	}

	@Then("^I should see all cds' elements in the category page$")
	public void i_should_see_all_cds_elements_in_the_category_page(){
		CdsCategoryPage cdsCategoryPage = pageObjectManager.getCdsCategoryPage();

		assertThat(cdsCategoryPage.getAllProductsAuthors()).as("Some product authors are not displayed").isNotEmpty();
		assertThat(cdsCategoryPage.getAllProductTitles()).as("Some product titles are not displayed").isNotEmpty();
		assertThat(cdsCategoryPage.getAllProductsPrices()).as("Some product prices are not displayed").isNotEmpty();
		assertThat(cdsCategoryPage.areTheProductsAddToBasketButtonsDisplayed()).as("Some add to basket buttons are not displayed.").isTrue();
		assertThat(cdsCategoryPage.areTheProductsImagesDisplayed()).as("Some product images are not displayed.").isTrue();

	}

	@Then("^I should see the empty book category page$")
	public void i_should_see_the_empty_book_category_page(){
		BooksPage booksPage = pageObjectManager.getBooksPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(booksPage.getEmptyErrorMsg()).as("Error msg is not displayed").isNotEmpty();
		softly.assertThat(booksPage.getEmptyErrorMsg()).as("Error msg is not displayed").isEqualTo("There are no products in the category");
		softly.assertThat(booksPage.getPageTitle()).as("You are not redirected to a book category page.", booksPage.getPageTitle()).matches("Books (.*)");
		softly.assertThat(booksPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"Books\" Actual: Side menu title is \"%s\".").isEqualTo("Books");
		softly.assertThat(booksPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", booksPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(booksPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", booksPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(booksPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", booksPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(booksPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", booksPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(booksPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();

		softly.assertAll();
	}

	@Then("^I should see the empty CDs category page$")
	public void i_should_see_the_empty_CDs_category_page(){
		CdsPage cdsPage = pageObjectManager.getCdsPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(cdsPage.getEmptyErrorMsg()).as("Error msg is not displayed").isNotNull();
		softly.assertThat(cdsPage.getEmptyErrorMsg()).as("Error msg is not displayed").isEqualTo("There are no products in the category");
		softly.assertThat(cdsPage.getPageTitle()).as("You are not redirected to a book category page.", cdsPage.getPageTitle()).matches("CDs (.*)");
		softly.assertThat(cdsPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"Books\" Actual: Side menu title is \"%s\".").isEqualTo("CDs");
		softly.assertThat(cdsPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", cdsPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(cdsPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", cdsPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(cdsPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", cdsPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(cdsPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", cdsPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(cdsPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();

		softly.assertAll();

	}

	@Then("^I can see the product details page for \"([^\"]*)\" book/cd$")
	public void i_can_see_the_product_details_page_for_book_cd(String product){
		ProductDetailsPage productPage = pageObjectManager.getProductDetailsPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(productPage.getEmptyErrorMsg()).as("Error msg is displayed").isNull();
		//softly.assertThat(productPage.getPageTitle()).as("You are not redirected to a book category page.", productPage.getPageTitle()).matches("(Books (.*))||(CDs (.*))");
		softly.assertThat(productPage.getSideMenuTitle()).as("Expected: Side Menu title should be \"Books\" Actual: Side menu title is \"%s\".", productPage.getSideMenuTitle()).isNotNull();
		softly.assertThat(productPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", productPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(productPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", productPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(productPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", productPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(productPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", productPage.getViewBasketButtonText()).isEqualTo("Basket");
		softly.assertThat(productPage.getSideMenuButtonsText()).as("Side menu is empty.").isNotEmpty();

		softly.assertAll();
	}

	@Then("^I can see the book details$")
	public void i_can_see_the_book_details(){
		ProductDetailsPage productPage = pageObjectManager.getProductDetailsPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(productPage.getProductTitle()).as("Missing product name").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductISBN()).as("Missing product ISBN").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductAuthor()).as("Missing product author").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductPublisher()).as("Missing product publisher").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductPrice()).as("Missing product price").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.isProductImageDisplayed()).as("Missing product picture.").isTrue();
		softly.assertThat(productPage.isAddToBasketButtonDisplayed()).as("Missing \"add to basket\" button").isTrue();

		softly.assertAll();
	}

	@Then("^I can see the CD details$")
	public void i_can_see_the_CD_details(){
		ProductDetailsPage productPage = pageObjectManager.getProductDetailsPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(productPage.getProductTitle()).as("Missing product name").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductArtist()).as("Missing product Artist").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductLabel()).as("Missing product label").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.getProductPrice()).as("Missing product price").isNotNull()
				.isNotEqualTo("");
		softly.assertThat(productPage.isProductImageDisplayed()).as("Missing product picture.").isTrue();
		softly.assertThat(productPage.isAddToBasketButtonDisplayed()).as("Missing \"add to basket\" button").isTrue();

		softly.assertAll();
	}

	@Then("^I can see the shopping basket page$")
	public void i_can_see_the_shopping_basket_page(){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(basketPage.getPageTitle()).as("You are on wrong page.").isEqualTo("Shopping Basket");
		softly.assertThat(basketPage.getHomeButtonText()).as("Expected: Home button text \"Home\". Actual: Home button text \"%s\" ", basketPage.getHomeButtonText()).isEqualTo("Home");
		softly.assertThat(basketPage.getBooksButtonText()).as("Expected: Books button text \"Books\". Actual: Books button text \"%s\" ", basketPage.getBooksButtonText()).isEqualTo("Books");
		softly.assertThat(basketPage.getCDsButtonText()).as("Expected: Cds button text \"Cds\". Actual: Cds button text \"%s\" ", basketPage.getCDsButtonText()).isEqualTo("CDs");
		softly.assertThat(basketPage.getViewBasketButtonText()).as("Expected: Basket button text \"Basket\". Actual: Basket button text \"%s\" ", basketPage.getViewBasketButtonText()).isEqualTo("Basket");

		softly.assertAll();
	}

	@Then("^I can see the following text displayed : \"([^\"]*)\"$")
	public void i_can_see_the_following_text_displayed(String arg1){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(basketPage.getPageTitle()).as("You are on wrong page.").isEqualTo("Shopping Basket");
		softly.assertThat(basketPage.getEmptyBasketErrorMsg()).as("Error message not displayed.").isNotNull();
		softly.assertThat(basketPage.getEmptyBasketErrorMsg()).as("Wrong error message.").isEqualTo(arg1);
		softly.assertAll();

	}

	@Then("^I should see the shopping basket page$")
	public void i_should_see_the_shopping_basket_page(){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(basketPage.getPageTitle()).as("You are on wrong page.").isEqualTo("Shopping Basket");
		softly.assertThat(basketPage.getEmptyBasketErrorMsg()).as("Error message is displayed.").isNull();

		softly.assertAll();
	}

	@Then("^I should see all (\\d+) added products$")
	public void i_should_see_all_added_products(int number){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		assertThat(basketPage.getProductsCount()).as("Not all products are added to basket").isEqualTo(number);
	}

	@Then("^I should see all shopping basket information and buttons$")
	public void i_should_see_all_shopping_basket_information_and_buttons(){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(basketPage.getPageTitle()).as("You are on wrong page.").isEqualTo("Shopping Basket");
		softly.assertThat(basketPage.getEmptyBasketErrorMsg()).as("Error message is displayed.").isNull();
		softly.assertThat(basketPage.getProductsCount()).as("The basket is empty").isGreaterThan(0);
		softly.assertThat(basketPage.getProductPrice(0)).as("There is no price for the product.").isGreaterThan(0);
		softly.assertThat(basketPage.getProductSubtotal(0)).as("There is no subtotal for the product").isGreaterThan(0);
		softly.assertThat(basketPage.isAddOneProductButtonDisplayed()).as("+ button is missing").isTrue();
		softly.assertThat(basketPage.isRemoveOneProductButtonDisplayed()).as("- button is missing").isTrue();
		softly.assertThat(basketPage.isRemoveProductButtonDisplayed()).as("Remove product button is missing").isTrue();
		softly.assertThat(basketPage.isCheckoutButtonDisplayed()).as("Checkout button is missing").isTrue();

		softly.assertAll();
	}

	@Then("^Confirmation for purchase message is displayed$")
	public void confirmation_for_purchase_message_is_displayed(){
		CheckoutPage checkoutPage = pageObjectManager.getCheckoutPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(checkoutPage.getPageTitle())
				.as("You are on the wrong page.")
				.isNotNull()
				.isEqualTo("Confirm purchase");
		softly.assertThat(checkoutPage.isGoToHomeButtonDisplayed())
				.as("Go To home button is not displayed.")
				.isTrue();
		softly.assertThat(checkoutPage.getMainText())
				.as("Main paragraph has wrong text.")
				.isNotNull()
				.contains("Your order is accepted!");

		softly.assertAll();
	}

	@Then("^Message for canceld purchase is displayed$")
	public void message_for_canceld_purchase_is_displayed(){
		CheckoutPage checkoutPage = pageObjectManager.getCheckoutPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(checkoutPage.getPageTitle())
				.as("You are on the wrong page.")
				.isNotNull()
				.isEqualTo("Cancel purchase");
		softly.assertThat(checkoutPage.isGoToHomeButtonDisplayed())
				.as("Go To home button is not displayed.")
				.isTrue();
		softly.assertThat(checkoutPage.getMainText())
				.as("Main paragraph has wrong text.")
				.isNotNull()
				.contains("Purchase cancelled");

		softly.assertAll();
	}


	@Then("^The shipping cost is displayed and it is added to the total sum$")
	public void the_shipping_cost_is_displayed_and_it_is_added_to_the_total_sum(){
		CheckoutPage checkoutPage = pageObjectManager.getCheckoutPage();

		assertThat(checkoutPage.getTotalShipping())
				.as("Shipping field is not displayed.")
				.isNotEqualTo("-1");

		double tax = checkoutPage.getTotalTax();
		double shippingCost = checkoutPage.getTotalShipping();
		double totalSum = checkoutPage.getTotalSumField();
		double allProductsSum = checkoutPage.getAllBasketProductsCostSum();

		assertThat(totalSum).as("Total sum is not correct. Shipping cost = " + shippingCost +
				", all products sum without shipping = " + allProductsSum +
				", tax = "+tax+
				", total sum =" + totalSum + " .")
				.isEqualTo(tax + shippingCost + allProductsSum);
	}

	@Then("^The tax field is displayed and it is added to the total sum$")
	public void the_tax_field_is_displayed_and_it_is_added_to_the_total_sum(){
		CheckoutPage checkoutPage = pageObjectManager.getCheckoutPage();

		assertThat(checkoutPage.getTotalTax())
				.as("Tax field is not displayed.")
				.isNotEqualTo("-1");

		double tax = checkoutPage.getTotalTax();
		double shippingCost = checkoutPage.getTotalShipping();
		double totalSum = checkoutPage.getTotalSumField();
		double allProductsSum = checkoutPage.getAllBasketProductsCostSum();

		assertThat(totalSum).as("Total sum is not correct. Shipping cost = " + shippingCost +
				", all products sum without shipping = " + allProductsSum +
				"tax = "+tax+
				", total sum =" + totalSum + " .")
				.isEqualTo(tax + shippingCost + allProductsSum);
	}

	@Then("^Product is removed and following text is displayed \"([^\"]*)\"$")
	public void error_msg_is_displayed(String errorMsg){
		assertThat(pageObjectManager.getBasketPage().getEmptyBasketErrorMsg())
				.as("Wrong text or missing error msg.")
				.isNotNull()
				.isEqualTo(errorMsg);
	}

	@Then("^The number of copies for product number (-?\\d+) is increased by (-?\\d+)$")
	public void the_number_of_copies_for_product_is_increased_by(int productNumberRow, int count){
		int productCount = pageObjectManager.getBasketPage().getProductCount(productNumberRow);

		assertThat(productCount)
				.as("Expected result: product row %d should have quantity of %d. Actual: %d", productNumberRow, productQuantity + count, productCount)
				.isEqualTo(productQuantity + count);

	}

	@Then("^The basket icon in header is displaying the same number of products as in the shopping basket$")
	public void basket_icon_number_is_equal_to_actual_items(){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		assertThat(basketPage.getProductsCount())
				.as("Actual items in basket are not the same as the displayed ones on basket icon.")
				.isEqualTo(basketPage.getBasketCounter());
	}

	@Then("^(-?\\d+) should be displayed in shopping basket icon$")
	public void right_number_should_be_displayed_in_shopping_basket_icon(int itemsCount){
		assertThat(pageObjectManager.getBasketPage().getBasketCounter())
				.as("Actual items are not the same as expected ones.")
				.isEqualTo(itemsCount);
	}

	@Then("^(-?\\d+) product is removed$")
	public void product_is_removed(int productNumber){
		BasketPage basketPage = pageObjectManager.getBasketPage();
		assertThat(basketPage.getProductsCount())
				.as("Product is not removed")
				.isEqualTo(productQuantity-1);
	}

	@Then("^I am redirected on Checkout page$")
	public void i_am_redirected_to_checkout_page(){
		CheckoutPage checkoutPage = pageObjectManager.getCheckoutPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(checkoutPage.isOpen())
				.as("You are not on CheckoutPage")
				.isTrue();
		softly.assertThat(checkoutPage.isProductImageDisplayed(1)) //At least one product is displayed
				.as("Checkout login text is not visible or it has wrong text.")
				.isNotNull();
		softly.assertThat(checkoutPage.isCancelButtonDisplayed())
				.as("Cancel purchase button is not displayed.")
				.isTrue();
		softly.assertThat(checkoutPage.isCancelButtonDisplayed())
				.as("Confirm purchase button is not displayed.")
				.isTrue();

		softly.assertAll();
	}

	@Then("^I am redirectied to checkout login menu$")
	public void i_am_redirected_to_checkout_login_menu(){
		CheckoutPage checkoutPage = pageObjectManager.getCheckoutPage();
		SoftAssertions softly = new SoftAssertions();

		softly.assertThat(checkoutPage.isOpen())
				.as("You are not on CheckoutPage")
				.isTrue();
		softly.assertThat(checkoutPage.isLoginButtonDisplayed())
				.as("Login button is not displayed.")
				.isTrue();
		softly.assertThat(checkoutPage.isUsernameFieldDisplayed())
				.as("Username field is not displayed.")
				.isTrue();
		softly.assertThat(checkoutPage.isPasswordFieldDisplayed())
				.as("Password field is not displayed.")
				.isTrue();

		softly.assertAll();
	}

	/*
		Before and after statements
	 */
	@Before
	public void init() {
		PageObjectManager.init();
		pageObjectManager = PageObjectManager.getManager();
	}

	@After
	public void cleanUp() {
		productQuantity = 0;
		pageObjectManager.quit();
	}

}