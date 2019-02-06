package pageObjects;

import dataProviders.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class GeneralPage
{
    protected WebDriver driver;
    protected ConfigFileReader configFileReader;

    @FindBy( css = "a[href^='index.php?page=home']" )
    protected WebElement homeMenuElement;

    @FindBy( css = "a[href^='index.php?page=books']" )
    protected WebElement booksMenuElement;

    @FindBy( css = "a[href^='index.php?page=cds']" )
    protected WebElement cdsMenuElement;

    @FindBy( css = "a[href^='index.php?page=basket']" )
    protected WebElement basketMenuElement;

    @FindBy( css = "a[href^='index.php?page=register']" )
    protected WebElement registerMenuElement;

    @FindBy( css = "a[href^='index.php?page=login']" )
    protected WebElement loginMenuElement;

    @FindBy( css = "a[href^='index.php?page=logout']" )
    protected WebElement logoutMenuElement;

    @FindBy( css = "a[href^='index.php?page=logout']" )
    protected WebElement buttonLogout;

    @FindBy( css = "#main-big-col h3" )
    protected WebElement pageHeadingTitle;

    public GeneralPage( WebDriver driver )
    {
        this.driver = driver;
        PageFactory.initElements( driver,
                                  this );
        configFileReader = new ConfigFileReader();
    }

    public abstract GeneralPage open();

    public abstract boolean isOpen();

    public boolean isLoginButtonDisplayed(){
        return loginMenuElement.isDisplayed();
    }

    public boolean isLogoutButtonDisplayed(){
        return logoutMenuElement.isDisplayed();
    }

    public boolean isRegisterButtonDisplayed(){
        return registerMenuElement.isDisplayed();
    }

    public void clickLogOut()
    {
        buttonLogout.click();
    }

}
