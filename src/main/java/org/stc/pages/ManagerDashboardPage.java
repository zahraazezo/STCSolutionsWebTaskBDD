package org.stc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.stc.EnumConstants;
import utils.SeleniumUtilities;

import static utils.SeleniumUtilities.wait;

public class ManagerDashboardPage {
    private WebDriver driver;
    private By viewAddCustomerFormBtn = By.cssSelector("button[ng-click='addCust()']");
    private By viewOpenAccountFormBtn = By.cssSelector("button[ng-click='openAccount()']");
    private By viewAddCustomerListBtn = By.cssSelector("button[ng-click='showCust()']");

    public ManagerDashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void performAction(EnumConstants.ManagerActions action) {
        switch (action) {
            case ADD_CUSTOMER:
                SeleniumUtilities.waitForCondition(driver, viewAddCustomerFormBtn, EnumConstants.WaitConditions.VISIBLE, "View Add Customer Form Button");
                SeleniumUtilities.pressElement(driver, viewAddCustomerFormBtn, "View Add Customer Form Button");
                break;

            case VIEW_CUSTOMER_LIST:
                SeleniumUtilities.waitForCondition(driver, viewAddCustomerListBtn, EnumConstants.WaitConditions.VISIBLE, "View Customer List Button");
                SeleniumUtilities.pressElement(driver, viewAddCustomerListBtn, "View Customer List Button");
                break;

            case OPEN_ACCOUNT:
                SeleniumUtilities.waitForCondition(driver, viewOpenAccountFormBtn, EnumConstants.WaitConditions.VISIBLE, "View Open Account Form Button");
                SeleniumUtilities.pressElement(driver, viewOpenAccountFormBtn, "View Open Account Form Button");
                break;

            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }
}
