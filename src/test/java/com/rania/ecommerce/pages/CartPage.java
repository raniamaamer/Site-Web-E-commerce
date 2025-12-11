package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Page Object pour le panier
 */
public class CartPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By cartItemName = By.className("inventory_item_name");
    private By cartItemPrice = By.className("inventory_item_price");
    private By removeButtons = By.cssSelector("button[id^='remove']");
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains("cart"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getCartItemCount() {
        try {
            return driver.findElements(cartItems).size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }
    
    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }
    
    public List<String> getProductNames() {
        List<WebElement> items = getCartItems();
        return items.stream()
                .map(item -> item.findElement(cartItemName).getText())
                .toList();
    }
    
    public boolean isProductInCart(String productName) {
        List<String> names = getProductNames();
        return names.stream().anyMatch(name -> name.equals(productName));
    }
    
    public String getProductPrice(String productName) {
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            String name = item.findElement(cartItemName).getText();
            if (name.equals(productName)) {
                return item.findElement(cartItemPrice).getText();
            }
        }
        return null;
    }
    
    public void removeProductByIndex(int index) {
        List<WebElement> removeButtonsList = driver.findElements(removeButtons);
        if (index < removeButtonsList.size()) {
            System.out.println("Retrait du produit à l'index : " + index);
            removeButtonsList.get(index).click();
        }
    }
    
    public void removeProductByName(String productName) {
        System.out.println("Retrait du produit : " + productName);
        List<WebElement> items = getCartItems();
        for (WebElement item : items) {
            String name = item.findElement(cartItemName).getText();
            if (name.equals(productName)) {
                item.findElement(By.cssSelector("button[id^='remove']")).click();
                break;
            }
        }
    }
    
    public void continueShopping() {
        System.out.println("Continuer les achats");
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        driver.findElement(continueShoppingButton).click();
    }
    
    public void proceedToCheckout() {
        System.out.println("Procéder au paiement");
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
    }
}