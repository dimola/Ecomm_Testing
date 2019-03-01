package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	@FindBy(css = "#main-big-col input.formbtn")
	private WebElement buttonSubmit;

	@FindBy(css = "#product-list div.item")
	private List<WebElement> allBooks;

	@FindBy(css = "#product-list b")
	private List<WebElement> allBooksTitles;

	@FindBy(css = "#product-list span")
	private List<WebElement> allBooksPrices;

	@FindBy(css = "#product-list a.buy-btn")
	private List<WebElement> allBooksAddToBasketButtons;

	@FindBy(css = "#product-details div.author")
	private WebElement bookInfo;

	@FindBy(css = "#product-list > div > a:nth-child(2)")
	private WebElement bookTitle;

	@FindBy(css = "#product-details > div > h3")
	private WebElement bookHeadingTitle;

	@FindBy(css = "#main-big-col > b.err")
	private WebElement errorMessage;

	public BooksPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public BooksPage open() {
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
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

	public boolean isAuthorTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.authorTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if authorTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isTitleTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.titleTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if titleTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isPublisherTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.publisherTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if publisherTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isIsbnTextboxDisplayed() {
		boolean result = false;
		try {
			result = this.isbnTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if isbnTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public BooksPage enterAuthor(String author) {
		authorTextbox.sendKeys(author);
		return this;
	}

	public void clickSubmit() {
		buttonSubmit.click();
	}

	public String getAllBooksAuthors(int bookNumber) {
		String removeTitleText = this.allBooksTitles.get(bookNumber).getText();
		String removePricesText = this.allBooksPrices.get(bookNumber).getText();
		String removeButtonAddToBasketText = this.allBooksAddToBasketButtons.get(bookNumber).getText();
		return (this.allBooks.get(bookNumber).getText().replace(removeTitleText, "").replace(removePricesText, "")
				.replace(removeButtonAddToBasketText, "").replaceAll("\\r\\n|\\r|\\n", ""));
	}

	public boolean areAllBooksWrittenBySearchedAuthor(String author) {
		boolean result = false;
		String authorLowCase = author.toLowerCase();
		try {
			for (int i = 0; i < this.allBooks.size(); i++) {
				if (getAllBooksAuthors(i).toLowerCase().contains(authorLowCase)) {
					result = true;
				} else {
					result = false;
					break;
				}
			}
		} catch (Throwable e) {
			System.out.println(
					"Problem while checking if displayed books are written by the same author: " + e.getMessage());
		}
		return result;
	}

	public BooksPage enterTitle(String title) {
		titleTextbox.sendKeys(title);
		return this;
	}

	public String getAllBooksTitles(int bookNumber) {
		return (this.allBooksTitles.get(bookNumber).getText());
	}

	public boolean isSearchedTitleDisplayed(String title) {
		boolean result = false;
		String titleLowCase = title.toLowerCase();
		try {
			for (int i = 0; i < this.allBooks.size(); i++) {
				if (this.getAllBooksTitles(i).toLowerCase().contains(titleLowCase)) {
					result = true;
				} else {
					result = false;
					break;
				}
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book is with the same title: " + e.getMessage());
		}
		return result;
	}

	public BooksPage enterPublisher(String publisher) {
		publisherTextbox.sendKeys(publisher);
		return this;
	}

	public void clickAllBooksTitles(int bookNumber) {
		this.allBooksTitles.get(bookNumber).click();
	}

	public boolean arePublisherBooksDisplayed(String publisher) {
		boolean result = false;
		String publisherLowCase = publisher.toLowerCase();
		try {
			for (int i = 0; i < this.allBooks.size(); i++) {
				this.clickAllBooksTitles(i);
				if (this.bookInfo.getText().toLowerCase().contains(publisherLowCase)) {
					result = true;
				} else {
					result = false;
					break;
				}
				booksMenuElement.click();
				;
				enterPublisher(publisher);
				clickSubmit();
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are by this publisher: " + e.getMessage());
		}
		return result;
	}

	public BooksPage enterIsbn(String ISBN) {
		isbnTextbox.sendKeys(ISBN);
		return this;
	}

	public boolean areBooksWithThisNumberDisplayed(String ISBN) {
		boolean result = false;
		try {
			for (int i = 0; i < this.allBooks.size(); i++) {
				this.clickAllBooksTitles(i);
				if (this.bookInfo.getText().contains(ISBN)) {
					result = true;
				} else {
					result = false;
					break;
				}
				booksMenuElement.click();
				;
				enterIsbn(ISBN);
				clickSubmit();
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are with this number: " + e.getMessage());
		}
		return result;
	}

	public boolean isBookAnsweringRespectiveCriteria(String author, String title, String publisher, String ISBN) {
		boolean result = false;
		try {
			this.bookTitle.click();
			if (this.bookInfo.getText().contains(author) && this.bookInfo.getText().contains(publisher)
					&& this.bookInfo.getText().contains(ISBN) && this.bookHeadingTitle.getText().equals(title)) {
				result = true;
			} else {
				result = false;
			}
		} catch (Throwable e) {
			System.out.println(
					"Problem while checking if displayed book matches the respective criteria: " + e.getMessage());
		}
		return result;
	}

	public boolean isErrorMessageDisplayed() {
		boolean result = false;
		try {
			if (this.errorMessage.getText().equals("There are no Books matching the search criteria...")) {
				result = true;
			} else {
				result = false;
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are by this publisher: " + e.getMessage());
		}
		return result;
	}
}
