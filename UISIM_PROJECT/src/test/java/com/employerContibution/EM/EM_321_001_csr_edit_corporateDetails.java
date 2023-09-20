//--------Smoke-----
package com.employerContibution.EM;

import java.util.Map;
import java.util.Random;

 

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

 

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.LoginPage;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

 

import stepDefinitions.commonStepDefinitions;

 

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_001_csr_edit_corporateDetails extends TestBase {

 

    @Test(priority = 1, description = "EM.321.001 - Verify CSR is able to Edit Corporate Officer/Owner Details.", groups = {
            "Regression" })
    public void EM_321_001() throws Exception {

    	test = report.createTest("EM.321.001 - Verify CSR is able to Edit Corporate Officer/Owner Details.");

        employerManagementLocators eml = new employerManagementLocators();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
        commonStepDefinitions cf = new commonStepDefinitions();
        SREG_EM_mod sm = new SREG_EM_mod(driver);
        
    
        cf.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
        cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
        
       
        Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
                "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'GRDI' AND EAN IS NOT NULL", "EAN");
        String ernValue = databaseResults.get("EAN");
        System.out.println(ernValue);
        
        
        sleep(3000);
        cf.clickMenu("Menu");
        cf.ScrollMenu("Account Maintenance");
        cf.clickMenu("Account Maintenance");
        cf.ScrollMenu("Maintain Business Ownership");
        cf.screenShot("Menu", "Pass", "Maintain Business Ownership");
        cf.clickMenu("Maintain Business Ownership");

 

        // ---SREG-029---
        cf.screenShot("EM321001", "Pass", "Sucessfully launched to SREG-029 page");
        cf.enterTextboxContains("Employer Registration Number", "");
        cf.clickButton("Continue ");
        sleep();
        cf.screenShot("EM321001", "Pass", "Message 'Required' on SREG-070");
        cf.enterTextboxContains("Employer Registration Number", "4772");
        cf.clickButton("Continue ");
        sleep();
        cf.screenShot("EM321001", "Pass", "Message 'ERN Must be 7 digit' on SREG-070");
        cf.enterTextboxContains("Employer Registration Number", "0000000");
        cf.clickButton("Continue ");
        sleep();
        cf.screenShot("EM321001", "Pass", "Message 'Does not exist in the system' on SREG-070");
        cf.enterTextboxContains("Employer Registration Number", "4893040");
        cf.waitForLoadingIconToDisappear();
        cf.screenShot("EM321001", "Pass", "ERN Entered on SREG-070 page");
        cf.clickButton("Continue ");
        sleep(20000);

 

        // ---SREG-702---
        cf.screenShot("EM321001", "Pass", "Sucessfully launched to SREG-702 page");
        //cf.clickOnLink("Add Corporate/Owner Details");
        sleep(2000);
        sm.actionEditCorprate.click();
        
        sleep(2000);

        // --- SREG-701 ---
        cf.screenShot("EM321001", "Pass", "Launched to  SREG-701 page");
        cf.selectRadioQuestions(
                " Provide the information requested below for each Corporate Officer/Owner associated with this business. Additional corporate officer/member/owners can be added after validation of address on this screen.",
                " Individual");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.screenShot("EM321001", "Pass", "Message 'Required feilds' on SREG-006");
        cf.enterTextboxContains("First Name", "ALIZA");
        cf.enterTextboxContains("Last Name", "ABOUHAMRA");
        cf.clickButton("Submit ");
        sleep(2000);
        cf.screenShot("EM321001", "Pass", "Message 'Principal Social Security Number is required' on SREG-701");
        cf.enterTextboxContains("SSN", "254545");
        cf.clickButton("Submit ");
        sleep(2000);
        cf.screenShot("Invalid SSN", "Pass", "Invalid SSN Please enter valid SSN");
        cf.clearTextboxContains("SSN");
        cf.enterTextboxContains("SSN", "124881021");
        cf.enterTextboxContains("Address Line 1 ", "EX: Enter TEST%&*^^5TEST");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.screenShot("EM321001", "Pass", "Message 'Address Line 1 contains an invalid character(s).' on SREG-701");
        cf.clearTextboxContains("Address Line 1 ");
        cf.enterTextboxContains("Address Line 1 ", "1st");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.enterTextboxContains("City ", "%%%%%");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.screenShot("EM321001", "Pass", "Message 'City contains an invalid character(s).' on SREG-701");
        cf.clearTextboxContains("City ");
        cf.enterTextboxContains("City ", "New York");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.enterTextboxContains("Zip Code", "120");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.screenShot("EM321001", "Pass", "Message 'Zip Code must be 5 digit' on SREG-701");
        cf.clearTextboxContains("Zip Code");
        cf.enterTextboxContains("Zip Code", "12012");
        cf.clickButton("Submit ");
        sleep(10000);
        cf.enterTextboxContains(" Contact Number ", "8269375089");
        cf.selectDropdown("Source", " NYS-100 (paper) ");
        sleep(1000);
        cf.selectDropdown("Source Type", " NYS-100 ");
        sleep(1000);
        cf.clickButton("Submit ");
        sleep(2000);
        cf.screenShot("EM321001", "Pass", "Message 'Message same partner deatails added");
        cf.clearTextboxContains("SSN");
        cf.enterTextboxContains("SSN", Long.toString(cf.createRandomInteger(10000000, 99999999))
                + Long.toString(cf.createRandomInteger(9, 99)));
        cf.clickButton("Submit ");
        sleep(20000);

 

        // ---SREG-702---
        cf.screenShot("EM321001", "Pass", "Sucessfully launched to SREG-702 page");
        sleep(2000);
        cf.clickOnLink("Edit");
        sleep(2000);
        String ssnValue = String.valueOf((long) (Math.random() * Math.pow(10, 10)));
        populateFields(ssnValue);
        ssnValue = StringUtils.left(ssnValue, 3) + "-" + StringUtils.right(StringUtils.left(ssnValue, 5), 2) + "-"
                + StringUtils.right(ssnValue, 4);
        cf.clickButtonContains("Submit");
        Thread.sleep(2000);
        //cf.selectTable(ssnValue, 11, 1, "Individual as Corporate Officer ");
        Thread.sleep(2000);
        verifyFields("29-9663415");
        Thread.sleep(2000);


    }
    public void populateFields(String ssnValue) throws Exception {
        commonStepDefinitions commonFuntions = new commonStepDefinitions();
        Thread.sleep(2000);
        commonFuntions.selectRadioQuestions(
                " Provide the information requested below for each Corporate Officer/Owner associated with this business. Additional corporate officer/member/owners can be added after validation of address on this screen.",
                " Individual");
        Random random = new Random();
        commonFuntions.enterTextbox("SSN", ssnValue);
        commonFuntions.enterTextboxContains("Address Line 1", "Added address line 2");

        commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
        sleep(1000);
        commonFuntions.selectDropdown("Source Type", " NYS-100 ");
        sleep(1000);
        commonFuntions.screenShot("Submit", "Pass", "Submit corporate officer/ownerdetaisl");
    }
    
    public void verifyFields(String ssnValue) throws Exception {
        commonStepDefinitions commonFuntions = new commonStepDefinitions();
        String Address = commonFuntions.retrieveValueFromTable(ssnValue, 5, 1, "Corporate/Owner Details");
        System.out.println(Address);
        if(Address.contains("ADDED ADDRESS LINE 2")){
            commonFuntions.screenShot("Address Edit", "Pass", "Expected Address 1 is ADDED ADDRESS LINE 2 and actual is "+Address);
        }
        else 
        {
            commonFuntions.screenShot("Address Edit", "fail", "Expected Address 1 is ADDED ADDRESS LINE 2 and actual is "+Address);
        }
       Assert.assertEquals(Address.contains("ADDED ADDRESS LINE 2"), true);

        // DONE
    }
}