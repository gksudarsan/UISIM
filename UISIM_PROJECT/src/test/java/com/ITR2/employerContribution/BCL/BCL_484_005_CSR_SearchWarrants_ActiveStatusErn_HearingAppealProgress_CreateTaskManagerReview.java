package com.ITR2.employerContribution.BCL;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_484_005_CSR_SearchWarrants_ActiveStatusErn_HearingAppealProgress_CreateTaskManagerReview extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can search for warrants in an active status based on the ERN and process warrant vacate with reason for vacate is 'Hearing/Appeal in Progress', task will create for manager review", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_484_005() throws Exception {
	
		test = report.createTest("BCL.484.005 : Verify CSR can search for warrants in an active status based on the ERN and process warrant vacate with reason for vacate is 'Hearing/Appeal in Progress', task will create for manager review");
		
		// --- WI 1. Request to Vacate Warrant Approval WI
		// --- WI 2. Vacate warrant rejection task WI ---
		
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
		commonFunction.ScrollMenu("Request to Vacate Warrant");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Collection -> Warrant -> Request to Vacate Warrant");
		commonFunction.clickMenu("Request to Vacate Warrant");
		
		// --- COL-498 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484005", "Pass", "Successfully launched to Request to Vacate Warrant(COL-498) screen");
		commonFunction.enterTextboxContains("Employer Registration Number (ERN)", "0464364");
		sleep(2000);
		commonFunction.screenShot("BCL484005", "Pass", "ERN entered in COL-498 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-490 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484005", "Pass", "Successfully launched to List of Warrants(COL-490) screen");
		bclLocators.dataTableIdRadio.click();
		sleep(2000);
		commonFunction.screenShot("BCL484005", "Pass", "Successfully selected data on COL-490 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-499 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484005", "Pass", "Successfully launched to Request to Vacate Warrant Details(COL-499) screen");
		try {
			bclLocators.yesRadioInCircle.click();
		} catch (Exception exception) {
			bclLocators.yesRadioOutCircle.click();
		}
		commonFunction.selectDropdownEquals("If yes, select the reason to Vacate a Warrant", " Hearing/Appeal in Progress ");
		bclLocators.reasonExplanation.sendKeys("Ok");
		commonFunction.screenShot("BCL484005", "Pass", "Entered details COL-499 screen");
		
		sleep(3000);
		commonFunction.selectLink("Document", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("BCL484005", "Pass", "Sample document uploaded at COL-499 screen");
		commonFunction.clickButtonContains("Submit ");
		
		//commonFunction.screenShot("BCL484005", "Fail", "Error before SUC-002 screen");
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484005", "Pass", "Successfully launched to  SUC-002 screen");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
	     
		bclLocators.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("Work Item Description Free Text","Request to Vacate Warrant");
	    commonFunction.screenShot("BCL484005WiSearch","Pass","Text contains - Search");
	    commonFunction.clickButtonContains("Search");
	    Thread.sleep(2000);

	    // --- WI 1. Request to Vacate Warrant WI ---
	    
	    sleep(3000);
	    commonFunction.ScrollMenu("Request to Vacate Warrant");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Request to Vacate Warrant'");
	    sleep();
	    commonFunction.clickOnLink("Request to Vacate Warrant");
	    
	    
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484005", "Pass", "Successfully passed TC BCL.484.005");
		
		System.out.println("Pass :)");
		
		
	}

}
