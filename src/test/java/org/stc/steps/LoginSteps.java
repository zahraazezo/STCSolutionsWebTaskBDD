package org.stc.steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.stc.hooks.Hooks;
import org.stc.pages.LoginPage;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = Hooks.driver; // Assuming Hooks provides WebDriver instance
        this.loginPage = new LoginPage(driver);
    }

    @Given("^the (.*) is logged into the website$")
    public void userIsLoggedIntoTheWebsite(String role) {

        loginPage.loginAsRole(role);
    }
}
