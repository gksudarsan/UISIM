package com.employerContibution.RAD;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_246_01_006_CSR_TransferOverPayment_DuesMultiple_QuarterYear extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can transfer overpayment when dues available for multiple quarter/year of an To Account.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_246_01_006() throws Exception {
	
		test = report.createTest("RAD.246.01.006 : Verify CSR can transfer overpayment when dues available for multiple quarter/year of an To Account.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_6.toUpperCase(), COMMON_CONSTANT.CSR_USER_6_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		radLocators.menu.click();
		commonFunction.ScrollMenu("Contribution/Wage Maintenance");
		commonFunction.clickMenu("Contribution/Wage Maintenance");
		commonFunction.ScrollMenu("Transfer Payment / Overpayment");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution/Wage Maintenance -> Transfer Payment / Overpayment");
		commonFunction.clickMenu("Transfer Payment / Overpayment");
		
		// --- TWR-205 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD24601006", "Pass", "Successfully launched Transfer Payment/Overpayment(TWR-205) page");
		commonFunction.enterTextboxContains("From Employer Registration Number", "5410146");
		commonFunction.enterTextboxContains("To Employer Registration Number", "5410146");
		sleep(1500);
		commonFunction.screenShot("RAD24601006", "Pass", "Entered data in TWR-205 page and clicked on Search button");
		
		commonFunction.clickButtonContains(" Search ");
		
		commonFunction.screenShot("RAD24601006", "Pass", "Data after clicking on Search button");
		
		commonFunction.selectRadioInTable("21243541", 1, 1, "");
		commonFunction.selectRadioQuestions("Do you want to Suspend the payment? ", "No ");
		radLocators.slopOverCheckBox.click();
		sleep(1500);
		commonFunction.screenShot("RAD24601006", "Pass", "Selected appropriate options");
		
		commonFunction.clickButtonContains("Continue ");
		
		// --- TWR-206 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD24601006", "Pass", "Successfully launched Transfer Payment/Overpayment Verification(TWR-206) page");
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("RAD24601006", "Pass", "Successful launch to Transfer Payment/Overpayment Confirmation(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD24601006", "Pass", "Successfully passed TC RAD.246.01.006.");
	    
		
		System.out.println("Pass :)");
	}
}
