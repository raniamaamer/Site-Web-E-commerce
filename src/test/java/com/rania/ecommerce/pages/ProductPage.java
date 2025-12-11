package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object pour la page produit
 */
public class ProductPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By productName = By.className("inventory_details_name");
    private By addToCartButton = By.cssSelector("button[id^='add-to-cart']");
    private By removeButton = By.cssSelector("button[id^='remove']");
    private By backToProductsButton = By.id("back-to-products");
    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        return driver.findElement(productName).getText();
    }
    
    public void addToCart() {
        System.out.println("Ajout du produit au panier");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
    }
    
    public void backToProducts() {
        System.out.println("Retour à la liste des produits");
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsButton));
        driver.findElement(backToProductsButton).click();
    }
}