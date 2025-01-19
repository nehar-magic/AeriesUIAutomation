package com.project.automaxn.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.project.automaxn.resources.config.ConfigReader;

public class DriverManager {

    private static WebDriver driver;

    // Initialize WebDriver instance
    /* 1. Setup web driver.
     * 2. Select the Browser.
     * 3. Manage timeouts.
     * 4. Navigate to base URL.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser"); // Read browser choice from config
            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "D:\\Files\\SeleniumUIAutomationProject\\demo\\src\\main\\java\\com\\project\\automaxn\\resources\\drivers\\chromedriver-win64\\chromedriver.exe");

                    // Set ChromeOptions for controlling the zoom percentage
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--force-device-scale-factor=1"); // Set zoom to 90%
                    
                    // Initialize Chrome WebDriver
                    driver = new ChromeDriver(options);

                    // Set zoom to 80% using JavaScript (affects content scaling)
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("document.body.style.zoom='80%'");
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "demo\\src\\main\\java\\com\\project\\automaxn\\resources\\drivers\\firefoxdriver\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "demo\\src\\main\\java\\com\\project\\automaxn\\resources\\drivers\\edgedriver\\msedgedriver.exe");
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
            driver.manage().window().maximize();
            // driver.get(ConfigReader.getProperty("baseUrl"));
        }
        return driver;
    }

    // Quit WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
