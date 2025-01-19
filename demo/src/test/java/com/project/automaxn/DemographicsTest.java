package com.project.automaxn;

import org.testng.annotations.Test;

import com.project.automaxn.pages.Demographics;

public class DemographicsTest extends BaseTest{

    @Test
    public void verifyAddingNewStudent() {
        Demographics demographics = new Demographics(driver);

        //Navigate to Demographics Page   
        String pageName = "Demographics";

        demographics.pageNavigation(pageName);
        String id = demographics.addNewStudentInSchool("894");
        System.out.println(id);
    }
}
