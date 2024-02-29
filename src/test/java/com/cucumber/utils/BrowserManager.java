package com.cucumber.utils;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class BrowserManager { private static WebDriver driver;


    @Before
    public static WebDriver getDriver(BrowserType browserType) {
        if (driver == null) {
            switch (browserType) {
                case CHROME -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--start-maximized"); // Maximize the browser window
                    chromeOptions.addArguments("--disable-infobars"); // Disable the info bar
                    chromeOptions.addArguments("--disable-notifications"); // Disable notifications
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--incognito");// Disable browser extensions
                    driver = new ChromeDriver(chromeOptions);
                }
                case FIREFOX -> {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized"); // Maximize the browser window
                    firefoxOptions.addArguments("--disable-infobars"); // Disable the info bar
                    firefoxOptions.addArguments("--disable-notifications"); // Disable notifications
                    firefoxOptions.addArguments("--disable-extensions"); // Disable extensions

                    //firefoxOptions.addArguments("-private"); // Open a new private browsing window (incognito mode)
                    driver = new FirefoxDriver(firefoxOptions);
                }
            }
        }
        return driver;
    }



    @After
    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }



    public enum BrowserType {
        CHROME,
        FIREFOX
    }




}
