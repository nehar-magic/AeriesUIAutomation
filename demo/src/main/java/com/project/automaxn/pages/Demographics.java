package com.project.automaxn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Demographics extends BasePage {

    public Demographics(WebDriver driver) {
        super(driver);
    }

    //defining page elements
    WebElement addBtn, studentNotFoundBtn, saveBtn;

    public String addStudent() {
        //Click Add button
        addBtn = getElementByXPath("//input[@value='Add']");
        click(addBtn);

        //Click Student Not Found button
        studentNotFoundBtn = getElementByXPath("//input[@value='Student Not Found']");
        click(studentNotFoundBtn);

        //Click No on "Would you like to search for a sibling?" modal
        click(getElementByXPath("//input[@value='No']"));

        try {
            Thread.sleep(1000); // Pause for 1 second
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted!");
            e.printStackTrace();
        }

        //Fill important fields
        String firstName = "test" + getTodaysDTS();
        enterText(getinputFieldByFieldCode("STU.LN"), firstName);
        enterText(getinputFieldByFieldCode("STU.FN"), "demo");
        enterText(getElementByXPath("//td[@data-tcfc='STU.GN']/table//div/input"), "N");
        // try {
        //     Thread.sleep(4000); // Sleep for 1 second
        // } catch (InterruptedException e) {
        //     // Handle interruption here, for example, by ignoring it
        //     // or logging it if necessary, or just return from the method
        // }

        String birthDateValue = getTodaysMonth() + getTodaysDate() + Integer.toString(Integer.parseInt(getTodaysYear()) - 10);

        System.out.println("Student's BirthDate: " + birthDateValue);

        WebElement birthDateElement = getElementByXPath("//td[@data-tcfc='STU.BD']//input[@role='combobox']");
        click(birthDateElement);
        birthDateElement.sendKeys(birthDateValue);
        enterText(getElementByXPath("//td[@data-tcfc='STU.PED']//div/input"), "11");
        enterText(getinputFieldByFieldCode("STU.PG"), "test parent" + getTodaysDTS());

        //Scroll to bottom half of the page
        scrollAndEnterText((getElementByXPath("//td[@data-tcfc='STU.CL']//div/input")), "00");
        enterText(getElementByXPath("//td[@data-tcfc='STU.ETH']//div/input"), "N");        
        enterText(getElementByXPath("(//td[@data-tcfc='STU.RC1, STU.RC2, STU.RC3, STU.RC4, STU.RC5']//div/input)[1]"), "100");
        // enterText((getinputFieldByFieldCode("STU.CL")), "00");
        //Click on Save Button
        saveBtn = getElementByXPath("//div[@id='footer-btns']/input[@value='Save']");
        click(saveBtn);
        return getCurrentStudentID();
    }
}
