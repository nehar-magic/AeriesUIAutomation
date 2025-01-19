package com.project.automaxn;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.automaxn.pages.MedicalLog;

public class MedicalLogTest extends BaseTest {

    @Test
    public void verifyAddingMedicalLogRecord() {
        //Navigate to Medical Log Page
        MedicalLog medicalLog = new MedicalLog(driver);
        String pageName = "Medical Log";
        medicalLog.pageNavigation(pageName);

        //Verifying Medical Log page Navigation
        Assert.assertTrue(medicalLog.verifyPageNavigation(pageName), pageName + "page navigation was NOT successfully.");

        //Navigate to test student 
        medicalLog.searchStudentById("");

        //~~~~~~~~~~~~~~~~~~~Add Medical Log Record~~~~~~~~~~~~~~~~~~~
        String verifycomment = medicalLog.addMedicalLogRecord();

        //Verify the above created medical log record
        medicalLog.verifyMedicalLogRecord(verifycomment);
    }
}
