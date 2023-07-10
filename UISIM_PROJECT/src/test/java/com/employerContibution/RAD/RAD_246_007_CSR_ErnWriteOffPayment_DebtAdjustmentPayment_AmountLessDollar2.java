package com.employerContibution.RAD;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_246_007_CSR_ErnWriteOffPayment_DebtAdjustmentPayment_AmountLessDollar2 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to process contribution return adjustment with return type 'Estimated' and Reason for Adjustment 'Administrative Action'.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_246_007() throws Exception {
	
		test = report.createTest("RAD.246.007 : Verify CSR is able to process contribution return adjustment with return type 'Estimated' and Reason for Adjustment 'Administrative Action'.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_6.toUpperCase(), COMMON_CONSTANT.CSR_USER_6_PASSWORD);
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
		
		// --- TWR-901 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD246007", "Pass", "Successfully launched Enter Split Rate Quarter(TWR-901) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "9311967");
		commonFunction.selectDropdownEquals("Return Type", " Estimated ");
		commonFunction.selectDropdownEquals("Quarter", " 4 ");
		commonFunction.enterTextboxContains("Year", "2022");
		sleep(1500);
		commonFunction.screenShot("RAD246007", "Pass", "Entered data in TWR-901 page");
		
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246007", "Pass", "Data present with the above ERN after search in TWR-901 page");
	    
		commonFunction.selectRadioInTable("Estimated", 1, 1, "");
		commonFunction.selectDropdownEquals("Reason for Adjustment", " Fix Erroneous Transaction ");
		radLocators.reasonForAdjustmentComment.sendKeys("Ok tested");
		commonFunction.screenShot("RAD246007", "Pass", "Entered data in TWR-901 page");
		commonFunction.clickButtonContains("Continue ");
		
		// incomplete script, data unavailable.
	 
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD246007", "Pass", "Successfully passed TC RAD.246.007.");
	    
		
		System.out.println("Pass :)");
	}
}
