package com.cucumber.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SearchResultPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    public void verifySamsungPhones() {
    }

    public void verifySamsungPhoneDetails() {
    }
}
