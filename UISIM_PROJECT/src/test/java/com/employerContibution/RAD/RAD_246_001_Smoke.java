package com.employerContibution.RAD;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_246_001_Smoke extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to process contribution return adjustment with Return type 'Amended' and Reason for Adjustment 'Fix Return Type'", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_246_001() throws Exception {
		
		test = report.createTest("RAD.246.001 : Verify CSR is able to process contribution return adjustment with Return type 'Amended' and Reason for Adjustment 'Fix Return Type'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		radLocators.menu.click();
		commonFunction.ScrollMenu("Contribution Return Adjustment");
		commonFunction.clickMenu("Contribution Return Adjustment");
		commonFunction.ScrollMenu("Adjust Contribution Return");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Return Adjustment -> Adjust Contribution Return");
		commonFunction.clickMenu("Adjust Contribution Return");
		
		// --- TWR-237 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD246001", "Pass", "Successfully launched Contribution Return Adjustment - Select(TWR-237) page");
		
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on blank Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "9311967");
		commonFunction.selectDropdownEquals("Return Type", " Amended ");
		commonFunction.selectDropdownEquals("Quarter", " 1 ");
		commonFunction.enterTextboxContains("Year", "1988");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on wrong year combination before Search");
		
		commonFunction.selectDropdownEquals("Quarter", " 4 ");
		commonFunction.enterTextboxContains("Year", "");
		commonFunction.enterTextboxContains("Year", "8888");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on invalid Quarter-Year combination at Search");
		
		commonFunction.enterTextboxContains("Year", "");
		commonFunction.enterTextboxContains("Year", "2024");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on invalid Quarter-Year combination at Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Year", "");
		commonFunction.enterTextboxContains("Year", "2022");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on blank ERN Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Employer Registration Number", "0000001");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on invalid ERN Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Employer Registration Number", "5410145");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "No returns posted Error on wrong ERN-Quater/Year combination Search");
		
		sleep(1500);
		commonFunction.screenShot("RAD246001", "Pass", "Entered data in TWR-237 page");
		
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Data present with the above ERN after search in TWR-901 page");
		
		
		commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD246001o", "Pass", "Successfully passed TC RAD.246.001");
	    
		
		System.out.println("Pass :)");
	}

}
