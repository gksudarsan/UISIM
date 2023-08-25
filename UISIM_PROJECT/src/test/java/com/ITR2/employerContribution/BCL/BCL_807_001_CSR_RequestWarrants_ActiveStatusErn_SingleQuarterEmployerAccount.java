package com.ITR2.employerContribution.BCL;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_807_001_CSR_RequestWarrants_ActiveStatusErn_SingleQuarterEmployerAccount extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can request a Warrant for single quarter of an Employer account ", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_807_001() throws Exception {
	
		test = report.createTest("BCL.807.001 : Verify CSR can request a Warrant for single quarter of an Employer account ");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		BillingCollectionLiensLocators bclLocators = new BillingCollectionLiensLocators(driver);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		bclLocators.menu.click();
		commonFunction.ScrollMenu("Contribution Collection");
		commonFunction.clickMenu("Contribution Collection");
		commonFunction.clickMenu("Warrant");
		commonFunction.ScrollMenu("Request Warrant");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Collection -> Warrant -> Request Warrant");
		commonFunction.clickMenu("Request Warrant");
		
		// --- COL-570 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL807001", "Pass", "Successfully launched to Request Warrant – Enter Employer Registration Number(COL-570) screen");
		commonFunction.enterTextboxContains("Employer Registration Number", "0464364");
		sleep(2000);
		commonFunction.screenShot("BCL807001", "Pass", "ERN entered in COL-570 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-571 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL807001", "Pass", "Successfully launched to Request Warrant(COL-571) screen");
		commonFunction.enterFutureDate("Pending Warrant Date", 10);
		bclLocators.commentsId.sendKeys("Test");
		sleep(2000);
		commonFunction.screenShot("BCL807001", "Pass", "Successfully selected data on COL-571 screen");
		commonFunction.clickButtonContains("Submit ");
		
		sleep(2000);
		commonFunction.screenShot("BCL807001", "Pass", "Error on not selecting checkbox on COL-571 screen");
		
		//bclLocators.matCheckboxId.click();
		sleep(2000);
		commonFunction.screenShot("BCL807001", "Pass", "Successfully selected data on COL-571 screen");
		commonFunction.clickButtonContains("Submit ");
	
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL807001", "Pass", "Successfully launched to  SUC-002 screen");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL807001", "Pass", "Successfully passed TC BCL.807.001");
		
		System.out.println("Pass :)");	
		
	}

}
