package customAssertions;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WebElementAssert extends AbstractAssert<WebElementAssert, WebElement> {

    public WebElementAssert(WebElement actual){
        super(actual, WebElementAssert.class);
    }

    public static WebElementAssert assertThat(WebElement actual){
        return new WebElementAssert(actual);
    }

    public WebElementAssert isDisplayed(){
        isNotNull();

        if (actual == null){
            failWithMessage("WebElement is not displayed.");
        }
        else {
            if(!actual.isDisplayed()){
                failWithMessage("WebElement <%s> is not displayed.", actual);
            }
        }

        return this;
    }

    public WebElementAssert isNotDisplayed(){
        isNotNull();

        boolean isDisplayed = true;
        try{
            actual.isDisplayed();
        }
        catch (NoSuchElementException e){
            isDisplayed = false;
        }
        finally {
            if (isDisplayed){
                failWithMessage("Element <%s> is displayed when it should not.", actual);
            }
        }

        return this;
    }


    public WebElementAssert textEquals(String expected){
        isNotNull();

        if(actual == null){
            failWithMessage("Element is not loaded!");
        }
        else{
            if (!actual.getText().equals(expected)){
                failWithMessage("Expected text: <%s> . Actual text: <%s>: ", expected, actual.getText());
            }
        }

        return this;
    }

}