package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends GeneralPage{
    private static final String PAGE_URL = "/index.php?page=checkout";

    //elements for Checkout login menu
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(name = "userpass")
    private WebElement passwordField;

    @FindBy(name = "ses_login")
    private WebElement loginButton;

    @FindBy(css = "b")
    private WebElement mainText;

    //elements for actual checkout page with products
    @FindBy(css = "a[href^='index.php?page=confirm-purchase']")
    private WebElement confirmPurchaseButton;

    @FindBy(css = "a[href^='index.php?page=cancel-purchase']")
    private WebElement cancelPurchaseButton;

    @FindBy(className = "basket-row")
    private List<WebElement> productsElements; //Each product has 4 elements from this list (image with product name, price, count, subtotal)

    @FindBy(className = "basket")
    private List<WebElement> tableFrameElements; //Elements with names for rows etc.

    @FindBy(className = "basket-total")

    private List<WebElement> basketTotalElements; //Elements with total prices, count etc.

    @FindBy(className = "basket-caption")
    private List<WebElement> productsTitles;

    @FindBy(className = "basket-pic")
    private List<WebElement> productsPictures;

    //Only after purchase completeion
    @FindBy(className = "big-btn")
    private WebElement goToHomeButton;

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    /*
	Implementation from Home page abstract methods
	 */
    @Override
    public CheckoutPage open() {
        this.driver.get(this.configFileReader.getHost() + PAGE_URL);
        return this;
    }

    @Override
    public boolean isOpen() {
        boolean result = false;
        try {
            result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Checkout");
        } catch (Throwable e) {
            System.err.println("Problem while checking if Logout Page Heading is displayed: " + e.getMessage());
        }
        return result;
    }

    /*
	Text getters from Web Elements
	 */
    public String getProductTitle(int productNumber){
        /*
        Magic with productNumber - 1 means to make the
         lists indexing more human readable e.g.
        actual item is zero, but user perspective is 1;
        */
        try {
            if (this.productsTitles.get(productNumber - 1).isDisplayed()){
                return this.productsTitles.get(productNumber - 1).getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            //result remain empty string
            return null;
        }
    }

    public double getProductPrice(int productNumber){
        /*
        Magic (productNumber-1)*4 + 1 means to make the lists indexing more human readable e.g.
        actual item is zero, but user perspective is 1;
        */
        try {
            if (this.productsElements.get((productNumber-1)*4 + 1).isDisplayed()){
                return Double.parseDouble(this.productsElements.get((productNumber-1)*4 + 1).getText());
            }
            else{
                return -1;
            }
        }
        catch (NoSuchElementException e) {
            //result remain empty string
            return -1;
        }
    }

    public double getProductCount(int productNumber){
        /*
        Magic (productNumber-1)*4 + 2 means to make the lists indexing more human readable e.g.
        actual item is zero, but user perspective is 1;
        */
        try {
            if (this.productsElements.get((productNumber-1)*4 + 2).isDisplayed()){
                return Double.parseDouble(this.productsElements.get((productNumber-1)*4 + 2).getText());
            }
            else{
                return -1;
            }
        }
        catch (NoSuchElementException e) {
            //result remain empty string
            return -1;
        }
    }

    public double getProductSubtotal(int productNumber){
        /*
        Magic (productNumber-1)*4 + 3 means to make the lists indexing more human readable e.g.
        actual item is zero, but user perspective is 1;
        */
        try {
            if (this.productsElements.get((productNumber-1)*4 + 3).isDisplayed()){
                return Double.parseDouble(this.productsElements.get((productNumber-1)*4 + 3).getText());
            }
            else{
                return -1;
            }
        }
        catch (NoSuchElementException e) {
            return -1;
        }
    }

    public double getTotalTax(){
        try {
            if (this.basketTotalElements.get(0).isDisplayed()){
                return Double.parseDouble(this.basketTotalElements.get(0).getText());
            }
            else{
                return -1;
            }
        }
        catch (NoSuchElementException e) {
            return -1;
        }
    }

    public double getTotalShipping(){
        try {
            if (this.basketTotalElements.get(1).isDisplayed()){
                return Double.parseDouble(this.basketTotalElements.get(1).getText());
            }
            else{
                return -1;
            }
        }
        catch (NoSuchElementException e) {
            return -1;
        }
    }

    public double getTotalSumField(){
        try {
            if (this.basketTotalElements.get(2).isDisplayed()){
                return Double.parseDouble(this.basketTotalElements.get(2).getText());
            }
            else{
                return 1;
            }
        }
        catch (NoSuchElementException e) {
            return 1;
        }
    }

    public String getMainText(){
        try {
            if (this.mainText.isDisplayed()){
                return this.mainText.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            //result remain empty string
            return null;
        }
    }

    public String getGoToHomeButtonText(){
        try {
            if (this.goToHomeButton.isDisplayed()){
                return this.goToHomeButton.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            //result remain empty string
            return null;
        }
    }

    public double getAllBasketProductsCostSum(){
        double temp = getProductsCountInBasket();
        double sum = 0;
        for (int i = 1; i<=temp; i++){
            sum += this.getProductPrice(i);
        }

        return sum;
    }

    /*
	Actions in this page
	 */

    public void login(String username, String password){
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.loginButton.click();
    }

    public void confirmPurchase(){
        this.confirmPurchaseButton.click();
        driver.switchTo().alert().accept();
    }

    public void cancelPurchase(){
        this.cancelPurchaseButton.click();
        driver.switchTo().alert().accept();
    }

    public void clickGoToHomeButton(){
        this.goToHomeButton.click();
    }

    /*
	Checks for certain images, buttons if they are displayed
	 */
    public boolean isProductImageDisplayed(int productNumber){
        /*
        Magic productNumber-1 means to make the lists indexing more human readable e.g.
        actual item is zero, but user perspective is 1;
        */
        try {
            if (this.productsPictures.get(productNumber - 1).isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isUsernameFieldDisplayed(){
        try {
            if (this.usernameField.isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPasswordFieldDisplayed(){
        try {
            if (this.passwordField.isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed(){
        try {
            if (this.loginButton.isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isConfirmButtonDisplayed(){
        try {
            if (this.confirmPurchaseButton.isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isCancelButtonDisplayed(){
        try {
            if (this.cancelPurchaseButton.isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

	/*
	Helper functions
	 */
    public boolean isGoToHomeButtonDisplayed(){
        try {
            if (this.goToHomeButton.isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
	/*
	Helper functions
	 */

	public int getProductsCountInBasket(){
	    return (this.basketTotalElements.size() / 4) + 1;
    }
}
