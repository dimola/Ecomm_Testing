package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends CategoryPage {
	private static final String AUTUMN_COLORS_BOOK_DETAIS_URL = "/index.php?page=books&category=books-art&product=6";

	@FindBy(css = "#product-details .item > h3")
	private WebElement productTitle;

	@FindBy(css = "#product-details .author")
	private WebElement productInfo;

	@FindBy(css = "#product-details .author b")
	private List<WebElement> productInfoLabels;

	@FindBy(css = "#product-details .price")
	private WebElement productPrice;

	@FindBy(css = "#product-details .buy-btn-big")
	private WebElement addToBasketButton;

	@FindBy(css = "#product-details li")
	private WebElement backToProductListLink;

	@FindBy(css = "#product-details img")
	private WebElement productImage;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public ProductDetailsPage open() {
		this.driver.get(configFileReader.getHost() + AUTUMN_COLORS_BOOK_DETAIS_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.productTitle.isDisplayed() && this.productTitle.getText().equals("Autumn Colors");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books/CDs Category Page Heading (Book/CD title) is displayed: " + e.getMessage());
		}
		return result;
	}

	public ProductDetailsPage openProductDetailsPage(String detailsPageURL) {
		this.driver.get(configFileReader.getHost() + detailsPageURL);
		return this;
	}

	public boolean isProductDetailsPageOpen(String productName) {
		boolean result = false;
		try {
			result = this.productTitle.isDisplayed() && this.productTitle.getText().equals(productName);
		} catch (Throwable e) {
			System.err.println("Problem while checking if Books/CDs Category Page Heading(Book/CD title) is displayed: " + e.getMessage());
		}
		return result;
	}

	public String getProductAuthor() {
		String[] helpArray1 = this.productInfo.getText().split("Author: ");
		String[] helpArray2 = helpArray1[1].split("Publisher: ");

		return helpArray2[0].replaceAll("\\r\\n|\\r|\\n", "");
	}

	public String getProductPublisher() {
		String[] helpArray1 = this.productInfo.getText().split("Author: ");
		String[] helpArray2 = helpArray1[1].split("Publisher: ");
		String[] helpArray3 = helpArray2[1].split("ISBN: ");

		return helpArray3[0].replaceAll("\\r\\n|\\r|\\n", "");
	}

	public String getProductISBN() {
		String[] helpArray1 = this.productInfo.getText().split("Author: ");
		String[] helpArray2 = helpArray1[1].split("Publisher: ");
		String[] helpArray3 = helpArray2[1].split("ISBN: ");

		return helpArray3[1].replaceAll("\\r\\n|\\r|\\n", "");
	}
	
	public String getProductPrice() {
		String[] helpArray1 = this.productPrice.getText().split(" ");
		
		return helpArray1[0].replaceAll("\\r\\n|\\r|\\n", "");
	}

	public String getProductArtist() {
		String[] helpArray1 = this.productInfo.getText().split("Artist: ");
		String[] helpArray2 = helpArray1[1].split("Label: ");

		return helpArray2[0].replaceAll("\\r\\n|\\r|\\n", "");
	}
	
	public String getProductLabel() {
		String[] helpArray1 = this.productInfo.getText().split("Artist: ");
		String[] helpArray2 = helpArray1[1].split("Label: ");
		
		return helpArray2[1].replaceAll("\\r\\n|\\r|\\n", "");
	}
	
	public boolean isProductAuthorDisplayed() {
		boolean result = false;
		try {
			result = this.productInfo.isDisplayed() && !this.productInfoLabels.isEmpty()
					&& this.getProductAuthor() != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product info and author are displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isProductPublisherDisplayed() {
		boolean result = false;
		try {
			result = this.productInfo.isDisplayed() && !this.productInfoLabels.isEmpty()
					&& this.getProductPublisher() != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product info and publisher are displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isProductISBNDisplayed() {
		boolean result = false;
		try {
			result = this.productInfo.isDisplayed() && !this.productInfoLabels.isEmpty()
					&& this.getProductISBN() != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product Info and ISBN are displayed: " + e.getMessage());
		}
		return result;
	}
	
	public boolean isProductArtistDisplayed() {
		boolean result = false;
		try {
			result = this.productInfo.isDisplayed() && !this.productInfoLabels.isEmpty()
					&& this.getProductArtist() != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product Info and CD Artist are displayed: " + e.getMessage());
		}
		return result;
	}
	
	public boolean isProductLabelDisplayed() {
		boolean result = false;
		try {
			result = this.productInfo.isDisplayed() && !this.productInfoLabels.isEmpty()
					&& this.getProductLabel() != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product Info and CD Label are displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isProductPriceDisplayed() {
		boolean result = false;
		try {
			result = this.productPrice.isDisplayed() && this.getProductPrice() != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product Price is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isAddToBasketButtonDisplayed() {
		boolean result = false;
		try {
			result = this.addToBasketButton.isDisplayed() && this.addToBasketButton.getText().equals("add to basket");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Add to Basket button is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isBackToProductListLinkDisplayed() {
		boolean result = false;
		try {
			result = this.backToProductListLink.isDisplayed()
					&& this.backToProductListLink.getText().equals("Back to Product List");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Back to Product List link is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isProductImageDisplayed() {
		boolean result = false;
		try {
			result = this.productImage.isDisplayed() && this.productImage.getAttribute("src") != null;
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product Image is displayed: " + e.getMessage());
		}
		return result;
	}	
}
