package com.cucumber.utils;



import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class TestUtils  {
  private static WebDriver driver;

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }

    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(message, condition);
    }

    public static void log(String message) {
        System.out.println(message);
    }


    public static byte[] takeScreenshot(String filename, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
        String destination = BrowserManager.getScreenshotFilePath() + filename + getTimeStamp() + ".png";

        try {
            FileUtils.copyFile(screenshotFile, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return picBytes;
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().toString().replace(":", "-");
    }
}
