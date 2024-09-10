package org.stc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.stc.EnumConstants;
import utils.SeleniumUtilities;

public class LoginPage {
    private WebDriver driver;
    private By bankManagerLoginButton = By.cssSelector("button[ng-click='manager()']");
    private By customerLoginButton = By.cssSelector("button[ng-click='customer()']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public  void loginAsRole(String role)
    {
        if (role.equalsIgnoreCase(EnumConstants.Roles.MANAGER.toString()) ) {
            SeleniumUtilities.waitForCondition(driver, bankManagerLoginButton, EnumConstants.WaitConditions.VISIBLE, "Bank Manager Login Button");
            driver.findElement(bankManagerLoginButton).click();
        } else if (role.equalsIgnoreCase(EnumConstants.Roles.CUSTOMER.toString())) {
            SeleniumUtilities.waitForCondition(driver, customerLoginButton, EnumConstants.WaitConditions.VISIBLE, "Customer Login Button");
            driver.findElement(customerLoginButton).click();
        }
    }
}
