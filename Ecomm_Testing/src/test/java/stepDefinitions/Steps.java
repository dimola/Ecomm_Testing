package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Steps {
	
	WebDriver driver;
	

	@Given("^Login page is loaded$")
	public void Login_page_is_loaded() throws Throwable {
		System.setProperty("webdriver.chrome.driver","D:\\Java\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 driver.get("http://acme.qualityhouse.bg/build3/index.php?page=login");
	}

	@When("^I type valid username$")
	public void I_type_valid_username() throws Throwable {
	   WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
	   username.sendKeys("student1");
	}

	@And("^I type valid password$")
	public void I_type_valid_password() throws Throwable {
		WebElement password = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/form/input[2]"));
		   password.sendKeys("stpass1");  
	}

	@And("^I click on Login button$")
	public void I_click_on_Login_button() throws Throwable {
	   WebElement SubmitButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/form/input[3]"));
	   SubmitButton.click();
	}

	@Then("^I should be successfully logged in$")
	public void I_should_be_successfully_logged_in() throws Throwable {
	
		if(driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/a[6]")).size() != 0){
			System.out.println("Logout button is Present and user is successfully logged in");
			}else{
			System.out.println("Login button is Absent and user is not logged in ");
			
			}
		
		driver.quit();
		}
	
	}




