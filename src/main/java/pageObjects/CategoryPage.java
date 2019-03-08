package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class CategoryPage extends GeneralPage {

	@FindBy(css = "#side-menu > a.selected")
	protected WebElement selectedCategory;

	@FindBy(id = "side-menu")
	protected WebElement sideMenu;

	@FindBy(css = "#side-menu h2")
	protected WebElement sideMenuTitle;

	@FindBy(name = "search")
	protected WebElement searchForm;

	@FindBy(css = "#side-menu > a")
	List<WebElement> sideBarButtons;

	@FindBy(css = "#product-list div.item")
	private List<WebElement> allProductsPerCategory;

	@FindBy(css = "#product-list img")
	private List<WebElement> allProductsImagesPerCategory;

	@FindBy(css = "#product-list b:only-child")
	private List<WebElement> allProductsTitlesPerCategory;

	@FindBy(css = "#product-list span")
	private List<WebElement> allProductsPrisesPerCategory;

	@FindBy(css = "#product-list a.buy-btn")
	private List<WebElement> allProductsAddToBasketButtonsPerCategory;

	@FindBy(css = "#product-list > b.err")
	private WebElement emptyCategoryErr;

	public CategoryPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getSideBarButtons() {
		return sideBarButtons;
	}

	public String getSelectedCategoryTitle() {
		return this.selectedCategory.getText();
	}
	
	public String getProductAuthorFromProductListPerCategory(int productNumber) {
		String removeTitleText = this.allProductsTitlesPerCategory.get(productNumber).getText();
		String removePricesText = this.allProductsPrisesPerCategory.get(productNumber).getText();
		String removeButtonAddToBasketText = this.allProductsAddToBasketButtonsPerCategory.get(productNumber).getText();
		return (this.allProductsPerCategory.get(productNumber).getText().replace(removeTitleText, "")
				.replace(removePricesText, "").replace(removeButtonAddToBasketText, "").replaceAll(" ", "")
				.replaceAll("\\r\\n|\\r|\\n", ""));
	}

	public boolean isSideMenuDisplayed() {
		boolean result = false;
		try {
			result = this.sideMenu.isDisplayed();
		} catch (Throwable e) {
			System.out.println("The side menu is missing" + e.getMessage());
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

	public boolean isSelectedCategoryOpen(String categoryName) {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed()
					&& this.pageHeadingTitle.getText().equals(this.sideMenuTitle.getText() + " » " + categoryName);
		} catch (Throwable e) {
			System.err.println("Problem while checking if " + this.sideMenuTitle.getText()
					+ " Category Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean areTheProductsImagesDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allProductsPerCategory.size(); i++) {
				result = currentResult && this.allProductsImagesPerCategory.get(i).isDisplayed()
						&& (this.allProductsImagesPerCategory.get(i).getAttribute("src") != null);
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all images per product are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheProductsTitlesDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allProductsPerCategory.size(); i++) {
				result = currentResult && this.allProductsTitlesPerCategory.get(i).isDisplayed()
						&& (this.allProductsTitlesPerCategory.get(i).getText() != null);
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Titles per product category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheProductsAuthorsDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allProductsPerCategory.size(); i++) {
				result = currentResult && (this.getProductAuthorFromProductListPerCategory(i) != null);
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Authors per book category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheProductsPricesDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allProductsPerCategory.size(); i++) {
				result = currentResult && !this.allProductsPrisesPerCategory.isEmpty()
						&& (this.allProductsPrisesPerCategory.get(i).getText() != null);
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Prices per book category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheProductsAddToBasketButtonsDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allProductsPerCategory.size(); i++) {
				result = currentResult && !this.allProductsAddToBasketButtonsPerCategory.isEmpty()
						&& this.allProductsAddToBasketButtonsPerCategory.get(i).getText().equals("add to basket");
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Add to basket per book category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean isCategoryEmpty() {
		boolean result = false;
		try {
			result = this.emptyCategoryErr.isDisplayed()
					&& this.emptyCategoryErr.getText().equals("There are no products in the category");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Empty Category Error is displayed: " + e.getMessage());
		}
		return result;
	}
	
	public void addRandomProductToBasketFromProductList() {
		int rnd = (int)(Math.random()*this.allProductsAddToBasketButtonsPerCategory.size());
		this.allProductsAddToBasketButtonsPerCategory.get(rnd).click();
	}
}
