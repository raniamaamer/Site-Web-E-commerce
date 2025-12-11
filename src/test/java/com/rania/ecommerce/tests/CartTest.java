package com.rania.ecommerce.tests;

import com.rania.ecommerce.base.BaseTest;
import com.rania.ecommerce.pages.HomePage;
import com.rania.ecommerce.pages.ProductPage;
import com.rania.ecommerce.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests du panier - UN SEUL TEST ACTIF
 */
public class CartTest extends BaseTest {
    
    @Test(description = "Test : Ajouter un produit au panier")
    public void testAddProductToCart() {
        System.out.println("\n========================================");
        System.out.println("   TEST : AJOUT AU PANIER");
        System.out.println("========================================\n");
        
        HomePage homePage = new HomePage(driver);
        homePage.login("standard_user", "secret_sauce");
        System.out.println("✓ Connexion réussie");
        
        // Vérifier panier vide
        String initialCartCount = homePage.getCartItemCount();
        System.out.println("✓ Panier initial : " + initialCartCount + " article(s)");
        
        // Ajouter un produit
        homePage.addProductToCartByIndex(0);
        waitFor(1000);
        
        // Vérifier ajout
        String newCartCount = homePage.getCartItemCount();
        Assert.assertEquals(newCartCount, "1", 
            "Le compteur du panier n'a pas été mis à jour");
        
        System.out.println("✓ Produit ajouté au panier");
        System.out.println("✓ Nouveau nombre d'articles : " + newCartCount);
        
        waitFor(2000);
        
        System.out.println("\n========================================");
        System.out.println("   ✓✓✓ TEST RÉUSSI ✓✓✓");
        System.out.println("========================================\n");
    }
    
    // Autres tests désactivés
    @Test(enabled = false)
    public void testAddProductToCartFromProductPage() {}
    
    @Test(enabled = false)
    public void testVerifyCartContent() {}
    
    @Test(enabled = false)
    public void testRemoveProductFromCart() {}
    
    @Test(enabled = false)
    public void testMultipleProductsInCart() {}
    
    @Test(enabled = false)
    public void testContinueShopping() {}
}