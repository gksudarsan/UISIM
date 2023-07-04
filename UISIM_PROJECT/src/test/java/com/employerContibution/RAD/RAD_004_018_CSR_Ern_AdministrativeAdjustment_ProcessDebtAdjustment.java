package com.employerContibution.RAD;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_004_018_CSR_Ern_AdministrativeAdjustment_ProcessDebtAdjustment extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to search ERN with Adjustment Type 'Administrative Adjustment' and process debt adjustment", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_004_018() throws Exception {
	
		test = report.createTest("RAD.004.018 : Verify CSR is able to search ERN with Adjustment Type 'Administrative Adjustment' and process debt adjustment.");
		
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
		commonFunction.screenShot("RAD004018", "Pass", "Successfully launched Debt Adjustment - Enter Employer Registration Number(TWR-074) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "5253968");
		commonFunction.selectRadioQuestions("Adjustment Type", "Administrative Adjustment");
		sleep();
		commonFunction.selectDropdown("Quarter", " 1 ");
		commonFunction.enterTextboxContains("Year", "2016");
		commonFunction.screenShot("RAD004018", "Pass", "Entered data on TWR-074 page");
		commonFunction.clickButtonContains(" Search ");
		
		commonFunction.screenShot("RAD004018", "Pass", "Data present in TWR-074 page");
		
		sleep(3000);
		try {
			radLocators.dataTableId001_innerRadio.click();
		} catch (Exception exception) {
			radLocators.dataTableId001_outerRadio.click();
		}
		sleep();
		commonFunction.screenShot("RAD004018", "Pass", "Clicked required radio option");
		
		commonFunction.clickButtonContains("Continue ");
		
		// --- TWR-075 ---
		commonFunction.waitForLoadingIconToDisappear();
		//Use break point here, this page has a dynamic waiting time.
		commonFunction.screenShot("RAD004018", "Pass", "Successfully launched Debt Adjustment - Enter Amount(TWR-075) page");
		
		
		radLocators.normalTimelyWriteOffAmt.clear();
		radLocators.normalTimelyWriteOffAmt.sendKeys("5000");
		sleep();
		radLocators.normalTimelyReestablishedAmt.clear();
		radLocators.normalTimelyReestablishedAmt.sendKeys("5000");
		sleep(1500);
		commonFunction.screenShot("RAD004018", "Pass", "Entered required data in Write Off and Re-Established Amount");
		
		commonFunction.selectRadioQuestions("Is this request from the Collection Section?", "No ");
		radLocators.updateReasonId.sendKeys("Ok to update");
		sleep(2000);
		commonFunction.screenShot("RAD004018", "Pass", "Selected appropirtae radio option and passed comment");
		
		commonFunction.clickButtonContains("Continue ");
		
		// --- TWR-076 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD004018", "Pass", "Successfully launched Debt Adjustment Verification(TWR-076) page");
		
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("RAD004018", "Pass", "Successful launch to Debt Adjustment Confirmation(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	 
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD004018", "Pass", "Successfully passed TC RAD.004.018");
	    
		
		System.out.println("Pass :)");
	}
}
