package org.stc.steps;

import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.stc.hooks.Hooks;
import org.stc.pages.OpenAccountPage;
import org.testng.Assert;

import java.io.IOException;

public class OpenAccountSteps {
    private WebDriver driver;
    private OpenAccountPage openAccount;

    public OpenAccountSteps() {
        this.driver = Hooks.driver; // Assuming Hooks provides WebDriver instance
        this.openAccount = new OpenAccountPage(driver);
    }

    @When("the bank manager selects the customer (.*)$")
    public void theBankManagerSelectsTheCustomer(String customerName) {
        openAccount.selectCustomer(customerName);
    }

    @When("the bank manager selects the currency (.*)$")
    public void theBankManagerSelectsTheCurrency(String currency) throws CsvValidationException, IOException {
        openAccount.selectCurrency(currency);
    }

    @When("the bank manager clicks on the Process button$")
    public void theBankManagerClicksProcessButton() {
        openAccount.process();
    }

    @Then("the account should be created successfully with an account number$")
    public void theCustomerShouldLogInWithTheAccountNumberAndVerify() {
        String actualMsg = openAccount.getSucessAlertMsgThenAccept();
        String expectedSuccessMsg = "Account created successfully with account Number :";
        Assert.assertTrue(actualMsg.contains(expectedSuccessMsg));
    }

    @Then("the bank manager should go back to home$")
    public void theBankManagerShouldGoBackToHome() {
        openAccount.goHome();
    }

}
