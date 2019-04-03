package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class CategoryPage extends GeneralPage {

	@FindBy(css = "#product-list h4")
	protected WebElement productListTitle;

	@FindBy(id = "side-menu")
	protected WebElement sideMenu;

	@FindBy(id = "product-list")
	protected WebElement productList;

	@FindBy(name = "search")
	protected WebElement searchForm;

	@FindBy(css = "#main-big-col input.formbtn")
	private WebElement buttonSubmit;
	
	@FindBy(css = "#product-list div.item")
	protected List<WebElement> allProducts;
	
	@FindBy(css = "#product-list b")
	private List<WebElement> allProductsTitles;
	
	@FindBy(css = "#product-list span")
	private List<WebElement> allProductsPrices;
	
	@FindBy(css = "#product-list a.buy-btn")
	private List<WebElement> allProductsAddToBasketButtons;
	
	@FindBy(css = "#product-details div.author")
	protected WebElement productInfo;
	
	@FindBy(css = "#main-big-col > b.err")
	private WebElement errorMessage;


	public CategoryPage(WebDriver driver) {
		super(driver);
	}

	//Getters
	public WebElement getProductListTitle(){
		return this.productListTitle;
	}

	public WebElement getSideMenu(){
		return this.sideMenu;
	}

	public WebElement getProductList(){
		return this.productList;
	}

	public WebElement getSearchForm(){
		return this.searchForm;
	}

	//To be deleted?

	public boolean isSideMenuDisplayed() {
		boolean result = false;
		try {
			result = this.sideMenu.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The side menu is missing" + e.getMessage());
		}
		return result;
	}

	public boolean isProductListDisplayed() {
		boolean result = false;
		try {
			result = this.productList.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The product list is missing" + e.getMessage());
		}
		return result;
	}

	public boolean isSearchFormDisplayed() {
		boolean result = false;
		try {
			result = this.searchForm.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The Search form is missing" + e.getMessage());
		}
		return result;
	}
  
	public void clickSubmit() {
		buttonSubmit.click();
	}
	
	public String getProductsAuthorOrArtistFromProductList(int productNumber) {
		String removeTitleText = this.allProductsTitles.get(productNumber).getText();
		String removePricesText = this.allProductsPrices.get(productNumber).getText();
		String removeButtonAddToBasketText = this.allProductsAddToBasketButtons.get(productNumber).getText();
		return (this.allProducts.get(productNumber).getText().replace(removeTitleText, "").replace(removePricesText, "")
				.replace(removeButtonAddToBasketText, "").replaceAll("\\r\\n|\\r|\\n", ""));
	}
	
	public boolean areAllProductsWrittenBySearchedAuthorOrArtist(String name) {
		boolean result = false;
		String nameLowCase = name.toLowerCase();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				if (getProductsAuthorOrArtistFromProductList(i).toLowerCase().contains(nameLowCase)) {
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
	
	public String getAllProductsTitles(int productNumber) {
		return (this.allProductsTitles.get(productNumber).getText());
	}
	
	public boolean doesAllProductsContainSearchedTitle(String title) {
		boolean result = false;
		String titleLowCase = title.toLowerCase();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				if (this.getAllProductsTitles(i).toLowerCase().contains(titleLowCase)) {
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
	
	public void openProductDetailsPageFromProductList(int productNumber) {
		this.allProductsTitles.get(productNumber).click();
	}

	public boolean isErrorMessageDisplayed() {
		boolean result = false;
		try {
			if (this.errorMessage.getText().equals("There are no Books matching the search criteria...") || this.errorMessage.getText().equals("There are no CDs matching the search criteria...")) {
				result = true;
			} else {
				result = false;
			}
		} catch (Throwable e) {
			System.out.println("Problem while checking if error message is displayed: " + e.getMessage());
		}
		return result;
	}
	
	public CategoryPage(WebDriver driver) {
		super(driver);
	}

}
