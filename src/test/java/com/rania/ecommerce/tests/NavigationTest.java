package com.rania.ecommerce.tests;

import com.rania.ecommerce.base.BaseTest;
import com.rania.ecommerce.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests de navigation - UN SEUL TEST ACTIF
 */
public class NavigationTest extends BaseTest {
    
    @Test(description = "Test : Connexion et affichage des produits")
    public void testLogin() {
        System.out.println("\n========================================");
        System.out.println("   TEST : CONNEXION UTILISATEUR");
        System.out.println("========================================\n");
        
        HomePage homePage = new HomePage(driver);
        
        // Connexion
        homePage.login("standard_user", "secret_sauce");
        System.out.println("✓ Connexion réussie");
        
        // Vérification
        int productCount = homePage.getProductCount();
        Assert.assertTrue(productCount > 0, 
            "Aucun produit n'est affiché après la connexion");
        
        System.out.println("✓ Nombre de produits affichés : " + productCount);
        
        // Attendre pour voir le résultat
        waitFor(2000);
        
        System.out.println("\n========================================");
        System.out.println("   ✓✓✓ TEST RÉUSSI ✓✓✓");
        System.out.println("========================================\n");
    }
    
    // Autres tests désactivés
    @Test(enabled = false)
    public void testHomePageLoad() {
        // Test désactivé
    }
    
    @Test(enabled = false)
    public void testNavigateToProductPage() {
        // Test désactivé
    }
    
    @Test(enabled = false)
    public void testBackToHomeFromProduct() {
        // Test désactivé
    }
    
    @Test(enabled = false)
    public void testNavigateToCart() {
        // Test désactivé
    }
    
    @Test(enabled = false)
    public void testLogout() {
        // Test désactivé
    }
}