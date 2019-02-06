package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage
        extends GeneralPage
{
    private static final String PAGE_URL = "/index.php?page=home";

    public HomePage( WebDriver driver )
    {
        super( driver );
    }

    @Override
    public HomePage open()
    {
        this.driver.get( PAGE_URL );
        return this;
    }

    @Override
    public boolean isOpen()
    {
        boolean result = false;
        try
        {
            result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText()
                                                                                 .equals( "Home" );
        } catch ( Throwable e )
        {
            System.err.println( "Problem while checking if Home Page Heading is displayed: " + e.getMessage() );
        }

        return result;
    }

}
