package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends GeneralPage {
	private static final String PAGE_URL = "/index.php?page=basket";

	@FindBy(css = "#main-big-col .err")
	private WebElement errorEmptyBasket;

	@FindBy(css = "#basket-details tr")
	private List<WebElement> basketTableRows;

	@FindBy(css = "#basket-details .basket-btn")
	private List<WebElement> productAddRemoveButtons;

	// when the defect about added products in basket is fixed, the css should
	// locate each price/subtotal/count cell per product
	@FindBy(css = "#basket-details > table > tbody > tr:nth-child(2) > td:nth-child(3)")
	private List<WebElement> productPrice;

	@FindBy(css = "#basket-details > table > tbody > tr:nth-child(2) > td:nth-child(5) > b")
	private List<WebElement> productSubtotal;

	@FindBy(css = "#basket-details .basket-total b")
	private WebElement basketTotal;

	@FindBy(css = "#main-big-col .big-btn")
	private WebElement checkoutButton;

	@FindBy(className ="basket-caption")
	private List<WebElement> allBasketProductTitles;

	public BasketPage(WebDriver driver) {
		super(driver);
	}

	/*
	Implementation from Home page abstract methods
	 */
	@Override
	public BasketPage open() {
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen() {
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Shopping Basket");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Basket Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	/*
	Text getters from Web Elements
	 */
	public String getEmptyBasketErrorMsg(){

		try {
			if(this.errorEmptyBasket.isDisplayed()){
				return this.errorEmptyBasket.getText();
			}
			else{
				return null;
			}

		} catch (Throwable e) {
			return null;
		}
	}

	public int getProductCount(int productRowNumber) {
		int productCount = 0;
		String[] helpArray1 = this.basketTableRows.get(productRowNumber).getText().split("  –    ");
		String[] helpArray2 = helpArray1[1].split("    +");
		productCount = Integer.parseInt(helpArray2[0]);

		return productCount;
	}

	public float getProductPrice(int productRowNumber) {
		return Float.parseFloat(this.productPrice.get(productRowNumber).getText());
	}

	public float getProductSubtotal(int productRowNumber) {
		return Float.parseFloat(this.productSubtotal.get(productRowNumber).getText());
	}

	public int getProductsCount() {
		int productsCount = 0;
		List<WebElement> productRows = new ArrayList<WebElement>();
		try{
			this.basketTableRows.isEmpty();
		}
		catch (Throwable e){
			return 0;
		}
		// creates list of WebElements with all product rows excluding the first and the
		// last rows that are not representations of products in the basket
		for (int i = 1; i < this.basketTableRows.size() - 1; i++) {
			productRows.add(this.basketTableRows.get(i));
		}
		// calculates how many products are added to the basket by counting the value in
		// the Count column in the basket
		for (int i = 1; i < this.basketTableRows.size() - 1; i++) {
			String[] helpArray1 = this.basketTableRows.get(i).getText().split("  –    ");
			String[] helpArray2 = helpArray1[1].split("    +");
			productsCount = productsCount + Integer.parseInt(helpArray2[0]);
		}
		return productsCount;
	}

	public int getAllAddedProducts(){
		try {
			int count =  this.getProductsCount();
			return count;
		} catch (Throwable e) {
			System.err.println(
					"Problem while checking if the right amount of books are added to the basket: " + e.getMessage());
			return 0;
		}
	}

	public String getBasketProductTitle(int product_number){
		try{
			String productName = this.allBasketProductTitles.get(product_number - 1).getText();
			return productName;
		} catch (Throwable e){
			System.out.println("No products in basket");
			return null;
		}
	}

	public List<String> getBasketAllProductTitles(){
		try{
			List<String> allProductTitles = new ArrayList<>();
			for(int i = 0; i < this.allBasketProductTitles.size(); i++){
				allProductTitles.add(allBasketProductTitles.get(i).getText());
			}
			return allProductTitles;
		} catch (Throwable e){
			return null;
		}
	}

	/*
	Actions in this page
	 */
	public void addOneQuantity(int productNumberRow){
		int addButtonRow = productNumberRow*3 - 1;
		System.out.println(this.productAddRemoveButtons.get(addButtonRow).getText());
		WebElement addButton = this.productAddRemoveButtons.get(addButtonRow);
		addButton.click();
	}

	public void removeOneQuantity(int productNumberRow){
		int addButtonRow = productNumberRow*3 - 2;
		System.out.println(this.productAddRemoveButtons.get(addButtonRow).getText());
		WebElement addButton = this.productAddRemoveButtons.get(addButtonRow);
		addButton.click();
	}

	public void removeProduct(int productNumberRow){
		int addButtonRow = productNumberRow*3 - 3;
		System.out.println(this.productAddRemoveButtons.get(addButtonRow).getText());
		WebElement addButton = this.productAddRemoveButtons.get(addButtonRow);
		addButton.click();
	}

	public void checkoutBasket(){
		this.checkoutButton.click();
		if (System.getProperty("os.name").contains("Windows")){
			driver.switchTo().alert().accept();
		}
	}

	public void acceptAlert(){
		if (System.getProperty("os.name").contains("Windows")){
			driver.switchTo().alert().accept();
		}
	}

	/*
	Checks for certain images, buttons if they are displayed
	 */
	public boolean areAllAddedProductsDisplayed(int number) {
		boolean result = false;
		try {
			result = number == this.getProductsCount();
		} catch (Throwable e) {
			System.err.println(
					"Problem while checking if the right amount of books are added to the basket: " + e.getMessage());
		}
		return result;
	}

	public boolean isCheckoutButtonDisplayed() {
		boolean result = false;
		try {
			result = this.checkoutButton.isDisplayed() && this.checkoutButton.getText().equals("Checkout");
		} catch (Throwable e) {
			System.err.println("Problem while checking if Basket Page Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isRemoveProductButtonDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 1; i < this.basketTableRows.size() - 1; i++) {
				// we should add code to change the basketTableRow according to the FOR
				result = currentResult && this.productAddRemoveButtons.get(0).isDisplayed()
						&& (this.productAddRemoveButtons.get(0).getAttribute("title").equals("remove"));
			}
		} catch (Throwable e) {
			System.err.println("Problem while checking if Remove Product Button is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isRemoveOneProductButtonDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 1; i < this.basketTableRows.size() - 1; i++) {
				// we should add code to change the basketTableRow according to the FOR
				result = currentResult && this.productAddRemoveButtons.get(1).isDisplayed()
						&& (this.productAddRemoveButtons.get(1).getAttribute("title").equals("remove one"));
			}
		} catch (Throwable e) {
			System.err.println("Problem while checking if Remove One Product Button is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isAddOneProductButtonDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 1; i < this.basketTableRows.size() - 1; i++) {
				// we should add code to change the basketTableRow according to the FOR
				result = currentResult && this.productAddRemoveButtons.get(2).isDisplayed()
						&& (this.productAddRemoveButtons.get(2).getAttribute("title").equals("add one"));
			}
		} catch (Throwable e) {
			System.err.println("Problem while checking if Add One Product button is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isBasketHeaderDisplayed() {
		boolean result = false;
		try {
			result = this.basketTableRows.get(0).isDisplayed()
					&& this.basketTableRows.get(0).getText().contains("Product")
					&& this.basketTableRows.get(0).getText().contains("Price")
					&& this.basketTableRows.get(0).getText().contains("Count")
					&& this.basketTableRows.get(0).getText().contains("Subtotal");
		} catch (Throwable e) {
			System.err.println("Problem while checking if the Basket Header is correctly displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isEmtpyBasketDisplayed() {
		boolean result = false;
		try {
			result = this.errorEmptyBasket.isDisplayed()
					&& this.errorEmptyBasket.getText().equals("The Shopping Basket is empty!");
		} catch (Throwable e) {
			System.err.println("Problem while checking if the empty shopping basket error message is displayed: "
					+ e.getMessage());
		}
		return result;
	}

	public boolean isProductCountDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 1; i < this.basketTableRows.size() - 1; i++) {
				result = currentResult && (String.valueOf(this.getProductCount(i)) != null);
			}
		} catch (Throwable e) {
			System.err.println("Problem while checking if product count is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isProductPriceDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.basketTableRows.size() - 2; i++) {
				result = currentResult && (String.valueOf(this.getProductPrice(i)) != null);
			}
		} catch (Throwable e) {
			System.err.println("Problem while checking if Product Price is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isProductSubtotalDisplayed() {
		boolean result = false;
		boolean currentResult = true;
		try {
			for (int i = 0; i < this.basketTableRows.size() - 2; i++) {
				result = currentResult && (String.valueOf(this.getProductSubtotal(i)) != null)
						&& (this.getProductSubtotal(i) == (this.getProductPrice(i) * this.getProductCount(i + 1)));
			}
		} catch (Throwable e) {
			System.err
					.println("Problem while checking if Product Subtotal is displayed and correct: " + e.getMessage());
		}
		return result;
	}

	public boolean isBasketTotalDisplayed() {
		boolean result = false;
		float basketTotalCurrent = 0;
		for (int i = 0; i < this.basketTableRows.size() - 2; i++) {
			basketTotalCurrent = basketTotalCurrent + this.getProductSubtotal(i);
		}
		try {
			result = this.basketTotal.isDisplayed()
					&& (Float.parseFloat(this.basketTotal.getText()) == basketTotalCurrent);
		} catch (Throwable e) {
			System.err
					.println("Problem while checking if the basket total is correct and displayed: " + e.getMessage());
		}
		return result;
	}

}
