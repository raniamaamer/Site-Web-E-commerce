package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object pour le checkout
 */
public class CheckoutPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Step 1: Information
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    
    // Step 2: Overview
    private By finishButton = By.id("finish");
    
    // Complete
    private By completeHeader = By.className("complete-header");
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isOnInformationPage() {
        try {
            wait.until(ExpectedConditions.urlContains("checkout-step-one"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isOnOverviewPage() {
        try {
            wait.until(ExpectedConditions.urlContains("checkout-step-two"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isOnCompletePage() {
        try {
            wait.until(ExpectedConditions.urlContains("checkout-complete"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void fillShippingInformation(String firstName, String lastName, String postalCode) {
        System.out.println("Remplissage des informations de livraison");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        
        driver.findElement(postalCodeField).clear();
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }
    
    public void clickContinue() {
        System.out.println("Clic sur Continue");
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        driver.findElement(continueButton).click();
    }
    
    public void finishOrder() {
        System.out.println("Finalisation de la commande");
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        driver.findElement(finishButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
    }
    
    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        return driver.findElement(completeHeader).getText();
    }
    
    public boolean isOrderComplete() {
        try {
            String message = getConfirmationMessage();
            return message.toLowerCase().contains("thank you") || 
                   message.toLowerCase().contains("complete");
        } catch (Exception e) {
            return false;
        }
    }
}