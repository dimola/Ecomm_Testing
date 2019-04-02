package customAssertions;

import managers.PageObjectManager;
import org.openqa.selenium.WebElement;


/*
This class provides an entry point for all custom assertions.
Just create an Assertions class providing static assertThat methods for each of the assertions classes.
You need static import to use your custom assertions e.g. : import static customAssertions.CustomAssertions.assertThat;
 */
public class CustomAssertions {

    public static PageObjectManagerAssert assertThat(PageObjectManager actual){
        return new PageObjectManagerAssert(actual);
    }

    public static WebElementAssert assertThat(WebElement actual){
        return new WebElementAssert(actual);
    }

}
