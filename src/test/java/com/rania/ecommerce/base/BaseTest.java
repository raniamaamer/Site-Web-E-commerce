package com.rania.ecommerce.base;

import java.time.Duration;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected Properties props = new Properties();

    /**
     * Flag pour garder le navigateur ouvert après les tests
     * true  => le navigateur reste ouvert
     * false => le navigateur se ferme automatiquement
     */
    protected boolean keepBrowserOpen = true;

    /**
     * Charge les propriétés depuis le fichier config.properties situé dans src/test/resources
     */
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
     * Initialise le driver Chrome avec WebDriverManager et des options par défaut
     */
    public void setupDriver() {
        loadProperties();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Ferme le navigateur si keepBrowserOpen est false
     */
    public void teardown() {
        if (!keepBrowserOpen && driver != null) {
            driver.quit();
        }
    }

    /**
     * Méthode utilitaire pour obtenir une propriété depuis config.properties
     */
    public String getProp(String key) {
        return props.getProperty(key);
    }
}
