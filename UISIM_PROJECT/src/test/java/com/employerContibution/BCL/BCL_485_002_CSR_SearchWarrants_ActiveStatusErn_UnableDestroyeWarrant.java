package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_485_002_CSR_SearchWarrants_ActiveStatusErn_UnableDestroyeWarrant extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can search for warrants in active status based on ERN and unable to destroyed warrant details.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_485_002() throws Exception {
	
		test = report.createTest("BCL.485.002 : Verify CSR can search for warrants in active status based on ERN and unable to destroyed warrant details.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		BillingCollectionLiensLocators bclLocators = new BillingCollectionLiensLocators(driver);
		
		//GET method
		// valid ERN where employer has existing Bankruptcy record
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT tea.EAN,* FROM T_TX_PROSECUTION ttp INNER JOIN T_REGULAR_EMPLOYER tre ON ttp.EMPLOYER_ID = tre.EMPLOYER_ID INNER JOIN T_EMPLOYER_ACCOUNT tea ON tea.EMPLOYER_ACCOUNT_ID = tre.EMPLOYER_ACCOUNT_ID",
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
		commonFunction.clickMenu("Warrant");
		commonFunction.ScrollMenu("Destroy Warrant");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Collection -> Warrant -> Destroy Warrant");
		commonFunction.clickMenu("Destroy Warrant");
		
		// --- COL-497 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL485002", "Pass", "Successfully launched to Destroy Warrant(COL-497) screen");
		commonFunction.enterTextboxContains("Employer Registration Number (ERN)", eanValue); //0464364
		sleep(2000);
		commonFunction.screenShot("BCL485002", "Pass", "ERN entered in COL-497 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-490 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL485002", "Pass", "Successfully launched to List of Warrants(COL-490) screen");
		bclLocators.dataTableIdRadio.click();
		sleep(2000);
		commonFunction.screenShot("BCL485002", "Pass", "Successfully selected data on COL-490 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-491 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL485002", "Pass", "Successfully launched to Destroy Warrant Details(COL-491) screen");
		try {
			bclLocators.noRadioInCircle.click();
		} catch (Exception exception) {
			bclLocators.noRadioOutCircle.click();
		}
		bclLocators.reasonExplanation.sendKeys("Not Cancelled");
		commonFunction.screenShot("BCL485002", "Pass", "Entered details COL-491 screen");
		commonFunction.clickButtonContains("Continue ");

		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL485002", "Pass", "Successfully passed TC BCL.485.002");
		
		System.out.println("Pass :)");
		
		
	}

}
