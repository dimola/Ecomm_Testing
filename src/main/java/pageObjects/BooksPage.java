package pageObjects;

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

	@FindBy(css = "#product-details > div > h3")
	private WebElement bookHeadingTitle;

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
