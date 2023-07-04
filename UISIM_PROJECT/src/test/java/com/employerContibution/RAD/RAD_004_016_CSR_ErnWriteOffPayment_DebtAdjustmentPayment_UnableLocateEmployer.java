package com.employerContibution.RAD;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_004_016_CSR_ErnWriteOffPayment_DebtAdjustmentPayment_UnableLocateEmployer extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to search ERN with Adjustment Type 'Write Off Payment' and process debt adjustment overpayment with reason 'Unable to  locate Employer'", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_004_016() throws Exception {
	
		test = report.createTest("RAD.004.016 : Verify CSR is able to search ERN with Adjustment Type 'Write Off Payment' and process debt adjustment overpayment with reason 'Unable to  locate Employer'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		radLocators.menu.click();
		commonFunction.ScrollMenu("Contribution/Wage Maintenance");
		commonFunction.clickMenu("Contribution/Wage Maintenance");
		commonFunction.ScrollMenu("Debt Adjustment");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution/Wage Maintenance -> Debt Adjustment");
		commonFunction.clickMenu("Debt Adjustment");
		
		// --- TWR-074 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD004016", "Pass", "Successfully launched Debt Adjustment - Enter Employer Registration Number(TWR-074) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "5117116");
		commonFunction.selectRadioQuestions("Adjustment Type", "Write Off Payment");
		sleep();
		commonFunction.screenShot("RAD004016", "Pass", "Entered data on TWR-074 page");
		commonFunction.clickButtonContains(" Search ");
		
		commonFunction.screenShot("RAD004016", "Pass", "Data present in TWR-074 page");
		
		sleep(3000);
		radLocators.dataTableId002_checkbox.click();
		sleep(1500);
		radLocators.dataTableId002.click();
		sleep();
		radLocators.unableToLocateEmployer.click();
		sleep(2000);
		commonFunction.screenShot("RAD004016", "Pass", "Selected reason as 'Unable to locate Employer'");
		
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("RAD004016", "Pass", "Successful launch to Debt Adjustment Confirmation(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	 
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD004016", "Pass", "Successfully passed TC RAD.004.016");
	    
		
		System.out.println("Pass :)");
	}
}
