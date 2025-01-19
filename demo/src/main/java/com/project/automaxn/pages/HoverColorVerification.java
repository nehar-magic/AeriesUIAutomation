package com.project.automaxn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HoverColorVerification {

	public static void main(String[] args) {
		// App.main("test");

		System.setProperty("webdriver.chromedriver", "resources\\drivers\\chromedriver-win64\\chromedriver.exe");

		// Set ChromeOptions for controlling the zoom percentage
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-device-scale-factor=1"); // Set zoom to 90%

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		// Set zoom to 80% using JavaScript (affects content scaling)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='80%'");


		String url = "https://demo.aeries.net/aeries/Login.aspx";
		driver.get(url);


		//Finding Username textbox using ID
		WebElement userNameBox = driver.findElement(By.id("Username_Aeries"));
		userNameBox.sendKeys("admin");

		//Finding Password textbox using ID
		WebElement passwordBox = driver.findElement(By.id("Password_Aeries"));
		passwordBox.sendKeys("admin");

		//Finding Sign In button using Name
		WebElement clickBtn = driver.findElement(By.name("btnSignIn_Aeries"));
		clickBtn.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Finding Continue button using Name
		driver.findElement(By.name("btnContinueIn_Aeries")).click();

		//Landing on Aeries Home Page
		driver.findElement(By.xpath("//div[text()=\"Welcome to Aeries\"]"));

		//Turning on Dark Mode in Aeries
		driver.findElement(By.className("next-navbar-avatar")).click();
		driver.findElement(By.xpath("//input[@onclick='DarkMode();']")).click();

        driver.quit();
	}

}
