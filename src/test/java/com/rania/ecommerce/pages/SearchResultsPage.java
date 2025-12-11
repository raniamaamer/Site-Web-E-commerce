package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private By productItems = By.cssSelector(".product-item"); // adapter
    private By productTitle = By.cssSelector(".product-title"); // adapter

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasResults() {
        List<WebElement> items = driver.findElements(productItems);
        return items.size() > 0;
    }

    // cliquer sur le premier produit
    public void openFirstProduct() {
        List<WebElement> items = driver.findElements(productItems);
        if (items.size() > 0) {
            items.get(0).click();
        } else {
            throw new RuntimeException("Aucun produit trouvé.");
        }
    }

    public String firstResultTitle() {
        List<WebElement> items = driver.findElements(productItems);
        if (items.size() > 0) {
            return items.get(0).findElement(productTitle).getText();
        } else return "";
    }
}