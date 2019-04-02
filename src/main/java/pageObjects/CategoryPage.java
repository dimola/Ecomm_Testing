package pageObjects;

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
}
