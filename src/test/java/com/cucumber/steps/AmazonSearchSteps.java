package com.cucumber.steps;


import com.cucumber.pages.HomePage;
import com.cucumber.pages.SearchResultPage;
import com.cucumber.utils.BrowserManager;
import com.cucumber.utils.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AmazonSearchSteps {

    private static final Logger logger = LoggerFactory.getLogger(SauceDemoSteps.class);

    private WebDriver driver;
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    public AmazonSearchSteps() {
        // Initialize WebDriver
        logger.info("------ Started Browser ------ ");
        this.driver = BrowserManager.getDriver(BrowserManager.BrowserType.CHROME);

        // Initialize page objects
        this.homePage = new HomePage(driver);
        this.searchResultPage = new SearchResultPage(driver);
    }

    @Given("^User is on Amazon.co.uk homepage$")
    public void userIsOnAmazonHomepage() {
        logger.info("------ Open Appliation ------ ");
        homePage.navigateToHomePage();

        homePage.handleCaptcha();

        logger.info("------ Click Decline Button ------ ");
        homePage.clickDeclineButton();


    }

    @When("^User click on All Menu$")
    public void userClickOnAllMenu() {
        TestUtils.waitFor(300);
        homePage.clickOnAllMenu();
    }

    @When("^User navigates to Electronics and Computers Phones and Accessories > Mobile Phones$")
    public void userNavigatesToMobilePhones() {
        homePage.navigateToMobilePhonesCategory();
    }

    @When("^User applies filters for Camera Resolution (.+), Model Year (.+), and Price Range (.+)$")
    public void userAppliesFilters(String cameraResolution, String modelYear, String priceRange) {
        homePage.applyFilters(cameraResolution, modelYear, priceRange);
    }

    @Then("^User should see Samsung phones with the specified specifications$")
    public void userShouldSeeSamsungPhones() {
        // Perform search and verify Samsung phones
        searchResultPage.verifySamsungPhones();
    }

    @Then("^User verifies the details of each Samsung phone$")
    public void userVerifiesDetailsOfSamsungPhones() {
        // Verify details of each Samsung phone
        searchResultPage.verifySamsungPhoneDetails();
    }


    public void tearDown() {
        // Quit WebDriver after test execution
        BrowserManager.quitDriver();
    }

}
