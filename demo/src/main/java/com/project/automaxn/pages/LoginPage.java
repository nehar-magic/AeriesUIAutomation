package com.project.automaxn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.project.automaxn.resources.config.ConfigReader;
import com.project.automaxn.utils.JsonConfigReader;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //defining page elements
    WebElement usernameInpuElement = getElementById("Username_Aeries");
    WebElement passwordInpuElement = getElementById("Password_Aeries");
    WebElement yearDropdown = getElementByXPath("//span[@aria-labelledby=\"Year_Aeries_label\"]");
    WebElement loginBtn = getElementByXPath("//input[@value='Log In']");
    WebElement continueBtn;

    String username = JsonConfigReader.getCredential(ConfigReader.getProperty("role"), "username");
    String password = JsonConfigReader.getCredential(ConfigReader.getProperty("role"), "password");

    // String username = "admin", password = "admin";

    public void enterUsername() {
        enterText(usernameInpuElement, username);
    }

    public void enterPassword() {
        enterText(passwordInpuElement, password);
    }

    public void enterUsername(String username) {
        enterText(usernameInpuElement, username);
    }

    public void enterPassword(String password) {
        enterText(passwordInpuElement, password);
    }

    public void enterLoginCredentials() {
        enterUsername();
        enterPassword();
    }

    public void clickLoginBtn() {
        click(loginBtn);
        continueBtn = getElementByName("btnContinueIn_Aeries");
    }

    public void clickContinueBtn() {
        click(continueBtn);
    }

    public void login() {
        enterUsername();
        enterPassword();
        clickLoginBtn();
        clickContinueBtn();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
    }

    public boolean isUserLoggedOut() {
        return usernameInpuElement.isDisplayed();
    }
}