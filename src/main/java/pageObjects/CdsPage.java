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
	

	public CdsPage(WebDriver driver) {
		super(driver);
	}

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

	public String getArtistLabelText(){
		try {
			if (this.artistLabel.isDisplayed()){
				return this.artistLabel.getText();
			}
			else{
				return "Can't find artist text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find artist text box";
		}
	}

	public String getTitleLabelText(){
		try {
			if (this.titleLable.isDisplayed()){
				return this.titleLable.getText();
			}
			else{
				return "Can't find title text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find title text box";
		}
	}

	public String getLabelLabelText(){
		try {
			if (this.labelLabel.isDisplayed()){
				return this.labelLabel.getText();
			}
			else{
				return "Can't find label text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find label text box";
		}
	}

	public String getComposerLabelText(){
		try {
			if (this.composerLabel.isDisplayed()){
				return this.composerLabel.getText();
			}
			else{
				return "Can't find composer text box";
			}
		}
		catch (NoSuchElementException e) {
			//result remain empty string
			return "Can't find composer text box";
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

}