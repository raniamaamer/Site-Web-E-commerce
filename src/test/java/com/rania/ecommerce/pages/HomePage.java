package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By productList = By.className("inventory_item");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLoggedIn() {
        // Vérifie si la liste de produits est visible
        return driver.findElements(productList).size() > 0;
    }

    public void search(String keyword) {
        // Saucedemo ne possède pas de barre de recherche, donc pour le test
        // vous pouvez simplement filtrer par produit directement
    }
}
