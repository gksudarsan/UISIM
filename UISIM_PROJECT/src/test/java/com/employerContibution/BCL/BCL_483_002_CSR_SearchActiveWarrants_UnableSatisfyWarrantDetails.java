package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_483_002_CSR_SearchActiveWarrants_UnableSatisfyWarrantDetails extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can search for warrants in active status based on ERN and unable to Satisfy warrant details.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_483_002() throws Exception {
	
		test = report.createTest("BCL.483.002 : Verify CSR can search for warrants in active status based on ERN and unable to Satisfy warrant details.");		
		
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
		commonFunction.clickMenu("Warrant");
		commonFunction.ScrollMenu("Satisfy Warrant");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Collection -> Warrant -> Satisfy Warrant");
		commonFunction.clickMenu("Satisfy Warrant");
		
		// --- COL-501 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL483002", "Pass", "Successfully launched to Satisfy Warrant - Enter ERN(COL-501) screen");
		commonFunction.enterTextboxContains("Employer Registration Number (ERN)", eanValue); //0464364
		sleep(2000);
		commonFunction.screenShot("BCL483002", "Pass", "ERN entered in COL-501 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-490 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL483002", "Pass", "Successfully launched to List of Warrants(COL-490) screen");
		bclLocators.dataTableIdRadio.click();
		sleep(2000);
		commonFunction.screenShot("BCL483002", "Pass", "Successfully selected data on COL-490 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-502 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL483002", "Pass", "Successfully launched to Satisfy Warrant Details(COL-502) screen");
		try {
			bclLocators.noRadioInCircle.click();
		} catch (Exception exception) {
			bclLocators.noRadioOutCircle.click();
		}
		bclLocators.reasonExplanation.sendKeys("Ok testing");
		sleep(2000);
		commonFunction.screenShot("BCL483002", "Pass", "Entered details COL-502 screen");
		
		
		commonFunction.clickButtonContains("Continue ");	    
	    
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL483002", "Pass", "Successfully passed TC BCL.483.002");
		
		System.out.println("Pass :)");
		
		
	}

}
