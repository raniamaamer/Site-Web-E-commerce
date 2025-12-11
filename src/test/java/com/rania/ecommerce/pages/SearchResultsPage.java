package com.rania.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object pour les résultats de recherche et liste de produits
 */
public class SearchResultsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By productContainer = By.className("inventory_list");
    private By productItems = By.className("inventory_item");
    private By productNames = By.className("inventory_item_name");
    private By productDescriptions = By.className("inventory_item_desc");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By addToCartButtons = By.cssSelector("button[id^='add-to-cart']");
    
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productContainer));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<WebElement> getAllProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItems));
        return driver.findElements(productItems);
    }
    
    public int getProductCount() {
        return getAllProducts().size();
    }
    
    public List<String> getAllProductNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNames));
        List<WebElement> nameElements = driver.findElements(productNames);
        return nameElements.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }
    
    public List<String> getSearchResults(String keyword) {
        List<WebElement> matchingProducts = searchProductsByKeyword(keyword);
        return matchingProducts.stream()
            .map(product -> product.findElement(By.className("inventory_item_name")).getText())
            .collect(Collectors.toList());
    }
    
    private List<WebElement> searchProductsByKeyword(String keyword) {
        System.out.println("Recherche de produits contenant : " + keyword);
        List<WebElement> allProducts = getAllProducts();
        
        return allProducts.stream()
            .filter(product -> {
                String name = product.findElement(By.className("inventory_item_name")).getText();
                String desc = product.findElement(By.className("inventory_item_desc")).getText();
                return name.toLowerCase().contains(keyword.toLowerCase()) || 
                       desc.toLowerCase().contains(keyword.toLowerCase());
            })
            .collect(Collectors.toList());
    }
    
    public boolean isProductDisplayed(String productName) {
        List<String> names = getAllProductNames();
        return names.stream()
            .anyMatch(name -> name.equalsIgnoreCase(productName));
    }
    
    public boolean areResultsRelevant(String keyword) {
        List<WebElement> products = getAllProducts();
        
        for (WebElement product : products) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            String desc = product.findElement(By.className("inventory_item_desc")).getText();
            
            if (name.toLowerCase().contains(keyword.toLowerCase()) || 
                desc.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public void sortProducts(String sortOption) {
        System.out.println("Tri des produits par : " + sortOption);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue(sortOption);
        waitFor(1000);
    }
    
    public boolean areProductsSortedByNameAsc() {
        List<String> names = getAllProductNames();
        List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());
        return names.equals(sortedNames);
    }
    
    public boolean areProductsSortedByNameDesc() {
        List<String> names = getAllProductNames();
        List<String> sortedNames = names.stream()
            .sorted((a, b) -> b.compareTo(a))
            .collect(Collectors.toList());
        return names.equals(sortedNames);
    }
    
    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        return priceElements.stream()
            .map(element -> {
                String priceText = element.getText().replace("$", "");
                return Double.parseDouble(priceText);
            })
            .collect(Collectors.toList());
    }
    
    public boolean areProductsSortedByPriceAsc() {
        List<Double> prices = getAllProductPrices();
        List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
        return prices.equals(sortedPrices);
    }
    
    public boolean areProductsSortedByPriceDesc() {
        List<Double> prices = getAllProductPrices();
        List<Double> sortedPrices = prices.stream()
            .sorted((a, b) -> Double.compare(b, a))
            .collect(Collectors.toList());
        return prices.equals(sortedPrices);
    }
    
    public void clickProductByName(String productName) {
        System.out.println("Clic sur le produit : " + productName);
        By productLink = By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(productLink));
        driver.findElement(productLink).click();
    }
    
    public ProductDetails getProductDetails(String productName) {
        List<WebElement> products = getAllProducts();
        
        for (WebElement product : products) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                String description = product.findElement(By.className("inventory_item_desc")).getText();
                String price = product.findElement(By.className("inventory_item_price")).getText();
                return new ProductDetails(name, description, price);
            }
        }
        return null;
    }
    
    public List<String> filterProductsByPriceRange(double minPrice, double maxPrice) {
        System.out.println("Filtrage des produits entre $" + minPrice + " et $" + maxPrice);
        List<WebElement> allProducts = getAllProducts();
        
        return allProducts.stream()
            .filter(product -> {
                String priceText = product.findElement(By.className("inventory_item_price"))
                    .getText().replace("$", "");
                double price = Double.parseDouble(priceText);
                return price >= minPrice && price <= maxPrice;
            })
            .map(product -> product.findElement(By.className("inventory_item_name")).getText())
            .collect(Collectors.toList());
    }
    
    public void addFirstProductToCart() {
        System.out.println("Ajout du premier produit au panier");
        List<WebElement> addButtons = driver.findElements(addToCartButtons);
        if (!addButtons.isEmpty()) {
            addButtons.get(0).click();
        }
    }
    
    private void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Classe interne pour les détails d'un produit
     */
    public static class ProductDetails {
        private String name;
        private String description;
        private String price;
        
        public ProductDetails(String name, String description, String price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }
        
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getPrice() { return price; }
        
        @Override
        public String toString() {
            return "Product: " + name + ", Price: " + price;
        }
    }
}