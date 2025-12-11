package com.rania.ecommerce.tests;

import com.rania.ecommerce.base.BaseTest;
import com.rania.ecommerce.pages.HomePage;
import com.rania.ecommerce.pages.CartPage;
import com.rania.ecommerce.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests du checkout - UN SEUL TEST ACTIF
 */
public class CheckoutTest extends BaseTest {
    
    @Test(description = "Test : Processus complet de commande")
    public void testCompleteOrder() {
        System.out.println("\n========================================");
        System.out.println("   TEST : COMMANDE COMPLÈTE");
        System.out.println("========================================\n");
        
        // Étape 1 : Connexion
        HomePage homePage = new HomePage(driver);
        homePage.login("standard_user", "secret_sauce");
        System.out.println("✓ Étape 1 : Connexion réussie");
        
        // Étape 2 : Ajouter un produit
        homePage.addProductToCartByIndex(0);
        waitFor(1000);
        System.out.println("✓ Étape 2 : Produit ajouté au panier");
        
        // Étape 3 : Aller au panier
        homePage.goToCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageLoaded(), "Panier non chargé");
        System.out.println("✓ Étape 3 : Panier affiché");
        
        // Étape 4 : Checkout
        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isOnInformationPage(), 
            "Page checkout non chargée");
        System.out.println("✓ Étape 4 : Page de checkout affichée");
        
        // Étape 5 : Informations
        checkoutPage.fillShippingInformation("Rania", "Test", "12345");
        checkoutPage.clickContinue();
        System.out.println("✓ Étape 5 : Informations remplies");
        
        // Étape 6 : Finaliser
        Assert.assertTrue(checkoutPage.isOnOverviewPage(), 
            "Page révision non affichée");
        checkoutPage.finishOrder();
        System.out.println("✓ Étape 6 : Commande finalisée");
        
        // Étape 7 : Confirmation
        Assert.assertTrue(checkoutPage.isOrderComplete(), 
            "Commande non confirmée");
        String message = checkoutPage.getConfirmationMessage();
        System.out.println("✓ Étape 7 : " + message);
        
        waitFor(2000);
        
        System.out.println("\n========================================");
        System.out.println("   ✓✓✓ TEST RÉUSSI ✓✓✓");
        System.out.println("========================================\n");
    }
    
    // Autres tests désactivés
    @Test(enabled = false)
    public void testAccessCheckoutPage() {}
    
    @Test(enabled = false)
    public void testFillShippingInformation() {}
    
    @Test(enabled = false)
    public void testOrderOverview() {}
    
    @Test(enabled = false)
    public void testCancelCheckout() {}
    
    @Test(enabled = false)
    public void testCompleteCheckoutWorkflow() {}
}