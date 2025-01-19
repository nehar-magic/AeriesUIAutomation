package com.project.automaxn.resources.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
    try {
        System.out.println("Loading configuration file...");
        FileInputStream file = new FileInputStream("D:\\Files\\SeleniumUIAutomationProject\\demo\\src\\main\\java\\com\\project\\automaxn\\resources\\config\\config.properties");
        properties.load(file);
        System.out.println("Configuration file loaded successfully.");
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
    }
}

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Key not found or empty in configuration: " + key);
        }
        return value.trim();
    }
}