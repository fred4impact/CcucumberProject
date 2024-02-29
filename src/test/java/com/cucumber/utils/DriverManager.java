package com.cucumber.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Set up the WebDriver instance
//            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
            WebDriverManager.chromedriver().setup();
            // Configure Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized"); // Maximize the browser window
            options.addArguments("--disable-infobars"); // Disable the info bar
            options.addArguments("--disable-notifications"); // Disable notifications
            options.addArguments("--disable-extensions");
            options.addArguments("--incognito");// Disable browser extensions

            // Create a new ChromeDriver instance
            driver = new ChromeDriver(options);

        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }



}
