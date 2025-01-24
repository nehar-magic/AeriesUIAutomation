package com.project.automaxn;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.project.automaxn.pages.BasePage;
import com.project.automaxn.pages.HomePage;
import com.project.automaxn.pages.LoginPage;
import com.project.automaxn.resources.config.ConfigReader;
import com.project.automaxn.utils.DriverManager;

public class BaseTest {

    protected WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    BasePage basePage = new BasePage(driver);

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        String baseUrl = ConfigReader.getProperty("baseUrl");
        // System.out.println(baseUrl);
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("Base URL is not set in the configuration file.");
        }
        driver.get(ConfigReader.getProperty("baseUrl"));
        Login();
    }

    // @BeforeSuite
    // public void createTestStudent() {
    //     setUp();
    //     Demographics demographics = new Demographics(driver);
    //     basePage.studentID = demographics.addNewStudentInSchool(ConfigReader.getProperty("school"));
    // }

    public void Login() {
        loginPage = new LoginPage(driver);

        loginPage.login();

        homePage = new HomePage(driver);

        // Assert that the login was successful
        Assert.assertTrue(homePage.isUserLoggedIn(), "User could NOT log in successfully.");
    }

    public void Logout() {
        homePage.logout();

        loginPage = new LoginPage(driver);

        // Assert that logout was successful
        Assert.assertTrue(loginPage.isUserLoggedOut(), "User could NOT log out successfully.");
    }

    @AfterMethod
    public void tearDown() {
        Logout();
        DriverManager.quitDriver();
    }
}
