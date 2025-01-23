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
       // addBtn = getElement("xpath", "//input[@value='Add']");
        click("xpath", "//input[@value='Add']");

        //Click Student Not Found button
       // studentNotFoundBtn = getElement("xpath", "//input[@value='Student Not Found']");
        click("xpath", "//input[@value='Student Not Found']");

        //Click No on "Would you like to search for a sibling?" modal
        click("xpath", "//input[@value='No']");

        try {
            Thread.sleep(1000); // Pause for 1 second
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted!");
            e.printStackTrace();
        }

        //Fill important fields
        String firstName = "test" + getDateTimeStamp("ddMMyy");
        //enterText(getinputFieldByFieldCode("STU.LN"), firstName);
        enterText("xpath",getinputFieldByFieldCode("STU.LN"), firstName );
        //enterText(getinputFieldByFieldCode("STU.FN"), "demo");
        enterText("xpath",getinputFieldByFieldCode("STU.FN"), "demo" );
        enterText(getElement("xpath", "//td[@data-tcfc='STU.GN']/table//div//input"), "N");
        // try {
        //     Thread.sleep(4000); // Sleep for 1 second
        // } catch (InterruptedException e) {
        //     // Handle interruption here, for example, by ignoring it
        //     // or logging it if necessary, or just return from the method
        // }

        String birthDateValue = getDateTimeStamp("mmddyyyy");
        System.out.println("Student's BirthDate: " + birthDateValue);

        WebElement birthDateElement = getElement("xpath", "//td[@data-tcfc='STU.BD']//input[@role='combobox']");
        click("xpath", "//td[@data-tcfc='STU.BD']//div//input[@role='combobox']");
        birthDateElement.sendKeys(birthDateValue);
        enterText("xpath", "//td[@data-tcfc='STU.PED']//div/input", "11");
        //enterText(getinputFieldByFieldCode("STU.PG"), "test parent" + getDateTimeStamp("ddMMyy"));
        enterText("xpath" ,getinputFieldByFieldCode("STU.PG"), "test parent" + getDateTimeStamp("ddMMyy"));



        //Scroll to bottom half of the page
        scrollAndEnterText((getElement("xpath", "//td[@data-tcfc='STU.CL']//div//input")), "00");
        enterText("xpath", "//td[@data-tcfc='STU.ETH']//div/input", "N");
        enterText("xpath", "(//td[@data-tcfc='STU.RC1, STU.RC2, STU.RC3, STU.RC4, STU.RC5']//div//input)[1]", "100");
        // enterText((getinputFieldByFieldCode("STU.CL")), "00");
        //Click on Save Button
       // saveBtn = getElementByXPath("//div[@id='footer-btns']/input[@value='Save']");
        click("xpath", "//div[@id='footer-btns']/input[@value='Save']");
        return getCurrentStudentID();
    }
}
