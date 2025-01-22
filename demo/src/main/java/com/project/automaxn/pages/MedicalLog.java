package com.project.automaxn.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.project.automaxn.utils.XPathObjects;


public class MedicalLog extends BasePage {

    public MedicalLog(WebDriver driver) {
        super(driver);
    }

    public String addMedicalLogRecord() {
        WebElement addNewRecordBtn = getElement("xpath",XPathObjects.MedicalLogPageXpaths.ADD_NEW_RECORD_BTN);

        addNewRecordBtn.click();

        WebElement saveBtn = getElement("title",XPathObjects.MedicalLogPageXpaths.SAVE_BTN_TITLE);
        String medicalLogRecordcomment = "This is a test comment" + getDateTimeStamp("ddMMyy");

        enterText(getElement("xpath", XPathObjects.MedicalLogPageXpaths.CD_DROPDOWN_GRID), "01");
        enterText("xpath", XPathObjects.MedicalLogPageXpaths.ST_KENDO_TIME_PICKER, "9:30 AM");
        enterText("xpath", XPathObjects.MedicalLogPageXpaths.ET_KENDO_TIME_PICKER, "6:00 PM");
        enterText("xpath", XPathObjects.MedicalLogPageXpaths.RE_DROPDOWN_GRID, "RC");
        enterText("name", XPathObjects.MedicalLogPageXpaths.TXT_CO_NAME, medicalLogRecordcomment);
        enterText("xpath", XPathObjects.MedicalLogPageXpaths.BC_DROPDOWN_GRID, "Health Technician");
        enterText("xpath", XPathObjects.MedicalLogPageXpaths.IN_DROPDOWN_GRID, "HCL");
        click("title", XPathObjects.MedicalLogPageXpaths.SAVE_BTN_TITLE);

        return medicalLogRecordcomment;
    }

    public void verifyMedicalLogRecord(String verifyTxt) {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + verifyTxt + "')]"));
        Assert.assertNotNull(element, "Record with '" + verifyTxt + "' is not present on the page.");
    }
}
