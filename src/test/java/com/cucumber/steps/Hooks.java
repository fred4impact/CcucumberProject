package com.cucumber.steps;

import com.cucumber.utils.BrowserManager;
import com.cucumber.utils.TestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Hooks {


    @Before
    public void start() {

            BrowserManager.getDriver(BrowserManager.BrowserType.CHROME); // Initialize the WebDriver here (e.g., using WebDriverManager)



    }


    @After
    public void end(Scenario scenario) {
        WebDriver driver = BrowserManager.getDriver(BrowserManager.BrowserType.CHROME); // Get the WebDriver instance
        byte[] picture;
        if (scenario.isFailed()) {
            // take screenshot and save it in /failed
            picture = TestUtils.takeScreenshot("failed/" + scenario.getName(), driver);
        } else {
            // take screenshot and put it in /passed folder
            picture = TestUtils.takeScreenshot("passed/" + scenario.getName(), driver);
        }

        scenario.attach(picture, "image/png", scenario.getName());

        BrowserManager.quitDriver();
    }

}