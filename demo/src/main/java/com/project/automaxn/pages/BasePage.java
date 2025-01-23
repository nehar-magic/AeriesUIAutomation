package com.project.automaxn.pages;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

/*    public String getTodaysDTS() {
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
*/

    public String getDateTimeStamp(String DTSformat) {
        // We can use this format notation -  ddMMyyHHmmss
        SimpleDateFormat dateFormat = new SimpleDateFormat(DTSformat);
        Date now = new Date();
        return dateFormat.format(now);
    }


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to find web elements based on different locator types
    public WebElement getElement(String locatorType, String locatorValue) {
        By by = switch (locatorType.toLowerCase()) {
            case "id" -> By.id(locatorValue);
            case "classname" -> By.className(locatorValue);
            case "name" -> By.name(locatorValue);
            case "xpath" -> By.xpath(locatorValue);
            case "css" -> By.cssSelector(locatorValue);
            case "linktext" -> By.linkText(locatorValue);
            case "partiallinktext" -> By.partialLinkText(locatorValue);
            case "tagname" -> By.tagName(locatorValue);
            case "title" -> By.xpath("//a[@title='" + locatorValue + "']");
            default ->
                    throw new IllegalArgumentException("The Locator : " + locatorType + " is of invalid type. Please re-check the locator type and try again");
        };

        return driver.findElement(by);
    }
//
/*    public WebElement getElementById(String id) {
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

*/

    public void click(String locatorType, String locatorValue) {
        WebElement el = getElement(locatorType, locatorValue);
        el.click();
    }


    public void enterText(WebElement el, String text) {
        el.sendKeys(text);
    }


    public void enterText(String locatorType, String locatorValue, String text) {
        WebElement el = getElement(locatorType, locatorValue);
        el.sendKeys(text);
    }

    public void scrollAndEnterText(WebElement element, String text) {
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Call the existing enterText function
        enterText(element, text);
    }

    public void pageNavigation(String pageName) {

        try {
            WebElement navigationCloseBtn = getElement("xpath", "//*[local-name()='use' and contains(@href, 'nav-close')]");

            // Check if Navigation close button is visible
            if (navigationCloseBtn.isDisplayed()) {
                System.out.println(" Closed button is visible . Nav bar is already open doing nothing");
            } else {
                navigationMenuBtn = getElement("xpath", "//*[local-name()='use' and contains(@href, 'nav-menu')]");
                navigationMenuBtn.click();
                System.out.println("Navigation close button is not visible. Clicked on Navigation Menu Button.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Navigation close button does not exist in the DOM. Clicking on Navigation Menu Button.");
            navigationMenuBtn = getElement("xpath", "//*[local-name()='use' and contains(@href, 'nav-menu')]");
            navigationMenuBtn.click();
        }

        navigationFilter = getElement("xpath", "//input[@placeholder='Filter Navigation']");
        enterText("xpath", "//input[@placeholder='Filter Navigation']", pageName);
        click("xpath", "//a[@title='" + pageName + "']");
    }

    public boolean verifyPageNavigation(String pageTitle) {
        return getElement("xpath", "//h2[contains(text(), '" + pageTitle + "')]").isDisplayed();
    }

    public String getSchoolName() {
        loggedInSchool = getElement("xpath", "//a[@title='Change School']");
        return loggedInSchool.getText();
    }

    public String getCurrentStudentID() {
        return getElement("xpath", "(//div[@class='data-row']/div[@data-tcfc='STU.ID'])[1]").getText();
    }

    public void searchStudentById(String id) {
        studentSearchElement = getElement("xpath", "//input[@placeholder='Search Student']");
        if (id == "") {
            studentSearchElement.sendKeys(JsonConfigReader.getCredential("student", "crudOprxn"));
        } else {
            studentSearchElement.sendKeys(id);
        }
    }

    public void changeSchool(String schoolName) {
        loggedInSchool = getElement("xpath", "//a[@title='Change School']");
        loggedInSchool.click();
        getElement("xpath", "//a[contains(text(), '" + schoolName + "')]").click();
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
/*
    public WebElement getinputFieldByFieldCode(String fieldCode) {
        //  return driver.findElement(By.xpath("//td[@data-tcfc='" + fieldCode + "']/input"));
        return getElement("xpath", "//td[@data-tcfc='" + fieldCode + "']/input");
    }
    */

    public String getinputFieldByFieldCode(String fieldCode) {
        return "//td[@data-tcfc='" + fieldCode + "']/input";
    }


    public void logout() {
        click("classname", "next-navbar-avatar");
        click("linktext", "Log Out");
    }
}