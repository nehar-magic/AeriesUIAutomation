package com.project.automaxn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //defining page elements
    WebElement homePageTitle = getElementByXPath("//div[@class='page-title flex-container start']/h2");

    public boolean isUserLoggedIn() {
        return homePageTitle.isDisplayed();
    }
}
