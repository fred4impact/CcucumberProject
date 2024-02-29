package com.cucumber.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDirectory = System.getProperty("user.dir") + "/screenshots/";
        Path path = Paths.get(screenshotDirectory);

        // Create the screenshots directory if it does not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String screenshotPath = screenshotDirectory + screenshotName + "_" + timestamp + ".png";

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), Paths.get(screenshotPath));
            System.out.println("Screenshot captured: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
