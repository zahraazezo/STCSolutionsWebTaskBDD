package org.stc.steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.stc.EnumConstants;
import org.stc.hooks.Hooks;
import org.stc.pages.ManagerDashboardPage;

public class ManagerDashboardSteps {
    private WebDriver driver;
    private ManagerDashboardPage managerDashboardPage;

    public ManagerDashboardSteps() {
        this.driver = Hooks.driver; // Assuming Hooks provides WebDriver instance
        this.managerDashboardPage = new ManagerDashboardPage(driver);
    }

    @Given("^the manager clicks on (.*) button in the manager actions section$")
    public void clickAddCustomerButton(EnumConstants.ManagerActions managerAction) {
        managerDashboardPage.performAction(managerAction);
    }
}
