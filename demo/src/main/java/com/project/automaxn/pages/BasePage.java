package com.project.automaxn.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.project.automaxn.utils.JsonConfigReader;

public class BasePage {

    protected WebDriver driver;
    WebElement userDropdownMenu, logOutElement, navigationFilter, loggedInSchool, navigationCloseBtn, navigationMenuBtn, studentSearchElement;
    public String studentID;
    LocalDate today = LocalDate.now();          // Get today's date
    DateTimeFormatter formatter;

    public String getTodaysDTS() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("ddMMyy");

        // Format the date
        return today.format(formatter);
    }

    public String getTodaysDate() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("dd");

        // Format the date
        return today.format(formatter);
    }

    public String getTodaysMonth() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("MM");

        // Format the date
        return today.format(formatter);
    }

    public String getTodaysYear() {
        // Define the desired format
        formatter = DateTimeFormatter.ofPattern("yyyy");

        // Format the date
        return today.format(formatter);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement getElementByClass(String className) {
        return driver.findElement(By.className(className));
    }

    public WebElement getElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    public WebElement getElementByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getElementByTitle(String title) {
        return getElementByXPath("//a[@title='" + title + "']");
    }

    public WebElement getElementByCSS(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement getElementByLinkText(String txt) {
        return driver.findElement(By.linkText(txt));
    }

    public void click(WebElement btn) {
        btn.click();
    }

    public void enterText(WebElement el, String text) {
        el.sendKeys(text);
    }

    public void scrollAndEnterText(WebElement element, String text) {
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Call the existing enterText function
        enterText(element, text);
    }

    public void pageNavigation(String pageName) {
        // Locate elements A and B
        By closeBtn = By.xpath("//*[local-name()='use' and contains(@href, 'nav-close')]");
        By menuBtn = By.xpath("//*[local-name()='use' and contains(@href, 'nav-menu')]");

        try {
            WebElement navigationCloseBtn = driver.findElement(closeBtn);

            // Check if Navigation close button is visible
            if (navigationCloseBtn.isDisplayed()) {
                System.out.println("Element A is visible. Doing nothing.");
            } else {
                navigationMenuBtn = driver.findElement(menuBtn);
                navigationMenuBtn.click();
                System.out.println("Navigation close button is not visible. Clicked on Navigation Menu Button.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Navigation close button does not exist in the DOM. Clicking on Navigation Menu Button.");
            navigationMenuBtn = driver.findElement(menuBtn);
            navigationMenuBtn.click();
        }

        // if (navigationCloseBtn = getElementByXPath("//a[@title='Open Navigation']")) {
        //     
        // }
        navigationFilter = getElementByXPath("//input[@placeholder='Filter Navigation']");
        enterText(navigationFilter, pageName);
        click((getElementByXPath("//a[@title='" + pageName + "']")));
    }

    public boolean verifyPageNavigation(String pageTitle) {
        return getElementByXPath("//h2[contains(text(), '" + pageTitle + "')]").isDisplayed();
    }

    public String getSchoolName() {
        loggedInSchool = getElementByXPath("//a[@title='Change School']");
        return loggedInSchool.getText();
    }

    public String getCurrentStudentID() {
        return getElementByXPath("(//div[@class='data-row']/div[@data-tcfc='STU.ID'])[1]").getText();
    }

    public void searchStudentById(String id) {
        studentSearchElement = getElementByXPath("//input[@placeholder='Search Student']");
        if (id == "") {
            studentSearchElement.sendKeys(JsonConfigReader.getCredential("student", "crudOprxn"));
        } else {
            studentSearchElement.sendKeys(id);
        }
    }

    public void changeSchool(String schoolName) {
        loggedInSchool = getElementByXPath("//a[@title='Change School']");
        loggedInSchool.click();
        getElementByXPath("//a[contains(text(), '" + schoolName + "')]").click();
    }

    public String addNewStudentInSchool(String schoolName) {
        if (getSchoolName().contains("District")) {
            changeSchool(schoolName);
        }
        pageNavigation("Demographics");

        Demographics demographics = new Demographics(driver);

        //import addStudent function from Demographics page
        return demographics.addStudent();
    }

    public WebElement getinputFieldByFieldCode(String fieldCode) {
        return driver.findElement(By.xpath("//td[@data-tcfc='" + fieldCode + "']/input"));
    }

    public void logout() {
        userDropdownMenu = getElementByClass("next-navbar-avatar");
        click(userDropdownMenu);
        logOutElement = getElementByLinkText("Log Out");
        click(logOutElement);
    }
}
