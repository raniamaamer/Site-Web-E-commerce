package com.rania.ecommerce.tests;

import com.rania.ecommerce.base.BaseTest;
import com.rania.ecommerce.pages.HomePage;
import com.rania.ecommerce.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Test de recherche - UN SEUL TEST ACTIF
 */
public class SearchTest extends BaseTest {
    
    @Test(description = "Test simple : Affichage des produits")
    public void testProductsDisplay() {
        System.out.println("\n========================================");
        System.out.println("   TEST : AFFICHAGE DES PRODUITS");
        System.out.println("========================================\n");
        
        // Étape 1 : Connexion
        HomePage homePage = new HomePage(driver);
        homePage.login("standard_user", "secret_sauce");
        System.out.println("✓ Connexion réussie");
        
        // Étape 2 : Vérification de la page
        SearchResultsPage searchPage = new SearchResultsPage(driver);
        Assert.assertTrue(searchPage.isPageLoaded(), 
            "La page des produits ne s'est pas chargée");
        System.out.println("✓ Page des produits chargée");
        
        // Étape 3 : Compter les produits
        int productCount = searchPage.getProductCount();
        Assert.assertTrue(productCount > 0, 
            "Aucun produit n'est affiché");
        System.out.println("✓ Nombre de produits : " + productCount);
        
        // Étape 4 : Lister les produits
        List<String> productNames = searchPage.getAllProductNames();
        Assert.assertFalse(productNames.isEmpty(), 
            "La liste des produits est vide");
        
        System.out.println("\n✓ Produits disponibles :");
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + productNames.get(i));
        }
        
        // Attendre 2 secondes pour voir le résultat
        waitFor(2000);
        
        System.out.println("\n========================================");
        System.out.println("   ✓✓✓ TEST RÉUSSI ✓✓✓");
        System.out.println("========================================\n");
    }
}