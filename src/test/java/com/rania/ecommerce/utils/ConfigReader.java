package com.rania.ecommerce.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties prop = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties introuvable dans resources");
            }
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger config.properties", e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static int getInt(String key, int defaultValue) {
        String v = prop.getProperty(key);
        return v == null ? defaultValue : Integer.parseInt(v);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = prop.getProperty(key);
        return v == null ? defaultValue : Boolean.parseBoolean(v);
    }
}
