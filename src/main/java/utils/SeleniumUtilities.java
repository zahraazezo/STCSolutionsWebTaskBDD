package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stc.pages.AppContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.stc.EnumConstants.WaitConditions;

public class SeleniumUtilities {
    private static final Logger log = LogManager.getLogger(SeleniumUtilities.class.getName());
    public static WebDriverWait wait;
    public static Properties config = new Properties();

    public static void waitForCondition(WebDriver driver, By itemToWaitFor, WaitConditions condition, String controlName) {
        log.info("Waiting for condition: " + condition + " on element: " + controlName);
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/properties/prop.properties")) {
            config.load(fis);
        } catch (FileNotFoundException e) {
            log.error("Properties file not found: " + e.getMessage());
        } catch (IOException e) {
            log.error("Failed to load properties: " + e.getMessage());
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));


        try {
            long start = System.currentTimeMillis();

            switch (condition) {
                case EXIST:
                    wait.until(ExpectedConditions.presenceOfElementLocated(itemToWaitFor));
                    break;
                case NOT_EXIST:
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(itemToWaitFor));
                    break;
                case VISIBLE:
                    wait.until(ExpectedConditions.visibilityOfElementLocated(itemToWaitFor));
                    break;
                case INVISIBLE:
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(itemToWaitFor));
                    break;
                case ENABLED:
                    wait.until(ExpectedConditions.elementToBeClickable(itemToWaitFor));
                    break;
                case CLICKABLE:
                    wait.until(ExpectedConditions.elementToBeClickable(itemToWaitFor));
                    break;
                case FRAME_AVAILABILITY_AND_SWITCH_TO_IT:
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(itemToWaitFor));
                    break;
                case SELECT_LIST_LOADED:
                    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(itemToWaitFor, 1));
                    break;
                case PAGE_READY_STATE:
                    wait.until(driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete"));
                    break;
                case PRESENCE_OF_ALL_ELEMENTS_LOCATED_BY:
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemToWaitFor));
                    break;
                case VISIBILITY_OF_ALL_ELEMENTS_LOCATED_BY:
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemToWaitFor));
                    break;
                case ELEMENT_TO_BE_SELECTED:
                    wait.until(ExpectedConditions.elementToBeSelected(itemToWaitFor));
                    break;
                case ALERT_IS_PRESENT:
                    wait.until(ExpectedConditions.alertIsPresent());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown wait condition: " + condition);
            }

            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            log.info("Total time for condition " + condition + " on " + controlName + ": " + totalTime + "ms");
        } catch (Exception e) {
            log.error("Failed to wait for condition '" + condition + "' on element '" + controlName + "' due to: " + e.getMessage());
        }
    }


    // Method to clear and enter data in text box
    public static void type(WebDriver driver, By txt_ID, String txt_Value, String ControlName) {
        WebElement txt = driver.findElement(txt_ID);
        try {
            txt.clear();
            txt.sendKeys(txt_Value);
            log.info("The" + txt_Value + "value is typed in the " + "'" + ControlName + "'" + " text box");
        } catch (Exception e) {
            log.error("The " + txt_Value + " value isn't typed in the " + "'" + ControlName + "'" + " text box due to:"
                    + e.getMessage());
        }
    }

    // press element using javascript
    public static void pressElement(WebDriver driver, By btn_ID, String ctrlName) {

        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(btn_ID));
            log.info("The " + ctrlName + "was pressed successfully");
        } catch (Exception e) {

            log.error("The " + ctrlName + " wasn't pressed as " + e.getMessage());
        }
    }

    public static boolean isElementPresent(WebDriver driver, By by, String ControlName) {
        try {
            driver.findElement(by);
            log.info("'" + ControlName + "'" + " Element is Present");
            return true;
        } catch (NoSuchElementException e) {
            log.error("'" + ControlName + "'" + " Element is not Present due to " + e.getStackTrace());
            return false;
        }
    }

    public static String getAlertWindowText(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static void pressEnter(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
    }

    public static void selectValue(WebDriver driver, By element, String value) {
        Select drp = new Select(driver.findElement(element));
        drp.selectByVisibleText(value);
    }

    public static String getAllertMsgThenAccept(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        String[] parts = msg.split(":");
        String accountNum = "";
        if (parts.length >= 2) {
            // Trim any extra spaces and return the second part
            accountNum = parts[1].trim();

            AppContext.getInstance().setAccountNumber(accountNum);
        }
        AppContext.getInstance().setAlertMsg(msg);
        alert.accept();
        return msg;
    }

    // Method to double click on any button
    public static void ClickElement(WebDriver driver, By btn_ID, int numberOfClicks, String ctrlName) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(btn_ID);
        // Double click
        for (int i = 0; i < numberOfClicks; i++) {
            action.click(element).perform();
        }
    }

    public static List<WebElement> getColumnCellsElements(WebDriver driver, String columnName, By table) {

        List<String> headers = new ArrayList<>();
        List<String> columnValues = new ArrayList<>();

        List<WebElement> thList = driver.findElement(table).findElements(By.xpath("//thead//td"));

        thList.stream().forEach(th -> headers.add(th.getText()));
        int index = headers.indexOf(columnName);

        List<WebElement> trList = driver.findElement(table).findElements(By.xpath("//tr"));
        List<WebElement> tdList = new ArrayList<>();
        List<WebElement> columnCells = new ArrayList<>();

        WebElement cell = null;
        for (int i = 1; i < trList.size(); i++) {
            tdList = trList.get(i).findElements(By.tagName("td"));
            cell = tdList.get(index);
            columnCells.add(cell);
        }
        return columnCells;
    }

    public static List<String> getColumnCells(WebDriver driver, String columnName, By table) {
        List<String> headers = new ArrayList<>();
        List<String> columnValues = new ArrayList<>();

        List<WebElement> thList = driver.findElement(table).findElements(By.xpath("//thead//td"));

        thList.stream().forEach(th -> headers.add(th.getText()));
        int index = headers.indexOf(columnName);

        List<WebElement> trList = driver.findElement(table).findElements(By.xpath("//tr"));
        List<WebElement> tdList = new ArrayList<>();
        List<WebElement> columnCells = new ArrayList<>();

        WebElement cell = null;
        for (int i = 1; i < trList.size(); i++) {
            tdList = trList.get(i).findElements(By.tagName("td"));
            cell = tdList.get(index);
            columnCells.add(cell);
        }
        columnCells.stream().forEach(th -> columnValues.add(th.getText()));
        return columnValues;
    }

    public static List<String> getDisplayedTableAllRows(WebDriver driver, By table) {
        var tableBody = driver.findElement(table).findElement(By.tagName("tbody"));
        var rows = tableBody.findElements(By.tagName("tr"));
        List<String> cells = new ArrayList<>();
        rows.stream().forEach(cell -> cells.add(cell.getText()));
        return cells;
    }

    public static String getMaxValueOfList(List<String> list) {
        return Collections.max(list);
    }
}
