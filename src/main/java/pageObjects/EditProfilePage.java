package pageObjects;

import cucumber.api.java.it.Ma;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EditProfilePage extends GeneralPage {
    private static final String PAGE_URL = "/index.php?page=edit-profile";

    @FindBy(name = "cEmail")
    private WebElement emailField;

    @FindBy(name = "cName")
    private WebElement nameField;

    @FindBy(name = "cPhone")
    private WebElement phoneField;

    @FindBy(name = "cAddress")
    private WebElement addressField;

    @FindBy(css = "input[value=Submit]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='main-big-col']/b")
    private WebElement successMessage;

    public EditProfilePage(WebDriver driver) {
        super(driver);
    }

    /*
    Implementation from Home page abstract methods
     */
    @Override
    public EditProfilePage open() {
        this.driver.get(configFileReader.getHost() + PAGE_URL);
        return this;
    }

    @Override
    public boolean isOpen() {
        boolean result = false;
        try {
            result = this.pageHeadingTitle.isDisplayed() && this.pageHeadingTitle.getText().equals("Edit Profile");
        } catch (Throwable e) {
            System.err.println("Problem while checking if Edit Profile page heading is displayed: " + e.getMessage());
        }
        return result;
    }

    /*
	Text getters from Web Elements
	 */
    public String getEmailFieldText(){
        try {
            if (this.emailField.isDisplayed()){
                return this.emailField.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getNameFieldText(){
        try {
            if (this.nameField.isDisplayed()){
                return this.nameField.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getPhoneFieldText(){
        try {
            if (this.phoneField.isDisplayed()){
                return this.phoneField.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getAddressFieldText(){
        try {
            if (this.addressField.isDisplayed()){
                return this.addressField.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getSubmitButtonText(){
        try {
            if (this.submitButton.isDisplayed()){
                return this.submitButton.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getSuccessMessage(){
        try {
            if (this.successMessage.isDisplayed()){
                return this.successMessage.getText();
            }
            else{
                return null;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getErrorMessage(){
        try {
            String alertText =  this.driver.switchTo().alert().getText();
            return alertText;
        }
        catch (Throwable e){
            return null;
        }
    }

    /*
	Actions in this page
	 */
    public void enterEmail(String email){
        this.emailField.clear();
        this.emailField.sendKeys(email);
    }

    public void enterName(String name){
        this.nameField.clear();
        this.nameField.sendKeys(name);
    }

    public void enterPhone(String phone){
        this.phoneField.clear();
        this.phoneField.sendKeys(phone);
    }

    public void enterAddress(String address){
        this.addressField.clear();
        this.addressField.sendKeys(address);
    }

    public void clickSubmitButton(){
        this.submitButton.click();
    }

    public void submitForm(String email, String name, String phone, String address){
        //Delete previous content
        this.emailField.clear();
        this.nameField.clear();
        this.phoneField.clear();
        this.addressField.clear();

        //
        this.emailField.sendKeys(email);
        this.nameField.sendKeys(name);
        this.phoneField.sendKeys(phone);
        this.addressField.sendKeys(address);
        this.submitButton.click();
    }

    /*
	Checks for certain images, buttons if they are displayed
	 */

    /*
	Helper functions
	 */
    public String createRandomValidEmail(){
        String alphabetWithNumbers = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "123456789";
        String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String firstPartEmail;
        String secondPartEmail;
        String thirdPartEmail;
        String generatedEmail = "";
        Random rd = new Random();

        int firstPartLength = ThreadLocalRandom.current().nextInt(1,10);
        int secondPartLength = ThreadLocalRandom.current().nextInt(1,10);
        int thirdPartLength = ThreadLocalRandom.current().nextInt(1,5);

        StringBuilder firstPartEmailBuilder = new StringBuilder(firstPartLength);
        StringBuilder secondPartEmailBuilder = new StringBuilder(secondPartLength);
        StringBuilder thirdPartEmailBuilder = new StringBuilder(thirdPartLength);

        for (int i = 0; i < firstPartLength; i++) {
            firstPartEmailBuilder.append(alphabetWithNumbers.charAt(rd.nextInt(alphabetWithNumbers.length())));
        }

        for (int i = 0; i < secondPartLength; i++) {
            secondPartEmailBuilder.append(smallAlphabet.charAt(rd.nextInt(smallAlphabet.length())));
        }

        for (int i = 0; i < thirdPartLength; i++) {
            thirdPartEmailBuilder.append(smallAlphabet.charAt(rd.nextInt(smallAlphabet.length())));
        }

        firstPartEmail = firstPartEmailBuilder.toString();
        secondPartEmail = secondPartEmailBuilder.toString();
        thirdPartEmail = thirdPartEmailBuilder.toString();

        generatedEmail = generatedEmail.concat(firstPartEmail).concat("@").concat(secondPartEmail).concat(".").concat(thirdPartEmail);

        System.out.println("Generated name is " + generatedEmail);
        return generatedEmail;
    }

    public String createRandomValidName(){
        String alphabet = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String firstName;
        String lastName;
        String generatedName = "";
        Random rd = new Random();
        int firstNameLength = ThreadLocalRandom.current().nextInt(1,10);
        int lastNameLength = ThreadLocalRandom.current().nextInt(1,10);
        StringBuilder firstNameBuilder = new StringBuilder(firstNameLength);
        StringBuilder lastNameBuilder = new StringBuilder(lastNameLength);

        for (int i = 0; i < firstNameLength; i++) {
            firstNameBuilder.append(alphabet.charAt(rd.nextInt(alphabet.length())));
        }

        for (int i = 0; i < lastNameLength; i++) {
            lastNameBuilder.append(alphabet.charAt(rd.nextInt(alphabet.length())));
        }

        firstName = firstNameBuilder.toString();
        lastName = lastNameBuilder.toString();

        generatedName = generatedName.concat(firstName).concat(" ").concat(lastName);

        System.out.println("Generated name is " + generatedName);
        return generatedName;
    }

    public String createRandomValidPhone(){
        /*
           Generates valid phone numbers following the E.164 convention
         */
        int tempNumber = 0;

        String countryCode = "";
        String localAreaCode = "";
        String localPhoneNumber = "";
        String generatedPhoneNumber = "";

        //Decides the length for all parts of the phone number
        int countryCodeDigitsNumber = ThreadLocalRandom.current().nextInt(1,4);
        int localAreaCodeDigitsNumber = ThreadLocalRandom.current().nextInt(1,4);
        int localPhoneNumberDigitsNumber = ThreadLocalRandom.current().nextInt(6,9);

        while (countryCodeDigitsNumber > 0){
            tempNumber = ThreadLocalRandom.current().nextInt(1,10); // generate a random digit
            countryCode = countryCode.concat(String.valueOf(tempNumber)); //add this digit to the phone country code
            countryCodeDigitsNumber -= 1;
        }

        while (localAreaCodeDigitsNumber > 0){
            tempNumber = ThreadLocalRandom.current().nextInt(1,10); // generate a random digit
            localAreaCode = localAreaCode.concat(String.valueOf(tempNumber)); //add this digit to the phone local area code
            localAreaCodeDigitsNumber -= 1;
        }

        while (localPhoneNumberDigitsNumber > 0){
            tempNumber = ThreadLocalRandom.current().nextInt(1,10);// generate a random digit
            localPhoneNumber = localPhoneNumber.concat(String.valueOf(tempNumber));//add this digit to the phone number part
            localPhoneNumberDigitsNumber -= 1;
        }

        //Generates full phone number
        generatedPhoneNumber = generatedPhoneNumber.concat("+").concat(countryCode).concat(localAreaCode).concat(localPhoneNumber);

        System.out.println("Generated phone number is " + generatedPhoneNumber);
        return generatedPhoneNumber;
    }

    public String createRandomValidAddress(){
        String alphabet = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "., '";
        Random rd = new Random();
        int strLength = ThreadLocalRandom.current().nextInt(10,40);
        StringBuilder generatedAddress = new StringBuilder(strLength);

        for (int i = 0; i < strLength; i++) {
            generatedAddress.append(alphabet.charAt(rd.nextInt(alphabet.length())));
        }

        System.out.println("Generated address  is " + generatedAddress.toString());
        return generatedAddress.toString();
    }
}
