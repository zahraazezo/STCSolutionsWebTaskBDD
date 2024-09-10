package org.stc.pages;

import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.stc.EnumConstants;
import utils.SeleniumUtilities;

import java.io.IOException;

public class OpenAccountPage {
    private WebDriver driver;
    public static String selectedCustomerFirstName;

    private By customerSelect = By.id("userSelect");
    private By currencySelect = By.id("currency");
    private By processBtn = By.cssSelector("button[type='submit']");
    private By homeBtn = By.cssSelector("button[ng-click='home()']");


    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public OpenAccountPage selectCustomer(String customer) {
        SeleniumUtilities.waitForCondition(driver, customerSelect, EnumConstants.WaitConditions.VISIBLE, "Bank Manager Login Button");
        SeleniumUtilities.selectValue(driver, customerSelect, customer);
        selectedCustomerFirstName = customer.substring(0);
        return this;
    }

    public OpenAccountPage selectCurrency(String currency) throws CsvValidationException, IOException {
        SeleniumUtilities.waitForCondition(driver, currencySelect, EnumConstants.WaitConditions.VISIBLE, "Bank Manager Login Button");
        SeleniumUtilities.selectValue(driver, currencySelect, currency);
        return this;
    }

    public OpenAccountPage process() {
        SeleniumUtilities.waitForCondition(driver, processBtn, EnumConstants.WaitConditions.VISIBLE, "Bank Manager Login Button");
        SeleniumUtilities.pressElement(driver, processBtn, "Process Button");
        return this;
    }

    public String getSucessAlertMsgThenAccept() {
        return SeleniumUtilities.getAllertMsgThenAccept(driver);
    }

    //TODO: Move this to common step class
    public void goHome() {
        SeleniumUtilities.waitForCondition(driver, homeBtn, EnumConstants.WaitConditions.VISIBLE, "Home Button");
        SeleniumUtilities.pressElement(driver, homeBtn, "Home Button");
    }

    public void setCustomerAccountNumber() {
        String[] parts = getSucessAlertMsgThenAccept().split(":");
        String accountNum = "";
        if (parts.length >= 2) {
            // Trim any extra spaces and return the second part
            accountNum = parts[1].trim();

            AppContext.getInstance().setCustomerName(accountNum);
        }
    }
}
