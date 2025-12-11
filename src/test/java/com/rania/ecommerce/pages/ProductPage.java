package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;

    private By firstProduct = By.className("inventory_item_name");
    private By addToCartButton = By.cssSelector(".btn_inventory");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFirstProduct() {
        driver.findElements(firstProduct).get(0).click();
    }

    public String getProductName() {
        return driver.findElement(firstProduct).getText();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void goToCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }
}
