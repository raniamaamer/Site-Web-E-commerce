package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By cartItems = By.cssSelector(".cart-item"); // adapter
    private By checkoutButton = By.cssSelector("button.checkout"); // adapter

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasProduct(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
