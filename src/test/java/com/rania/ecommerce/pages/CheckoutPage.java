package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    // SELECTEURS — adapter aux champs du site
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By email = By.id("email");
    private By address = By.id("address");
    private By city = By.id("city");
    private By postal = By.id("postal");
    private By cardNumber = By.id("card-number");
    private By cardExpiry = By.id("card-expiry");
    private By cardCvv = By.id("card-cvv");
    private By placeOrderBtn = By.cssSelector("button.place-order");
    private By orderConfirmationText = By.cssSelector(".order-confirmation");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillShipping(String fName, String lName, String emailVal, String addr, String cityVal, String postalVal) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(email).sendKeys(emailVal);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(city).sendKeys(cityVal);
        driver.findElement(postal).sendKeys(postalVal);
    }

    public void fillPayment(String cardNum, String expiry, String cvv) {
        driver.findElement(cardNumber).sendKeys(cardNum);
        driver.findElement(cardExpiry).sendKeys(expiry);
        driver.findElement(cardCvv).sendKeys(cvv);
    }

    public void placeOrder() {
        driver.findElement(placeOrderBtn).click();
    }

    public boolean isOrderConfirmed() {
        return driver.findElements(orderConfirmationText).size() > 0;
    }
}
