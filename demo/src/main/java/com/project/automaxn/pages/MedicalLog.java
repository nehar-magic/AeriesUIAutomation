package com.project.automaxn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MedicalLog extends BasePage {

    public MedicalLog(WebDriver driver) {
        super(driver);
    }

    public String addMedicalLogRecord() {
        WebElement addNewRecordBtn = getElementByXPath("//input[contains(@value, 'Add New Record')]");

        addNewRecordBtn.click();

        WebElement saveBtn = getElementByTitle("Save Record");
        String medicalLogRecordcomment = "This is a test comment" + getTodaysDTS();

        enterText(getElementByXPath("//input[contains(@onchange, 'CD_DropDownGrid')]"), "01");
        enterText(getElementByXPath("//input[contains(@ID, 'ST_txtKendoTimePicker')]"), "9:30 AM");
        enterText(getElementByXPath("//input[contains(@ID, 'ET_txtKendoTimePicker')]"), "6:00 PM");
        enterText(getElementByXPath("//input[contains(@onchange, 'RE_DropDownGrid')]"), "RC");
        enterText(getElementByName("ctl00$MainContent$subMED$rptMedicalLog$ctl01$txtCO"), medicalLogRecordcomment);
        enterText(getElementByXPath("//input[contains(@onchange, 'BC_DropDownGrid')]"), "Health Technician");
        enterText(getElementByXPath("//input[contains(@onchange, 'IN_DropDownGrid')]"), "HCL");
        click(saveBtn);

        return medicalLogRecordcomment;
    }

    public void verifyMedicalLogRecord(String verifyTxt) {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + verifyTxt + "')]"));
        Assert.assertNotNull(element, "Record with '" + verifyTxt + "' is not present on the page.");
    }
}
