package com.cucumber.steps;


import com.cucumber.SauceObjectPages.IndexPage;
import com.cucumber.SauceObjectPages.ProductPage;
import com.cucumber.utils.BrowserManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class SauceDemoSteps {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(SauceDemoSteps.class);


    private WebDriver driver;
    private BrowserManager browserManager;
    private IndexPage page;
    private ProductPage productPage;



    @Before
    public void setUp() {

        driver = BrowserManager.getDriver(BrowserManager.BrowserType.CHROME);
        logger.info("------ Started Browser ------ ");
        page = new IndexPage(driver);
        productPage = new ProductPage(driver);
    }


    @Given("User navigates to {string}")
    public void userNavigatesTo(String url) {

        page = new IndexPage(driver);
        page.navigateTo(url);
        logger.info("------ Open Application ------ ");

    }

    @When("User logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        page.login(username, password);
    }

    @Then("User searches for a product {string}")
    public void userSearchesForAPsaudemoroduct(String product) {
       // productPage.userSearchesForAProduct(product);
    }

    @Then("User validates the number of products displayed")
    public void userValidatesTheNumberOfProductsDisplayed() {
        productPage = new ProductPage(driver);
        int productCount = productPage.getProductCount();
        // Example: Assertion for product count
        assertEquals(6, productCount); // Just an example, actual count depends on the website
        BrowserManager.quitDriver();
    }

    public void userValidatesTheNumberOfProductsDisplayed(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String username = row.get("username");
            String password = row.get("password");
            String product = row.get("product");
            page.login(username, password);

            // Implement product search and validation
            System.out.println("Validating product count for user: " + username + ", product: " + product);
            productPage = new ProductPage(driver);
            int expectedCount = Integer.parseInt(row.get("expectedCount")); // Get expected count from DataTable
            int actualCount = productPage.getProductCount(); // Get actual count
            List<String> productNames = productPage.getProductNames(); // Get product names

            // Assertion for product count
            assertEquals(expectedCount, actualCount);

            // Display product count
            System.out.println("Number of products displayed: " + actualCount);

            // Display product names
            System.out.println("Product names:");
            for (String productName : productNames) {
                System.out.println(productName);
            }
        }

        logger.debug("------ Starting ------");
        BrowserManager.quitDriver();
    }




}
