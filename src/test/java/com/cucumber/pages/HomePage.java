package com.cucumber.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBar;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "nav-hamburger-menu")
    private WebElement categoryMenu;

    @FindBy(xpath = "//div[@id='hmenu-content']//a[text()='Electronics & Computers']")
    private WebElement electronicsAndComputersLink;

    @FindBy(xpath = "//a[contains(@href,'/mobile-phones')]")
    private WebElement phonesAndAccessoriesLink;

    @FindBy(xpath = "//div[contains(@aria-label,'Mobile Phones')]/..//a[contains(@href,'/mobile-phones')]")
    private WebElement mobilePhonesLink;

    @FindBy(linkText = "Accept")
    private WebElement accept;

    @FindBy(id = "sp-cc")
    private WebElement formDisplay;

    @FindBy(id = "sp-cc-rejectall-button")
    private WebElement declineButton;

    @FindBy(id = "captchaFrame")
    private WebElement captchaFrame;

    @FindBy(id = "captchaInput")
    private WebElement captchaInput;

    @FindBy(id = "captchaSubmitButton")
    private WebElement captchaSubmitButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://www.amazon.co.uk");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.titleContains("Amazon"));
        handleCaptcha();
        wait.until(ExpectedConditions.titleContains("Select your cookie preferences"));
        acceptAlertIfPresent();
        // move to form and click the decline button
        wait.until(ExpectedConditions.visibilityOf(formDisplay));
    }

    public void searchProduct(String productName) {
        searchBar.sendKeys(productName);
        searchButton.click();
    }

    public void navigateToMobilePhonesCategory() {
        categoryMenu.click();
        electronicsAndComputersLink.click();
        phonesAndAccessoriesLink.click();
        mobilePhonesLink.click();
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
        categoryMenu.click();
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
            return captchaFrame.isDisplayed();
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public void handleCaptcha() {
        if (isCaptchaDisplayed()) {
            captchaInput.sendKeys("Your CAPTCHA solution here");
            captchaSubmitButton.click();
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
        WebElement declineBtn = wait.until(ExpectedConditions.visibilityOf(declineButton));
        declineBtn.click();
    }




}
