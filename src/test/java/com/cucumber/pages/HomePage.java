package com.cucumber.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class HomePage {

    private  WebDriver driver;
    private  WebDriverWait wait;

    private  By searchBar = By.id("twotabsearchtextbox");
    private  By searchButton = By.id("nav-search-submit-button");
    private  By categoryMenu = By.id("nav-hamburger-menu");
    private  By electronicsAndComputersLink = By.xpath("//div[@id='hmenu-content']//a[text()='Electronics & Computers']");
    private  By phonesAndAccessoriesLink = By.xpath("//a[contains(@href,'/mobile-phones')]");
    private  By mobilePhonesLink = By.xpath("//div[contains(@aria-label,'Mobile Phones')]/..//a[contains(@href,'/mobile-phones')]");
    private  By accept = By.linkText("Accept");
    private final By formDisplay = By.id("sp-cc");
    private final By declineButton = By.id("sp-cc-rejectall-button");

    private By captchaFrame = By.id("captchaFrame");
    private By captchaInput = By.id("captchaInput");
    private By captchaSubmitButton = By.id("captchaSubmitButton");



    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToHomePage() {
        driver.get("https://www.amazon.co.uk");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.titleContains("Amazon"));

        handleCaptcha();
        wait.until(ExpectedConditions.titleContains("Select your cookie preferences"));
        acceptAlertIfPresent();
        // move to form and click the declinen utton
        wait.until(ExpectedConditions.visibilityOfElementLocated(formDisplay));
    }

    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchInput.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void navigateToMobilePhonesCategory() {
        WebElement categoryMenuButton = wait.until(ExpectedConditions.elementToBeClickable(categoryMenu));
        categoryMenuButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(electronicsAndComputersLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(phonesAndAccessoriesLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobilePhonesLink)).click();
    }

    public void applyFilters(String cameraResolution, String modelYear, String priceRange) {
        setCameraResolutionFilter(cameraResolution);
        setModelYearFilter(modelYear);
        setPriceRangeFilter(priceRange);
        clickApplyFiltersButton();
    }

    public List<WebElement> getSamsungPhones() {
        return driver.findElements(By.xpath("//div[contains(@class,'samsung-phone')]"));
    }

    public void clickOnAllMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(categoryMenu)).click();
    }

    private void acceptAlertIfPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (Exception ignored) {
            System.out.println("No Alert Present");
        }
    }

    private boolean isCaptchaDisplayed() {
        try {
            return driver.findElement(captchaFrame).isDisplayed();
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    private void handleCaptcha() {
        if (isCaptchaDisplayed()) {
            WebElement captchaInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(captchaInput));
            captchaInputField.sendKeys("Your CAPTCHA solution here");

            wait.until(ExpectedConditions.elementToBeClickable(captchaSubmitButton)).click();
        }
    }

    private void setCameraResolutionFilter(String cameraResolution) {
        WebElement cameraResolutionFilter = driver.findElement(By.id("camera-resolution-filter"));
        cameraResolutionFilter.sendKeys(cameraResolution);
    }

    private void setModelYearFilter(String modelYear) {
        WebElement modelYearFilter = driver.findElement(By.id("model-year-filter"));
        modelYearFilter.sendKeys(modelYear);
    }

    private void setPriceRangeFilter(String priceRange) {
        WebElement priceRangeFilter = driver.findElement(By.id("price-range-filter"));
        priceRangeFilter.sendKeys(priceRange);
    }

    private void clickApplyFiltersButton() {
        WebElement applyFiltersButton = driver.findElement(By.id("apply-filters-button"));
        applyFiltersButton.click();
    }


    public void clickDeclineButton() {
        // Click on the decline button on the modal form

        WebElement declineBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(declineButton));
        declineBtn.click();
    }


}
