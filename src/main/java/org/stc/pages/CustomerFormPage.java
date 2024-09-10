package org.stc.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.SeleniumUtilities;

import static utils.SeleniumUtilities.wait;

public class CustomerFormPage {
    private WebDriver driver;
    private By firstNameInput = By.cssSelector("input[ng-model='fName']");
    private By lastNameInput = By.cssSelector("input[ng-model='lName']");
    private By postCodeInput = By.cssSelector("input[ng-model='postCd']");
    private By addCustomerButton = By.cssSelector("button[type='submit']");

    private static final Logger log = LogManager.getLogger(CustomerFormPage.class.getName());

    public CustomerFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public CustomerFormPage fillAddCustomerForm(String firstname, String lastname, String postCode) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
            SeleniumUtilities.type(driver, firstNameInput, firstname, "Customer First Name");

            wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
            SeleniumUtilities.type(driver, lastNameInput, lastname, "Customer Last Name");

            wait.until(ExpectedConditions.visibilityOfElementLocated(postCodeInput));
            SeleniumUtilities.type(driver, postCodeInput, postCode, "Customer Post Code");
        } catch (Exception e) {
            log.error("Error filling the customer form: ", e);
            throw e;  // Rethrow or handle as needed
        }
        return this;
    }

    public CustomerFormPage addCustomer() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addCustomerButton));
            SeleniumUtilities.pressElement(driver, addCustomerButton, "Add Customer Button");
        } catch (Exception e) {
            log.error("Error clicking the Add Customer button: ", e);
            throw e;
        }
        return this;
    }

    public String getAddCustomerSuccessMessageThenAccept() {
        try {

            return SeleniumUtilities.getAllertMsgThenAccept(driver);
        } catch (Exception e) {
            log.error("Error retrieving or accepting alert message: ", e);
            throw e;
        }
    }

}
