package com.rania.ecommerce.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

/**
 * Classe de base pour tous les tests
 * Gère l'initialisation et la fermeture du WebDriver
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected String baseUrl = "https://www.saucedemo.com";
    
    /**
     * Configuration avant chaque test
     * @param browser Type de navigateur (chrome, firefox)
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        System.out.println("Initialisation du test avec le navigateur : " + browser);
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                // Décommentez la ligne suivante pour le mode headless (sans interface)
                // chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;
                
            default:
                throw new IllegalArgumentException("Navigateur non supporté : " + browser);
        }
        
        // Configuration des timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        // Navigation vers l'URL de base
        driver.get(baseUrl);
        System.out.println("Navigation vers : " + baseUrl);
    }
    
    /**
     * Nettoyage après chaque test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Fermeture du navigateur");
            driver.quit();
        }
    }
    
    /**
     * Méthode utilitaire pour attendre
     * @param milliseconds Temps d'attente en millisecondes
     */
    protected void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}