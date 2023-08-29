package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_484_003_CSR_SearchWarrants_ActiveStatusErn_PaymentPlanEstablished_CreateTaskManagerReview extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can search for warrants in an active status based on the ERN and process warrant vacate with reason for vacate is 'Payment Plan Established', task will create for manager review", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_484_003() throws Exception {
	
		test = report.createTest("BCL.484.003 : Verify CSR can search for warrants in an active status based on the ERN and process warrant vacate with reason for vacate is 'Payment Plan Established', task will create for manager review");
		
		// --- WI 1. Request to Vacate Warrant WI
		// --- WI 2. Vacate warrant follow-up task WI ---
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		BillingCollectionLiensLocators bclLocators = new BillingCollectionLiensLocators(driver);
		
		String eanValue = "0464364";
		
		//GET method
		// valid ERN where employer has existing Bankruptcy record
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV'",
				"EAN");
		String eanValue1 = databaseEanResult.get("EAN");
		
		if ((eanValue == null) || eanValue.isEmpty())
		{
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanValue + ".");
		}
		
		
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
		commonFunction.screenShot("BCL484003", "Pass", "Successfully launched to Request to Vacate Warrant(COL-498) screen");
		commonFunction.enterTextboxContains("Employer Registration Number (ERN)", eanValue);
		sleep(2000);
		commonFunction.screenShot("BCL484003", "Pass", "ERN entered in COL-498 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-490 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484003", "Pass", "Successfully launched to List of Warrants(COL-490) screen");
		commonFunction.selectRadioInTable("000005347551007", 1, 1, "");
		sleep(2000);
		commonFunction.screenShot("BCL484003", "Pass", "Successfully selected data on COL-490 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-499 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484003", "Pass", "Successfully launched to Request to Vacate Warrant Details(COL-499) screen");
		try {
			bclLocators.yesRadioInCircle.click();
		} catch (Exception exception) {
			bclLocators.yesRadioOutCircle.click();
		}
		commonFunction.selectDropdownEquals("If yes, select the reason to Vacate a Warrant", " Payment Plan Established ");
		bclLocators.reasonExplanation.sendKeys("Ok");
		commonFunction.screenShot("BCL484003", "Pass", "Entered details COL-499 screen");
		
		sleep(3000);
		commonFunction.selectLink("Document", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("BCL484003", "Pass", "Sample document uploaded at COL-499 screen");
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484003", "Pass", "Successfully launched to  SUC-002 screen");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
	     
		bclLocators.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("Work Item Description Free Text","Request to Vacate Warrant");
	    commonFunction.screenShot("BCL484003WiSearch","Pass","Text contains - Search");
	    commonFunction.clickButtonContains("Search");
	    Thread.sleep(2000);

	    // --- WI 1. Request to Vacate Warrant WI ---
	    
	    sleep(3000);
	    commonFunction.ScrollMenu("Request To Vacate Warrant");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Request To Vacate Warrant'");
	    sleep();
	    commonFunction.clickOnLink("Request To Vacate Warrant");
	    
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("BCL484003", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
	    
	    // --- BCL-WI-300 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("BCL484003", "Pass", "Successful launch to Request to Vacate Warrant Approval Task(BCL-WI-300) page");
	    try {
			bclLocators.approveRadioInCircle.click();
		} catch (Exception exception) {
			bclLocators.approveRadioOutCircle.click();
		}
	    bclLocators.comments.sendKeys("Done");
	    sleep(2000);
	    commonFunction.screenShot("BCL484003", "Pass", "Entered required data on BCL-WI-300 screen");
	    
	    sleep(3000);
		commonFunction.selectLink("Document", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("BCL484003", "Pass", "Sample document uploaded at COL-499 screen");
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484003", "Pass", "Successfully launched to  SUC-002 screen");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
	     
		bclLocators.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
	    commonFunction.screenShot("BCL484003WiSearch","Pass","EAN Value - Search");
	    commonFunction.clickButtonContains("Search");
	    Thread.sleep(2000);
	    
	    // --- WI 2. Request to Vacate Warrant WI ---
	    
	    sleep(3000);
	    commonFunction.ScrollMenu("Vacate Warrant Follow Up Task");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Vacate Warrant Follow Up Task'");
	    sleep();
	    commonFunction.clickOnLink("Vacate Warrant Follow Up Task");
	    
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("BCL484003", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL484003", "Pass", "Successfully passed TC BCL.485.002");
		
		System.out.println("Pass :)");
		
		
	}

}
