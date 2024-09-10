package org.stc.steps;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.stc.hooks.Hooks;
import org.stc.pages.CustomerFormPage;
import org.testng.Assert;

public class CustomerFormSteps {

    private WebDriver driver;
    private CustomerFormPage customerFormPage;

    public CustomerFormSteps() {
        this.driver = Hooks.driver;
        this.customerFormPage = new CustomerFormPage(driver);

    }

    @When("^the manager fills in the customer details first name (.*), last name (.*), and post code (.*)$")
    public void theManagerFillsInTheCustomerDetails(String firstName, String lastName, String postCode) {
        customerFormPage.fillAddCustomerForm(firstName, lastName, postCode);
    }

    @When("^clicks on the Add Customer from button$")
    public void theManagerClicksOnAddCustomer() {
        customerFormPage.addCustomer();
    }

    @When("^the success message Customer added successfully should be displayed$")
    public void addCustomerSuccessMsg() {

        String actualSuccessMsg = customerFormPage.getAddCustomerSuccessMessageThenAccept();
        String expectedSuccessMsg = "Customer added successfully with customer id :";
        Assert.assertTrue(actualSuccessMsg.contains(expectedSuccessMsg));
    }
}
