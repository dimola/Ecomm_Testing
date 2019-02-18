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
	protected WebElement searchFields;

	public boolean checkSideMenu() {
		boolean result = false;
		try {
			result = this.sideMenu.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The side menu is missing" + e.getMessage());
		}
		return result;
	}

	public boolean checkProductList() {
		boolean result = false;
		try {
			result = this.productList.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The product list is missing" + e.getMessage());
		}
		return result;
	}

	public boolean checkSearchForm() {
		boolean result = false;
		try {
			result = this.searchFields.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The Search form is missing" + e.getMessage());
		}
		return result;
	}

	public CategoryPage(WebDriver driver) {
		super(driver);
	}
}
