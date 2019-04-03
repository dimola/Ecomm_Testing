package customAssertions;

import managers.PageObjectManager;
import org.assertj.core.api.AbstractAssert;
import pageObjects.*;

/*
This class provides all assertions for single class
They can be used via the entry point (CustomAssertions class) with providing the object
*/
public class PageObjectManagerAssert extends AbstractAssert<PageObjectManagerAssert, PageObjectManager> {

    public PageObjectManagerAssert(PageObjectManager actual){

        super(actual, PageObjectManagerAssert.class);

    }

    public static PageObjectManagerAssert assertThat(PageObjectManager actual){
        return new PageObjectManagerAssert(actual);
    }

    public PageObjectManagerAssert homePageIsOpen(){

        isNotNull();

        HomePage homePage = actual.getHomePage();

        boolean statement = homePage.getPageHeadingTitle().isDisplayed() && homePage.getPageHeadingTitle().getText().equals("Home");

        if (!statement){
            failWithMessage("Home page is not open! Page title is not displayed or is not \"Home\" but <%s>.", homePage.getPageHeadingTitle().getText());
        }

        return this;
    }

    public PageObjectManagerAssert booksPageIsOpen(){

        isNotNull();

        BooksPage booksPage = actual.getBooksPage();

        boolean statement = booksPage.getPageHeadingTitle().isDisplayed() && booksPage.getPageHeadingTitle().getText().equals("Books");

        if (!statement){
            failWithMessage("Home page is not open! Page title is not displayed or is not \"Books\" but <%s>.", booksPage.getPageHeadingTitle().getText());
        }

        return this;
    }

    public PageObjectManagerAssert cdsPageIsOpen(){

        isNotNull();

        CdsPage cdsPage = actual.getCdsPage();

        boolean statement = cdsPage.getPageHeadingTitle().isDisplayed() && cdsPage.getPageHeadingTitle().getText().equals("CDs");

        if (!statement){
            failWithMessage("Home page is not open! Page title is not displayed or is not \"CDs\" but <%s>.", cdsPage.getPageHeadingTitle().getText());
        }

        return this;
    }

    public PageObjectManagerAssert loginPageIsOpen(){

        isNotNull();

        LoginPage loginPage = actual.getLoginPage();

        boolean statement = loginPage.getPageHeadingTitle().isDisplayed() && loginPage.getPageHeadingTitle().getText().equals("Login");

        if (!statement){
            failWithMessage("Home page is not open! Page title is not displayed or is not \"LogIn\" but <%s>.", loginPage.getPageHeadingTitle().getText());
        }

        return this;
    }

    public PageObjectManagerAssert logoutPageIsOpen(){

        isNotNull();

        LogoutPage logoutPage = actual.getLogoutPage();

        boolean statement = logoutPage.getPageHeadingTitle().isDisplayed() && logoutPage.getPageHeadingTitle().getText().equals("Logout");

        if (!statement){
            failWithMessage("Home page is not open! Page title is not displayed or is not \"LogOut\" but <%s>.", logoutPage.getPageHeadingTitle().getText());
        }

        return this;
    }
}
