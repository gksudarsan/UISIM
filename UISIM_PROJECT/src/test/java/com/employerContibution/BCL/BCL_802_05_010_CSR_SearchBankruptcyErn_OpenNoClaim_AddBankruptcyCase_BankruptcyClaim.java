package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_05_010_CSR_SearchBankruptcyErn_OpenNoClaim_AddBankruptcyCase_BankruptcyClaim extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can Search ERN with select bankruptcy case status 'Open-no claim' and add Bankruptcy Case Activity for activity type Outgoing and activity name 'Bankruptcy Claim'", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_802_05_010() throws Exception {
		
		test = report.createTest("BCL.802.05.010 : Verify CSR can Search ERN with select bankruptcy case status 'Open-no claim' and add Bankruptcy Case Activity for activity type Outgoing and activity name 'Bankruptcy Claim'");
		
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
		commonFunction.screenShot("BCL80205006", "Pass", "Successfully launched to Review/Update Bankruptcy Case Activity(COL-474) screen");
		commonFunction.enterTextboxContains("b. Employer Registration Number", eanValue); //0524100
		commonFunction.selectDropdown("2. Bankruptcy Status", " Open-no claim ");
		sleep(2000);
		commonFunction.screenShot("BCL80205010", "Pass", "ERN entered in COL-474 screen");
		commonFunction.clickButtonContains(" Search ");
		
		sleep(4000);
		commonFunction.screenShot("BCL80205010", "Pass", "List of Bankruptcy cases on ERN search");
		
		commonFunction.selectRadioInTable("05-24100", 1, 1, "");
		sleep(2000);
		commonFunction.screenShot("BCL80205010", "Pass", "Selected radio basis of ERN");
		
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-475 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205010", "Pass", "Successfully launched to View Bankruptcy Case Deatils/History(COL-475) screen");
		commonFunction.clickButtonContains("Add New Activity ");
		
		// --- COL-402 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205010", "Pass", "Successfully launched to Add/Edit Activity - Bankruptcy Case Activity(COL-402) screen");
		commonFunction.selectRadioQuestions("1. Activity Type", "Outgoing activity");
		commonFunction.selectRadioQuestions("2. Is a response expected ?", "Yes ");
		commonFunction.selectDropdown("3. Activity Name", " Bankruptcy Claim ");
		bclLocators.remarks.sendKeys("Test");
		commonFunction.enterPastDate("5. Activity Date", 13);
		commonFunction.selectRadioQuestions("6. Is this a Response to existing activity?", "No ");
		sleep(2000);
		commonFunction.screenShot("BCL80205010", "Pass", "Data entered in COL-402 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-513 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205010", "Pass", "Successfully launched to Add/Edit - Activity Verification(COL-513) screen");
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80205010", "Pass", "Successfully launched to SUC-002 screen");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		sleep(3000);
		commonFunction.screenShot("BCL80205010", "Pass", "Successfully passed TC BCL.802.05.010");
		
		System.out.println("Pass");
		
	}
}
