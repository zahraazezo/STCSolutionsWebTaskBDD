package org.stc.pages;

import com.google.common.collect.Ordering;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.stc.EnumConstants;
import utils.SeleniumUtilities;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersListPage {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(CustomersListPage.class.getName());

    private By viewCustomerTableBtn = By.cssSelector("button[ng-click='showCust()']");
    private By customersTable = By.cssSelector("table");
    private By tableRows = By.cssSelector("table tbody tr");
    private By postCodeLink = By.xpath("//a[contains(@ng-click, 'postCd')]");
    private By searchCustomerInput = By.cssSelector("input[ng-model='searchCustomer']");

    public CustomersListPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCustomersCount() {
        SeleniumUtilities.waitForCondition(driver, tableRows, EnumConstants.WaitConditions.VISIBLE, "Bank Manager Login Button");
        return driver.findElements(tableRows).size();
    }

    //TODO : make it general e.g sorting by postal code or Last Name , etc.
    public boolean sortCustomersByPostCode(EnumConstants.SortOrder sortOrder) {
        SeleniumUtilities.waitForCondition(driver, postCodeLink, EnumConstants.WaitConditions.VISIBLE, "Post Code Link");

        String action = sortOrder == EnumConstants.SortOrder.ASCENDING ? "ascending" : "descending";
        log.info("Sorting customers by Post Code in " + action + " order");
        SeleniumUtilities.pressElement(driver, postCodeLink, "Post Code Link");

        Iterable<String> columnValues = SeleniumUtilities.getColumnCells(driver, "Post Code", customersTable);

        // Determine whether to sort 4 = "E55555"in ascending or descending order based on the enum value
        boolean isSortedCorrectly = sortOrder == EnumConstants.SortOrder.ASCENDING
                ? Ordering.natural().isOrdered(columnValues)
                : Ordering.natural().reverse().isOrdered(columnValues);

        if (!isSortedCorrectly) {
            log.info("Toggling sorting order to achieve " + action + " order");
            SeleniumUtilities.pressElement(driver, postCodeLink, "Post Code Link");
        }

        // Re-check if the sort order is correct after toggling
        columnValues = SeleniumUtilities.getColumnCells(driver, "Post Code", customersTable);
        return sortOrder == EnumConstants.SortOrder.ASCENDING
                ? Ordering.natural().isOrdered(columnValues)
                : Ordering.natural().reverse().isOrdered(columnValues);
    }

    public void searchForCustomerByFirstName(String firstName) {
        SeleniumUtilities.waitForCondition(driver, searchCustomerInput, EnumConstants.WaitConditions.VISIBLE, "Bank Manager Login Button");
        log.info("Searching for customer with first name: " + firstName);
        SeleniumUtilities.type(driver, searchCustomerInput, firstName, "First Name Textbox");
    }

    public List<String> getDisplayedCustomers() {
        return SeleniumUtilities.getDisplayedTableAllRows(driver, customersTable);
    }
}
