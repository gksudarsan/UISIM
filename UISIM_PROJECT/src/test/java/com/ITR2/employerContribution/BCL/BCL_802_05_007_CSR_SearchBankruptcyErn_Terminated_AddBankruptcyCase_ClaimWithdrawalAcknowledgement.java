package com.ITR2.employerContribution.BCL;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_05_007_CSR_SearchBankruptcyErn_Terminated_AddBankruptcyCase_ClaimWithdrawalAcknowledgement extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can Search ERN with select bankruptcy case status 'Terminated' and add Bankruptcy Case Activity for activity type incoming and activity name 'Claim Withdrawal Acknowledgement'", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_802_05_007() throws Exception {
		
		test = report.createTest("BCL.802.05.007 : Verify CSR can Search ERN with select bankruptcy case status 'Terminated' and add Bankruptcy Case Activity for activity type incoming and activity name 'Claim Withdrawal Acknowledgement'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		BillingCollectionLiensLocators bclLocators = new BillingCollectionLiensLocators(driver);
		
		//GET method
		// valid ERN where employer has existing Bankruptcy record
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		
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
		bclLocators.bankruptcyMenuLocator.click();
		commonFunction.ScrollMenu("Review/Update Bankruptcy Case Activity");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Collection -> Bankruptcy -> Review/Update Bankruptcy Case Activity");
		commonFunction.clickMenu("Review/Update Bankruptcy Case Activity");
		
		// --- COL-474 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205007", "Pass", "Successfully launched to Review/Update Bankruptcy Case Activity(COL-474) screen");
		commonFunction.enterTextboxContains("b. Employer Registration Number", eanValue); //0526421
		commonFunction.selectDropdown("2. Bankruptcy Status", " Terminated ");
		sleep(2000);
		commonFunction.screenShot("BCL80205007", "Pass", "ERN entered in COL-474 screen");
		commonFunction.clickButtonContains(" Search ");
		
		sleep(4000);
		commonFunction.screenShot("BCL80205007", "Pass", "List of Bankruptcy cases on ERN search");
		
		commonFunction.selectRadioInTable("05-26421", 1, 1, "");
		sleep(2000);
		commonFunction.screenShot("BCL80205007", "Pass", "Selected radio basis of ERN");
		
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-475 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205007", "Pass", "Successfully launched to View Bankruptcy Case Deatils/History(COL-475) screen");
		commonFunction.clickButtonContains("Add New Activity ");
		
		// --- COL-402 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205007", "Pass", "Successfully launched to Add/Edit Activity - Bankruptcy Case Activity(COL-402) screen");
		commonFunction.selectRadioQuestions("1. Activity Type", "Incoming activity");
		commonFunction.selectRadioQuestions("2. Is a response expected ?", "Yes ");
		commonFunction.selectDropdown("3. Activity Name", " Claim Withdrawal Acknowledgement ");
		bclLocators.remarks.sendKeys("Test");
		commonFunction.enterPastDate("5. Activity Date", 13);
		commonFunction.selectRadioQuestions("6. Is this a Response to existing activity?", "No ");
		sleep(2000);
		commonFunction.screenShot("BCL80205007", "Pass", "Data entered in COL-402 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-513 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205007", "Pass", "Successfully launched to Add/Edit - Activity Verification(COL-513) screen");
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205007", "Pass", "Successfully launched to SUC-002 screen");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFunction.screenShot("BCL80205007", "Pass", "Successfully passed TC BCL.802.05.007");
		
		System.out.println("Pass");
		
	}
}
