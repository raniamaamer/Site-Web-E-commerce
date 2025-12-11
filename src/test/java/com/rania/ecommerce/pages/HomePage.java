package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Page Object pour la page d'accueil
 */
public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By inventoryContainer = By.className("inventory_container");
    private By productItems = By.className("inventory_item");
    private By shoppingCartLink = By.className("shopping_cart_link");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(usernameField),
                ExpectedConditions.visibilityOfElementLocated(inventoryContainer)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void login(String username, String password) {
        System.out.println("Connexion avec : " + username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
    }
    
    public List<WebElement> getProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItems));
        return driver.findElements(productItems);
    }
    
    public int getProductCount() {
        return getProducts().size();
    }
    
    public String getCartItemCount() {
        try {
            By cartBadge = By.className("shopping_cart_badge");
            WebElement badge = driver.findElement(cartBadge);
            return badge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public void goToCart() {
        System.out.println("Navigation vers le panier");
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        driver.findElement(shoppingCartLink).click();
    }
    
    public void addProductToCartByIndex(int index) {
        List<WebElement> products = getProducts();
        if (index < products.size()) {
            WebElement product = products.get(index);
            WebElement addToCartBtn = product.findElement(By.cssSelector("button[id^='add-to-cart']"));
            String productName = product.findElement(By.className("inventory_item_name")).getText();
            System.out.println("Ajout au panier : " + productName);
            addToCartBtn.click();
        }
    }
}