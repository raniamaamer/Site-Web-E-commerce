package com.rania.ecommerce.base;

import java.time.Duration;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
// SUPPRIMEZ : import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected Properties props = new Properties();
    protected boolean keepBrowserOpen = true;

    public void loadProperties() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("Impossible de trouver config.properties dans src/test/resources !");
            }
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement du fichier config.properties", e);
        }
    }

    /**
     * Initialise le driver Chrome avec Selenium Manager
     * (gestion automatique du driver depuis Selenium 4.6+)
     */
    public void setupDriver() {
        loadProperties();
        
        // SUPPRIMEZ cette ligne :
        // WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        
        // Selenium Manager s'occupe automatiquement du driver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void teardown() {
        if (!keepBrowserOpen && driver != null) {
            driver.quit();
        }
    }

    public String getProp(String key) {
        return props.getProperty(key);
    }
}