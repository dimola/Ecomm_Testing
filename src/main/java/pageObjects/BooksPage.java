package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BooksPage extends CategoryPage {

	private static final String PAGE_URL = "/index.php?page=books";

	@FindBy(name = "Author")
	private WebElement authorTextbox;

	@FindBy(name = "Title")
	private WebElement titleTextbox;

	@FindBy(name = "Publisher")
	private WebElement publisherTextbox;

	@FindBy(name = "ISBN")
	private WebElement isbnTextbox;

	@FindBy(css = "#product-details > div > h3")
	private WebElement bookHeadingTitle;

	@FindBy(xpath = "//tbody/tr[1]/td[1]")
	private WebElement authorLabel;

	@FindBy(xpath = "//tbody/tr[2]/td[1]")
	private WebElement titleLable;

	@FindBy(xpath = "//tbody/tr[3]/td[1]")
	private WebElement publisherLabel;

	@FindBy(xpath = "//tbody/tr[4]/td[1]")
	private WebElement isbnLable;

	@FindBy(className = "formbtn")
	private WebElement submitButton;

	public BooksPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public BooksPage open(){
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen(){
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Books")
					&& this.productListTitle.isDisplayed()
					&& this.productListTitle.getText().equals("Browse Books by category:");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	public BooksPage enterAuthor(String author) {
		authorTextbox.sendKeys(author);
		return this;
	}

	public BooksPage enterTitle(String title) {
		titleTextbox.sendKeys(title);
		return this;
	}

	public BooksPage enterPublisher(String publisher) {
		publisherTextbox.sendKeys(publisher);
		return this;
	}

	public BooksPage enterIsbn(String ISBN) {
		isbnTextbox.sendKeys(ISBN);
		return this;
	}

	public void Submit(String author, String title, String publisher, String isbn){
		authorTextbox.sendKeys(author);
		titleTextbox.sendKeys(title);
		publisherTextbox.sendKeys(publisher);
		isbnTextbox.sendKeys(isbn);
		submitButton.click();
	}

	public String getSubmitButtonText(){
		try {
			if (this.submitButton.isDisplayed()){
				return this.submitButton.getText();
			}
			else{
				return "Can't find submit button";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find submit button";
		}
	}

	public String getAuthorLabelText(){
		try {
			if (this.authorLabel.isDisplayed()){
				return this.authorLabel.getText();
			}
			else{
				return "Can't find Author text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find Author text box";
		}
	}

	public String getTitleLabelText(){
		try {
			if (this.titleLable.isDisplayed()){
				return this.titleLable.getText();
			}
			else{
				return "Can't find title text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find title text box";
		}
	}

	public String getPublisherLabelText(){
		try {
			if (this.publisherLabel.isDisplayed()){
				return this.publisherLabel.getText();
			}
			else{
				return "Can't find publisher text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find publisher text box";
		}
	}

	public String getIsbnLabelText(){
		try {
			if (this.isbnLable.isDisplayed()){
				return this.isbnLable.getText();
			}
			else{
				return "Can't find ISBN text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find ISBN text box";
		}
	}

	public List<String> getSearchBarFieldsLabels(){
		List<String> searchBarFieldsLabels = new ArrayList<>();
		searchBarFieldsLabels.add(this.getAuthorLabelText());
		searchBarFieldsLabels.add(this.getTitleLabelText());
		searchBarFieldsLabels.add(this.getPublisherLabelText());
		searchBarFieldsLabels.add(this.getIsbnLabelText());

		return searchBarFieldsLabels;
	}

	//To be deleted or refactored

	public List<String> getAllProductsPublishers(){
		List<String> allPublishers = new ArrayList<>();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				ProductDetailsPage product = new ProductDetailsPage(driver);
				allPublishers.add(product.getProductPublisher());
				driver.navigate().back();
			}

			return allPublishers;
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are by this publisher: " + e.getMessage());
			return null;
		}
	}

	public boolean areAllBooksPublishedBySearchedPublisher(String publisher) {
		boolean result = false;
		String publisherLowCase = publisher.toLowerCase();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				if (this.productInfo.getText().toLowerCase().contains(publisherLowCase)) {
					result = true;
				} else {
					result = false;
					break;
				}
				booksMenuElement.click();
				enterPublisher(publisher);
				clickSubmit();
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are by this publisher: " + e.getMessage());
		}
		return result;
	}

	public List<String> getAllProductsISBNs(){
		List<String> allISBNs = new ArrayList<>();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				ProductDetailsPage product = new ProductDetailsPage(driver);
				allISBNs.add(product.getProductISBN());
				driver.navigate().back();
			}

			return allISBNs;
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are by this publisher: " + e.getMessage());
			return null;
		}
	}

	public boolean doesAllProductsContainSearchedISBN(String ISBN) {
		boolean result = false;
		try {
			for (int i = 0; i < allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				if (productInfo.getText().contains(ISBN)) {
					result = true;
				} else {
					result = false;
					break;
				}
				booksMenuElement.click();
				enterIsbn(ISBN);
				clickSubmit();
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are with this number: " + e.getMessage());
		}
		return result;
	}

	public boolean isTheResultAnsweringRespectiveSearchCriteria(String author, String title, String publisher,
			String ISBN) {
		boolean result = false;
		try {
			for (int i = 0; i < allProducts.size(); i++) {
				openProductDetailsPageFromProductList(i);
				if (productInfo.getText().contains(author) && productInfo.getText().contains(publisher)
						&& productInfo.getText().contains(ISBN) && this.bookHeadingTitle.getText().contains(title)) {
					result = true;
				} else {
					result = false;
					break;
				}
				booksMenuElement.click();
				enterAuthor(author);
				enterTitle(title);
				enterPublisher(publisher);
				enterIsbn(ISBN);
				clickSubmit();
			}
		} catch (Throwable e) {
			System.out.println(
					"Problem while checking if displayed book matches the respective criteria: " + e.getMessage());
		}
		return result;
	}

}
