package stepDefinitions;

import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageObjectManager;

public class Steps
{
    PageObjectManager pageObjectManager;

    @Given( "^Login page is loaded$" )
    public void Login_page_is_loaded() throws Throwable
    {
        pageObjectManager.getLoginPage()
                         .open();
    }

    @When( "^I type username \"([^\"]*)\"$" )
    public void I_type_username( String username ) throws Throwable
    {
        pageObjectManager.getLoginPage()
                         .enterUserName( username );
    }

    @And( "^I type password \"(.*)\"$" )
    public void I_type_password( String password ) throws Throwable
    {
        pageObjectManager.getLoginPage()
                         .enterPassword( password );

    }

    @And( "^I click on Login button$" )
    public void I_click_on_Login_button() throws Throwable
    {
        pageObjectManager.getLoginPage()
                         .clickLogIn();
    }

    @Then( "^I should be successfully logged in$" )
    public void I_should_be_successfully_logged_in() throws Throwable
    {
        Assert.assertTrue( "Home page is not on focus",
                           pageObjectManager.getHomePage()
                                            .isOpen() );
        Assert.assertFalse( "Login button is displayed after login but it should not",
                            pageObjectManager.getHomePage()
                                             .isLoginButtonDisplayed() );
        Assert.assertFalse( "Register button is displayed after login but it should not",
                            pageObjectManager.getHomePage()
                                             .isRegisterButtonDisplayed() );
        Assert.assertTrue( "Logout button is not displayed after login but it should",
                           pageObjectManager.getHomePage()
                                            .isLogoutButtonDisplayed() );
    }


    @Then( "^An error message is displayed$" )
    public void An_error_message_is_displayed() throws Throwable
    {
        Assert.assertTrue( "Timer is not displayed",
                           pageObjectManager.getLoginPage()
                                            .timerIsDisplayed() );

    }

    @Then( "^I am not logged in the system$" )
    public void I_am_not_logged_in_the_system() throws Throwable
    {
        Assert.assertTrue( "Login page is not on focus",
                           pageObjectManager.getLoginPage().isOpen());
    }

    // TC 3 Logout

    @Given( "^I am logged in with credentials \"([^\"]*)\" and \"([^\"]*)\"$" )
    @When( "^I login with credentials \"([^\"]*)\" and \"([^\"]*)\"$" )
    public void I_am_logged_in_with_credentials_and( String username,
                                                     String password ) throws Throwable
    {
        pageObjectManager.getLoginPage()
                         .login( username,
                                 password );
    }

    @When( "^I logout$" )
    public void I_click_on_Logout() throws Throwable
    {
        pageObjectManager.getHomePage()
                         .clickLogOut();
        pageObjectManager.getLogoutPage()
                         .clickConfirmLogOut();
    }

    @Then( "^I am successfully logged out$" )
    public void I_am_successfully_logged_out() throws Throwable
    {
        Assert.assertTrue( "Login button is not displayed after logout",
                           pageObjectManager.getHomePage()
                                            .isLoginButtonDisplayed() );
        Assert.assertTrue( "Register button is not displayed after logout",
                           pageObjectManager.getHomePage()
                                            .isRegisterButtonDisplayed() );
        Assert.assertFalse( "Logout button is displayed after logout",
                            pageObjectManager.getHomePage()
                                             .isLogoutButtonDisplayed() );
    }

    @After
    public void cleanUp()
    {
        pageObjectManager.quit();
    }

    @Before
    public void init()
    {
        PageObjectManager.init();
        pageObjectManager = PageObjectManager.getManager();
    }

}
