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
	private List<WebElement> allItemsPerCategory;

	@FindBy(css = "#product-list img")
	private List<WebElement> allItemsImagesPerCategory;

	@FindBy(css = "#product-list b:only-child")
	private List<WebElement> allItemsTitlesPerCategory;

	@FindBy(css = "#product-list span")
	private List<WebElement> allItemsPrisesPerCategory;

	@FindBy(css = "#product-list a.buy-btn")
	private List<WebElement> allItemsAddToBasketButtonsPerCategory;

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

	public String getAllItemsAuthorsPerCategory(int itemNumber) {
		String removeTitleText = this.allItemsTitlesPerCategory.get(itemNumber).getText();
		String removePricesText = this.allItemsPrisesPerCategory.get(itemNumber).getText();
		String removeButtonAddToBasketText = this.allItemsAddToBasketButtonsPerCategory.get(itemNumber).getText();
		return (this.allItemsPerCategory.get(itemNumber).getText().replace(removeTitleText, "")
				.replace(removePricesText, "").replace(removeButtonAddToBasketText, "").replaceAll(" ", "")
				.replaceAll("\\r\\n|\\r|\\n", ""));
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

	public boolean areTheItemsImagesDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allItemsPerCategory.size(); i++) {
				result = currentResult && this.allItemsImagesPerCategory.get(i).isDisplayed()
						&& (this.allItemsImagesPerCategory.get(i).getAttribute("src") != null);
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all images per item are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheItemsTitlesDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allItemsPerCategory.size(); i++) {
				result = currentResult && this.allItemsTitlesPerCategory.get(i).isDisplayed()
						&& (this.allItemsTitlesPerCategory.get(i).getText() != null);
				System.out.println(this.allItemsTitlesPerCategory.get(i).getText());
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Titles per item category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheItemsAuthorsDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allItemsPerCategory.size(); i++) {
				result = currentResult && (this.getAllItemsAuthorsPerCategory(i) != null);
				System.out.println(this.getAllItemsAuthorsPerCategory(i));
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Authors per book category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheItemsPricesDisplayed() {
		boolean result = false;
		boolean currentResult = true;

		try {
			for (int i = 0; i < this.allItemsPerCategory.size(); i++) {

				result = currentResult && !this.allItemsPrisesPerCategory.isEmpty()
						&& (this.allItemsPrisesPerCategory.get(i).getText() != null);
				System.out.println(this.allItemsPrisesPerCategory.get(i).getText());
				currentResult = result;
			}
		} catch (Throwable e) {
			System.out.println("Not all Prices per book category are shown" + e.getMessage());
		}
		return result;
	}

	public boolean areTheItemsAddToBasketButtonsDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.allItemsPerCategory.size(); i++) {
				result = currentResult && !this.allItemsAddToBasketButtonsPerCategory.isEmpty()
						&& this.allItemsAddToBasketButtonsPerCategory.get(i).getText().equals("add to basket");
				System.out.println(this.allItemsAddToBasketButtonsPerCategory.get(i).getText());
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
}
