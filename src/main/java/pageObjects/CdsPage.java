package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CdsPage extends CategoryPage {
	private static final String PAGE_URL = "/index.php?page=cds";

	@FindBy(name = "Artist")
	private WebElement artistTextbox;
	
	@FindBy(name = "Title")
	private WebElement titleTextbox;
	
	@FindBy(name = "Label")
	private WebElement labelTextbox;
	
	@FindBy(name = "Composer")
	private WebElement composerTextbox;

	@FindBy(xpath = "//tbody/tr[1]/td[1]")
	private WebElement artistLabel;

	@FindBy(xpath = "//tbody/tr[2]/td[1]")
	private WebElement titleLable;

	@FindBy(xpath = "//tbody/tr[3]/td[1]")
	private WebElement labelLabel;

	@FindBy(xpath = "//tbody/tr[4]/td[1]")
	private WebElement composerLabel;

	@FindBy(className = "formbtn")
	private WebElement submitButton;
	

	public CdsPage(WebDriver driver) {
		super(driver);
	}

	/*
	Implementation from Home page abstract methods
	 */
	@Override
	public CdsPage open(){
		this.driver.get(configFileReader.getHost() + PAGE_URL);
		return this;
	}

	@Override
	public boolean isOpen(){
		boolean result = false;
		try {
			result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("CDs")
					&& this.productListTitle.isDisplayed()
					&& this.productListTitle.getText().equals("Browse CDs by category:");
		} catch (Throwable e) {
			System.err.println("Problem while checking if CDs Heading is displayed: " + e.getMessage());
		}
		return result;
	}

	/*
	Text getters from Web Elements
	 */
	public String getArtistLabelText(){
		try {
			if (this.artistLabel.isDisplayed()){
				return this.artistLabel.getText();
			}
			else{
				return null;
			}
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public String getTitleLabelText(){
		try {
			if (this.titleLable.isDisplayed()){
				return this.titleLable.getText();
			}
			else{
				return null;
			}
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public String getLabelLabelText(){
		try {
			if (this.labelLabel.isDisplayed()){
				return this.labelLabel.getText();
			}
			else{
				return null;
			}
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public String getComposerLabelText(){
		try {
			if (this.composerLabel.isDisplayed()){
				return this.composerLabel.getText();
			}
			else{
				return null;
			}
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<String> getSearchBarFieldsLabels(){
		List<String> searchBarFieldsLabels = new ArrayList<>();
		searchBarFieldsLabels.add(this.getArtistLabelText());
		searchBarFieldsLabels.add(this.getTitleLabelText());
		searchBarFieldsLabels.add(this.getLabelLabelText());
		searchBarFieldsLabels.add(this.getComposerLabelText());

		return searchBarFieldsLabels;
	}

	/*
	Actions in this page
	 */
	public void enterArtist(String artist) {
		artistTextbox.sendKeys(artist);
	}

	public void enterTitle(String title) {
		titleTextbox.sendKeys(title);
	}

	public void enterLabel(String label) {
		labelTextbox.sendKeys(label);
	}

	public void enterComposer(String composer) {
		composerTextbox.sendKeys(composer);
	}

	public void submit(String artist, String title, String label, String composer){
		enterArtist(artist);
		enterTitle(title);
		enterLabel(label);
		enterComposer(composer);
		this.submitButton.click();
	}

	public List<String> getAllProductsArtists(){
		List<String> allArtists = new ArrayList<>();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				ProductDetailsPage product = new ProductDetailsPage(driver);
				allArtists.add(product.getProductArtist());
				product.backToProductList();
			}

			return allArtists;
		} catch (Throwable e) {
			return null;
		}
	}

	public List<String> getAllProductsTitles(){
		List<String> allTitles = new ArrayList<>();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				ProductDetailsPage product = new ProductDetailsPage(driver);
				allTitles.add(product.getProductTitle());
				product.backToProductList();
			}

			return allTitles;
		} catch (Throwable e) {
			System.out.println("Problem while checking if displayed book are by this publisher: " + e.getMessage());
			return null;
		}
	}

	public List<String> getAllProductsLabels(){
		List<String> allLabels = new ArrayList<>();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				ProductDetailsPage product = new ProductDetailsPage(driver);
				allLabels.add(product.getProductLabel());
				product.backToProductList();
			}

			return allLabels;
		} catch (Throwable e) {
			return null;
		}
	}

	public List<String> getAllProductsComposers(){
		List<String> allComposers = new ArrayList<>();
		try {
			for (int i = 0; i < this.allProducts.size(); i++) {
				this.openProductDetailsPageFromProductList(i);
				ProductDetailsPage product = new ProductDetailsPage(driver);
				allComposers.add(product.getProductComposer());
				product.backToProductList();
			}

			return allComposers;
		} catch (Throwable e) {
			return null;
		}
	}

	/*
	Checks for certain images, buttons if they are displayed
	 */
	public boolean isArtistTextboxDisplayed(){
		boolean result = false;
		try {
			result = this.artistTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if artistTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isTitleTextboxDisplayed(){
		boolean result = false;
		try {
			result = this.titleTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if titleTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isLabelTextboxDisplayed(){
		boolean result = false;
		try {
			result = this.labelTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if labelTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	public boolean isComposerTextboxDisplayed(){
		boolean result = false;
		try {
			result = this.composerTextbox.isDisplayed();
		} catch (Throwable e) {
			System.out.println("Problem while checking if composerTextbox is displayed: " + e.getMessage());
		}
		return result;
	}

	/*
	Helper functions
	 */

}