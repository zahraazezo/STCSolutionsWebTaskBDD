package org.stc.steps;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.stc.EnumConstants;
import org.stc.hooks.Hooks;
import org.stc.pages.AppContext;
import org.stc.pages.CustomersListPage;
import org.testng.Assert;

import java.util.List;

public class CustomersListSteps {
    private WebDriver driver;
    private CustomersListPage customersListPage;
    private boolean isSorted;

    public CustomersListSteps() {
        this.driver = Hooks.driver;
        this.customersListPage = new CustomersListPage(driver);
    }

    @When("^the Bank Manager types the first name (.*) in the search field$")
    public void theBankManagerTypesFirstNameInSearchField(String firstName) {
        customersListPage.searchForCustomerByFirstName(firstName);
    }

    @When("^the account number of the last added account matches the generated account number$")
    public void verifyAccountNumberMatches() {
        List<String> displayedCustomers = customersListPage.getDisplayedCustomers();
        String accountNumber = AppContext.getInstance().getAccountNumber();
        boolean isAccountNumberFound = false;
        for (String customer : displayedCustomers) {
            String[] customerDetails = customer.split(" ");

            String customerAccountNumber = customerDetails[customerDetails.length - 2];

            if (customerAccountNumber.equals(accountNumber)) {
                isAccountNumberFound = true;
                break;
            }
        }
        Assert.assertTrue(isAccountNumberFound);
    }

    @When("^the Bank Manager clicks on the (.*) column to sort in (.*) order$")
    public void sortColumnInSpecifiedOrder(String sortColumn, EnumConstants.SortOrder sortOrder) {
        isSorted = customersListPage.sortCustomersByPostCode(sortOrder);
        Assert.assertTrue(isSorted, "Customers are not sorted in descending order");

    }

    @When("^the customers should be sorted by (.*) in (.*) order$")
    public void verifyLastAddedAccountNumberMatchesGenerated(String sortColumn, EnumConstants.SortOrder sortOrder) {
        Assert.assertTrue(isSorted, "Customers are not sorted in descending order");

    }
}



