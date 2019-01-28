package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dataProviders.ConfigFileReader;

public class LoginPage {
WebDriver driver;
ConfigFileReader configFileReader;

public LoginPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,  this);
	configFileReader = new ConfigFileReader();
}	


@FindBy(id = "username")
private WebElement txtbxUserName;

@FindBy(css = "input[name='userpass']")                                          
private WebElement txtbxPassword;

@FindBy(css = "input[name='ses_login']")
private WebElement buttonLogin;



public void open() {
	this.driver.get("http://acme.qualityhouse.bg/build3/index.php?page=login");
}


public void enterUserName(String username) {
	txtbxUserName.sendKeys("student1");
}

public void enterPassword(String password) {
	txtbxPassword.sendKeys("stpass1");
}

public void clickLogIn() {
	buttonLogin.click();
}

}

