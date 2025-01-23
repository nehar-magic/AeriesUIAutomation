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
    WebElement usernameInpuElement = getElement("id","Username_Aeries");
    WebElement passwordInpuElement = getElement("id","Password_Aeries");
    WebElement yearDropdown = getElement("xpath", "//span[@aria-labelledby=\"Year_Aeries_label\"]");
    WebElement loginBtn = getElement("xpath", "//input[@value='Log In']");
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
        click("xpath", "//input[@value='Log In']");
        continueBtn = getElement("name" ,"btnContinueIn_Aeries");
    }

    public void clickContinueBtn() {
        click("name", "btnContinueIn_Aeries");
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