package org.stc.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class.getName());
    public static WebDriver driver;
    private static Properties config = new Properties();

    @Before
    public void setUp() {
        log.info("Setting up driver ...");

        // Load properties file
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/properties/prop.properties")) {
            config.load(fis);
        } catch (IOException e) {
            log.error("Failed to load properties: " + e.getMessage());
        }

        String browser = config.getProperty("browser");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");
            log.info("Launching Chrome browser ...");
            driver = new ChromeDriver(options);
        } else {
            log.error("Browser not supported: " + browser);
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get(config.getProperty("testSitUrl"));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Closed all browser windows.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}