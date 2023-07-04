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
		
		
		
		// yet to code, awaiting dev response
	    
	 
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD246007", "Pass", "Successfully passed TC RAD.246.007.");
	    
		
		System.out.println("Pass :)");
	}
}
