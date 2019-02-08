package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class CategoryPage extends GeneralPage {
	
	@FindBy(css = "#product-list h4")
	protected WebElement productListTitle;
	
	public CategoryPage(WebDriver driver) {
		super(driver);
	}

}
