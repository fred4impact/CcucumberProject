package com.cucumber.SauceObjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {

    private WebDriver driver;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getProductCount() {
        return productNames.size();
    }

    public List<String> getProductNames() {
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productElements) {
            String productName = element.getText();
            productNames.add(productName);
            System.out.println("Product name: " + productName); // Log product name to console
        }
        return productNames;
    }


    // Method to search for a product
    public void userSearchesForAProduct(String product) {
        // Assuming the product search field has id "search" on the page
        driver.findElement(By.id("search")).sendKeys(product);

        // Assuming the search button has id "search-button" on the page
        driver.findElement(By.id("search-button")).click();
    }
}
