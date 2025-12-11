package com.rania.ecommerce.tests;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import com.rania.ecommerce.base.BaseTest;
import com.rania.ecommerce.pages.*;

public class E2ETest extends BaseTest {

    private HomePage home;
    private ProductPage product;
    private CartPage cart;
    private CheckoutPage checkout;

    @BeforeClass
    public void beforeClass() {
        setupDriver();
        home = new HomePage(driver);
        product = new ProductPage(driver);
        cart = new CartPage(driver);
        checkout = new CheckoutPage(driver);
    }

    @Test(priority = 0)
    public void testLogin() {
        home.open(props.getProperty("base.url"));
        home.login(props.getProperty("username"), props.getProperty("password"));
        assertTrue(home.isLoggedIn(), "Login échoué");
    }

    @Test(priority = 1)
    public void testAddProductToCart() {
        home.search(props.getProperty("search.keyword"));
        product.openFirstProduct();
        String productName = product.getProductName();
        product.addToCart();
        product.goToCart();
        assertTrue(cart.hasProduct(productName), "Le produit n'est pas dans le panier");
    }

    @Test(priority = 2)
    public void testCheckoutProcess() {
        cart.proceedToCheckout();
        checkout.fillShipping(
            props.getProperty("first.name"),
            props.getProperty("last.name"),
            props.getProperty("email"),
            props.getProperty("address"),
            props.getProperty("city"),
            props.getProperty("postal")
        );
        checkout.fillPayment(
            props.getProperty("card.number"),
            props.getProperty("card.expiry"),
            props.getProperty("card.cvv")
        );
        checkout.placeOrder();
        assertTrue(checkout.isOrderConfirmed(), "Commande non confirmée");
    }

    @AfterClass
    public void tearDown() {
        teardown();
    }
}
